package com.indiapoliticaledge.network.responsemodel;

import java.io.Serializable;

public class Candidates implements Serializable {

    public int userId;
    public String firstName;
    public String lastName;
    public String mobileNumber;
    public String password;
    public String createdDate;
    public String modifiedDate;
    public String activeFlag;
    public String shortDescriptionAbout;
    public int userRoleId;
    public int constituencyId;
    public String deleteFlag;
    public String moreInfo;
    public String birthPlace;
    public String education;
    public String voterIdNumber;
    public String mandalName;
    public String villageName;
    public String language;
    public String roleName;

    @Override
    public String toString() {
        return "Candidates{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", password='" + password + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", modifiedDate='" + modifiedDate + '\'' +
                ", activeFlag='" + activeFlag + '\'' +
                ", shortDescriptionAbout='" + shortDescriptionAbout + '\'' +
                ", userRoleId=" + userRoleId +
                ", constituencyId=" + constituencyId +
                ", deleteFlag='" + deleteFlag + '\'' +
                ", moreInfo='" + moreInfo + '\'' +
                ", birthPlace='" + birthPlace + '\'' +
                ", education='" + education + '\'' +
                ", voterIdNumber='" + voterIdNumber + '\'' +
                ", mandalName='" + mandalName + '\'' +
                ", villageName='" + villageName + '\'' +
                ", language='" + language + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
