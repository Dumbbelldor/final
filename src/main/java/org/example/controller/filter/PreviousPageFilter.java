package org.example.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.example.model.entity.enumeration.Destination;
import org.example.model.util.cleaner.InputCleaner;
import org.example.model.util.cleaner.impl.InputCleanerImpl;

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

    private static final String HEADER_NAME = "referer";

    private static final int GROUP_WITH_MATCHED_URI = 1;

    private static final InputCleaner cleaner = InputCleanerImpl.INSTANCE;

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
            header = cleaner.cleanse(header);
            Matcher matcher = Pattern.compile(REGEX_MATCH_URI).matcher(header);

            if (matcher.find()) {
                uri = uri.concat(matcher.group(GROUP_WITH_MATCHED_URI));
            }
        }

        if (referrers.containsKey(uri)) {
            Destination destination = Destination.getByReferrer(uri);
            switch (destination) {
                case GOTO_AUTHORIZATION, GOTO_REGISTRATION -> {}
                default -> req.getSession().setAttribute(
                        "currentReferrerCT", destination);
            }
        }

        chain.doFilter(req, response);
    }
}
