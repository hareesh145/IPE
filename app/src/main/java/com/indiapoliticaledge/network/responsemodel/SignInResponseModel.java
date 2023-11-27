package com.indiapoliticaledge.network.responsemodel;

import com.indiapoliticaledge.model.CountriesList;
import com.indiapoliticaledge.model.UserInfo;

import java.util.ArrayList;

public class SignInResponseModel {

    public UserInfo userInfo;

    public boolean success;
    public ArrayList<CountriesList> countriesList;

    public String successCode;


    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<CountriesList> getCountriesList() {
        return countriesList;
    }

    public void setCountriesList(ArrayList<CountriesList> countriesList) {
        this.countriesList = countriesList;
    }

    public String getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(String successCode) {
        this.successCode = successCode;
    }

    @Override
    public String toString() {
        return "SignInResponseModel{" +
                "userInfo=" + userInfo +
                ", success=" + success +
                ", countriesList=" + countriesList +
                ", successCode='" + successCode + '\'' +
                '}';
    }
}
