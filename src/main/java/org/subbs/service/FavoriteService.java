package org.subbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.subbs.dao.FavoriteDao;
import org.subbs.dao.ForumDao;
import org.subbs.entity.Favorite;
import org.subbs.entity.Forum;
import org.subbs.entity.Post;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/26/19
 * Time: 4:07 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Service
public class FavoriteService {
    @Autowired
    private FavoriteDao favoriteDao;

    /**
     *  以前端为主，以用户体验为先
     *  前端发送                收藏           取消收藏             Y 代表正常操作
     *  后端   数据库有      数据库弃旧迎新         Y
     *        数据库没有         Y           数据库不作操作
     *
     *                       收藏               取消收藏
     *        数据库有      先 旧 del ，再 新 save      旧 del
     *        数据库没有            新 save
     */
    public void saveOrDelete(Favorite favorite, boolean isLike) {
        int userId = favorite.getUserId();
        int topicId = favorite.getTopicId();
        Favorite oldFavorite = favoriteDao.getFavoriteByUserIdAndTopicId(userId, topicId);
        boolean hasOldFavorite = oldFavorite != null;
        if(hasOldFavorite){
            favoriteDao.remove(oldFavorite);
        }
        if(isLike){
            Timestamp now = new Timestamp(System.currentTimeMillis());
            favorite.setFavoriteCreateDate(now);
            favoriteDao.save(favorite);
        }
    }
}
