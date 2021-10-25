package org.example.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.entity.enumeration.Destination;
import org.example.model.util.helper.RequestHelper;

import java.io.IOException;

/**
 * Servlet that simply displays static info page.
 */
@WebServlet(name = "info", urlPatterns = "/about")
public class InfoPageServlet extends HttpServlet {

    private static final RequestHelper helper = RequestHelper.INSTANCE;

    /**
     * Loads and shows info page.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        helper.init(req, resp);
        helper.dispatch(Destination.GOTO_INFO_PAGE);
    }
}
