package com.develop_ping.union.common.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // 400 Bad Request
    INPUT_VALUE_INVALID("INPUT_VALUE_INVALID", "데이터 형식이 맞지 않습니다.", 400),
    UNSUPPORTED_FILE_FORMAT("UNSUPPORTED_FILE_FORMAT", "지원하지 않는 파일 형식입니다.", 400),
    INVALID_TARGET_TYPE("INVALID_TARGET_TYPE", "잘못된 타겟 타입이 전달되었습니다.", 400),
    INVALID_S3_URL("INVALID_S3_URL", "잘못된 S3 URL이 전달되었습니다.", 400),
    INVALID_INPUT("INVALID_INPUT", "입력 값이 형식에 맞지 않습니다.", 400),

    // 401 Unauthorized
    INVALID_TOKEN("INVALID_TOKEN", "유효하지 않은 JWT 토큰입니다.", 401),
    OAUTH_NOT_PREPARED("OAUTH_NOT_PREPARED", "Oauth 등록이 수행되지 않았습니다.", 401),

    // 404 Not Found
    USER_NOT_FOUND("USER_NOT_FOUND", "해당 유저를 찾을 수 없습니다.", 404),
    POST_NOT_FOUND("POST_NOT_FOUND", "해당 게시글을 찾을 수 없습니다.", 404),
    GATHERING_NOT_FOUND("GATHERING_NOT_FOUND", "해당 모임을 찾을 수 없습니다.", 404),

    // 409 Conflict
    DUPLICATE_NICKNAME("DUPLICATE_NICKNAME", "이미 존재하는 닉네임입니다.", 409),

    // 422 Unprocessable Entity
    IMAGE_UPLOAD_FAILED("IMAGE_UPLOAD_FAILED", "이미지 업로드에 실패하였습니다.", 422),
    ;

    private final String code;
    private final String message;
    private final Integer status;

    ErrorCode(String code, String message, Integer status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
