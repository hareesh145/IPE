package com.indiapoliticaledge.network.responsemodel;

import java.io.Serializable;
import java.util.ArrayList;

public class StatesResponse implements Serializable {

    public boolean success;
    public ArrayList<StatesList> statesList;
    public String successCode;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<StatesList> getStatesList() {
        return statesList;
    }

    public void setStatesList(ArrayList<StatesList> statesList) {
        this.statesList = statesList;
    }

    public String getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(String successCode) {
        this.successCode = successCode;
    }

    @Override
    public String toString() {
        return "StatesResponse{" +
                "success=" + success +
                ", statesList=" + statesList +
                ", successCode='" + successCode + '\'' +
                '}';
    }
}
