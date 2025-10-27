package com.scankorea.server.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@ControllerAdvice(annotations = org.springframework.stereotype.Controller.class)
@org.springframework.core.annotation.Order
public class GlobalExceptionHandler {
    @ExceptionHandler(NoResourceFoundException.class)
    public String handle404(NoResourceFoundException e) {
        log.warn("404 Not Found: {}", e.getResourcePath());
        return "error/404";
    }

    @ExceptionHandler(Exception.class)
    public String handle500(Exception e) {
        log.error("500 Internal Server Error: ", e);
        return "error/500";
    }
}
