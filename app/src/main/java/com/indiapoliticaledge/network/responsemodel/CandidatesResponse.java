package com.indiapoliticaledge.network.responsemodel;

import java.util.ArrayList;

public class CandidatesResponse {

    public boolean success;
    public ArrayList<CandidatesList> candidatesList;
    public MlaInfo mlaInfo;
    public String successCode;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<CandidatesList> getCandidatesList() {
        return candidatesList;
    }

    public void setCandidatesList(ArrayList<CandidatesList> candidatesList) {
        this.candidatesList = candidatesList;
    }

    public MlaInfo getMlaInfo() {
        return mlaInfo;
    }

    public void setMlaInfo(MlaInfo mlaInfo) {
        this.mlaInfo = mlaInfo;
    }

    public String getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(String successCode) {
        this.successCode = successCode;
    }

    @Override
    public String toString() {
        return "CandidatesResponse{" +
                "success=" + success +
                ", candidatesList=" + candidatesList +
                ", mlaInfo=" + mlaInfo +
                ", successCode='" + successCode + '\'' +
                '}';
    }
}
