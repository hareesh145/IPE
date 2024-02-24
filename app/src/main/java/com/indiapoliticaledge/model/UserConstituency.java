package com.indiapoliticaledge.model;

public class UserConstituency {
    public int userConstituencyId;
    public int userId;
    public int constituencyId;
    public String startDate;
    public String endDate;
    public String activeFlag;

    @Override
    public String toString() {
        return "UserConstituency{" +
                "userConstituencyId=" + userConstituencyId +
                ", userId=" + userId +
                ", constituencyId=" + constituencyId +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", activeFlag='" + activeFlag + '\'' +
                '}';
    }
}
