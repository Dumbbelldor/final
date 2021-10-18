package org.example.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controller.connection.impl.ProxyConnection;
import org.example.model.dao.TaskDao;
import org.example.model.entity.Task;
import org.example.model.entity.enumeration.Category;
import org.example.model.entity.enumeration.Language;
import org.example.model.entity.localization.LocalizedCategory;
import org.example.model.util.statement.StatementUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TaskDaoImpl extends BaseDaoImpl<Task> implements TaskDao {

    private static final Logger log = LogManager.getLogger();

    private final StatementUtil<Task> utils;

    public TaskDaoImpl(StatementUtil<Task> utils) {
        super(utils);
        this.utils = utils;
    }

    @Override
    public Optional<Task> findByName(String sql,
                                     Language lang,
                                     String name) {
        return find(sql, lang, List.of(name));
    }

    @Override
    public List<Task> findByCategory(String sql,
                                     Language lang,
                                     Category category) {
        return findAll(sql, lang, List.of(category.name()));
    }

    @Override
    public final Map<String, Integer> countTasksForAllCategories(String sql,
                                                                 Language lang,
                                                                 List<Object> params) {
        String localizedSql = createLocalizedSql(sql, lang);
        Map<String, Integer> result = new HashMap<>();
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(localizedSql)) {

            utils.fillStatement(statement, params);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String tempCat = rs.getString(PARAM_FIRST);
                Integer tempInt = rs.getInt(PARAM_SECOND);
                result.put(tempCat, tempInt);
            }
        } catch (SQLException e) {
            log.error(ERROR_SQL, e);
        }
        return result;
    }

    @Override
    public boolean isSolvedTaskPresent(String sql, Long userId, Long taskId) {
        boolean flag = false;
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(sql)) {
            utils.fillStatement(statement, List.of(userId, taskId));
            if (statement.execute()) {
                flag = true;
            }
        } catch (SQLException e) {
            log.error(ERROR_SQL, e);
        }
        return flag;
    }

    @Override
    public List<LocalizedCategory> findAllTaskCategories(String sql, Language lang) {
        String localizedSql = createLocalizedSql(sql, lang);
        List<LocalizedCategory> result = new ArrayList<>();
        try (ProxyConnection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(localizedSql)) {

            utils.fillStatement(statement, null);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result.add(new LocalizedCategory(
                        Category.valueOf(
                                rs.getString(PARAM_FIRST)),
                        rs.getString(PARAM_SECOND),
                        rs.getString(PARAM_THIRD)
                ));
            }
        } catch (SQLException e) {
            log.error(ERROR_SQL, e);
        }
        return result;
    }
}
