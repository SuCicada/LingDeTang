package org.subbs.dao;

import org.springframework.stereotype.Repository;
import org.subbs.entity.Favorite;
import org.subbs.entity.Forum;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/26/19
 * Time: 3:58 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Repository
public class FavoriteDao extends BaseDao<Favorite> {
    private static final String QUERY_FAVORITE_BY_USER_AND_TOPIC =
            "from Favorite where userId = ? and topicId = ? ";

    public ArrayList<Favorite> getFavoriteByUserIdAndTopicId(int userId, int topicId) {
        Object res = getHibernateTemplate().find(QUERY_FAVORITE_BY_USER_AND_TOPIC, userId, topicId);
        return res != null ? (ArrayList<Favorite>)res : null;
    }
}
