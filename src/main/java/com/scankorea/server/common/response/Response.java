package com.scankorea.server.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.scankorea.server.common.code.Code;
import com.scankorea.server.common.code.ErrorCode;
import com.scankorea.server.common.code.SuccessCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"success", "code", "message", "httpStatus", "data"})
public class Response<T> {
    private boolean success;   // success or not
    private String code;       // application code (stringifies)
    private String message;    // final message (code’s message)
    private Integer httpStatus; // numeric HTTP status for clients
    private T data;            // optional payload

    private Response(Code code,
                     String message,
                     T data) {
        this.success = code.isSuccess();
        this.code = code.name();
        this.message = (message != null && !message.isBlank()) ? message : "";
        this.httpStatus = code.getHttpStatusCode();
        this.data = data;
    }

    public static Response<Void> success(SuccessCode successCode,
                                         String message) {
        return new Response<>(successCode, message, null);
    }

    public static <T> Response<T> success(SuccessCode successCode,
                                          String message,
                                          T data) {
        return new Response<>(successCode, message, data);
    }

    public static <T> Response<T> error(ErrorCode errorCode,
                                        String message) {
        return new Response<>(errorCode, message, null);
    }
}
