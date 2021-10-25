package org.example.model.util.security;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class InputCleanerTest {

    private static final String BAD_1 = "?name=<strong>Yoy</strong>";
    private static final String BAD_2 = "https://site.com/home.php=<STYLE>alert(\"XSS\")';</STYLE>";
    private static final String BAD_3 = "<form action=\"destination.asp\"><table><tr><td>Login:</td><td><input type=text length=20 name=login></td></tr><tr><td>Password:</td><td><input type=text length=20 name=password></td></tr></table><input type=submit value=LOGIN></form>";
    private static final String BAD_4 = "?id=<script>Very Heavy Attack</script>";

    private static final String GOOD_1 = "?name=Yoy";
    private static final String GOOD_2 = "https://site.com/home.php=alert()';";
    private static final String GOOD_3 = "Login:Password:";
    private static final String GOOD_4 = "?id=Very Heavy Attack";

    private final InputCleaner cleaner = InputCleaner.INSTANCE;

    private String actual = null;
    private String expected = null;

    @Test
    public void testCleanse1() {
        actual = cleaner.cleanse(BAD_1);
        expected = GOOD_1;
        assertEquals(actual, expected);
    }

    @Test
    public void testCleanse2() {
        actual = cleaner.cleanse(BAD_2);
        expected = GOOD_2;
        assertEquals(actual, expected);
    }

    @Test
    public void testCleanse3() {
        actual = cleaner.cleanse(BAD_3);
        expected = GOOD_3;
        assertEquals(actual, expected);
    }

    @Test
    public void testCleanse4() {
        actual = cleaner.cleanse(BAD_4);
        expected = GOOD_4;
        assertEquals(actual, expected);
    }
}