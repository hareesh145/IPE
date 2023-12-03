package com.indiapoliticaledge.network.requestmodel;

import java.io.Serializable;

public class Donate implements Serializable {

    public String candidatesId;
    public String userId;
    public String constituencyId;
    public int donationAmount;
    public String donationPurpose;
    public String createdDate;


    public String getCandidatesId() {
        return candidatesId;
    }

    public void setCandidatesId(String candidatesId) {
        this.candidatesId = candidatesId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getConstituencyId() {
        return constituencyId;
    }

    public void setConstituencyId(String constituencyId) {
        this.constituencyId = constituencyId;
    }

    public int getDonationAmount() {
        return donationAmount;
    }

    public void setDonationAmount(int donationAmount) {
        this.donationAmount = donationAmount;
    }

    public String getDonationPurpose() {
        return donationPurpose;
    }

    public void setDonationPurpose(String donationPurpose) {
        this.donationPurpose = donationPurpose;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Donate{" +
                "candidatesId='" + candidatesId + '\'' +
                ", userId='" + userId + '\'' +
                ", constituencyId='" + constituencyId + '\'' +
                ", donationAmount=" + donationAmount +
                ", donationPurpose='" + donationPurpose + '\'' +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }
}
