package com.scankorea.server.common.exception;

import com.scankorea.server.common.code.ErrorCode;
import com.scankorea.server.common.response.CustomResponseBuilder;
import com.scankorea.server.common.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(annotations = org.springframework.web.bind.annotation.RestController.class)
@org.springframework.core.annotation.Order(org.springframework.core.Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class GlobalApiExceptionHandler {
    private final CustomResponseBuilder responseBuilder;

    @ExceptionHandler(CustomException.class)
    public Response<Void> handleCustomException(CustomException e) {
        log.error("handle CustomException: ", e);
        ErrorCode errorCode = e.getCode();
        return responseBuilder.of(errorCode);
    }

    @ExceptionHandler(Exception.class)
    public Response<Void> handleException(Exception e) {
        log.error("handle exception: ", e);
        return responseBuilder.of(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
