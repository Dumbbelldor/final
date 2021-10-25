package org.example.model.util.statement;

import org.example.model.entity.User;
import org.example.model.util.statement.impl.UserStatementUtil;
import org.mockito.Mock;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.testng.Assert.*;
import static org.mockito.Mockito.*;

public class StatementUtilTest {

    private static final StatementUtil<User> util = UserStatementUtil.INSTANCE;

    @Mock
    private final PreparedStatement statement = mock(PreparedStatement.class);

    @Mock
    private final PreparedStatement ps = mock(PreparedStatement.class);

    @BeforeTest
    public void setup() throws SQLException {
        ps.setObject(1, 1);
        ps.setObject(2, "asd");
        ps.setObject(3, "123");
    }

    @Test
    public void testFillStatement() throws SQLException {
        List<Object> params = List.of(1, "asd", "123");
        util.fillStatement(statement, params);
        var actual = statement.getParameterMetaData();
        var expected = ps.getParameterMetaData();
        assertEquals(actual, expected);
    }
}