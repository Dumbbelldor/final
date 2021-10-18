package org.example.model.dao;

import org.example.model.entity.Entity;
import org.example.model.entity.enumeration.Language;

import java.util.List;
import java.util.Optional;

/**
 * <p>Generic interface for all common database operations.</p>
 *
 * <p>Establishes connection with JDBC, fetches results and
 * creates objects from {@link java.sql.ResultSet}.</p>
 *
 * <p>Utilizes special SQL commands with emptied language
 * markers in it to return localized results depending on
 * a given locale.</p>
 *
 * <p>The given SQL may not support localization injections nor
 * have parameters to be filled; in that case a SQL command
 * will be executed anyway.</p>
 *
 * @param <T> representing entity to be formed in result
 */
public interface BaseDao<T extends Entity> {

    /**
     * Retrieves an {@link Optional} of entity from a database with
     * the given SQL command, the localization parameter and
     * the list of parameters, which will be inserted into the SQL
     * command to fetch localized entity from a database.
     *
     * @param sql the special SQL string
     * @param lang an enum constant defining chosen locale
     * @param params a list of statement parameters to be inserted into SQL
     *
     * @return a localized entity or an empty {@link Optional}
     */
    Optional<T> find(String sql, Language lang, List<Object> params);

    /**
     * Retrieves a {@link List} of entities from a database with
     * the given SQL command and the localization parameter.
     *
     * @param sql the special SQL string
     * @param lang an enum constant defining chosen locale
     * @param params a list of statement parameters to be inserted into SQL
     *
     * @return a localized entities or an empty {@link List}
     */
    List<T> findAll(String sql, Language lang, List<Object> params);

    /**
     * Calls {@link #findAll(String, Language, List)}
     * with nullified {@link List} of params.
     *
     * @param sql the special SQL string
     * @param lang an enum constant defining chosen locale
     *
     * @return a localized entities or an empty {@link List}
     */
    List<T> findAll(String sql, Language lang);

    /**
     * Saves the given {@code entity} in a database.
     *
     * @param sql the special SQL string
     * @param lang an enum constant defining chosen locale
     * @param entity the entity to be saved
     *
     * @return true if the operation succeeded
     */
    boolean save(String sql, Language lang, T entity);

    /**
     * General purpose method to save various information
     * in various forms in a database depending on the given
     * {@code params}.
     *
     * @param sql the special SQL string
     * @param params a list of statement parameters to be inserted into SQL
     *
     * @return true if the operation succeeded
     */
    boolean save(String sql, List<Object> params);

    /**
     * Transactional save of many entities that are passed to this method.
     *
     * @param sql the special SQL string
     * @param lang an enum constant defining chosen locale
     * @param entities the entities to be saved
     *
     * @return true if the operation succeeded
     */
    boolean saveAll(String sql, Language lang, List<T> entities);

    /**
     * Sets {@link org.example.model.entity.enumeration.Status} to DELETED
     * by the given id.
     *
     * @param sql the special SQL string
     * @param id an id of a desired entity
     *
     * @return true if the operation succeeded
     */
    boolean deleteById(String sql, Long id);

    /**
     * Counts all rows from a table that are retrieved
     * through passed {@code sql} parameter.
     *
     * @param sql the special SQL string
     * @param params a list of statement parameters to be inserted into SQL
     *
     * @return number of rows retrieved as {@link Long}
     */
    long count(String sql, List<Object> params);

    /**
     * Calls {@link #count(String, List)} with nullified
     * parameters.
     *
     * @param sql the special SQL string
     *
     * @return number of rows rerieved as {@link Long}
     */
    long count(String sql);
}