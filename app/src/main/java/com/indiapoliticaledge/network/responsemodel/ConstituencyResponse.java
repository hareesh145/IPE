package com.indiapoliticaledge.network.responsemodel;

import com.indiapoliticaledge.model.ConstituencyList;

import java.io.Serializable;
import java.util.ArrayList;

public class ConstituencyResponse implements Serializable {

    public boolean success;
    public ArrayList<ConstituencyList> constituencyList;
    public String successCode;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<ConstituencyList> getConstituencyList() {
        return constituencyList;
    }

    public void setConstituencyList(ArrayList<ConstituencyList> constituencyList) {
        this.constituencyList = constituencyList;
    }

    public String getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(String successCode) {
        this.successCode = successCode;
    }

    @Override
    public String toString() {
        return "ConstituencyResponse{" +
                "success=" + success +
                ", constituencyList=" + constituencyList +
                ", successCode='" + successCode + '\'' +
                '}';
    }
}
