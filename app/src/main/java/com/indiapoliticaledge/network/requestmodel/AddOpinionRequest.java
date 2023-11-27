package com.indiapoliticaledge.network.requestmodel;

public class AddOpinionRequest {
    public String userId;
    public int constituencyId;
    public String opinionMessage;
    public String createdDate;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getConstituencyId() {
        return constituencyId;
    }

    public void setConstituencyId(int constituencyId) {
        this.constituencyId = constituencyId;
    }

    public String getOpinionMessage() {
        return opinionMessage;
    }

    public void setOpinionMessage(String opinionMessage) {
        this.opinionMessage = opinionMessage;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "AddOpinionRequest{" +
                "userId='" + userId + '\'' +
                ", constituencyId=" + constituencyId +
                ", opinionMessage='" + opinionMessage + '\'' +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }
}
