package org.example.model.util.helper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.entity.enumeration.Destination;

import java.io.IOException;

/**
 * <p>Interface-wrapper that handles all mundane work
 * within custom servlets. Contains methods that
 * wrap relocation, fetching attributes actions.</p>
 *
 * <p>Needs to be initialized with
 * {@link RequestHelper#init(HttpServletRequest, HttpServletResponse)}
 * method every time before getting started, or else
 * {@link ServletException} will be thrown.</p>
 */
public interface RequestHelper {

    /**
     * Initializes {@link HttpServletRequest} and
     * {@link HttpServletResponse} to be able to
     * function.
     *
     * @param req a servlet's request
     * @param resp a servlet's response
     */
    void init(HttpServletRequest req, HttpServletResponse resp);

    /**
     * Wraps {@code forward} command and performs dispatching
     * to the {@link Destination} destination.
     *
     * @param destination an enum type destination
     *
     * @throws ServletException when a request hasn't been initialized
     */
    void dispatch(Destination destination) throws ServletException, IOException;

    /**
     * Wraps {@link HttpServletResponse#sendRedirect(String)} command
     * and performs redirect action to the {@link Destination} destination.
     *
     * @param destination an enum type destination
     *
     * @throws ServletException when a request hasn't been initialized
     */
    void redirect(Destination destination) throws IOException, ServletException;

    /**
     * Redirects {@link #redirect(Destination)} to a previously
     * visited page.
     */
    void redirectWithReferrer() throws IOException, ServletException;

    /**
     * Wraps an action of getting a session attribute.
     *
     * @param name a session attribute's name
     *
     * @return a stored object if present, otherwise null
     *
     * @throws ServletException when a request hasn't been initialized
     */
    Object getSessionAttribute(String name) throws ServletException;

    /**
     * Wraps an action of setting up a session attribute.
     *
     * @param <T> any object type
     * @param name a session attribute's name
     * @param object any object to be set
     *
     * @throws ServletException when a request hasn't been initialized
     */
    <T> void setSessionAttribute(String name, T object) throws ServletException;

    /**
     * Wraps an action of getting a request parameter.
     *
     * @param name a session attribute's name
     *
     * @return a stored object if present, otherwise null
     *
     * @throws ServletException when a request hasn't been initialized
     */
    String getParameter(String name) throws ServletException;
}
