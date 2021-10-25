package org.example.model.util.statement.impl;

import org.example.model.entity.Achievement;
import org.example.model.entity.enumeration.Status;
import org.mockito.Mock;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.model.util.statement.impl.Constants.*;
import static org.testng.Assert.*;
import static org.mockito.Mockito.*;

public class AchStatementUtilTest {

    private static final AchStatementUtil util = AchStatementUtil.INSTANCE;

    @Mock
    private final ResultSet resultSet = mock(ResultSet.class);

    private Achievement achievement;

    @BeforeTest
    public void setup() throws SQLException {
        when(resultSet.getLong(ACH_ID)).thenReturn(123L);
        when(resultSet.getString(NAME)).thenReturn("name");
        when(resultSet.getString(FLAVOR)).thenReturn("flavor");
        when(resultSet.getString(PICTURE)).thenReturn("pic");
        when(resultSet.getString(STATUS)).thenReturn(Status.ACTIVE.name());

        achievement = Achievement.newBuilder()
                .setAchId(123L)
                .setName("name")
                .setFlavor("flavor")
                .setPicture("pic")
                .setStatus(Status.valueOf("ACTIVE"))
                .build();
    }

    @Test
    public void testBuildEntity() throws SQLException {
        Achievement actual = util.buildEntity(resultSet);
        assertEquals(actual, achievement);
    }
}