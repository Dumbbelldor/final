package org.example.model.util.helper.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.entity.enumeration.Destination;
import org.example.model.util.helper.RequestHelper;

import java.io.IOException;

import static org.example.controller.servlet.ServletConstants.*;

/**
 * An implementation of {@link RequestHelper} interface.
 */
public enum RequestHelperImpl implements RequestHelper {

    INSTANCE;

    private static final String INIT_ERROR = "Initialize helper before invoking its methods";

    private HttpServletResponse resp;
    private HttpServletRequest req;

    /**{@inheritDoc}*/
    @Override
    public void init(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
    }

    /**{@inheritDoc}*/
    @Override
    public void dispatch(Destination destination) throws ServletException, IOException {
        if(req == null) {
            throw new ServletException(INIT_ERROR);
        }

        req.getRequestDispatcher(destination.getAddress()).forward(req, resp);
    }

    /**{@inheritDoc}*/
    @Override
    public void redirect(Destination destination) throws IOException, ServletException {
        if(req == null) {
            throw new ServletException(INIT_ERROR);
        }

        resp.sendRedirect(destination.getReferrer());
    }

    /**{@inheritDoc}*/
    @Override
    public void redirectWithReferrer() throws IOException, ServletException {
        Destination destination = (Destination) getSessionAttribute(SESSION_REFERRER);
        if (destination == null) {
            setSessionAttribute(SESSION_REFERRER, Destination.GOTO_HOME);
            destination = (Destination) getSessionAttribute(SESSION_REFERRER);
        }
        redirect(destination);
    }

    /**{@inheritDoc}*/
    @Override
    public Object getSessionAttribute(String name) throws ServletException {
        if(req == null) {
            throw new ServletException(INIT_ERROR);
        }

        return req.getSession().getAttribute(name);
    }

    /**{@inheritDoc}*/
    @Override
    public <T> void setSessionAttribute(String name, T object) throws ServletException {
        if(req == null) {
            throw new ServletException(INIT_ERROR);
        }

        req.getSession().setAttribute(name, object);
    }

    /**{@inheritDoc}*/
    @Override
    public String getParameter(String name) throws ServletException {
        if(req == null) {
            throw new ServletException(INIT_ERROR);
        }

        return req.getParameter(name);
    }
}