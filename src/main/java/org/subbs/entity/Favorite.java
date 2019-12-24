package org.subbs.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ClassName LdtFavoritesEntity
 * @Date 2019/12/24 23:25
 */
@Entity
@Table(name = "ldt_favorites", schema = "lingdetang", catalog = "")
public class Favorite {
    private int favoriteId;
    private int userId;
    private int topicId;
    private Timestamp favoriteCreateDate;

    @Id
    @Column(name = "favorite_id")
    public int getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
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
    @Column(name = "topic_id")
    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    @Basic
    @Column(name = "favorite_create_date")
    public Timestamp getFavoriteCreateDate() {
        return favoriteCreateDate;
    }

    public void setFavoriteCreateDate(Timestamp favoriteCreateDate) {
        this.favoriteCreateDate = favoriteCreateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorite that = (Favorite) o;
        return favoriteId == that.favoriteId &&
                userId == that.userId &&
                topicId == that.topicId &&
                Objects.equals(favoriteCreateDate, that.favoriteCreateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(favoriteId, userId, topicId, favoriteCreateDate);
    }
}
