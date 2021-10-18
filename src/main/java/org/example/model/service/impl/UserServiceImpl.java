package org.example.model.service.impl;

import org.example.model.dao.UserDao;
import org.example.model.dao.impl.UserDaoImpl;
import org.example.model.entity.User;
import org.example.model.entity.enumeration.Language;
import org.example.model.entity.localization.LocalizedLevel;
import org.example.model.service.UserService;
import org.example.model.util.statement.impl.UserStatementUtil;

import java.util.List;
import java.util.Optional;

import static org.example.model.service.impl.SqlQueries.*;

/**
 * An implementation of the {@link UserService}.
 */
public enum UserServiceImpl implements UserService {

    INSTANCE;

    private final UserDao userDao;

    UserServiceImpl() {
        userDao = new UserDaoImpl(new UserStatementUtil());
    }

    /**{@inheritDoc}*/
    @Override
    public Optional<User> findById(Long id, Language lang) {
        return userDao.find(LOCALE_USER_FIND_BY_ID, lang, List.of(id));
    }

    /**{@inheritDoc}*/
    @Override
    public List<User> findAll(Language lang) {
        return userDao.findAll(LOCALE_USER_FIND_ALL, lang);
    }

    /**{@inheritDoc}*/
    @Override
    public boolean save(User entity) {
        return userDao.save(USER_INSERT, entity);
    }

    /**{@inheritDoc}*/
    @Override
    public Optional<User> findByLogin(String login, Language lang) {
        return userDao.findByLogin(USER_SELECT_BY_LOGIN, lang, login);
    }

    /**{@inheritDoc}*/
    @Override
    public boolean earnExpAndLevel(User entity) {
        return userDao.save(USER_UPDATE_EXP_AND_LEVEL, List.of(entity.getExperience(),
                entity.getLevel(), entity.getUserId()));
    }

    /**{@inheritDoc}*/
    @Override
    public boolean earnAchievement(Long userId, Long achId) {
        return userDao.save(US_ACH_INSERT, List.of(userId, achId));
    }

    /**{@inheritDoc}*/
    @Override
    public LocalizedLevel getLevelByUserId(Long userId, Language lang) {
        return userDao.getLevelByUserId(LOCALE_FIND_LEVEL_BY_USER_ID, lang, userId);
    }

    /**{@inheritDoc}*/
    @Override
    public long countSolvedForUser(Long userId) {
        return userDao.countSolvedForUser(USER_COUNT_SOLVED_BY_ID, userId);
    }

    /**{@inheritDoc}*/
    @Override
    public boolean deleteImageById(Long userId) {
        return userDao.save(USER_DELETE_IMAGE_BY_ID, List.of(userId));
    }

    /**{@inheritDoc}*/
    @Override
    public boolean uploadImageById(String picture, Long userId) {
        return userDao.save(USER_INSERT_IMAGE_BY_ID, List.of(picture, userId));
    }
}