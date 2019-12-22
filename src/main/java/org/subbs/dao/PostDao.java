package org.subbs.dao;

import org.springframework.stereotype.Repository;
import org.subbs.entity.Post;
import org.subbs.entity.Topic;

/**
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/22/19
 * Time: 10:37 AM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Repository
public class PostDao extends BaseDao<Post>{

    private static final String GET_PAGED_TOPICS =
                    "from Post as Post " +
                    "where Post.topicId = ? " +
                    "order by Post.postIndex desc";

    private static final String QUERY_TOPIC_BY_TITILE =
            "from Post where topicTitle like ? order by topicUpdateDate desc";



    /**
     * 获取论坛版块分页的主题帖子
     * @param topicId TopicID
     * @param pageNo 页号，从1开始。
     * @param pageSize 每页的记录数
     * @return 包含分页信息的Page对象
     */
    public Page getPagedTopics(int topicId, int pageNo, int pageSize) {

        return pagedQuery(GET_PAGED_TOPICS,pageNo,pageSize, topicId);
    }



}
