package org.example.model.service;

import org.example.model.entity.Achievement;
import org.example.model.entity.enumeration.Language;

import java.util.List;
import java.util.Optional;

public interface AchService {

    /**
     * Fetches the localized {@link Achievement} entity from
     * a database, or else returns {@link Optional#empty()}.
     *
     * @param id an task's id
     * @param lang a selected language
     *
     * @return {@link Optional} object
     */
    Optional<Achievement> findById(Long id, Language lang);

    /**
     * Fetches the localized {@link Achievement} entities from
     * a database, or else returns {@link java.util.Collections#emptyList()}.
     *
     * @param lang a selected language
     *
     * @return {@link List} of objects
     */
    List<Achievement> findAll(Language lang);

    /**
     * Saves the given {@link Achievement} entity in
     * a database.
     *
     * @param entity entity to be saved
     *
     * @return true if operation is successful
     */
    boolean save(Achievement entity, Language lang);

    /**
     * Fetches all localized {@link Achievement} entities
     * by the given user id.
     *
     * @param userId the user's id
     * @param lang a selected language
     *
     * @return {@link List} of objects
     */
    List<Achievement> findAllAchForUser(Long userId, Language lang);

    /**
     * Fetches all unclaimed localized {@link Achievement} entities
     * by the given user id.
     *
     * @param userId the user's id
     * @param lang a selected language
     *
     * @return {@link List} of objects
     */
    List<Achievement> findUnclaimedForUser(Long userId, Language lang);
}
