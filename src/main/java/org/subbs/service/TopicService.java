package org.subbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.subbs.controller.Result;
import org.subbs.dao.Page;
import org.subbs.dao.TopicDao;
import org.subbs.dao.UserDao;
import org.subbs.entity.Topic;
import org.subbs.entity.User;
import org.subbs.util.O2M;

import java.sql.ResultSet;
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
public class TopicService {
    @Autowired
    private TopicDao topicDao;

    @Autowired
    private UserDao userDao;


    public void save(Topic topic){
        topicDao.save(topic);
    }

    /**
     * 获取论坛版块某一页主题帖，以最后回复时间降序排列
     * @return
     */
    public Page getPagedTopics(int forumId, int pageNo, int pageSize){
        Page page = topicDao.getPagedTopics(forumId,pageNo,pageSize);
        List<Object[]> data = page.getResult();
        for(int i=0;i<data.size();i++){
            User user = (User)(data.get(i))[1];
            /* 这里是返回给前端的用户信息 */
            String info[] = new String[]{"userPassword"};
            Map userInfo = O2M.parseExclude(user,info);
            (data.get(i))[1] = userInfo;
            user.setUserPassword("");
        }
        return page;
    }

    public Long getCount(){
        return topicDao.count();
    }

    public List<Topic> getAllTopic(){
        return topicDao.loadAll();
    }

    public Result getTopicById(int topicId){
        Topic topic = topicDao.get(topicId);
        if (topic == null) {
            System.out.println("Topic with id " + topic + " not found");
            return new Result(0,"Topic not found");
        }
        int userId = topic.getUserId();
        User user = userDao.get(userId);
        Map data = new HashMap();
        data.put("topic",topic);
        if (user == null) {
            System.out.println("User with id " + user + " not found");
            return new Result(1,"User not found",data);
        }
        String info[] = new String[]{"userPassword"};
        Map userInfo = O2M.parseExclude(user,info);
        data.put("user",userInfo);
        return new Result(1,"",data);
    }

    public int addViewCount(int topicId){
        return addViewCount(topicId,1);
    }
    public int addViewCount(int topicId,int addNum){
        Topic topic = topicDao.get(topicId);
        topic.setTopicViewCount(topic.getTopicViewCount()+addNum);
        topicDao.update(topic);
        return topic.getTopicViewCount();
    }
}
