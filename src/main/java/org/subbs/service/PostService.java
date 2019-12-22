package org.subbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.subbs.controller.Result;
import org.subbs.dao.Page;
import org.subbs.dao.PostDao;
import org.subbs.dao.TopicDao;
import org.subbs.dao.UserDao;
import org.subbs.entity.Post;
import org.subbs.entity.Topic;
import org.subbs.entity.User;
import org.subbs.util.O2M;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/22/19
 * Time: 11:03 AM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Service
public class PostService {
    @Autowired
    private PostDao postDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private TopicDao topicDao;

    public void save(Post post) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        post.setPostCreateDate(now);
        post.setPostHeart(0);
        post.setPostIndex(topicDao.get(post.getTopicId())
                .getTopicPostCount() + 2);
        postDao.save(post);
    }

    /**
     * 获取论坛版块某一页主题帖，以最后回复时间降序排列
     *
     * @return
     */
    public Page getPagedTopics(int topicId, int pageNo, int pageSize) {
        Page page = postDao.getPagedTopics(topicId, pageNo, pageSize);
        List<Object> data = page.getResult();
        List res = new ArrayList();
        Topic topic = topicDao.get(topicId);
        int topicUserId = topic.getUserId();
        Map topicInfo = new HashMap();
        topicInfo.put("userId",topicUserId);
        for (int i = 0; i < data.size(); i++) {
            Post post = (Post) data.get(i);
            User user = userDao.get(post.getUserId());
            /* 这里是返回给前端的用户信息 */
            String info[] = new String[]{"userPassword"};
            Map userInfo = O2M.parseExclude(user, info);
            Map map = new HashMap();
            map.put("post",post);
            map.put("user",userInfo);
            map.put("topic",topicInfo);
            res.add(map);
        }
        page.setData(res);
        return page;
    }

    public Long getCount() {
        return postDao.count();
    }

    public List<Post> getAllTopic() {
        return postDao.loadAll();
    }

    public Result getPostById(int postId) {
        Post post = postDao.get(postId);
        if (post == null) {
            System.out.println("Post with id " + post + " not found");
            return new Result(0, "Post not found");
        }
        int userId = post.getUserId();
        User user = userDao.get(userId);
        Map data = new HashMap();
        data.put("post", post);
        if (user == null) {
            System.out.println("User with id " + user + " not found");
            return new Result(1, "User not found", data);
        }
        String info[] = new String[]{"userPassword"};
        Map userInfo = O2M.parseExclude(user, info);
        data.put("user", userInfo);
        return new Result(1, "", data);
    }

}
