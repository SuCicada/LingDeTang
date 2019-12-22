package org.subbs.dao;

import org.springframework.stereotype.Repository;
import org.subbs.entity.Topic;
import org.subbs.entity.User;
import org.subbs.util.O2M;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/22/19
 * Time: 10:37 AM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Repository
public class TopicDao extends BaseDao<Topic> {

    private static final String GET_PAGED_TOPICS =
//            "select Topic.topicId as topicId, " +
//                    "Topic.forumId as forumId," +
//                    "Topic.userId as userId," +
//                    "Topic.topicTitle as topicTitle," +
//                    "Topic.topicText as topicText," +
//                    "Topic.topicViewCount as topicViewCount," +
//                    "Topic.topicPostCount as topicPostCount," +
//                    "Topic.topicUpdateDate as topicUpdateDate," +
//                    "Topic.topicCreateDate as topicCreateDate," +
//                    "User.userPhoto as userPhoto " +
            "from Topic as Topic, User as User " +
                    "where User.userId = Topic.userId and Topic.forumId = ? " +
                    "order by Topic.topicUpdateDate desc";

    private static final String QUERY_TOPIC_BY_TITILE_From_ForumId =
//            "from Topic where topicTitle like ? order by topicUpdateDate desc";
            "from Topic as Topic, User as User " +
                    "where User.userId = Topic.userId " +
                    "and Topic.forumId = ? " +
                    "and Topic.topicTitle like ?" +
                    "order by Topic.topicUpdateDate desc";

    private static final String QUERY_TOPIC_BY_TITILE =
            "from Topic as Topic, User as User " +
                    "where User.userId = Topic.userId  " +
                    "and Topic.topicTitle like ?" +
                    "order by Topic.topicUpdateDate desc";

    /**
     * 获取论坛版块分页的主题帖子
     *
     * @param forumId  论坛版块ID
     * @param pageNo   页号，从1开始。
     * @param pageSize 每页的记录数
     * @return 包含分页信息的Page对象
     */
    public Page getPagedTopics(int forumId, int pageNo, int pageSize) {

        return pagedQuery(GET_PAGED_TOPICS, pageNo, pageSize, forumId);
    }

    public Page getSearchPageTopics(int searchForumId, String searchContent, int pageNo, int pageSize) {
        String searchContentStr = "%" + searchContent + "%";
        if (searchForumId == 0) {
            return pagedQuery(QUERY_TOPIC_BY_TITILE, pageNo, pageSize, searchContentStr);
        }
        return pagedQuery(QUERY_TOPIC_BY_TITILE_From_ForumId, pageNo, pageSize, searchForumId, searchContentStr);
    }


}
