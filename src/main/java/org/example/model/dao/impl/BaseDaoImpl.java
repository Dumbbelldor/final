package org.example.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controller.connection.ConnectionPool;
import org.example.controller.connection.impl.BasicConnectionPool;
import org.example.controller.connection.impl.ProxyConnection;
import org.example.model.dao.BaseDao;
import org.example.model.entity.Entity;
import org.example.model.entity.enumeration.Language;
import org.example.model.util.statement.StatementUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>Implementation of an {@link BaseDao} interface,
 * which provides generified solutions for the most
 * common uses.</p>
 *
 * @param <T> classes that implement {@link Entity} interface
 */
public class BaseDaoImpl<T extends Entity> implements BaseDao<T> {

    private static final Logger log = LogManager.getLogger();

    protected static final String ERROR_SQL = "SQL exception occurred ";
    protected static final String SPECIAL_SYMBOL = "|-|";

    protected static final int PARAM_FIRST = 1;
    protected static final int PARAM_SECOND = 2;
    protected static final int PARAM_THIRD = 3;

    protected final ConnectionPool pool;

    private final StatementUtil<T> utils;

    public BaseDaoImpl(StatementUtil<T> utils) {
        this.utils = utils;
        pool = BasicConnectionPool.INSTANCE;
    }

    /**
     * <p>General purpose method that executes the special SQL
     * command, retrieves {@link ResultSet} and builds an
     * entity.</p>
     *
     * <p>{@link #createLocalizedSql(String, Language)} localizes
     * the given SQL string.</p>
     *
     * <p>Using {@link StatementUtil} implementations the {@link ResultSet}
     * will be parsed into final entity.</p>
     *
     * <p>Passing {@code null} as {@link List} of objects
     * will result in executing SQL query "as it is".</p>
     *
     * <p>This method is suitable for every searching operation
     * that requires building up an localized entity in the end.</p>
     *
     * @param sql the special SQL query
     * @param lang the language of the result
     * @param params params of the SQL query
     *
     * @return {@link Optional} entity
     */
    @Override
    public Optional<T> find(String sql, Language lang, List<Object> params) {
        String localizedSql = createLocalizedSql(sql, lang);
        Optional<T> result = Optional.empty();
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(localizedSql)) {

            utils.fillStatement(statement, params);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result = Optional.of(utils.buildEntity(rs));
            }
        } catch (SQLException e) {
            log.error(ERROR_SQL, e);
        }
        return result;
    }

    /**{@inheritDoc}*/
    @Override
    public List<T> findAll(String sql, Language lang) {
        return findAll(sql, lang, null);
    }

    /**{@inheritDoc}*/
    @Override
    public List<T> findAll(String sql, Language lang, List<Object> params) {
        String localizedSql = createLocalizedSql(sql, lang);
        List<T> result = new ArrayList<>();
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(localizedSql)) {

            utils.fillStatement(statement, params);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                T entity = utils.buildEntity(rs);
                result.add(entity);
            }
        } catch (SQLException e) {
            log.error(ERROR_SQL, e);
        }
        return result;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean save(String sql, Language lang, T entity) {
        String localizedSql = createLocalizedSql(sql, lang);
        boolean flag = false;
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(localizedSql)) {
            utils.fillStatementWithEntity(statement, entity);
            statement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            log.error(ERROR_SQL, e);
        }
        return flag;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean save(String sql, List<Object> params) {
        boolean flag = false;
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(sql)) {
            utils.fillStatement(statement, params);
            statement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            log.error(ERROR_SQL, e);
        }
        return flag;
    }

    /**
     * <p>If the SQL string is created with special "|-|"
     * locale suffix (name_en => name_|-|), then
     * {@link Language#getLocale()} will be called to be inserted
     * instead of special symbol if given.</p>
     *
     * @param sql a SQL query to be localized
     * @param lang the language to be applied
     *
     * @return modified SQL string
     */
    protected String createLocalizedSql(String sql, Language lang) {
        String localizedSql;
        if (lang != null) {
            localizedSql = sql.replace(SPECIAL_SYMBOL, lang.getLocale());
        } else {
            localizedSql = sql;
        }
        return localizedSql;
    }
}