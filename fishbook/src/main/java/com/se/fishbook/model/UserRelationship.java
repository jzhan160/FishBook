package com.se.fishbook.model;

public class UserRelationship {
    private Integer relationshipid;

    private Integer followerid;

    private Integer followeeid;

    public Integer getRelationshipid() {
        return relationshipid;
    }

    public void setRelationshipid(Integer relationshipid) {
        this.relationshipid = relationshipid;
    }

    public Integer getFollowerid() {
        return followerid;
    }

    public void setFollowerid(Integer followerid) {
        this.followerid = followerid;
    }

    public Integer getFolloweeid() {
        return followeeid;
    }

    public void setFolloweeid(Integer followeeid) {
        this.followeeid = followeeid;
    }
}