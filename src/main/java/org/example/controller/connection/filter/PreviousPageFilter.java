package org.example.controller.connection.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.example.model.entity.enumeration.CommandType;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Memorizes the most recent called servlet and stores
 * its referrer to be accessed on demand.
 */
@WebFilter("*")
public class PreviousPageFilter implements Filter {

    private static final String REGEX_MATCH_URI = ".+/(\\w+)";

    private static final String FORBIDDEN_AUTH = CommandType.GOTO_AUTHORIZATION.getReferrer();
    private static final String FORBIDDEN_REG = CommandType.GOTO_REGISTRATION.getReferrer();

    /**
     * Performs actions on resolving and saving information
     * about last addressed servlet.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        var referrers = CommandType.getReferrers();
        String header = req.getHeader("referer");
        String uri = "/";

        if (header != null) {
            Matcher matcher = Pattern.compile(REGEX_MATCH_URI).matcher(header);

            if (matcher.find()) {
                uri = uri.concat(matcher.group(1));
            }
        }

        if (referrers.containsKey(uri) && !uri.equals(FORBIDDEN_AUTH)
                && !uri.equals(FORBIDDEN_REG)) {

            req.getSession().setAttribute(
                    "currentReferrerCT", CommandType.getByReferrer(uri));
        }
        chain.doFilter(req, response);
    }
}
