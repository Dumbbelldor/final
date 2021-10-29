package org.example.model.validation.impl;

import org.example.model.validation.Validator;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class EmailValidatorTest {

    private static final String GOOD_1 = "asfasf@gmail.com";
    private static final String GOOD_2 = "ga1515af@mail.ru";
    private static final String GOOD_3 = "asfq@yahoo.com";
    private static final String GOOD_4 = "yahh@gmail.com";

    private static final String BAD_1 = "asfasf@bmail.com";
    private static final String BAD_2 = "ga1515af@mail.ru<script>";
    private static final String BAD_3 = "asfq@yahoo.coma";
    private static final String BAD_4 = "yahh@gmail.con";

    private static final Validator validator = EmailValidator.INSTANCE;

    @Test
    public void testValidateGood1() {
        assertTrue(validator.validate(GOOD_1));
    }

    @Test
    public void testValidateGood2() {
        assertTrue(validator.validate(GOOD_2));
    }

    @Test
    public void testValidateGood3() {
        assertTrue(validator.validate(GOOD_3));
    }

    @Test
    public void testValidateGood4() {
        assertTrue(validator.validate(GOOD_4));
    }

    @Test
    public void testValidateBad1() {
        assertFalse(validator.validate(BAD_1));
    }

    @Test
    public void testValidateBad2() {
        assertFalse(validator.validate(BAD_2));
    }

    @Test
    public void testValidateBad3() {
        assertFalse(validator.validate(BAD_3));
    }

    @Test
    public void testValidateBad4() {
        assertFalse(validator.validate(BAD_4));
    }
}