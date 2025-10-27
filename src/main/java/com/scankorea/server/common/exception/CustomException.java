package com.scankorea.server.common.exception;

import com.scankorea.server.common.code.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode code;

    public CustomException(final ErrorCode code) {
        super(code.name());
        this.code = code;
    }
}
