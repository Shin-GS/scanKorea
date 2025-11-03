package com.scankorea.server.common.constant;

/**
 * 지원 언어 코드
 */
public enum LanguageCode {
    EN,     // 영어
    KO,     // 한국어
    JA,     // 일본어
    ZH_CN, // 중국어 간체
    ZH_TW; // 중국어 번체

    public static LanguageCode from(String type) {
        if (type == null) {
            return null;
        }

        try {
            String normalized = type.trim().toUpperCase();
            return LanguageCode.valueOf(normalized);

        } catch (Exception e) {
            return null;
        }
    }
}
