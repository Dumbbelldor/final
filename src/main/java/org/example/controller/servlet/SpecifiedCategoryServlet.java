package org.example.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.entity.enumeration.Category;
import org.example.model.entity.enumeration.CommandType;
import org.example.model.entity.enumeration.Language;
import org.example.model.entity.wrapper.StatusTaskWrapper;
import org.example.model.util.helper.RequestHelper;
import org.example.model.entity.User;
import org.example.model.security.InputCleaner;
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

        if (user != null) {

            String name = cleaner.cleanse(helper.getParameter(PARAM_CATEGORY));
            Category category = null;
            try {

                if (name != null) {
                    category = Category.valueOf(name.toUpperCase());
                }

                Language locale = Language.getByLocale(
                        (String) helper.getSessionAttribute(SESSION_LANGUAGE));
                List<StatusTaskWrapper> tasks = service
                        .resolveTasksForUserByCategory(user.getUserId(),
                                locale, category);
                String localizedCategory = tasks.get(0).task().getCategory();

                /* Without using helper due to jsp attributes visibility breaking */
                /* Single occurrence of the string representation of attributes
                 "currentCategory" and "currentTasks" */
                req.getSession().setAttribute("currentCategory", localizedCategory);
                req.getSession().setAttribute("currentTasks", tasks);

                helper.dispatch(CommandType.GOTO_SPEC_CATEGORY);
            } catch (IllegalArgumentException e) {
                helper.redirect(CommandType.GOTO_GEN_CATEGORIES);
            }

        } else {
            helper.redirect(CommandType.GOTO_AUTHORIZATION);
        }
    }
}
