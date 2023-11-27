package com.indiapoliticaledge.network.responsemodel;

public class CandidateDonateResponse {

    public boolean success;
    public CandidatesDonationInfo candidatesDonationInfo;
    public String successCode;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public CandidatesDonationInfo getCandidatesDonationInfo() {
        return candidatesDonationInfo;
    }

    public void setCandidatesDonationInfo(CandidatesDonationInfo candidatesDonationInfo) {
        this.candidatesDonationInfo = candidatesDonationInfo;
    }

    public String getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(String successCode) {
        this.successCode = successCode;
    }

    @Override
    public String toString() {
        return "CandidateDonateResponse{" +
                "success=" + success +
                ", candidatesDonationInfo=" + candidatesDonationInfo +
                ", successCode='" + successCode + '\'' +
                '}';
    }
}
