package com.indiapoliticaledge.network.responsemodel;

import com.indiapoliticaledge.model.DepartmentsList;

import java.util.ArrayList;

public class DepartmentResponse {

    public boolean success;
    public ArrayList<DepartmentsList> departmentsList;
    public String successCode;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<DepartmentsList> getDepartmentsList() {
        return departmentsList;
    }

    public void setDepartmentsList(ArrayList<DepartmentsList> departmentsList) {
        this.departmentsList = departmentsList;
    }

    public String getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(String successCode) {
        this.successCode = successCode;
    }


    @Override
    public String toString() {
        return "DepartmentResponse{" +
                "success=" + success +
                ", departmentsList=" + departmentsList +
                ", successCode='" + successCode + '\'' +
                '}';
    }
}
