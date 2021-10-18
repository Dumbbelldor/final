package org.example.model.dao;

import org.example.model.entity.Task;
import org.example.model.entity.enumeration.Category;
import org.example.model.entity.enumeration.Language;
import org.example.model.entity.localization.LocalizedCategory;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TaskDao extends BaseDao<Task> {

    Optional<Task> findByName(String sql, Language lang, String name);
    List<Task> findByCategory(String sql, Language lang, Category category);
    Map<String, Integer> countTasksForAllCategories(String sql, Language lang,
                                                      List<Object> params);
    boolean isSolvedTaskPresent(String sql, Long userId, Long taskId);
    List<LocalizedCategory> findAllTaskCategories(String sql, Language lang);
}
