package org.example.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.example.model.entity.enumeration.Destination;

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

    private static final String FORBIDDEN_AUTH = Destination.GOTO_AUTHORIZATION.getReferrer();
    private static final String FORBIDDEN_REG = Destination.GOTO_REGISTRATION.getReferrer();
    private static final String HEADER_NAME = "referer";

    private static final int GROUP_WITH_MATCHED_URI = 1;

    /**
     * Performs actions on resolving and saving information
     * about last addressed servlet.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        var referrers = Destination.getReferrers();
        String header = req.getHeader(HEADER_NAME);
        String uri = "/";

        if (header != null) {
            Matcher matcher = Pattern.compile(REGEX_MATCH_URI).matcher(header);

            if (matcher.find()) {
                uri = uri.concat(matcher.group(GROUP_WITH_MATCHED_URI));
            }
        }

        if (referrers.containsKey(uri) && !uri.equals(FORBIDDEN_AUTH)
                && !uri.equals(FORBIDDEN_REG)) {

            req.getSession().setAttribute(
                    "currentReferrerCT", Destination.getByReferrer(uri));
        }
        chain.doFilter(req, response);
    }
}
