package org.example.model.dao.impl;

import org.example.model.dao.AchDao;
import org.example.model.entity.Achievement;
import org.example.model.entity.enumeration.Language;
import org.example.model.util.statement.StatementUtil;

import java.util.List;

public class AchDaoImpl extends BaseDaoImpl<Achievement> implements AchDao {

    public AchDaoImpl(StatementUtil<Achievement> utils) {
        super(utils);
    }

    @Override
    public boolean uploadImageByAchId(String sql, String filePath, Long id) {
        return save(sql, List.of(filePath, id));
    }

    @Override
    public List<Achievement> findUnclaimedAchForUser(String sql,
                                                     Language lang,
                                                     Long userId) {
        return findAll(sql, lang, List.of(userId));
    }
}
