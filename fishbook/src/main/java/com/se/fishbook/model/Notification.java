package com.se.fishbook.model;

public class Notification {
    private Integer notificationid;

    private Integer receiverid;

    private Integer triggerid;

    private String event;

    private Byte viewed;

    public Integer getNotificationid() {
        return notificationid;
    }

    public void setNotificationid(Integer notificationid) {
        this.notificationid = notificationid;
    }

    public Integer getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(Integer receiverid) {
        this.receiverid = receiverid;
    }

    public Integer getTriggerid() {
        return triggerid;
    }

    public void setTriggerid(Integer triggerid) {
        this.triggerid = triggerid;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event == null ? null : event.trim();
    }

    public Byte getViewed() {
        return viewed;
    }

    public void setViewed(Byte viewed) {
        this.viewed = viewed;
    }
}