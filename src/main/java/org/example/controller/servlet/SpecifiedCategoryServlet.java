package org.example.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.entity.enumeration.Category;
import org.example.model.entity.enumeration.CommandType;
import org.example.model.entity.enumeration.Language;
import org.example.model.entity.wrapper.StatusTaskWrapper;
import org.example.model.util.helper.RequestHelper;
import org.example.model.entity.User;
import org.example.model.util.security.InputCleaner;
import org.example.model.service.TaskService;
import org.example.model.service.impl.TaskServiceImpl;

import java.io.IOException;
import java.util.List;

import static org.example.controller.servlet.ServletConstants.*;

/**
 * Servlet that prepares and displays tasks of the specified category.
 */
@WebServlet(name = "specified_category", urlPatterns = "/category")
public class SpecifiedCategoryServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger();

    private static final RequestHelper helper = RequestHelper.INSTANCE;
    private static final InputCleaner cleaner = InputCleaner.INSTANCE;
    private static final TaskService service = TaskServiceImpl.INSTANCE;

    /**
     * Checks the given category parameter and if it is valid
     * fills with content and displays specified category page,
     * or else sends back to the general categories page.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        helper.init(req, resp);

        User user = (User) helper.getSessionAttribute(SESSION_CURRENT_USER);
        String categoryName = helper.getParameter(PARAM_CATEGORY);

        if (categoryName != null) {
            try {
                String name = cleaner.cleanse(categoryName);
                Category category = Category.valueOf(name.toUpperCase());
                req.getSession().setAttribute("currentCategory", category);

                preparePage(user, category, req);

            } catch(IllegalArgumentException e) {
                log.error("Attempted to pass unsupported category name", e);
                helper.redirect(CommandType.GOTO_GEN_CATEGORIES);
            }
        } else {
            Category category = (Category) helper.getSessionAttribute(SESSION_CATEGORY);

            if (category != null) {
                preparePage(user, category, req);
            } else {
                helper.redirect(CommandType.GOTO_GEN_CATEGORIES);
            }
        }
    }

    private void preparePage(User user, Category category,
                             HttpServletRequest req)
            throws ServletException, IOException {
        if (user != null) {

            Language locale = Language.getByLocale(
                    (String) helper.getSessionAttribute(SESSION_LANGUAGE));
            List<StatusTaskWrapper> tasks = service
                    .resolveTasksForUserByCategory(user.getUserId(),
                            locale, category);
            String localizedCategory = tasks.get(0).task().getCategory();

            /* Without using helper due to jsp attributes visibility breaking */
                    /* Single occurrence of the string representation of attributes
                     "currentCategory" and "currentTasks" */
            req.getSession().setAttribute("currentLocalizedCategory", localizedCategory);
            req.getSession().setAttribute("currentTasks", tasks);

            helper.dispatch(CommandType.GOTO_SPEC_CATEGORY);

        } else {
            log.info(LOG_UNAUTHORIZED_ACCESS);
            helper.redirect(CommandType.GOTO_AUTHORIZATION);
        }
    }
}
