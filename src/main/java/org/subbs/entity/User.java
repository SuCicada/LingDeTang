package org.subbs.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/19/19
 * Time: 1:11 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "ldt_users", schema = "lingdetang")
public class User {
    private int userId;
    private String username;
    private String userPassword;
    private String userNickname;
    private int userSex;
    private String userEmail;
    private String userSignature;
    private byte[] userPhoto;
    private Integer userPhone;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "user_password")
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Basic
    @Column(name = "user_nickname")
    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    @Basic
    @Column(name = "user_sex")
    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    @Basic
    @Column(name = "user_email")
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Basic
    @Column(name = "user_signature")
    public String getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature;
    }

    @Basic
    @Column(name = "user_photo")
    public byte[] getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(byte[] userPhoto) {
        this.userPhoto = userPhoto;
    }

    @Basic
    @Column(name = "user_phone")
    public Integer getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Integer userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User that = (User) o;
        return userId == that.userId &&
                userSex == that.userSex &&
                Objects.equals(username, that.username) &&
                Objects.equals(userPassword, that.userPassword) &&
                Objects.equals(userNickname, that.userNickname) &&
                Objects.equals(userEmail, that.userEmail) &&
                Objects.equals(userSignature, that.userSignature) &&
                Arrays.equals(userPhoto, that.userPhoto) &&
                Objects.equals(userPhone, that.userPhone);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(userId, username, userPassword, userNickname, userSex, userEmail, userSignature, userPhone);
        result = 31 * result + Arrays.hashCode(userPhoto);
        return result;
    }
}
