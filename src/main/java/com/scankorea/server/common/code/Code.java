package com.scankorea.server.common.code;

import org.springframework.http.HttpStatus;

public interface Code {
    HttpStatus getHttpStatus();

    String name();

    boolean isSuccess();

    default int getHttpStatusCode() {
        return getHttpStatus().value();
    }
}
