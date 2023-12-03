package com.indiapoliticaledge.network.responsemodel;

import com.indiapoliticaledge.model.ConstituencyDepartmentsList;

import java.io.Serializable;
import java.util.ArrayList;

public class VDevelopmentResponse implements Serializable {

    public ArrayList<ConstituencyDepartmentsList> constituencyDepartmentsList;
    public boolean success;
    public String successCode;

    public ArrayList<ConstituencyDepartmentsList> getConstituencyDepartmentsList() {
        return constituencyDepartmentsList;
    }

    public void setConstituencyDepartmentsList(ArrayList<ConstituencyDepartmentsList> constituencyDepartmentsList) {
        this.constituencyDepartmentsList = constituencyDepartmentsList;
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
        return "VDevelopmentResponse{" +
                "constituencyDepartmentsList=" + constituencyDepartmentsList +
                ", success=" + success +
                ", successCode='" + successCode + '\'' +
                '}';
    }
}
