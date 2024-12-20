package com.develop_ping.union.comment.presentation.dto.response;

import com.develop_ping.union.comment.domain.dto.CommentInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentDetailResponse {
    private Long id;
    private String content;
    private Long postId;
    private Long parentId;
    private String parentNickname;
    private ZonedDateTime createdAt;
    private long commentLikes;
    private boolean liked;
    private boolean deleted;
    private CommenterResponse commenter;
    private List<CommentDetailResponse> children;

    @Builder
    public CommentDetailResponse(Long id,
                                 String content,
                                 Long postId,
                                 Long parentId,
                                 String parentNickname,
                                 ZonedDateTime createdAt,
                                 long commentLikes,
                                 boolean liked,
                                 boolean deleted,
                                 CommenterResponse commenter,
                                 List<CommentDetailResponse> children) {
        this.id = id;
        this.content = content;
        this.postId = postId;
        this.parentId = parentId;
        this.parentNickname = parentNickname;
        this.createdAt = createdAt;
        this.commentLikes = commentLikes;
        this.liked = liked;
        this.deleted = deleted;
        this.commenter = commenter;
        this.children = children != null ? children : new ArrayList<>();
    }

    public static CommentDetailResponse from(CommentInfo info) {
        return CommentDetailResponse.builder()
                .id(info.getId())
                .content(info.getContent())
                .postId(info.getPostId())
                .parentId(info.getParentId())
                .parentNickname(info.getParentNickname())
                .createdAt(info.getCreatedAt())
                .commentLikes(info.getCommentLikes())
                .liked(info.isLiked())
                .deleted(info.isDeleted())
                .commenter(CommenterResponse.from(info))
                .children(new ArrayList<>())
                .build();
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CommenterResponse {
        private String token;
        private String nickname;
        private String profileImage;
        private String univName;

        @Builder
        private CommenterResponse(String token,
                                  String nickname,
                                  String profileImage,
                                  String univName) {
            this.token = token;
            this.nickname = nickname;
            this.profileImage = profileImage;
            this.univName = univName;
        }

        public static CommenterResponse from(CommentInfo info) {
            return CommenterResponse.builder()
                    .token(info.getToken())
                    .nickname(info.getNickname())
                    .profileImage(info.getProfileImage())
                    .univName(info.getUnivName())
                    .build();
        }
    }
}
