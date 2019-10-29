package org.subbs.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/26/19
 * Time: 3:57 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Entity
@Table(name = "ldt_forums", schema = "lingdetang", catalog = "")
public class Forum {
    private int forumId;
    private String forumName;
    private String forumDesc;

    @Id
    @Column(name = "forum_id", nullable = false)
    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    @Basic
    @Column(name = "forum_name", nullable = false, length = 45)
    public String getForumName() {
        return forumName;
    }

    public void setForumName(String forumName) {
        this.forumName = forumName;
    }

    @Basic
    @Column(name = "forum_desc", nullable = true, length = 45)
    public String getForumDesc() {
        return forumDesc;
    }

    public void setForumDesc(String forumDesc) {
        this.forumDesc = forumDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forum forum = (Forum) o;
        return forumId == forum.forumId &&
                Objects.equals(forumName, forum.forumName) &&
                Objects.equals(forumDesc, forum.forumDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(forumId, forumName, forumDesc);
    }
}
