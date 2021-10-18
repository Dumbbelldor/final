package org.example.model.dao;

import org.example.model.entity.User;
import org.example.model.entity.enumeration.Language;
import org.example.model.entity.localization.LocalizedLevel;

import java.util.Optional;

/**
 * An extension for the {@link BaseDao} interface
 * to work with {@link User} entity exclusively.
 */
public interface UserDao extends BaseDao<User> {

    /**
     * Saves the unlocalized {@link User} into
     * a database with the given SQL query.
     *
     * @param sql the sql query
     * @param entity the user's entity
     *
     * @return true if operation succeeded
     */
    boolean save(String sql, User entity);

    /**
     * Fetches a localized {@link User} from a database
     * with the given SQL query by the given login.
     *
     * @param sql the sql query
     * @param lang a selected language
     * @param login the user's login string
     *
     * @return an {@link Optional} entity
     */
    Optional<User> findByLogin(String sql, Language lang, String login);

    /**
     * Fetches the {@link LocalizedLevel} from a database
     * with the given SQL query by the user id.
     *
     * @param sql the sql query
     * @param lang a selected language
     * @param userId the user's id
     *
     * @return {@link LocalizedLevel} entity
     */
    LocalizedLevel getLevelByUserId(String sql, Language lang, Long userId);

    /**
     * Counts all solved tasks for the {@link User} by his id
     * with the given SQL query.
     *
     * @param sql the sql query
     * @param userId the user's id
     *
     * @return the number of solved tasks
     */
    long countSolvedForUser(String sql, Long userId);
}
