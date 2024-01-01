package com.indiapoliticaledge.model;

import java.io.Serializable;

public class UserInfo implements Serializable {
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
    public Constituency constituency;


    public String moreInfo;
    public String birthPlace;
    public String education;
    public String voterIdNumber;
    public String mandalName;
    public String villageName;
    public String language;
    public String roleName;

    public String startDate;

    public String endDate;

    private String partyName;
    private String partyLogo;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getPartyLogo() {
        return partyLogo;
    }

    public void setPartyLogo(String partyLogo) {
        this.partyLogo = partyLogo;
    }

    public UserConstituencies userConstituencies;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(String activeFlag) {
        this.activeFlag = activeFlag;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public int getConstituencyId() {
        return constituencyId;
    }

    public void setConstituencyId(int constituencyId) {
        this.constituencyId = constituencyId;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Constituency getConstituency() {
        return constituency;
    }

    public void setConstituency(Constituency constituency) {
        this.constituency = constituency;
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

    public UserConstituencies getUserConstituencies() {
        return userConstituencies;
    }

    public void setUserConstituencies(UserConstituencies userConstituencies) {
        this.userConstituencies = userConstituencies;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getUsing2fa() {
        return using2fa;
    }

    public void setUsing2fa(String using2fa) {
        this.using2fa = using2fa;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public String getShortDescriptionAbout() {
        return shortDescriptionAbout;
    }

    public void setShortDescriptionAbout(String shortDescriptionAbout) {
        this.shortDescriptionAbout = shortDescriptionAbout;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", password='" + password + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", modifiedDate=" + modifiedDate +
                ", activeFlag='" + activeFlag + '\'' +
                ", secretKey=" + secretKey +
                ", using2fa=" + using2fa +
                ", profilePhotoUrl=" + profilePhotoUrl +
                ", shortDescriptionAbout=" + shortDescriptionAbout +
                ", userRoleId=" + userRoleId +
                ", constituencyId=" + constituencyId +
                ", deleteFlag='" + deleteFlag + '\'' +
                ", constituency=" + constituency +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", userConstituencies=" + userConstituencies +
                '}';
    }
}
