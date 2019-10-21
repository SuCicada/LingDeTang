package org.subbs.entity;

import javax.persistence.*;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/21/19
 * Time: 11:07 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Entity
@Table(name = "ldt_users", schema = "lingdetang", catalog = "")
public class User {
    private int userId;
    private String username;
    private String userPassword;
    private String userNickname;
    private String userSex;
    private String userEmail;
    private String userSignature;
    private byte[] userPhoto;
    private Integer userPhone;

    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    @Column(name = "user_nickname", nullable = true, length = 20)
    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    @Basic
    @Column(name = "user_sex", nullable = false, length = 20)
    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
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
    @Column(name = "user_signature", nullable = true, length = 200)
    public String getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature;
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
    @Column(name = "user_phone", nullable = true)
    public Integer getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Integer userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (userPassword != null ? !userPassword.equals(user.userPassword) : user.userPassword != null) return false;
        if (userNickname != null ? !userNickname.equals(user.userNickname) : user.userNickname != null) return false;
        if (userSex != null ? !userSex.equals(user.userSex) : user.userSex != null) return false;
        if (userEmail != null ? !userEmail.equals(user.userEmail) : user.userEmail != null) return false;
        if (userSignature != null ? !userSignature.equals(user.userSignature) : user.userSignature != null)
            return false;
        if (!Arrays.equals(userPhoto, user.userPhoto)) return false;
        if (userPhone != null ? !userPhone.equals(user.userPhone) : user.userPhone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        result = 31 * result + (userNickname != null ? userNickname.hashCode() : 0);
        result = 31 * result + (userSex != null ? userSex.hashCode() : 0);
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        result = 31 * result + (userSignature != null ? userSignature.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(userPhoto);
        result = 31 * result + (userPhone != null ? userPhone.hashCode() : 0);
        return result;
    }
}
