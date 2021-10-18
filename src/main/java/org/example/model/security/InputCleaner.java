package org.example.model.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class searches for malware inputs from
 * any possible outer sources and then cleanses
 * from it.
 */
public enum InputCleaner {

    INSTANCE;

    private static final String REGEX_SCRIPT_CHECKER = "<(.*?)>|\"(.*?)\"";

    /**
     * Scans the given input and purifies it from unwanted symbols.
     *
     * @param string the target string
     *
     * @return cleansed string
     */
    public String cleanse(String string) {
        String result = string;
        if (result != null && !result.isBlank()) {
            Matcher matcher = Pattern.compile(REGEX_SCRIPT_CHECKER).matcher(result);
            result =  matcher.replaceAll("");
        }
        return result;
    }
}
