package com.indiapoliticaledge.network.responsemodel;

import java.io.Serializable;
import java.util.ArrayList;

public class DistrictResponse implements Serializable {

    public ArrayList<DistrictsList> districtsList;
    public boolean success;
    public String successCode;

    public ArrayList<DistrictsList> getDistrictsList() {
        return districtsList;
    }

    public void setDistrictsList(ArrayList<DistrictsList> districtsList) {
        this.districtsList = districtsList;
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
        return "DistrictResponse{" +
                "districtsList=" + districtsList +
                ", success=" + success +
                ", successCode='" + successCode + '\'' +
                '}';
    }
}
