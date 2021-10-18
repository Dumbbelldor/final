package org.example.model.entity.enumeration;

import java.util.HashMap;
import java.util.Map;

/**
 * This enum represents allowed locales to be applied
 * during localization processes. If unsupported by
 * this class language code will be passed, resets it
 * to one of the supported languages.
 */
public enum Language {

    ENGLISH("en"),
    RUSSIAN("ru"),
    GERMAN("de");

    private final String value;

    Language(String value) {
        this.value = value;
    }

    private static final Map<String, Language> map;

    public String getLocale() {
        return value;
    }

    static {
        map = new HashMap<>();
        for (var elem: values()) {
            map.put(elem.getLocale(), elem);
        }
    }

    /**
     * Returns an enum constant by the given language code
     * if present, otherwise sets it to {@code ENGLISH}.
     *
     * @param locale a language code
     *
     * @return enum constant
     */
    public static Language getByLocale(String locale) {
        return map.getOrDefault(locale, ENGLISH);
    }
}
