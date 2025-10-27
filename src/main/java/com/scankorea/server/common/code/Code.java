package com.scankorea.server.common.code;

import org.springframework.http.HttpStatus;

public interface Code {
    HttpStatus getHttpStatus();

    String name();

    default int getStatus() {
        return getHttpStatus().value();
    }
}
