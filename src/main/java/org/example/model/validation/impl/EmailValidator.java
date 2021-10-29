package org.example.model.validation.impl;

import org.example.model.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validates email address to be
 */
public enum EmailValidator implements Validator {

    INSTANCE;

    private static final String REGEX_EMAIL_EXT = ".+(@gmail.com|@mail.ru|@yahoo.com)$";

    /**
     * Checks the given email address if it
     * matches one of the permitted mail domains.
     *
     * @param string email to be checked
     *
     * @return true if check successful
     */
    @Override
    public boolean validate(String string) {
        boolean flag = false;
        if (string != null && !string.isBlank()) {
            Matcher matcher = Pattern.compile(REGEX_EMAIL_EXT)
                    .matcher(string.toLowerCase());
            if (matcher.find()) {
                flag = true;
            }
        }
        return flag;
    }
}
