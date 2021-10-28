package org.example.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.entity.enumeration.Destination;
import org.example.model.util.helper.RequestHelper;
import org.example.model.util.helper.impl.RequestHelperImpl;

import java.io.IOException;

import static org.example.controller.servlet.ServletConstants.*;

/**
 * Servlet that receives and handles commands
 * from the header navigation bar.
 */
@WebServlet(urlPatterns = "/header_nav")
public class HeaderServlet extends HttpServlet {

    private static final RequestHelper helper = RequestHelperImpl.INSTANCE;

    /**
     * Register changes made by users by pressing
     * buttons on the navbar.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        helper.init(req, resp);
        String lang = req.getParameter("lang");
        String exit = req.getParameter("exit");

        if (lang != null) {
            req.getSession().setAttribute("currentLang", lang);
            helper.redirectWithReferrer();
        }
        if (exit != null) {
            req.getSession().setAttribute(SESSION_CURRENT_USER, null);
//            req.getSession().invalidate();
            helper.redirect(Destination.GOTO_HOME);
        }
    }
}
