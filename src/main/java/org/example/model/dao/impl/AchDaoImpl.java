package org.example.model.dao.impl;

import org.example.model.dao.AchDao;
import org.example.model.entity.Achievement;
import org.example.model.entity.enumeration.Language;
import org.example.model.util.statement.StatementUtil;

import java.util.List;

/**
 * An implementation of the {@link AchDao} interface.
 */
public class AchDaoImpl extends BaseDaoImpl<Achievement> implements AchDao {

    public AchDaoImpl(StatementUtil<Achievement> utils) {
        super(utils);
    }

    /**{@inheritDoc}*/
    @Override
    public List<Achievement> findUnclaimedAchForUser(String sql,
                                                     Language lang,
                                                     Long userId) {
        return findAll(sql, lang, List.of(userId));
    }
}
