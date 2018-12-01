package com.se.fishbook.model;

import java.util.List;

public class PostDisplay {
    private Post post;
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
