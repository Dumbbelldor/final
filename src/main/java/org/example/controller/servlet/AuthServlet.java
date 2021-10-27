package org.example.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.entity.enumeration.Destination;
import org.example.model.entity.enumeration.Language;
import org.example.model.util.helper.RequestHelper;
import org.example.model.util.helper.impl.RequestHelperImpl;
import org.example.model.entity.User;
import org.example.model.util.cleaner.InputCleaner;
import org.example.model.util.cleaner.impl.InputCleanerImpl;
import org.example.model.service.UserService;
import org.example.model.service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.Optional;

import static org.example.controller.servlet.ServletConstants.*;

/**
 * Servlet that handles user authorization
 * processes.
 */
@WebServlet(name = "auth", urlPatterns = "/authorization")
public class AuthServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger();

    private static final RequestHelper helper = RequestHelperImpl.INSTANCE;
    private static final InputCleaner cleaner = InputCleanerImpl.INSTANCE;
    private static final UserService service = UserServiceImpl.INSTANCE;

    /**
     * If the user is not authorized sends him to the authorization page,
     * otherwise forbids an access to it.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        helper.init(req, resp);
        User user = (User) helper.getSessionAttribute(SESSION_CURRENT_USER);
        if (user == null) {
            helper.dispatch(Destination.GOTO_AUTHORIZATION);
        } else {
            helper.redirectWithReferrer();
        }
    }

    /**
     * Authenticate user's inputs: if credentials are correct the user
     * is considered authorized, or else will be compelled to try again.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        helper.init(req, resp);
        Language locale = Language.getByLocale(
                (String) helper.getSessionAttribute(SESSION_LANGUAGE));

        String login = helper.getParameter(PARAM_LOGIN);
        String password = helper.getParameter(PARAM_PASSWORD);
        if (login != null || password != null) {
            login = cleaner.cleanse(login);
            password = cleaner.cleanse(password);
        } else {
            log.warn("Attempted to pass null as the parameter");
            helper.redirect(Destination.GOTO_AUTHORIZATION);
        }

        Optional<User> opt = service.findByLogin(login, locale);
        if (opt.isPresent()) {
            User user = opt.get();

            /* Without using helper due to jsp attributes visibility breaking */
            /* Single occurrence of the string representation of attribute "currentUser" */
            if (user.getPassword().equals(password)) {
                req.getSession().setAttribute("currentUser", user);
                helper.redirectWithReferrer();
            } else {
                helper.redirect(Destination.GOTO_AUTHORIZATION);
            }

        } else {
            helper.redirect(Destination.GOTO_REGISTRATION);
        }
    }
}
