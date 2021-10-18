package org.example.model.security;

import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.testng.Assert.*;

public class InputCleanerTest {

    private static final String BAD_1 = "?name=<strong>Yoy</strong>";
    private static final String BAD_2 = "http://site.com/home.php=<STYLE>alert(\"XSS\")';</STYLE>";
    private static final String BAD_3 = "<form action=\"destination.asp\"><table><tr><td>Login:</td><td><input type=text length=20 name=login></td></tr><tr><td>Password:</td><td><input type=text length=20 name=password></td></tr></table><input type=submit value=LOGIN></form>";
    private static final String BAD_4 = "?id=<script>Very Heavy Attack</script>";

    private static final String GOOD_1 = "?name=Yoy";
    private static final String GOOD_2 = "http://site.com/home.php=alert()';";
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

    @Test
    public void test() {
        String LOCALE_TASK_FIND_BY_ID = """
            select t.task_id as task_id,
                   lt.name_|-| as name,
                   lt.desc_|-| as description,
                   lt.answer_|-| as answer,
                   d.name_|-| as difficulty,
                   c.name_|-| as category,
                   t.experience as experience,
                   t.status as status,
                   t.created as created,
                   t.changed as changed
            from tasks t
            inner join localized_tasks lt on t.task_id = lt.loc_task_id
            inner join difficulties d on d.difficulty = t.difficulty
            inner join categories c on c.category = t.category
            where task_id = ?""";
        System.out.println(LOCALE_TASK_FIND_BY_ID.replace("|-|", "en"));
    }

    @Test
    public void testio() {
        Path path = Path.of("src/main/webapp/user_images").toAbsolutePath();
        System.out.println(path);
        assertTrue(Files.exists(Path.of("C:\\Users\\Max\\IdeaProjects\\Final\\src\\main\\webapp\\user_images\\Безымянный.png")));
    }
}