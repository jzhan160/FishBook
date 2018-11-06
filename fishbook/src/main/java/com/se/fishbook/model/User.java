package com.se.fishbook.model;

import com.se.fishbook.util.DateUtil;

import java.util.Date;

public class User extends UserKey {
    private String username;

    private String password;

    private String gender;

    private Date createtime;

    private String imagepath;

    private Integer isadmin;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getCreatetime() {
        return DateUtil.date2Str(this.createtime);
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath == null ? null : imagepath.trim();
    }

    public Integer getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(Integer isadmin) {
        this.isadmin = isadmin;
    }

    @Override
    public String toString() {
        return "User{" +"id='"+super.getUserid() +'\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='"+super.getEmail()+ '\'' +
                ", gender='" + gender + '\'' +
                ", createtime=" + createtime +
                ", imagepath='" + imagepath + '\'' +
                ", isadmin=" + isadmin +
                '}';
    }
}