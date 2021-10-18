package org.example.model.service;

import org.example.model.entity.Task;
import org.example.model.entity.enumeration.*;
import org.example.model.entity.localization.LocalizedCategory;
import org.example.model.entity.wrapper.SolvedTasksWrapper;
import org.example.model.entity.wrapper.StatusTaskWrapper;

import java.util.List;
import java.util.Optional;

/**
 * Service layer that distributes ready-to-use
 * methods to work with database specially for
 * {@link Task} entity that conceals every
 * corresponding detail of the DAO.
 */
public interface TaskService {

    /**
     * Fetches the localized {@link Task} entity from
     * a database, or else returns {@link Optional#empty()}.
     *
     * @param id an task's id
     * @param lang a selected language
     *
     * @return {@link Optional} object
     */
    Optional<Task> findById(Long id, Language lang);

    /**
     * Fetches the localized {@link Task} entities from
     * a database, or else returns {@link java.util.Collections#emptyList()}.
     *
     * @param lang a selected language
     *
     * @return {@link List} of objects
     */
    List<Task> findAll(Language lang);

    /**
     * Saves the given {@link Task} entity in
     * a database.
     *
     * @param entity entity to be saved
     *
     * @return true if operation is successful
     */
    boolean save(Task entity, Language lang);

    /**
     * Fetches all localized {@link Task} entities for
     * the given {@link Category}.
     *
     * @param category category for searching
     * @param lang a selected language
     *
     * @return {@link List} of entities
     */
    List<Task> findByCategory(Category category, Language lang);

    /**
     * Fetches {@link SolvedTasksWrapper} from a database for the given
     * user id.
     *
     * @param userId the user's id
     * @param lang a selected language
     *
     * @return {@link List} of objects
     */
    List<SolvedTasksWrapper> countProgression(Long userId, Language lang);

    /**
     * Saves task with the given task id for the user.
     *
     * @param userId the user's id
     * @param taskId the task's id
     *
     * @return true if operation succeeded
     */
    boolean saveTaskForUser(Long userId, Long taskId);

    /**
     * Fetches all solved {@link Task} by the given user id.
     *
     * @param userId the user's id
     * @param lang a selected entity
     *
     * @return {@link List} of entities
     */
    List<Task> findAllSolvedTasksForUser(Long userId, Language lang);

    /**
     * Ensures that the given task by its id is solved by the user.
     *
     * @param userId the user's id
     * @param taskId the task's id
     *
     * @return true if task is solved
     */
    boolean isSolvedTaskPresent(Long userId, Long taskId);

    /**
     * Fetches {@link StatusTaskWrapper} from a database for the given user id
     * and the chosen {@link Category}.
     *
     * @param userId the user's id
     * @param lang a selected language
     * @param category the chosen category
     *
     * @return {@link List} of objects
     */
    List<StatusTaskWrapper> resolveTasksForUserByCategory(Long userId,
                                                         Language lang,
                                                         Category category);

    /**
     * Returns {@link LocalizedCategory} from a database by the
     * given language.
     *
     * @param lang a selected language
     *
     * @return {@link List} of objects
     */
    List<LocalizedCategory> findAllTaskCategories(Language lang);
}