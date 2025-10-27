package com.scankorea.server.common.i18n;

import com.scankorea.server.common.code.Code;
import com.scankorea.server.common.code.ErrorCode;
import com.scankorea.server.common.code.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class CodeMessageGetter {
    private final MessageSource messageSource;

    public Locale currentLocale() {
        return LocaleContextHolder.getLocale();
    }

    public String getMessage(Code code) {
        Locale locale = currentLocale();
        return messageSource.getMessage(resolvePrefix(code) + code.name(), null, code.name(), locale);
    }

    public String getMessage(Code code, Object... args) {
        Locale locale = currentLocale();
        return messageSource.getMessage(resolvePrefix(code) + code.name(), args, code.name(), locale);
    }

    private String resolvePrefix(Code code) {
        if (code instanceof SuccessCode) {
            return "success.";
        }

        return (code instanceof ErrorCode) ? "error." : "";
    }
}
