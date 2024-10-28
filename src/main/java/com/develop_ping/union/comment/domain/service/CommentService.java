package com.develop_ping.union.comment.domain.service;

import com.develop_ping.union.comment.domain.dto.CommentCommand;
import com.develop_ping.union.comment.domain.dto.CommentInfo;
import com.develop_ping.union.user.domain.entity.User;

public interface CommentService {
    CommentInfo createComment(CommentCommand command);
    CommentInfo getComment(Long commentId);
    CommentInfo updateComment(CommentCommand command);
    void deleteComment(CommentCommand command);
}
