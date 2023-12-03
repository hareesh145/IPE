package com.indiapoliticaledge.network.responsemodel;

import java.io.Serializable;
import java.util.ArrayList;

public class ViewPhotosResponse implements Serializable {

    public boolean success;
    public ArrayList<CandidatesAVImagesList> candidatesAVImagesList;
    public String successCode;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<CandidatesAVImagesList> getCandidatesAVImagesList() {
        return candidatesAVImagesList;
    }

    public void setCandidatesAVImagesList(ArrayList<CandidatesAVImagesList> candidatesAVImagesList) {
        this.candidatesAVImagesList = candidatesAVImagesList;
    }

    public String getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(String successCode) {
        this.successCode = successCode;
    }


    @Override
    public String toString() {
        return "ViewPhotosResponse{" +
                "success=" + success +
                ", candidatesAVImagesList=" + candidatesAVImagesList +
                ", successCode='" + successCode + '\'' +
                '}';
    }
}
