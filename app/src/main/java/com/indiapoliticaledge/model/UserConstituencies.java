package com.indiapoliticaledge.model;

import java.io.Serializable;

public class UserConstituencies implements Serializable {

    public int userConstituencyId;
    public int userId;
    public int constituencyId;
    public String startDate;
    public String endDate;
    public String activeFlag;

    public int getUserConstituencyId() {
        return userConstituencyId;
    }

    public void setUserConstituencyId(int userConstituencyId) {
        this.userConstituencyId = userConstituencyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getConstituencyId() {
        return constituencyId;
    }

    public void setConstituencyId(int constituencyId) {
        this.constituencyId = constituencyId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(String activeFlag) {
        this.activeFlag = activeFlag;
    }

    @Override
    public String toString() {
        return "UserConstituencies{" +
                "userConstituencyId=" + userConstituencyId +
                ", userId=" + userId +
                ", constituencyId=" + constituencyId +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", activeFlag='" + activeFlag + '\'' +
                '}';
    }
}
