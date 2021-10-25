package org.example.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.entity.enumeration.CommandType;
import org.example.model.entity.enumeration.Language;
import org.example.model.util.helper.RequestHelper;
import org.example.model.entity.Task;
import org.example.model.entity.User;
import org.example.model.util.security.InputCleaner;
import org.example.model.service.TaskService;
import org.example.model.service.impl.TaskServiceImpl;
import org.example.model.util.listener.UserActivityListener;

import java.io.IOException;
import java.util.Optional;

import static org.example.controller.servlet.ServletConstants.*;

/**
 * Servlet that handles matters of displaying and
 * resolving operations on the task page.
 */
@WebServlet(name = "task", urlPatterns = "/task")
public class TaskServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger();

    private static final RequestHelper helper = RequestHelper.INSTANCE;
    private static final InputCleaner cleaner = InputCleaner.INSTANCE;
    private static final TaskService taskService = TaskServiceImpl.INSTANCE;
    private static final UserActivityListener listener = UserActivityListener.INSTANCE;

    /**
     * If accessor if authorized, prepares and
     * displays task page for him, otherwise sends him
     * to authorization page.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        helper.init(req, resp);

        User user = (User) helper.getSessionAttribute(SESSION_CURRENT_USER);
        Language locale = Language.getByLocale(
                (String) helper.getSessionAttribute(SESSION_LANGUAGE));
        String paramId = helper.getParameter(REQUEST_TASK);

        if (paramId != null) {
            try {
                long taskId = Long.parseLong(cleaner.cleanse(paramId));

                prepareTask(user, taskId, locale, req);

            } catch (NumberFormatException e) {
                log.error("Attempted to pass NaN as the parameter for task id", e);
                helper.redirectWithReferrer();
            }
        } else {
            Task task = (Task) helper.getSessionAttribute(SESSION_CURRENT_TASK);

            if (task != null) {
                prepareTask(user, task.getTaskId(), locale, req);
            } else {
                helper.redirect(CommandType.GOTO_SPEC_CATEGORY);
            }
        }
    }

    /**
     * Matches the user's answer against database's one: if correct then
     * saves the current task as solved for the user, granting all
     * consequential rewards if possible, or else compels him to retry.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        helper.init(req, resp);

        Task task = (Task) helper.getSessionAttribute(SESSION_CURRENT_TASK);

        String userAnswer = helper.getParameter(REQUEST_ANSWER);
        if (userAnswer != null) {
            userAnswer = cleaner.cleanse(userAnswer).toLowerCase();

            String dbAnswer = task.getAnswer().toLowerCase();

            if (dbAnswer.equals(userAnswer)) {

                User user = (User) helper.getSessionAttribute(SESSION_CURRENT_USER);

                if (!taskService.isSolvedTaskPresent(user.getUserId(), task.getTaskId())) {
                    taskService.saveTaskForUser(user.getUserId(), task.getTaskId());

                    listener.resolveLevelAndExp(helper, user, task);
                    listener.resolveAchievements(helper, user);

                    helper.setSessionAttribute(SESSION_CURRENT_USER, user);
                }

                helper.redirect(CommandType.GOTO_SPEC_CATEGORY);
            } else {
                /* Typically should not be reached */
                req.setAttribute("taskAlert", PARAM_ALERT);
                helper.dispatch(CommandType.GOTO_TASK);
            }

        } else {
            req.setAttribute(REQUEST_ALERT, PARAM_ALERT);
            helper.dispatch(CommandType.GOTO_TASK);
        }
    }

    private void prepareTask(User user, long taskId,
                             Language locale, HttpServletRequest req)
            throws ServletException, IOException {

        if (user != null) {
            Optional<Task> optional = taskService.findById(taskId, locale);

            if (optional.isPresent()) {
                Task currentTask = optional.get();

                /* Without using helper due to jsp attributes visibility breaking */
                /* Single string representation of attribute "currentTask" */
                req.getSession().setAttribute("currentTask", currentTask);
                helper.dispatch(CommandType.GOTO_TASK);
            } else {
                log.error("Attempted to search for non-existent task");
                helper.redirect(CommandType.GOTO_SPEC_CATEGORY);
            }

        } else {
            log.info(LOG_UNAUTHORIZED_ACCESS);
            helper.redirect(CommandType.GOTO_AUTHORIZATION);
        }
    }
}
