package org.example.model.util.statement.impl;

import org.example.model.entity.Task;
import org.mockito.Mock;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.model.util.statement.impl.Constants.*;
import static org.testng.Assert.*;
import static org.mockito.Mockito.*;

public class TaskStatementUtilTest {

    private static final TaskStatementUtil util = TaskStatementUtil.INSTANCE;

    @Mock
    private final ResultSet resultSet = mock(ResultSet.class);

    private Task task;

    @BeforeTest
    public void setup() throws SQLException {
        when(resultSet.getLong(TASK_ID)).thenReturn(100L);
        when(resultSet.getString(NAME)).thenReturn("name");
        when(resultSet.getString(DESCRIPTION)).thenReturn("desc");
        when(resultSet.getString(ANSWER)).thenReturn("ans");
        when(resultSet.getInt(EXPERIENCE)).thenReturn(100);
        when(resultSet.getString(DIFFICULTY)).thenReturn("diff");
        when(resultSet.getString(CATEGORY)).thenReturn("cat");

        task = Task.newBuilder()
                .setTaskId(100L)
                .setName("name")
                .setDescription("desc")
                .setAnswer("ans")
                .setExperience(100)
                .setDifficulty("diff")
                .setCategory("cat")
                .build();
    }

    @Test
    public void testBuildEntity() throws SQLException {
        Task actual = util.buildEntity(resultSet);
        assertEquals(actual, task);
    }
}