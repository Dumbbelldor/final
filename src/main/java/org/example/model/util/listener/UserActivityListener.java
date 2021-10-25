package org.example.model.util.listener;

import jakarta.servlet.ServletException;
import org.example.model.entity.Achievement;
import org.example.model.entity.Task;
import org.example.model.entity.User;
import org.example.model.entity.enumeration.Language;
import org.example.model.entity.enumeration.Level;
import org.example.model.entity.localization.LocalizedLevel;
import org.example.model.service.AchService;
import org.example.model.service.UserService;
import org.example.model.service.impl.AchServiceImpl;
import org.example.model.service.impl.UserServiceImpl;
import org.example.model.util.helper.RequestHelper;

import java.util.List;

import static org.example.controller.servlet.ServletConstants.*;
import static org.example.model.util.listener.UserListenerConstants.*;

/**
 * Class that resolves level, experience and
 * achievements as the result of an user's
 * activity.
 */
public enum UserActivityListener {

    INSTANCE;

    private static final UserService userService = UserServiceImpl.INSTANCE;
    private static final AchService achService = AchServiceImpl.INSTANCE;

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
    public void resolveLevelAndExp(RequestHelper helper, User user, Task task)
            throws ServletException {

        LocalizedLevel level = getLocalizedLevel(helper, user);
        Level nextLevel = level.level();

        int currentUserExp = user.getExperience();
        int rewardExp = task.getExperience();
        int expForLvlUp = level.exp();
        int resultExp = currentUserExp + rewardExp;
        int remainder = ZERO;

        if (resultExp >= expForLvlUp) {
            remainder = resultExp - expForLvlUp;
            nextLevel = Level.next(nextLevel);
            resultExp = ZERO;
        }

        User updated = User.newBuilder()
                .setUserId(user.getUserId())
                .setExperience(resultExp + remainder)
                .setLevel(nextLevel.name())
                .build();

        userService.earnExpAndLevel(updated);
    }

    /**
     * After successful solution of the given task this method will
     * resolve achievements if possible for the given user.
     *
     * @param helper the distributor of data from the servlet side
     * @param user a user to apply changes to
     *
     * @throws ServletException if helper was corrupted
     */
    public void resolveAchievements(RequestHelper helper, User user)
            throws ServletException {

        List<Achievement> unclaimed = achService
                .findUnclaimedForUser(user.getUserId(), Language.ENGLISH);

        long solvedTasks = userService.countSolvedForUser(user.getUserId());

        LocalizedLevel level = getLocalizedLevel(helper, user);
        Level levelConstant = level.level();

        for (var elem: unclaimed) {
            long currentAchId = elem.getAchId();
            switch ((int) currentAchId) {
                case ACH_ID_REACH_LEVEL_5, ACH_ID_REACH_LEVEL_10, ACH_ID_REACH_LEVEL_15 -> {

                    switch (levelConstant) {
                        case INITIATE, RISING_STAR, GOD_OF_SCIENCE ->
                                userService.earnAchievement(user.getUserId(), currentAchId);
                        default -> {}
                    }
                }
                case ACH_ID_SOLVE_10_TASKS -> {
                    if (solvedTasks >= QUANTITY_OF_TASKS_10) {
                        userService.earnAchievement(user.getUserId(), currentAchId);
                    }
                }
                case ACH_ID_SOLVE_50_TASKS -> {
                    if (solvedTasks >= QUANTITY_OF_TASKS_50) {
                        userService.earnAchievement(user.getUserId(), currentAchId);
                    }
                }
                case ACH_ID_SOLVE_100_TASKS -> {
                    if (solvedTasks >= QUANTITY_OF_TASKS_100) {
                        userService.earnAchievement(user.getUserId(), currentAchId);
                    }
                }
                default -> {}
            }
        }
    }

    private LocalizedLevel getLocalizedLevel(RequestHelper helper,
                                             User user) throws ServletException {
        LocalizedLevel level = (LocalizedLevel) helper
                .getSessionAttribute(SESSION_LOCALIZED_LEVEL);
        if (level == null) {
            level = userService.getLevelByUserId(
                    user.getUserId(), Language.ENGLISH);
            helper.setSessionAttribute(SESSION_LOCALIZED_LEVEL, level);
        }
        return level;
    }
}
