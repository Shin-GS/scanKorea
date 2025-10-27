package com.scankorea.server.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode implements Code {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED),
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND),
    ;

    private final HttpStatus httpStatus;

    @Override
    public boolean isSuccess() {
        return false;
    }
}
