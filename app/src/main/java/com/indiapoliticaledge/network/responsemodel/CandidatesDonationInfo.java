package com.indiapoliticaledge.network.responsemodel;

import com.google.gson.annotations.SerializedName;

public class CandidatesDonationInfo {

    public int candidatesDonationId;
    public int candidatesId;
    public int userId;
    public int constituencyId;
    public double donationAmount;
    public String donationPurpose;
    public String createdDate;
    @SerializedName("candidates")
    public CandidatesList candidates;

    public int getCandidatesDonationId() {
        return candidatesDonationId;
    }

    public void setCandidatesDonationId(int candidatesDonationId) {
        this.candidatesDonationId = candidatesDonationId;
    }

    public int getCandidatesId() {
        return candidatesId;
    }

    public void setCandidatesId(int candidatesId) {
        this.candidatesId = candidatesId;
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

    public double getDonationAmount() {
        return donationAmount;
    }

    public void setDonationAmount(double donationAmount) {
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

    public CandidatesList getCandidates() {
        return candidates;
    }

    public void setCandidates(CandidatesList candidates) {
        this.candidates = candidates;
    }

    @Override
    public String toString() {
        return "CandidatesDonationInfo{" +
                "candidatesDonationId=" + candidatesDonationId +
                ", candidatesId=" + candidatesId +
                ", userId=" + userId +
                ", constituencyId=" + constituencyId +
                ", donationAmount=" + donationAmount +
                ", donationPurpose='" + donationPurpose + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", candidates=" + candidates +
                '}';
    }
}
