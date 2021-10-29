package org.example.model.validation;

/**
 * Validates the given resource to match
 * dependent requirements.
 */
public interface Validator {

    /**
     * Matches the given input against some rule.
     *
     * @return true if operation succeeded
     */
    boolean validate(String string);
}
