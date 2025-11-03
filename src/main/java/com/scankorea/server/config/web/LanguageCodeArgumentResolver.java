package com.scankorea.server.config.web;

import com.scankorea.server.common.annotation.Lang;
import com.scankorea.server.common.constant.Constant;
import com.scankorea.server.common.constant.LanguageCode;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LanguageCodeArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(@NonNull MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Lang.class) && LanguageCode.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter,
                                  @Nullable ModelAndViewContainer mavContainer,
                                  @NonNull NativeWebRequest webRequest,
                                  @Nullable WebDataBinderFactory binderFactory) {
        Lang langAnnotation = parameter.getParameterAnnotation(Lang.class);

        // 1) Query parameter
        String parameterLang = webRequest.getParameter(langAnnotation != null ? langAnnotation.param() : "lang");
        if (parameterLang != null && !parameterLang.isEmpty()) {
            LanguageCode languageCode = LanguageCode.from(parameterLang);
            return languageCode == null ? Constant.DEFAULT_LANGUAGE : languageCode;
        }

        // 2) Cookie fallback
        String cookieName = (langAnnotation != null ? langAnnotation.cookie() : "lang");
        HttpServletRequest req = webRequest.getNativeRequest(HttpServletRequest.class);
        if (req == null || req.getCookies() == null) {
            return Constant.DEFAULT_LANGUAGE;
        }

        for (Cookie cookie : req.getCookies()) {
            if (!cookieName.equals(cookie.getName())) {
                continue;
            }

            String cookieValue = cookie.getValue();
            if (cookieValue != null && !cookieValue.isEmpty()) {
                LanguageCode languageCode = LanguageCode.from(cookieValue);
                return languageCode == null ? Constant.DEFAULT_LANGUAGE : languageCode;
            }
        }

        return Constant.DEFAULT_LANGUAGE;
    }
}
