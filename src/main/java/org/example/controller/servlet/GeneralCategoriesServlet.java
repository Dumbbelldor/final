package org.example.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.entity.enumeration.Destination;
import org.example.model.entity.enumeration.Language;
import org.example.model.entity.localization.LocalizedCategory;
import org.example.model.service.TaskService;
import org.example.model.service.impl.TaskServiceImpl;
import org.example.model.util.helper.RequestHelper;

import java.io.IOException;
import java.util.List;

import static org.example.controller.servlet.ServletConstants.SESSION_LANGUAGE;

/**
 * Servlet that prepares and displays all
 * available task categories.
 */
@WebServlet(name = "categories", urlPatterns = "/categories")
public class GeneralCategoriesServlet extends HttpServlet {

    private static final TaskService service = TaskServiceImpl.INSTANCE;
    private static final RequestHelper helper = RequestHelper.INSTANCE;

    /**
     * Displays a localized general categories page.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        helper.init(req, resp);

        Language locale = Language.getByLocale(
                (String) helper.getSessionAttribute(SESSION_LANGUAGE));
        List<LocalizedCategory> categories = service.findAllTaskCategories(
                locale);

        /* Without using helper due to jsp attributes visibility breaking */
        /* Single occurrence of the string representation of attribute "allCategories" */
        req.setAttribute("allCategories", categories);
        helper.dispatch(Destination.GOTO_GEN_CATEGORIES);
    }
}