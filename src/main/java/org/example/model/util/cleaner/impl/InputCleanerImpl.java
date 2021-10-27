package org.example.model.util.cleaner.impl;

import org.example.model.util.cleaner.InputCleaner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An implementation of {@link InputCleaner} interface.
 */
public enum InputCleanerImpl implements InputCleaner {

    INSTANCE;

    private static final String REGEX_SCRIPT_CHECKER = "<(.*?)>|\"(.*?)\"";

    /**{@inheritDoc}*/
    @Override
    public String cleanse(String string) {
        String result = string;
        if (result != null && !result.isBlank()) {
            Matcher matcher = Pattern.compile(REGEX_SCRIPT_CHECKER).matcher(result);
            result =  matcher.replaceAll("");
        }
        return result;
    }
}
