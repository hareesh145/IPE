package com.indiapoliticaledge.network.responsemodel;

import com.indiapoliticaledge.model.UserInfo;

import java.util.ArrayList;

public class MembersResponse {

    public ArrayList<UserInfo> usersList;
    public boolean success;
    public String successCode;

    public ArrayList<UserInfo> getUsersList() {
        return usersList;
    }

    public void setUsersList(ArrayList<UserInfo> usersList) {
        this.usersList = usersList;
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
        return "MembersResponse{" +
                "usersList=" + usersList +
                ", success=" + success +
                ", successCode='" + successCode + '\'' +
                '}';
    }
}
