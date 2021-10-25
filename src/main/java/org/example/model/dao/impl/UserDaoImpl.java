package org.example.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controller.connection.impl.ProxyConnection;
import org.example.model.dao.UserDao;
import org.example.model.entity.User;
import org.example.model.entity.enumeration.Language;
import org.example.model.entity.enumeration.Level;
import org.example.model.entity.localization.LocalizedLevel;
import org.example.model.util.statement.StatementUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * An implementation of the {@link UserDao} interface.
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    private static final Logger log = LogManager.getLogger();

    private final StatementUtil<User> utils;

    public UserDaoImpl(StatementUtil<User> utils) {
        super(utils);
        this.utils = utils;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean save(String sql, User entity) {
        return super.save(sql, null, entity);
    }

    /**{@inheritDoc}*/
    @Override
    public Optional<User> findByLogin(String sql, Language lang, String login) {
        return find(sql, lang, List.of(login));
    }

    /**{@inheritDoc}*/
    @Override
    public LocalizedLevel getLevelByUserId(String sql, Language lang, Long userId) {
        String localizedSql = createLocalizedSql(sql, lang);
        LocalizedLevel result = null;
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(localizedSql)) {

            utils.fillStatement(statement, List.of(userId));

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result = new LocalizedLevel(
                        Level.valueOf(
                                rs.getString(PARAM_FIRST)),
                        rs.getInt(PARAM_SECOND),
                        rs.getString(PARAM_THIRD)
                );
            }
        } catch (SQLException e) {
            log.error(ERROR_SQL, e);
        }
        return result;
    }

    /**{@inheritDoc}*/
    @Override
    public long countSolvedForUser(String sql, Long userId) {
        long result = 0;
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(sql)) {
            utils.fillStatement(statement, List.of(userId));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result = rs.getLong(PARAM_FIRST);
            }
        } catch (SQLException e) {
            log.error(ERROR_SQL, e);
        }
        return result;
    }
}
