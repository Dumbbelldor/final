package org.example.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.entity.enumeration.Destination;
import org.example.model.util.helper.RequestHelper;
import org.example.model.util.helper.impl.RequestHelperImpl;
import org.example.model.entity.User;
import org.example.model.util.cleaner.InputCleaner;
import org.example.model.util.cleaner.impl.InputCleanerImpl;
import org.example.model.service.UserService;
import org.example.model.service.impl.UserServiceImpl;
import org.example.model.validation.Validator;
import org.example.model.validation.impl.EmailValidator;

import java.io.IOException;

import static org.example.controller.servlet.ServletConstants.*;

/**
 * Servlet that handles all registration matters.
 */
@WebServlet(name = "user_registration", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    private static final RequestHelper helper = RequestHelperImpl.INSTANCE;
    private static final InputCleaner cleaner = InputCleanerImpl.INSTANCE;
    private static final UserService service = UserServiceImpl.INSTANCE;
    private static final Validator validator = EmailValidator.INSTANCE;

    /**
     * If user is not authorized sends him to registration page,
     * or else restrict an access to it.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        helper.init(req, resp);
        User user = (User) helper.getSessionAttribute(SESSION_CURRENT_USER);
        if (user == null) {
            helper.dispatch(Destination.GOTO_REGISTRATION);
        } else {
            helper.redirectWithReferrer();
        }
    }

    /**
     * <p>Creates an user with the given input parameters.</p>
     *
     * <p>If user sends corrupted data compels him to retry.</p>
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        helper.init(req, resp);

        String login = helper.getParameter(PARAM_LOGIN);
        String password = helper.getParameter(PARAM_PASSWORD);
        String email = helper.getParameter(PARAM_EMAIL);

        if (login != null && password != null && email != null) {
            login = cleaner.cleanse(login);
            password = cleaner.cleanse(password);
            email = cleaner.cleanse(email);

            if (validator.validate(email)) {

                User user = User.newBuilder()
                        .setLogin(login)
                        .setEmail(email)
                        .setPassword(password)
                        .build();

                service.save(user);
                helper.setSessionAttribute(SESSION_CURRENT_USER, user);
                helper.redirectWithReferrer();
            } else {
                helper.dispatch(Destination.GOTO_REGISTRATION);
            }

        } else {
            helper.dispatch(Destination.GOTO_REGISTRATION);
        }
    }
}
