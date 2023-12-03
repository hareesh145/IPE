package com.indiapoliticaledge.network.responsemodel;

import com.google.gson.annotations.SerializedName;
import com.indiapoliticaledge.model.ConstituencyDepartmentsList;

import java.util.ArrayList;

public class FilteredVCDByYearsResponse {
    @SerializedName("filterConstituencyDepartmentsList")
    public ArrayList<ConstituencyDepartmentsList> filterConstituencyDepartmentsList;
    public boolean success;
    @SerializedName("currentConstituencyDepartmentsList")
    public ArrayList<ConstituencyDepartmentsList> currentConstituencyDepartmentsList;
    public String successCode;

    public ArrayList<ConstituencyDepartmentsList> getFilterConstituencyDepartmentsList() {
        return filterConstituencyDepartmentsList;
    }

    public void setFilterConstituencyDepartmentsList(ArrayList<ConstituencyDepartmentsList> filterConstituencyDepartmentsList) {
        this.filterConstituencyDepartmentsList = filterConstituencyDepartmentsList;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<ConstituencyDepartmentsList> getCurrentConstituencyDepartmentsList() {
        return currentConstituencyDepartmentsList;
    }

    public void setCurrentConstituencyDepartmentsList(ArrayList<ConstituencyDepartmentsList> currentConstituencyDepartmentsList) {
        this.currentConstituencyDepartmentsList = currentConstituencyDepartmentsList;
    }

    public String getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(String successCode) {
        this.successCode = successCode;
    }

    @Override
    public String toString() {
        return "FilteredVCDByYearsResponse{" +
                "filterConstituencyDepartmentsList=" + filterConstituencyDepartmentsList +
                ", success=" + success +
                ", currentConstituencyDepartmentsList=" + currentConstituencyDepartmentsList +
                ", successCode='" + successCode + '\'' +
                '}';
    }
}
