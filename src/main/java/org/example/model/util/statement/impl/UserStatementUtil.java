package org.example.model.util.statement.impl;

import org.example.model.entity.User;
import org.example.model.entity.enumeration.Status;
import org.example.model.util.statement.StatementUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.example.model.util.statement.impl.Constants.*;

/**
 * An implementation of {@link StatementUtil} for {@link User} entity.
 */
public enum UserStatementUtil implements StatementUtil<User> {

    INSTANCE;

    /**{@inheritDoc}*/
    @Override
    public User buildEntity(ResultSet resultSet) throws SQLException {
        return User.newBuilder()
                .setUserId(resultSet.getLong(USER_ID))
                .setLogin(resultSet.getString(LOGIN))
                .setPassword(resultSet.getString(PASSWORD))
                .setEmail(resultSet.getString(EMAIL))
                .setExperience(resultSet.getInt(EXPERIENCE))
                .setLevel(resultSet.getString(LEVEL))
                .setStatus(Status.valueOf(
                        resultSet.getString(STATUS)))
                .setCreated(resultSet.getTimestamp(CREATED))
                .setChanged(resultSet.getTimestamp(CHANGED))
                .setPicture(resultSet.getString(PICTURE))
                .build();
    }

    /**{@inheritDoc}*/
    @Override
    public void fillStatementWithEntity(PreparedStatement statement,
                                        User entity) throws SQLException {
        fillStatement(statement, List.of(entity.getLogin(), entity.getPassword(),
                entity.getEmail()));
    }
}
