package org.example.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.example.model.entity.enumeration.Language;

import java.io.IOException;
import java.util.Locale;

import static org.example.controller.servlet.ServletConstants.*;

/**
 * This filter tracks requests to exclude erroneous
 * situations with a localization displaying.
 */
@WebFilter("*")
public class LanguageFilter implements Filter {

    /**
     * Supervises that language attribute always presents.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String language = (String) req.getSession().getAttribute(SESSION_LANGUAGE);

        if (language == null) {
            Language locale = Language.getByLocale(
                    Locale.getDefault().getLanguage());
            req.getSession().setAttribute(SESSION_LANGUAGE, locale.getLocale());
        }

        chain.doFilter(req, response);
    }
}
