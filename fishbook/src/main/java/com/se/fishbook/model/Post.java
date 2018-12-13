package com.se.fishbook.model;

import com.se.fishbook.util.DateUtil;

import java.util.Date;
//java bean for the post table

public class Post {
    private Integer postid;

    private String content;

    private Date createtime;

    private String imagepath;

    private Integer authorid;

    private Double locationlatitude;

    private Double locationlongitude;

    private Integer likecount;

    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCreatetime() {
        return DateUtil.date2Str(createtime);
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

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public Double getLocationlatitude() {
        return locationlatitude;
    }

    public void setLocationlatitude(Double locationlatitude) {
        this.locationlatitude = locationlatitude;
    }

    public Double getLocationlongitude() {
        return locationlongitude;
    }

    public void setLocationlongitude(Double locationlongitude) {
        this.locationlongitude = locationlongitude;
    }

    public Integer getLikecount() {
        return likecount;
    }

    public void setLikecount(Integer likecount) {
        this.likecount = likecount;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postid=" + postid +
                ", content='" + content + '\'' +
                ", createtime=" + createtime +
                ", imagepath='" + imagepath + '\'' +
                ", authorid=" + authorid +
                ", locationlatitude=" + locationlatitude +
                ", locationlongitude=" + locationlongitude +
                ", likecount=" + likecount +
                '}';
    }
}