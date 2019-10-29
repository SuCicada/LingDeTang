package org.subbs.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/24/19
 * Time: 8:46 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Entity
@Table(name = "ldt_users", schema = "lingdetang", catalog = "")
public class User {
    private int userId;
    private String userEmail;
    private String userNickname;
    private String userPassword;
    private Integer userPhone;
    private byte[] userPhoto;
    private Integer userSex;
    private String userSignature;
    private String username;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_email", nullable = true, length = 20)
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Basic
    @Column(name = "user_nickname", nullable = true, length = 20)
    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    @Basic
    @Column(name = "user_password", nullable = false, length = 20)
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Basic
    @Column(name = "user_phone", nullable = true)
    public Integer getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Integer userPhone) {
        this.userPhone = userPhone;
    }

    @Basic
    @Column(name = "user_photo", nullable = true)
    public byte[] getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(byte[] userPhoto) {
        this.userPhoto = userPhoto;
    }

    @Basic
    @Column(name = "user_sex", nullable = false)
    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    @Basic
    @Column(name = "user_signature", nullable = true, length = 200)
    public String getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                Objects.equals(userEmail, user.userEmail) &&
                Objects.equals(userNickname, user.userNickname) &&
                Objects.equals(userPassword, user.userPassword) &&
                Objects.equals(userPhone, user.userPhone) &&
                Arrays.equals(userPhoto, user.userPhoto) &&
                Objects.equals(userSex, user.userSex) &&
                Objects.equals(userSignature, user.userSignature) &&
                Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(userId, userEmail, userNickname, userPassword, userPhone, userSex, userSignature, username);
        result = 31 * result + Arrays.hashCode(userPhoto);
        return result;
    }
}
