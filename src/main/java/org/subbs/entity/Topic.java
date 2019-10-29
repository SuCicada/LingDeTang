package org.subbs.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/22/19
 * Time: 9:52 AM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Entity
@Table(name = "ldt_topics", schema = "lingdetang", catalog = "")
public class Topic {
    private int topicId;
    private int forumId;
    private int userId;
    private String topicTitle;
    private String topicText;
    private int topicViewCount;
    private int topicPostCount;
    private Timestamp topicUpdateDate;
    private Timestamp topicCreateDate;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "topic_id", nullable = false)
    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    @Basic
    @Column(name = "forum_id", nullable = false)
    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "topic_title", nullable = false, length = 45)
    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    @Basic
    @Column(name = "topic_text", nullable = false, length = -1)
    public String getTopicText() {
        return topicText;
    }

    public void setTopicText(String topicText) {
        this.topicText = topicText;
    }

    @Basic
    @Column(name = "topic_view_count", nullable = false)
    public int getTopicViewCount() {
        return topicViewCount;
    }

    public void setTopicViewCount(int topicViewCount) {
        this.topicViewCount = topicViewCount;
    }

    @Basic
    @Column(name = "topic_post_count", nullable = false)
    public int getTopicPostCount() {
        return topicPostCount;
    }

    public void setTopicPostCount(int topicPostCount) {
        this.topicPostCount = topicPostCount;
    }

    @Basic
    @Column(name = "topic_update_date", nullable = false)
    public Timestamp getTopicUpdateDate() {
        return topicUpdateDate;
    }

    public void setTopicUpdateDate(Timestamp topicUpdateDate) {
        this.topicUpdateDate = topicUpdateDate;
    }

    @Basic
    @Column(name = "topic_create_date", nullable = false)
    public Timestamp getTopicCreateDate() {
        return topicCreateDate;
    }

    public void setTopicCreateDate(Timestamp topicCreateDate) {
        this.topicCreateDate = topicCreateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Topic topic = (Topic) o;

        if (topicId != topic.topicId) return false;
        if (forumId != topic.forumId) return false;
        if (userId != topic.userId) return false;
        if (topicViewCount != topic.topicViewCount) return false;
        if (topicPostCount != topic.topicPostCount) return false;
        if (topicTitle != null ? !topicTitle.equals(topic.topicTitle) : topic.topicTitle != null) return false;
        if (topicText != null ? !topicText.equals(topic.topicText) : topic.topicText != null) return false;
        if (topicUpdateDate != null ? !topicUpdateDate.equals(topic.topicUpdateDate) : topic.topicUpdateDate != null)
            return false;
        if (topicCreateDate != null ? !topicCreateDate.equals(topic.topicCreateDate) : topic.topicCreateDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = topicId;
        result = 31 * result + forumId;
        result = 31 * result + userId;
        result = 31 * result + (topicTitle != null ? topicTitle.hashCode() : 0);
        result = 31 * result + (topicText != null ? topicText.hashCode() : 0);
        result = 31 * result + topicViewCount;
        result = 31 * result + topicPostCount;
        result = 31 * result + (topicUpdateDate != null ? topicUpdateDate.hashCode() : 0);
        result = 31 * result + (topicCreateDate != null ? topicCreateDate.hashCode() : 0);
        return result;
    }
}
