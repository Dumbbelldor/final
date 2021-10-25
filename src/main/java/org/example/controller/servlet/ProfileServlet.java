package org.example.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.entity.enumeration.Destination;
import org.example.model.entity.enumeration.Language;
import org.example.model.entity.localization.LocalizedLevel;
import org.example.model.util.helper.RequestHelper;
import org.example.model.entity.Achievement;
import org.example.model.entity.User;
import org.example.model.service.AchService;
import org.example.model.service.TaskService;
import org.example.model.service.UserService;
import org.example.model.service.impl.AchServiceImpl;
import org.example.model.service.impl.TaskServiceImpl;
import org.example.model.service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.example.controller.servlet.ServletConstants.*;

/**
 * Servlet that handles all activities on
 * the profile page.
 */
@WebServlet(name = "profile", urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {

    private static final RequestHelper helper = RequestHelper.INSTANCE;

    private static final UserService userService = UserServiceImpl.INSTANCE;
    private static final AchService achService = AchServiceImpl.INSTANCE;
    private static final TaskService taskService = TaskServiceImpl.INSTANCE;

    /**
     * If accessed without an user's authorization sends to authorization page,
     * otherwise prepares and displays current user's profile page.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        helper.init(req, resp);
        Language locale = Language.getByLocale(
                (String) helper.getSessionAttribute(SESSION_LANGUAGE));

        User user = (User) helper.getSessionAttribute(SESSION_CURRENT_USER);

        if (user != null) {
            Optional<User> opt = userService.findById(user.getUserId(), locale);
            if (opt.isPresent()) {
                user = opt.get();

                prepareLevel(user, req, locale);
                prepareAchievements(user, req, locale);
                prepareProgression(user, req, locale);

                helper.setSessionAttribute(SESSION_CURRENT_USER, user);
            }
            helper.dispatch(Destination.GOTO_PROFILE);
        } else {
            helper.redirect(Destination.GOTO_AUTHORIZATION);
        }
    }

    /**
     * Prepares current user's level information.
     */
    private void prepareLevel(User user,
                              HttpServletRequest req,
                              Language lang) {
        LocalizedLevel level = userService.getLevelByUserId(user.getUserId(), lang);
        req.getSession().setAttribute("currentLocalizedLevel", level);
    }

    /**
     * Prepares current user's achievements.
     */
    private void prepareAchievements(User user,
                                     HttpServletRequest req,
                                     Language lang) {
        List<Achievement> achievements = achService.findAllAchForUser(
                user.getUserId(), lang);
        req.getSession().setAttribute("currentUserAch", achievements);
    }

    /**
     * Prepares current user's tasks overall progression.
     */
    private void prepareProgression(User user,
                                    HttpServletRequest req,
                                    Language lang) {
        var countedSolvedAndAll = taskService
                .countProgression(user.getUserId(), lang);
        req.getSession().setAttribute("currentCountedTasks", countedSolvedAndAll);
    }
}
