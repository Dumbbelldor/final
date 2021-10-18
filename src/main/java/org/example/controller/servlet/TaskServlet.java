package org.example.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.entity.enumeration.CommandType;
import org.example.model.entity.enumeration.Language;
import org.example.model.util.helper.RequestHelper;
import org.example.model.entity.Task;
import org.example.model.entity.User;
import org.example.model.security.InputCleaner;
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

        if (user != null) {
            Long taskId = Long.parseLong(
                    cleaner.cleanse(helper.getParameter(REQUEST_TASK)));
            Optional<Task> optional = taskService.findById(taskId, locale);
            if (optional.isPresent()) {
                Task task = optional.get();

                /* Without using helper due to jsp attributes visibility breaking */
                /* Single string representation of attribute "currentTask" */
                req.getSession().setAttribute("currentTask", task);
            }

            helper.dispatch(CommandType.GOTO_TASK);
        } else {
            helper.redirect(CommandType.GOTO_AUTHORIZATION);
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
            userAnswer = cleaner.cleanse(userAnswer);

            String dbAnswer = task.getAnswer();

            if (dbAnswer.equals(userAnswer)) {

                User user = (User) helper.getSessionAttribute(SESSION_CURRENT_USER);

                if (!taskService.isSolvedTaskPresent(user.getUserId(), task.getTaskId())) {
                    taskService.saveTaskForUser(user.getUserId(), task.getTaskId());

                    listener.resolveLevelAndExp(helper, user, task);
                    listener.resolveAchievements(helper, user);

                    helper.setSessionAttribute(SESSION_CURRENT_USER, user);
                }

                /* Without using helper due to jsp attributes visibility breaking */
                /* Single occurrence of the string representation of attribute "taskAlert" */
                req.setAttribute("taskAlert", PARAM_ALERT);
                helper.redirect(CommandType.GOTO_SPEC_CATEGORY);
            }
        } else {
            req.setAttribute(REQUEST_ALERT, PARAM_ALERT);
            helper.dispatch(CommandType.GOTO_TASK);
        }
    }
}
