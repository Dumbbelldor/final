package org.example.model.util.statement.impl;

import org.example.model.entity.User;
import org.mockito.Mock;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static org.example.model.util.statement.impl.Constants.*;
import static org.testng.Assert.*;
import static org.mockito.Mockito.*;

public class UserStatementUtilTest {

    private static final UserStatementUtil util = UserStatementUtil.INSTANCE;

    @Mock
    private final ResultSet resultSet = mock(ResultSet.class);

    private User user;

    @BeforeTest
    public void setup() throws SQLException {
        Timestamp current = new Timestamp(System.currentTimeMillis());

        when(resultSet.getLong(USER_ID)).thenReturn(1L);
        when(resultSet.getString(LOGIN)).thenReturn("name");
        when(resultSet.getString(PASSWORD)).thenReturn("pass");
        when(resultSet.getString(EMAIL)).thenReturn("email");
        when(resultSet.getInt(EXPERIENCE)).thenReturn(100);
        when(resultSet.getString(LEVEL)).thenReturn("level");
        when(resultSet.getTimestamp(CREATED))
                .thenReturn(current);
        when(resultSet.getTimestamp(CHANGED))
                .thenReturn(current);
        when(resultSet.getString(PICTURE)).thenReturn("picture");

        user = User.newBuilder()
                .setUserId(1L)
                .setLogin("name")
                .setPassword("pass")
                .setEmail("email")
                .setExperience(100)
                .setLevel("level")
                .setCreated(current)
                .setChanged(current)
                .setPicture("picture")
                .build();
    }

    @Test
    public void testBuildEntity() throws SQLException {
        User actual = util.buildEntity(resultSet);
        assertEquals(actual, user);
    }
}