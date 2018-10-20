package com.se.fishbook.model;

import java.util.Date;

public class Comment {
    private Integer commentid;

    private String content;

    private Date createtime;

    private Integer authorid;

    private Integer postid;

    public Integer getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
    }
}