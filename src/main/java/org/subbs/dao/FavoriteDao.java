package org.subbs.dao;

import org.springframework.stereotype.Repository;
import org.subbs.entity.Favorite;
import org.subbs.entity.Forum;

/**
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/26/19
 * Time: 3:58 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Repository
public class FavoriteDao extends BaseDao<Favorite>{
    private static final String QUERY_FAVORITE_BY_USER_AND_TOPIC =
            "from Favorite where userId = ? and topicId = ? ";

    public Favorite getFavoriteByUserIdAndTopicId(int userId,int topicId){
        return (Favorite)getHibernateTemplate().find(QUERY_FAVORITE_BY_USER_AND_TOPIC,userId,topicId);
    }
}
