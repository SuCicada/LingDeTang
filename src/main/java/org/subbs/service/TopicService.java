package org.subbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.subbs.dao.Page;
import org.subbs.dao.TopicDao;
import org.subbs.entity.Topic;

import java.util.List;

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

    public void save(Topic topic){
        topicDao.save(topic);
    }

    /**
     * 获取论坛版块某一页主题帖，以最后回复时间降序排列
     * @return
     */
    public Page getPagedTopics(int forumId, int pageNo, int pageSize){
        return topicDao.getPagedTopics(forumId,pageNo,pageSize);
    }

    public List<Topic> getAllTopic(){
        return topicDao.loadAll();
    }

}
