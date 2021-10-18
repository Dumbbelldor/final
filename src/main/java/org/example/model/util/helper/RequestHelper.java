package org.example.model.util.helper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.entity.enumeration.CommandType;

import java.io.IOException;

import static org.example.model.util.helper.HelperConstants.*;
import static org.example.controller.servlet.ServletConstants.*;

/**
 * <p>Class-wrapper that handles all mundane work
 * within custom servlets. Contains methods that
 * wrap relocation, fetching attributes actions.</p>
 *
 * <p>Needs to be initialized with
 * {@link RequestHelper#init(HttpServletRequest, HttpServletResponse)}
 * method every time before getting started, or else
 * {@link ServletException} will be thrown.</p>
 */
public enum RequestHelper {

    INSTANCE;

    private HttpServletResponse resp;
    private HttpServletRequest req;

    /**
     * Initializes {@link HttpServletRequest} and
     * {@link HttpServletResponse} to be able to
     * function.
     *
     * @param req a servlet's request
     * @param resp a servlet's response
     */
    public void init(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
    }

    /**
     * Wraps {@code forward} command and performs dispatching
     * to the {@link CommandType} destination.
     *
     * @param commandType an enum type destination
     *
     * @throws ServletException when a request hasn't been initialized
     */
    public void dispatch(CommandType commandType) throws ServletException, IOException {
        if(req == null) {
            throw new ServletException(INIT_ERROR);
        }

        req.getRequestDispatcher(commandType.getAddress()).forward(req, resp);
    }

    /**
     * Wraps {@link HttpServletResponse#sendRedirect(String)} command
     * and performs redirect action to the {@link CommandType} destination.
     *
     * @param commandType an enum type destination
     *
     * @throws ServletException when a request hasn't been initialized
     */
    public void redirect(CommandType commandType) throws IOException, ServletException {
        if(req == null) {
            throw new ServletException(INIT_ERROR);
        }

        resp.sendRedirect(commandType.getReferrer());
    }

    /**
     * Redirects {@link #redirect(CommandType)} to a previously
     * visited page.
     */
    public void redirectWithReferrer() throws IOException, ServletException {
        CommandType commandType = (CommandType) getSessionAttribute(SESSION_REFERRER);
        if (commandType == null) {
            setSessionAttribute(SESSION_REFERRER, CommandType.GOTO_HOME);
            redirect((CommandType) getSessionAttribute(SESSION_REFERRER));
        } else {
            redirect(commandType);
        }
    }

    /**
     * Wraps an action of getting a session attribute.
     *
     * @param name a session attribute's name
     *
     * @return a stored object if present, otherwise null
     *
     * @throws ServletException when a request hasn't been initialized
     */
    public Object getSessionAttribute(String name) throws ServletException {
        if(req == null) {
            throw new ServletException(INIT_ERROR);
        }

        return req.getSession().getAttribute(name);
    }

    /**
     * Wraps an action of setting up a session attribute.
     *
     * @param <T> any object type
     * @param name a session attribute's name
     * @param object any object to be set
     *
     * @throws ServletException when a request hasn't been initialized
     */
    public <T> void setSessionAttribute(String name, T object) throws ServletException {
        if(req == null) {
            throw new ServletException(INIT_ERROR);
        }

        req.getSession().setAttribute(name, object);
    }

    /**
     * Wraps an action of getting a request parameter.
     *
     * @param name a session attribute's name
     *
     * @return a stored object if present, otherwise null
     *
     * @throws ServletException when a request hasn't been initialized
     */
    public String getParameter(String name) throws ServletException {
        if(req == null) {
            throw new ServletException(INIT_ERROR);
        }

        return req.getParameter(name);
    }
}