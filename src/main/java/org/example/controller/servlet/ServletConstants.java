package org.example.controller.servlet;

/**
 * Listing of constants used primarily in
 * servlets and their handlers.
 */
public final class ServletConstants {

    private ServletConstants() {}

    /* Session ServletConstants */
    public static final String SESSION_CURRENT_USER = "currentUser";
    public static final String SESSION_CURRENT_TASK = "currentTask";
    public static final String SESSION_LANGUAGE = "currentLang";
    public static final String SESSION_REFERRER = "currentReferrerCT";
    public static final String SESSION_CHOSEN_CATEGORY = "currentChosenCategory";
    public static final String SESSION_LOCALIZED_LEVEL = "currentLocalizedLevel";
    public static final String SESSION_USERS_ACH = "currentUserAch";

    /* Request ServletConstants */
    public static final String REQUEST_TASK = "task";
    public static final String REQUEST_ANSWER = "answer";
    public static final String REQUEST_ALERT = "taskAlert";

    /* Parameter ServletConstants */
    public static final String PARAM_CATEGORY = "category";
    public static final String PARAM_LOGIN = "login";
    public static final String PARAM_PASSWORD = "password";
    public static final String PARAM_EMAIL = "email";
    public static final String PARAM_ALERT = "alert";
    public static final String PARAM_DELETE_IMAGE = "deleteImage";
    public static final String PARAM_PART_IMAGE = "uploadedPicture";
}
