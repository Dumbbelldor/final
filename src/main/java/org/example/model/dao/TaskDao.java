package org.example.model.dao;

import org.example.model.entity.Task;
import org.example.model.entity.enumeration.Category;
import org.example.model.entity.enumeration.Language;
import org.example.model.entity.localization.LocalizedCategory;

import java.util.List;
import java.util.Map;

/**
 * An extension for the {@link BaseDao} interface
 * to work with {@link Task} entity exclusively.
 */
public interface TaskDao extends BaseDao<Task> {

    /**
     * Fetches all localized {@link Task} entities from a database
     * be the given category with the SQL query.
     *
     * @param sql the sql query
     * @param lang a selected language
     * @param category a selected category
     *
     * @return {@link List} of entities
     */
    List<Task> findByCategory(String sql, Language lang, Category category);

    /**
     * Returns a {@link Map} filled with fetched localized categories and
     * its quantity with the given SQL query.
     *
     * @param sql the sql query
     * @param lang a selected language
     * @param params {@link List} of params
     *
     * @return {@link Map} with categories and task number
     */
    Map<String, Integer> countTasksForAllCategories(String sql, Language lang,
                                                      List<Object> params);

    /**
     * Checks if the {@link Task} is solved by the given user id
     * with the SQL query.
     *
     * @param sql the sql query
     * @param userId the user's id
     * @param taskId the task's id
     *
     * @return true if task is solved
     */
    boolean isSolvedTaskPresent(String sql, Long userId, Long taskId);

    /**
     * Fetches a {@link LocalizedCategory} collection by the given SQL query.
     *
     * @param sql the sql query
     * @param lang a selected language
     *
     * @return {@link List} of categories
     */
    List<LocalizedCategory> findAllTaskCategories(String sql, Language lang);
}
