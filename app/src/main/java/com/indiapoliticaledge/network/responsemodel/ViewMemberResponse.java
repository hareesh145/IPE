package com.indiapoliticaledge.network.responsemodel;

import com.indiapoliticaledge.model.UserInfo;

import java.io.Serializable;

public class ViewMemberResponse implements Serializable {
    public UserInfo userInfo;
    public boolean success;
    public String successCode;
    public String constituencyMap;
    public String constituencyState;
    public String constituencyDistrict;
    public String constituencyCountry;

    public String getConstituencyMap() {
        return constituencyMap;
    }

    public void setConstituencyMap(String constituencyMap) {
        this.constituencyMap = constituencyMap;
    }

    public String getConstituencyState() {
        return constituencyState;
    }

    public void setConstituencyState(String constituencyState) {
        this.constituencyState = constituencyState;
    }

    public String getConstituencyDistrict() {
        return constituencyDistrict;
    }

    public void setConstituencyDistrict(String constituencyDistrict) {
        this.constituencyDistrict = constituencyDistrict;
    }

    public String getConstituencyCountry() {
        return constituencyCountry;
    }

    public void setConstituencyCountry(String constituencyCountry) {
        this.constituencyCountry = constituencyCountry;
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

    public String getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(String successCode) {
        this.successCode = successCode;
    }

    @Override
    public String toString() {
        return "ViewMemberResponse{" +
                "userInfo=" + userInfo +
                ", success=" + success +
                ", successCode='" + successCode + '\'' +
                ", constituencyMap='" + constituencyMap + '\'' +
                ", constituencyState='" + constituencyState + '\'' +
                ", constituencyDistrict='" + constituencyDistrict + '\'' +
                ", constituencyCountry='" + constituencyCountry + '\'' +
                '}';
    }
}
