package org.example.model.util.statement.impl;

import org.example.model.entity.Task;
import org.example.model.entity.enumeration.Status;
import org.example.model.util.statement.StatementUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.example.model.util.statement.impl.Constants.*;

/**
 * An implementation of {@link StatementUtil} for {@link Task} entity.
 */
public enum TaskStatementUtil implements StatementUtil<Task> {

    INSTANCE;

    /**{@inheritDoc}*/
    @Override
    public Task buildEntity(ResultSet resultSet) throws SQLException {
        return Task.newBuilder()
                .setTaskId(resultSet.getLong(TASK_ID))
                .setName(resultSet.getString(NAME))
                .setDescription(resultSet.getString(DESCRIPTION))
                .setAnswer(resultSet.getString(ANSWER))
                .setExperience(resultSet.getInt(EXPERIENCE))
                .setDifficulty(resultSet.getString(DIFFICULTY))
                .setCategory(resultSet.getString(CATEGORY))
                .setStatus(Status.valueOf(
                        resultSet.getString(STATUS)))
                .setCreated(resultSet.getTimestamp(CREATED))
                .setChanged(resultSet.getTimestamp(CHANGED))
                .build();
    }

    /**{@inheritDoc}*/
    @Override
    public void fillStatementWithEntity(PreparedStatement statement,
                                        Task entity) throws SQLException {
        fillStatement(statement, List.of(entity.getExperience(), entity.getDifficulty(),
                entity.getCategory(), entity.getName(),
                entity.getDescription(), entity.getAnswer()));
    }
}