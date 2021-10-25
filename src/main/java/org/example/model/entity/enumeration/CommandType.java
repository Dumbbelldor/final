package org.example.model.entity.enumeration;

import java.util.HashMap;
import java.util.Map;

/**
 * Used in conjunction with the {@link org.example.model.util.helper.RequestHelper}
 * to provide destination points for navigation.
 */
public enum CommandType {

    GOTO_HOME("/index.jsp", "/"),
    GOTO_AUTHORIZATION("/WEB-INF/views/authorization.jsp", "/authorization"),
    GOTO_REGISTRATION("/WEB-INF/views/registration.jsp", "/registration"),
    GOTO_SPEC_CATEGORY("/WEB-INF/views/spec_category.jsp", "/category"),
    GOTO_TASK("/WEB-INF/views/task.jsp", "/task"),
    GOTO_PROFILE("/WEB-INF/views/profile.jsp", "/profile"),
    GOTO_GEN_CATEGORIES("/WEB-INF/views/categories.jsp", "/categories"),
    GOTO_INFO_PAGE("/WEB-INF/views/about.jsp", "/about");

    private static final Map<String, CommandType> map;

    static {
        map = new HashMap<>();
        for (var elem: CommandType.values()) {
            map.put(elem.referrer, elem);
        }
    }

    private final String address;
    private final String referrer;

    CommandType(String address, String referrer) {
        this.address = address;
        this.referrer = referrer;
    }

    public String getAddress() {
        return address;
    }

    public String getReferrer() {
        return referrer;
    }

    /**
     * Returns the enum constant by its referrer,
     * otherwise returns {@link #GOTO_HOME}.
     *
     * @param referrer a referrer
     *
     * @return the enum constant
     */
    public static CommandType getByReferrer(String referrer) {
        return map.getOrDefault(referrer, CommandType.GOTO_HOME);
    }

    /**
     * Returns a {@link Map} with all available referrers
     * and corresponding enum constants.
     */
    public static Map<String, CommandType> getReferrers() {
        return map;
    }
}
