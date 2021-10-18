package org.example.model.util.statement.impl;

import org.example.model.entity.Achievement;
import org.example.model.entity.enumeration.Status;
import org.example.model.util.statement.StatementUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.example.model.util.statement.impl.Constants.*;

/**
 * An implementation of {@link StatementUtil} for {@link Achievement} entity.
 */
public class AchStatementUtil implements StatementUtil<Achievement> {

    /**{@inheritDoc}*/
    @Override
    public Achievement buildEntity(ResultSet resultSet) throws SQLException {
        return Achievement.newBuilder()
                .setAchId(resultSet.getLong(ACH_ID))
                .setName(resultSet.getString(NAME))
                .setFlavor(resultSet.getString(FLAVOR))
                .setPicture(resultSet.getString(PICTURE))
                .setStatus(Status.valueOf(
                        resultSet.getString(STATUS)))
                .build();
    }

    /**{@inheritDoc}*/
    @Override
    public void fillStatementWithEntity(PreparedStatement statement,
                                        Achievement entity) throws SQLException {
        fillStatement(statement, List.of(entity.getName(), entity.getFlavor()));
    }

    /**{@inheritDoc}*/
    @Override
    public void fillStatementWithEntities(PreparedStatement statement,
                                          List<Achievement> entities)
            throws SQLException {
        for (var elem: entities) {
            fillStatementWithEntity(statement, elem);
            statement.addBatch();
        }
    }
}
