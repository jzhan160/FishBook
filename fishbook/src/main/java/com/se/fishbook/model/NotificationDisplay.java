package com.se.fishbook.model;

public class NotificationDisplay {
    private Post post;
    private Notification notification;
    private User trigger;
    private String text;
    private int hasPost = 0;

    public int getHasPost() {
        return hasPost;
    }

    public void setHasPost(int hasPost) {
        this.hasPost = hasPost;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public User getTrigger() {
        return trigger;
    }

    public void setTrigger(User trigger) {
        this.trigger = trigger;
    }
}
