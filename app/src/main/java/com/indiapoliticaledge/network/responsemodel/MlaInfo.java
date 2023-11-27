package com.indiapoliticaledge.network.responsemodel;

import com.indiapoliticaledge.model.Constituency;

public class MlaInfo {

    public int userId;
    public String firstName;
    public String lastName;
    public String mobileNumber;
    public String password;
    public String createdDate;
    public Object modifiedDate;
    public String activeFlag;
    public Object secretKey;
    public Object using2fa;
    public Object profilePhotoUrl;
    public Object shortDescriptionAbout;
    public int userRoleId;
    public int constituencyId;
    public String deleteFlag;
    public Constituency constituency;


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

    public Object getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Object modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(String activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Object getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(Object secretKey) {
        this.secretKey = secretKey;
    }

    public Object getUsing2fa() {
        return using2fa;
    }

    public void setUsing2fa(Object using2fa) {
        this.using2fa = using2fa;
    }

    public Object getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(Object profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public Object getShortDescriptionAbout() {
        return shortDescriptionAbout;
    }

    public void setShortDescriptionAbout(Object shortDescriptionAbout) {
        this.shortDescriptionAbout = shortDescriptionAbout;
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

    @Override
    public String toString() {
        return "MlaInfo{" +
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
                '}';
    }
}
