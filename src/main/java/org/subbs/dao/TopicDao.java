package org.subbs.dao;

import org.springframework.stereotype.Repository;
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
public class TopicDao extends BaseDao<Topic>{

    private static final String GET_PAGED_TOPICS =
            "from Topic where boardId = ? order by topicUpdateDate desc";

    private static final String QUERY_TOPIC_BY_TITILE =
            "from Topic  where topicTitle like ? order by topicUpdateDate desc";

    /**
     * 获取论坛版块分页的主题帖子
     * @param boardId 论坛版块ID
     * @param pageNo 页号，从1开始。
     * @param pageSize 每页的记录数
     * @return 包含分页信息的Page对象
     */
    public Page getPagedTopics(int boardId,int pageNo,int pageSize) {
        return pagedQuery(GET_PAGED_TOPICS,pageNo,pageSize,boardId);
    }
}
