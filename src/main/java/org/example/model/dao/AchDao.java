package org.example.model.dao;

import org.example.model.entity.Achievement;
import org.example.model.entity.enumeration.Language;

import java.util.List;

/**
 * Extension interface for {@link BaseDao} containing
 * methods to work with Achievement table.
 */
public interface AchDao extends BaseDao<Achievement> {

    boolean uploadImageByAchId(String sql, String filePath, Long id);

    /**
     * Retrieves a {@link List} of {@link Achievement} from a database with
     * the given SQL command and the localization parameter by the userId.
     *
     * @param sql the special SQL string
     * @param lang an enum constant defining chosen locale
     * @param userId a user id
     *
     * @return a localized entities or an empty {@link List}
     */
    List<Achievement> findUnclaimedAchForUser(String sql, Language lang, Long userId);
}
