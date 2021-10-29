package org.example.model.service.impl;

import org.example.model.dao.TaskDao;
import org.example.model.dao.impl.TaskDaoImpl;
import org.example.model.entity.Task;
import org.example.model.entity.enumeration.*;
import org.example.model.entity.localization.LocalizedCategory;
import org.example.model.entity.wrapper.StatusTaskWrapper;
import org.example.model.service.TaskService;
import org.example.model.util.statement.impl.TaskStatementUtil;
import org.example.model.entity.wrapper.SolvedTasksWrapper;

import java.util.*;

import static org.example.model.service.impl.SqlQueries.*;

/**
 * An implementation of the {@link TaskService} interface.
 */
public enum TaskServiceImpl implements TaskService {

    INSTANCE;

    private final TaskDao taskDao;

    TaskServiceImpl() {
        taskDao = new TaskDaoImpl(TaskStatementUtil.INSTANCE);
    }

    /**{@inheritDoc}*/
    @Override
    public Optional<Task> findById(Long id, Language lang) {
        return taskDao.find(LOCALE_TASK_FIND_BY_ID, lang, List.of(id));
    }

    /**{@inheritDoc}*/
    @Override
    public List<Task> findAll(Language lang) {
        return taskDao.findAll(LOCALE_TASK_FIND_ALL, lang);
    }

    /**{@inheritDoc}*/
    @Override
    public boolean save(Task entity, Language lang) {
        return taskDao.save(LOCALE_TASK_SAVE, lang, entity);
    }

    /**{@inheritDoc}*/
    @Override
    public List<Task> findByCategory(Category category, Language lang) {
        return taskDao.findByCategory(LOCALE_TASK_FIND_BY_CATEGORY, lang, category);
    }

    /**{@inheritDoc}*/
    @Override
    public List<SolvedTasksWrapper> countProgression(Long userId,
                                                     Language lang) {

        var overallTasks = taskDao
                .countTasksForAllCategories(LOCALE_TASK_COUNT_BY_CATEGORIES, lang, null);
        var solvedTasks = taskDao
                .countTasksForAllCategories(LOCALE_TASK_COUNT_SOLVED_BY_CATEGORIES_FOR_USER,
                        lang, List.of(userId));

        List<String> categories = new ArrayList<>(overallTasks.keySet());
        List<SolvedTasksWrapper> result = new ArrayList<>();

        for (var elem: categories) {

            int solved = 0;
            int overall = 0;

            if (solvedTasks.containsKey(elem)) {
                solved = solvedTasks.get(elem);
            }

            if (overallTasks.containsKey(elem)) {
                overall = overallTasks.get(elem);
            }

            result.add(new SolvedTasksWrapper(elem, solved, overall));
        }

        return result;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean saveTaskForUser(Long userId, Long taskId) {
        return taskDao.save(SOLVED_INSERT, List.of(userId, taskId));
    }

    /**{@inheritDoc}*/
    @Override
    public List<Task> findAllSolvedTasksForUser(Long userId, Language lang) {
        return taskDao.findAll(LOCALE_SOLVED_SELECT_ALL_BY_USER_ID, lang, List.of(userId));
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isSolvedTaskPresent(Long userId, Long taskId) {
        return taskDao.isSolvedTaskPresent(SOLVED_SELECT_BY_USER_ID_AND_TASK_ID,
                userId, taskId);
    }

    /**{@inheritDoc}*/
    @Override
    public List<StatusTaskWrapper> resolveTasksForUserByCategory(Long userId,
                                                                 Language lang,
                                                                 Category category) {
        List<StatusTaskWrapper> result = new ArrayList<>();

        List<Task> tasks = findByCategory(category, lang);
        List<Task> solved = findAllSolvedTasksForUser(userId, lang);

        for (var elem: tasks) {
            if (solved.contains(elem)) {
                result.add(new StatusTaskWrapper(elem, SolutionStatus.SOLVED));
            } else {
                result.add(new StatusTaskWrapper(elem, SolutionStatus.UNSOLVED));
            }
        }

        return result;
    }

    /**{@inheritDoc}*/
    @Override
    public List<LocalizedCategory> findAllTaskCategories(Language lang) {
        return taskDao.findAllTaskCategories(LOCALE_TASK_FIND_ALL_CATEGORIES, lang);
    }
}
