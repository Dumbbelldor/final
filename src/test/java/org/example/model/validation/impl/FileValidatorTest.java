package org.example.model.validation.impl;

import org.example.model.validation.Validator;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FileValidatorTest {

    private static final String GOOD_1 = "/files/example.bmp";
    private static final String GOOD_2 = "/files/example.jpeg";
    private static final String GOOD_3 = "/files/example.jpg";
    private static final String GOOD_4 = "/files/example.png";

    private static final String BAD_1 = "/files/example.txt";
    private static final String BAD_2 = "/files/example.script";
    private static final String BAD_3 = "/files/example.exe";
    private static final String BAD_4 = "/files/example.doc";

    private static final Validator validator = FileValidator.INSTANCE;

    @Test
    public void testValidateImageGood1() {
        assertTrue(validator.validate(GOOD_1));
    }

    @Test
    public void testValidateImageGood2() {
        assertTrue(validator.validate(GOOD_2));
    }

    @Test
    public void testValidateImageGood3() {
        assertTrue(validator.validate(GOOD_3));
    }

    @Test
    public void testValidateImageGood4() {
        assertTrue(validator.validate(GOOD_4));
    }

    @Test
    public void testValidateImageBad1() {
        assertFalse(validator.validate(BAD_1));
    }

    @Test
    public void testValidateImageBad2() {
        assertFalse(validator.validate(BAD_2));
    }

    @Test
    public void testValidateImageBad3() {
        assertFalse(validator.validate(BAD_3));
    }

    @Test
    public void testValidateImageBad4() {
        assertFalse(validator.validate(BAD_4));
    }
}