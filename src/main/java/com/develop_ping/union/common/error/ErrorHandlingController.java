package com.develop_ping.union.common.error;


import com.develop_ping.union.auth.exception.InvalidTokenException;
import com.develop_ping.union.auth.exception.OauthNotPreparedException;
import com.develop_ping.union.chat.exception.ChatroomNotFoundException;
import com.develop_ping.union.comment.exception.CommentNotFoundException;
import com.develop_ping.union.comment.exception.CommentPermissionDeniedException;
import com.develop_ping.union.comment.exception.CommenterMismatchException;
import com.develop_ping.union.gathering.exception.*;
import com.develop_ping.union.party.exception.AlreadyJoinedException;
import com.develop_ping.union.party.exception.ParticipationNotFoundException;
import com.develop_ping.union.post.exception.PostNotFoundException;
import com.develop_ping.union.post.exception.PostPermissionDeniedException;
import com.develop_ping.union.s3.exception.ImageUploadFailedException;
import com.develop_ping.union.s3.exception.InvalidS3UrlException;
import com.develop_ping.union.s3.exception.UnsupportedFileFormatException;
import com.develop_ping.union.user.exception.BlockRelationshipNotFoundException;
import com.develop_ping.union.user.exception.DuplicateNicknameException;
import com.develop_ping.union.user.exception.UserBlockedException;
import com.develop_ping.union.user.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

import static com.develop_ping.union.common.error.ErrorBuildFactory.*;

@Slf4j
@RestControllerAdvice
public class ErrorHandlingController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("잘못된 요청 값이 전달되었습니다.");
        List<ErrorResponse.FieldError> fieldErrors = getFieldErrors(e.getBindingResult());
        return buildFieldErrors(ErrorCode.INPUT_VALUE_INVALID, fieldErrors);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("타입이 맞지 않는 요청 값입니다.");
        ErrorResponse.FieldError fieldError = new ErrorResponse.FieldError(e.getPropertyName(), (String) e.getValue(), e.getMessage());
        return buildFieldErrors(ErrorCode.INPUT_VALUE_INVALID, List.of(fieldError));
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ErrorResponse handleInvalidTokenException(InvalidTokenException e) {
        log.error("유효하지 않은 JWT 토큰입니다.");
        return buildError(ErrorCode.INVALID_TOKEN);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handleUserNotFoundException(UserNotFoundException e) {
        log.error("해당 유저를 찾을 수 없습니다.");
        log.error("user 정보: {}", e.getUser());
        return buildError(ErrorCode.USER_NOT_FOUND);
    }

    @ExceptionHandler(PostNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handlePostNotFoundException(PostNotFoundException e) {
        log.error("해당 게시글을 찾을 수 없습니다.");
        log.error("post id: {}", e.getPostId());
        return buildError(ErrorCode.POST_NOT_FOUND);
    }

    @ExceptionHandler(GatheringNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handleGatheringNotFoundException(GatheringNotFoundException e) {
        log.error("해당 모임을 찾을 수 없습니다.");
        log.error("gathering id: {}", e.getGatheringId());
        return buildError(ErrorCode.GATHERING_NOT_FOUND);
    }

    @ExceptionHandler(DuplicateNicknameException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ErrorResponse handleDuplicateNicknameException(DuplicateNicknameException e) {
        log.error("이미 존재하는 닉네임입니다.");
        log.error("nickname: {}", e.getNickname());
        return buildError(ErrorCode.DUPLICATE_NICKNAME);
    }

    @ExceptionHandler(UnsupportedFileFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleUnsupportedFileFormatException(UnsupportedFileFormatException e) {
        log.error("지원하지 않는 파일 형식입니다.");
        log.error("extension: {}", e.getExtension());
        return buildError(ErrorCode.UNSUPPORTED_FILE_FORMAT);
    }

    @ExceptionHandler(ImageUploadFailedException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ErrorResponse handleImageUploadFailedException(ImageUploadFailedException e) {
        log.error("이미지 업로드에 실패하였습니다.");
        log.error("file name: {}", e.getFileName());
        return buildError(ErrorCode.IMAGE_UPLOAD_FAILED);
    }

    @ExceptionHandler(OauthNotPreparedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleOauthNotPreparedException(OauthNotPreparedException e) {
        log.error("Oauth등록이 수행되지 않았습니다.");
        return buildError(ErrorCode.OAUTH_NOT_PREPARED);
    }

    @ExceptionHandler(GatheringValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleGatheringValidationException(GatheringValidationException e) {
        log.error("모임 검증에 실패하였습니다.");
        log.error("gathering: {}", e.getMessage());
        return buildError(ErrorCode.INPUT_VALUE_INVALID);
    }

    @ExceptionHandler(InvalidS3UrlException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleInvalidS3UrlException(InvalidS3UrlException e) {
        log.error("등록되지 않은 이미지 URL이 전달되었습니다.");
        log.error("url: {}", e.getUrl());
        return buildError(ErrorCode.INVALID_S3_URL);
    }

    @ExceptionHandler(PostPermissionDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ErrorResponse handlePostPermissionDeniedException(PostPermissionDeniedException e) {
        log.error("해당 게시글에 대한 권한이 없습니다.");
        log.error("user id: {}, post id: {}", e.getUserId(), e.getPostId());
        return buildError(ErrorCode.POST_PERMISSION_DENIED);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handleCommentNotFoundException(CommentNotFoundException e) {
        log.error("해당 댓글을 찾을 수 없습니다.");
        log.error("comment id: {}", e.getCommentId());
        return buildError(ErrorCode.COMMENT_NOT_FOUND);
    }

    @ExceptionHandler(CommentPermissionDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ErrorResponse handleCommentPermissionDeniedException(CommentPermissionDeniedException e) {
        log.error("해당 댓글에 대한 권한이 없습니다.");
        log.error("user id: {}, comment id: {}", e.getUserId(), e.getCommentId());
        return buildError(ErrorCode.COMMENT_PERMISSION_DENIED);
    }

    @ExceptionHandler(BlockRelationshipNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handleBlockRelationshipNotFoundException(BlockRelationshipNotFoundException e) {
        log.error("해당 유저가 차단되어 있지 않습니다.");
        log.error("차단하는 user id: {}, 차단되는 user id: {}", e.getBlockingUserName(), e.getBlockedUserName());
        return buildError(ErrorCode.BLOCK_RELATIONSHIP_NOT_FOUND);
    }

    @ExceptionHandler(UserBlockedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handleUserBlockedException(UserBlockedException e) {
        log.error("차단된 유저의 정보입니다.");
        log.error("조회한 user id: {}, 차단한 user id: {}", e.getBlockedUserName(), e.getBlockingUserName());
        return buildError(ErrorCode.BLOCK_RELATIONSHIP_NOT_FOUND);
    }

    @ExceptionHandler(CommenterMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleCommenterMismatchException(CommenterMismatchException e) {
        log.error("댓글 작성자가 일치하지 않습니다.");
        log.error("commenter nickname: {}", e.getNickname());
        return buildError(ErrorCode.COMMENTER_MISMATCH);
    }

    @ExceptionHandler(GatheringPermissionDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ErrorResponse handleGatheringPermissionDeniedException(GatheringPermissionDeniedException e) {
        log.error("해당 모임에 대한 권한이 없습니다.");
        log.error("user id: {}, owner id: {}", e.getUserId(), e.getOwnerId());
        return buildError(ErrorCode.GATHERING_PERMISSION_DENIED);
    }

    @ExceptionHandler(AlreadyJoinedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ErrorResponse handleAlreadyJoinedException(AlreadyJoinedException e) {
        log.error("이미 참가한 모임입니다.");
        log.error("gathering id: {}", e.getMessage());
        return buildError(ErrorCode.ALREADY_JOINED);
    }

    @ExceptionHandler(ParticipantLimitExceededException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ErrorResponse handleParticipantLimitExceededException(ParticipantLimitExceededException e) {
        log.error("모임 인원이 가득 찼습니다.");
        log.error("message: {}", e.getMessage());
        return buildError(ErrorCode.PARTICIPANT_LIMIT_EXCEEDED);
    }

    @ExceptionHandler(ParticipationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handleParticipationNotFoundException(ParticipationNotFoundException e) {
        log.error("참여하지 않은 모임입니다.");
        log.error("message: {}", e.getMessage());
        return buildError(ErrorCode.PARTICIPATION_NOT_FOUND);
    }

    @ExceptionHandler(OwnerCannotExitException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ErrorResponse handleOwnerCannotExitException(OwnerCannotExitException e) {
        log.error("주최자는 모임에서 나갈 수 없습니다.");
        log.error("message: {}", e.getMessage());
        return buildError(ErrorCode.OWNER_CANNOT_EXIT);
    }

    @ExceptionHandler(ChatroomNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handleChatroomNotFoundException(ChatroomNotFoundException e) {
        log.error("해당 채팅방을 찾을 수 없습니다.");
        log.error("채팅방 ID : {}", e.getChatroomId());
        return buildError(ErrorCode.CHATROOM_NOT_FOUND);
    }

    @ExceptionHandler(RecruitmentAlreadyCompletedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ErrorResponse handleRecruitmentAlreadyCompletedException(RecruitmentAlreadyCompletedException e) {
        log.error("이미 모집 완료된 모임입니다.");
        log.error("message: {}", e.getMessage());
        return buildError(ErrorCode.RECRUITMENT_ALREADY_COMPLETED);
    }

    @ExceptionHandler(NoMatchingResultsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handleNoMatchingResultsException(NoMatchingResultsException e) {
        log.error("해당 검색 결과를 찾을 수 없습니다.");
        log.error("message: {}", e.getMessage());

        return buildError(ErrorCode.NO_MATCHING_RESULTS);
    }
}
