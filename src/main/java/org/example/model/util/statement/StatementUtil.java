package org.example.model.util.statement;

import org.example.model.entity.Entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * An interface for rendering an assistance in
 * filling {@link PreparedStatement} with parameters,
 * building an {@link Entity} out of a {@link ResultSet}.
 *
 * @param <T> a class that implements {@link Entity}
 */
public interface StatementUtil<T extends Entity> {

    /**
     * Builds {@link Entity} out of a {@link ResultSet}.
     *
     * @param resultSet a result set to make an entity out of it
     *
     * @return a complete entity
     *
     * @throws SQLException if the result set is inappropriate
     */
    T buildEntity(ResultSet resultSet) throws SQLException;

    /**
     * Fills the given {@link PreparedStatement} with the
     * information that is distributed by the given {@link Entity}.
     *
     * @param statement a prepared statement to be filled
     * @param entity an entity bearing information
     *
     * @throws SQLException if the statement is inappropriate
     */
    void fillStatementWithEntity(PreparedStatement statement, T entity) throws SQLException;

    /**
     * Method for filling the given {@link PreparedStatement}
     * with numerous params.
     *
     * @param statement a statement to be filled
     * @param params params to fill the statement with
     *
     * @throws SQLException if the statement is inappropriate
     */
    default void fillStatement(PreparedStatement statement,
                               List<Object> params) throws SQLException {
        if (params != null && !params.isEmpty()) {
            for (int i = 0, j = 1; i < params.size(); i++, j++) {
                statement.setObject(j, params.get(i));
            }
        }
    }
}
