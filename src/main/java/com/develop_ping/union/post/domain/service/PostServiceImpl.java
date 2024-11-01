package com.develop_ping.union.post.domain.service;

import com.develop_ping.union.post.domain.PostManager;
import com.develop_ping.union.post.domain.dto.command.PostCommand;
import com.develop_ping.union.post.domain.dto.command.PostListCommand;
import com.develop_ping.union.post.domain.dto.info.PostInfo;
import com.develop_ping.union.post.domain.dto.info.PostListInfo;
import com.develop_ping.union.post.domain.entity.Post;
import com.develop_ping.union.post.exception.PostPermissionDeniedException;
import com.develop_ping.union.user.domain.UserManager;
import com.develop_ping.union.user.domain.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostManager postManager;
    private final UserManager userManager;

    @Override
    @Transactional
    public PostInfo createPost(PostCommand command) {
        log.info("[ CALL: PostService.createPost() ] title: {}",command.getTitle());

        User user = command.getUser();

        // TODO: thumbnail에 들어갈 사진 압축하기
        String thumbnail = null;
        if (command.getThumbnail() != null) {
            // thumbnail을 compress하는 메소드 호출
            thumbnail = command.getThumbnail();
        }

        Post post = postManager.save(Post.of(command, user, thumbnail));

        log.info("[ New Post! ] post id: {}", post.getId());
        return PostInfo.from(post);
    }

    @Override
    @Transactional
    public PostInfo updatePost(PostCommand command) {
        log.info("[ CALL: PostService.updatePost() ] post id: {}", command.getId());

        Post post = postManager.findById(command.getId());
        User user = command.getUser();

        validatePostOwner(user, post);

        post.updateTitle(command.getTitle());
        post.updateContent(command.getContent());

        Post updatedPost = postManager.save(post);

        log.info("[ Updated Post! ] post id: {}", updatedPost.getId());
        return PostInfo.from(updatedPost);
    }

    @Override
    @Transactional
    public void deletePost(PostCommand command) {
        log.info("[ CALL: PostService.deletePost() ] post id: {}", command.getId());

        Post post = postManager.findById(command.getId());
        User user = command.getUser();

        validatePostOwner(user, post);

        postManager.delete(post);
        log.info("[ Deleted Post! ]");
    }

    @Override
    @Transactional
    public PostInfo getPost(PostCommand command) {
        log.info("[ CALL: PostService.getPost() ] post id: {}", command.getId());

        Post post = postManager.findById(command.getId());

        post.incrementViews();
        postManager.save(post);

        log.info("[ Found Post! ] post id: {}", post.getId());
        return PostInfo.from(post);
    }

    @Override
    public Page<PostListInfo> getPosts(PostListCommand command) {
        log.info("[ CALL: PostService.getPostsByPage() ]");

        Page<Post> posts = findPostsByCriterion(command);

        log.info("[ Found Posts! ] total elements: {}", posts.getTotalElements());
        return posts.map(PostListInfo::from);
    }

    private void validatePostOwner(User user, Post post) {
        if (!user.getId().equals(post.getUser().getId())) {
            throw new PostPermissionDeniedException(user.getId(), post.getId());
        }
    }

    private Page<Post> findPostsByCriterion(PostListCommand command) {
        log.info("[ Finding Posts By {} ]", command.getCriterion());

        Pageable pageable = PageRequest.of(
                command.getPage(),
                command.getSize(),
                Sort.by(Sort.Direction.DESC, "createdAt"));

        return
            switch (command.getCriterion()) {
                case BOARD -> postManager.findByPostType(command.getPostType(), pageable);
                case MY -> postManager.findByUser(command.getUser(), pageable);
                case MY_COMMENT -> postManager.findPostsByUserComments(command.getUser(), pageable);
                case USER -> {
                    User user = userManager.findByToken(command.getUserToken());
                    yield postManager.findByUser(user, pageable);
                }
                case USER_COMMENT -> {
                    User user = userManager.findByToken(command.getUserToken());
                    yield postManager.findPostsByUserComments(user, pageable);
                }
            };
    }
}
