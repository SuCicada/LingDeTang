package org.subbs.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ClassName Post
 * @Date 2019/12/22 16:42
 */
@Entity
@Table(name = "ldt_posts", schema = "lingdetang", catalog = "")
public class Post {
    private int postId;
    private int topicId;
    private int userId;
    private int postIndex;
    private String postText;
    private int postHeart;
    private Timestamp postCreateDate;

    @Id
    @Column(name = "post_id")
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Basic
    @Column(name = "topic_id")
    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "post_index")
    public int getPostIndex() {
        return postIndex;
    }

    public void setPostIndex(int postIndex) {
        this.postIndex = postIndex;
    }

    @Basic
    @Column(name = "post_text")
    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    @Basic
    @Column(name = "post_heart")
    public int getPostHeart() {
        return postHeart;
    }

    public void setPostHeart(int postHeart) {
        this.postHeart = postHeart;
    }

    @Basic
    @Column(name = "post_create_date")
    public Timestamp getPostCreateDate() {
        return postCreateDate;
    }

    public void setPostCreateDate(Timestamp postCreateDate) {
        this.postCreateDate = postCreateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post that = (Post) o;
        return postId == that.postId &&
                topicId == that.topicId &&
                userId == that.userId &&
                postIndex == that.postIndex &&
                postHeart == that.postHeart &&
                Objects.equals(postText, that.postText) &&
                Objects.equals(postCreateDate, that.postCreateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, topicId, userId, postIndex, postText, postHeart, postCreateDate);
    }
}
