package org.example.model.service;

import org.example.model.entity.User;
import org.example.model.entity.enumeration.Language;
import org.example.model.entity.localization.LocalizedLevel;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Service layer that distributes ready-to-use
 * methods to work with database specially for
 * {@link User} entity that conceals every
 * corresponding detail of the DAO.
 */
public interface UserService {

    /**
     * Fetches the localized {@link User} entity from
     * a database, or else returns {@link Optional#empty()}.
     *
     * @param id an user's id
     * @param lang a selected language
     *
     * @return {@link Optional} object
     */
    Optional<User> findById(Long id, Language lang);

    /**
     * Fetches the localized {@link User} entities from
     * a database, or else returns {@link Collections#emptyList()}.
     *
     * @param lang a selected language
     *
     * @return {@link List} of objects
     */
    List<User> findAll(Language lang);

    /**
     * Saves the given {@link User} entity in
     * a database.
     *
     * @param entity entity to be saved
     *
     * @return true if operation is successful
     */
    boolean save(User entity);

    /**
     * Fetches the localized {@link User} entity from
     * a database, or else returns {@link Optional#empty()}.
     *
     * @param login the user's login
     * @param lang a selected language
     *
     * @return {@link Optional} object
     */
    Optional<User> findByLogin(String login, Language lang);

    /**
     * Updates the given {@link User} entity in
     * a database.
     *
     * @param entity entity to be updated
     *
     * @return true if operation is successful
     */
    boolean earnExpAndLevel(User entity);

    /**
     * Updates the given {@link User} entity in
     * a database.
     *
     * @param userId the user's id
     * @param achId the achievement's id
     *
     * @return true if operation is successful
     */
    boolean earnAchievement(Long userId, Long achId);

    /**
     * Fetches {@link LocalizedLevel} from a database.
     *
     * @param userId the user's id
     * @param lang a selected language
     *
     * @return {@link LocalizedLevel} object
     */
    LocalizedLevel getLevelByUserId(Long userId, Language lang);

    /**
     * Counts all solved tasks for the given
     * {@link User}.
     *
     * @param userId the user's id
     *
     * @return quantity of solved tasks
     */
    long countSolvedForUser(Long userId);

    /**
     * Deletes image reference for the given
     * {@link User}.
     *
     * @param userId the user's id
     *
     * @return true if operation succeeded
     */
    boolean deleteImageById(Long userId);

    /**
     * Creates an image storage reference for the
     * fiven {@link User}.
     *
     * @param picture a link to the image
     * @param userId the user's id
     *
     * @return true if operation succeeded
     */
    boolean uploadImageById(String picture, Long userId);
}