package com.se.fishbook.model;

import java.util.List;
//the encapsulating class to show posts on the page

public class PostDisplay {
    private Post post;
    private Integer commentCount = 0;
    private User user;
//    private List<CommentDisplay> cd;
//
//    public List<CommentDisplay> getCd() {
//        return cd;
//    }
//
//    public void setCd(List<CommentDisplay> cd) {
//        this.cd = cd;
//    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
