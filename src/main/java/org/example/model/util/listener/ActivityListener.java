package org.example.model.util.listener;

import jakarta.servlet.ServletException;
import org.example.model.entity.Task;
import org.example.model.entity.User;
import org.example.model.util.helper.RequestHelper;

/**
 * Class that resolves level, experience and
 * achievements as the result of an user's
 * activity.
 */
public interface ActivityListener {

    /**
     * After successful solution of the given task this method will
     * resolve final experience and level gain if possible
     * for the given user.
     *
     * @param helper the distributor of data from the servlet side
     * @param user a user to apply changes to
     * @param task a solved task
     *
     * @throws ServletException if helper was corrupted
     */
    void resolveLevelAndExp(RequestHelper helper, User user, Task task)
            throws ServletException;

    /**
     * After successful solution of the given task this method will
     * resolve achievements if possible for the given user.
     *
     * @param helper the distributor of data from the servlet side
     * @param user a user to apply changes to
     *
     * @throws ServletException if helper was corrupted
     */
    void resolveAchievements(RequestHelper helper, User user)
            throws ServletException;
}
