package com.indiapoliticaledge.network.responsemodel;

import com.indiapoliticaledge.model.CountriesList;
import com.indiapoliticaledge.model.NoticeMessagesList;
import com.indiapoliticaledge.model.UserInfo;

import java.io.Serializable;
import java.util.ArrayList;

public class SignInResponseModel implements Serializable {

    public UserInfo userInfo;

    public boolean success;
    public ArrayList<CountriesList> countriesList;

    public String successCode;

    public String errorMessage;


    public ArrayList<ConstituencyImage> constituencyImages;
    public String constituencyCountry;
    public ArrayList<MyAV> myAVs;
    public ArrayList<NoticeMessagesList> noticeMessagesList;
    public String constituencyDistrict;
    public ArrayList<MyImage> myImages;
    public ArrayList<ConstituencyAV> constituencyAVs;
    public String constituencyState;

    public MlaInfo mlaInfo;

    public String constituencyName;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

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
                ", errorMessage='" + errorMessage + '\'' +
                ", constituencyImages=" + constituencyImages +
                ", constituencyCountry='" + constituencyCountry + '\'' +
                ", myAVs=" + myAVs +
                ", noticeMessagesList=" + noticeMessagesList +
                ", constituencyDistrict='" + constituencyDistrict + '\'' +
                ", myImages=" + myImages +
                ", constituencyAVs=" + constituencyAVs +
                ", constituencyState='" + constituencyState + '\'' +
                '}';
    }
}
