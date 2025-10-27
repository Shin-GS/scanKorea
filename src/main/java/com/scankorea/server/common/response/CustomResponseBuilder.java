package com.scankorea.server.common.response;

import com.scankorea.server.common.code.Code;
import com.scankorea.server.common.i18n.CodeMessageGetter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomResponseBuilder {
    private final CodeMessageGetter messageGetter;

    public Response<Void> of(Code code) {
        String message = messageGetter.getMessage(code);
        return Response.of(code, message);
    }

    public Response<Void> of(Code code,
                             Object... args) {
        String message = messageGetter.getMessage(code, args);
        return Response.of(code, message);
    }

    public <T> Response<T> of(Code code,
                              T data) {
        String message = messageGetter.getMessage(code);
        return Response.of(code, message, data);
    }

    public <T> Response<T> of(Code code,
                              T data,
                              Object... args) {
        String message = messageGetter.getMessage(code, args);
        return Response.of(code, message, data);
    }
}
