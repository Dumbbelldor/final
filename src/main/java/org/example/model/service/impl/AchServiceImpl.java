package org.example.model.service.impl;

import org.example.model.dao.AchDao;
import org.example.model.dao.impl.AchDaoImpl;
import org.example.model.entity.Achievement;
import org.example.model.entity.enumeration.Language;
import org.example.model.service.AchService;
import org.example.model.util.statement.impl.AchStatementUtil;

import java.util.List;
import java.util.Optional;

import static org.example.model.service.impl.SqlQueries.*;

/**
 * An implementation of the {@link AchService} interface.
 */
public enum AchServiceImpl implements AchService {

    INSTANCE;

    private final AchDao achDao;

    AchServiceImpl() {
        achDao = new AchDaoImpl(new AchStatementUtil());
    }

    /**{@inheritDoc}*/
    @Override
    public Optional<Achievement> findById(Long id, Language lang) {
        return achDao.find(LOCALE_ACH_FIND_BY_ID, lang, List.of(id));
    }

    /**{@inheritDoc}*/
    @Override
    public List<Achievement> findAll(Language lang) {
        return achDao.findAll(LOCALE_ACH_FIND_ALL, lang);
    }

    /**{@inheritDoc}*/
    @Override
    public boolean save(Achievement entity, Language lang) {
        return achDao.save(LOCALE_ACH_SAVE, lang, entity);
    }

    /**{@inheritDoc}*/
    @Override
    public List<Achievement> findAllAchForUser(Long userId, Language lang) {
        return achDao.findAll(LOCALE_US_ACH_FIND_BY_USER_ID, lang, List.of(userId));
    }

    /**{@inheritDoc}*/
    @Override
    public List<Achievement> findUnclaimedForUser(Long userId, Language lang) {
        return achDao.findUnclaimedAchForUser(LOCALE_ACH_FIND_UNCLAIMED, lang, userId);
    }
}
