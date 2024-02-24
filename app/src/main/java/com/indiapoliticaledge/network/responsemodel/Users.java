package com.indiapoliticaledge.network.responsemodel;

import com.indiapoliticaledge.model.Constituency;
import com.indiapoliticaledge.model.UserConstituency;

public class Users {

    public int userId;
    public String firstName;
    public String lastName;
    public String mobileNumber;
    public String password;
    public String createdDate;
    public String modifiedDate;
    public String activeFlag;
    public String secretKey;
    public String using2fa;
    public String profilePhotoUrl;
    public String shortDescriptionAbout;
    public int userRoleId;
    public int constituencyId;
    public String deleteFlag;
    public String partyName;
    public String partyLogo;
    public String language;
    public String startDate;
    public String endDate;
    public String roleName;
    public Constituency constituency;
    public UserConstituency userConstituencies;

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", password='" + password + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", modifiedDate='" + modifiedDate + '\'' +
                ", activeFlag='" + activeFlag + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", using2fa='" + using2fa + '\'' +
                ", profilePhotoUrl='" + profilePhotoUrl + '\'' +
                ", shortDescriptionAbout='" + shortDescriptionAbout + '\'' +
                ", userRoleId=" + userRoleId +
                ", constituencyId=" + constituencyId +
                ", deleteFlag='" + deleteFlag + '\'' +
                ", partyName='" + partyName + '\'' +
                ", partyLogo='" + partyLogo + '\'' +
                ", language='" + language + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", roleName='" + roleName + '\'' +
                ", constituency=" + constituency +
                ", userConstituencies='" + userConstituencies + '\'' +
                '}';
    }
}
