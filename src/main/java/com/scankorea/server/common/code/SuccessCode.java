package com.scankorea.server.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum SuccessCode implements Code {
    SUC(HttpStatus.OK),
    ;

    private final HttpStatus httpStatus;
}
