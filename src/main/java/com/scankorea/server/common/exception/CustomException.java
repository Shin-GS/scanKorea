package com.scankorea.server.common.exception;

import com.scankorea.server.common.code.Code;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final Code code;

    public CustomException(final Code code) {
        super(code.name());
        this.code = code;
    }
}
