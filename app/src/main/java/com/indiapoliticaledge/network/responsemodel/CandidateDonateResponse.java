package com.indiapoliticaledge.network.responsemodel;

import java.io.Serializable;
import java.util.ArrayList;

public class CandidateDonateResponse implements Serializable {

    public ArrayList<CandidatesDonationsList> candidatesDonationsList;
    public boolean success;
    public String successCode;
    public double totalDonationAmount;

    public ArrayList<CandidatesDonationsList> getCandidatesDonationsList() {
        return candidatesDonationsList;
    }

    public void setCandidatesDonationsList(ArrayList<CandidatesDonationsList> candidatesDonationsList) {
        this.candidatesDonationsList = candidatesDonationsList;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(String successCode) {
        this.successCode = successCode;
    }

    public double getTotalDonationAmount() {
        return totalDonationAmount;
    }

    public void setTotalDonationAmount(double totalDonationAmount) {
        this.totalDonationAmount = totalDonationAmount;
    }
}
