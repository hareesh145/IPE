package com.indiapoliticaledge.network.responsemodel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OpinionPollingResponse {

    @SerializedName("OpinionPollingList")
    public ArrayList<OpinionPollingList> opinionPollingList;
    public boolean success;
    public String successCode;

    public ArrayList<OpinionPollingList> getOpinionPollingList() {
        return opinionPollingList;
    }

    public void setOpinionPollingList(ArrayList<OpinionPollingList> opinionPollingList) {
        this.opinionPollingList = opinionPollingList;
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

    @Override
    public String toString() {
        return "OpinionPollingResponse{" +
                "opinionPollingList=" + opinionPollingList +
                ", success=" + success +
                ", successCode='" + successCode + '\'' +
                '}';
    }
}
