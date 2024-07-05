package com.newcentury99.p010_nc99_auth_server.commons.base;

import com.newcentury99.p010_nc99_auth_server.library_temp.messages.SDKError;
import lombok.Getter;

import java.util.Arrays;
import java.util.Locale;

@Getter
public enum SupportedLanguage {
    en("en"),
    ko("ko"),
    ja("ja"),
    cn("zh-cn"),
    de("de"),
    fr("fr"),
    es("es"),
    pt("pt"),
    it("it"),
    ru("ru"),
    vi("vi");

    private final String value;

    SupportedLanguage(String value) {
        this.value = value;
    }

    public Locale toLocale() {
        return Locale.of(this.getValue());
    }

    public static Locale parseLanguageStr(String localeStr) throws IllegalArgumentException {
        return Arrays.stream(SupportedLanguage.values())
                .filter(l -> l.getValue().equalsIgnoreCase(localeStr))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(SDKError.ERROR_REQUEST_LANGUAGE_NOT_SUPPORTED.getMessage()))
                .toLocale();
    }
}
