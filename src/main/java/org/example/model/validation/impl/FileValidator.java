package org.example.model.validation.impl;

import org.example.model.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validates a file to be appropriate to some rule.
 */
public enum FileValidator implements Validator {

    INSTANCE;

    private static final String REGEX_IMAGE_EXT = ".(jpe?g|png|bmp)$";

    /**
     * Checks the given file if it matches one of the
     * permitted image extensions.
     *
     * @param filePath a path to file
     *
     * @return true if it is an image file
     */
    public boolean validateImage(String filePath) {
        boolean flag = false;
        if (filePath != null && !filePath.isBlank()) {
            Matcher matcher = Pattern.compile(REGEX_IMAGE_EXT)
                    .matcher(filePath.toLowerCase());
            if (matcher.find()) {
                flag = true;
            }
        }
        return flag;
    }
}
