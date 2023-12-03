package com.indiapoliticaledge.network.responsemodel;

import java.io.Serializable;

public class ConstituencyMapResponse implements Serializable {

    public String constituencyName;
    public boolean success;
    public String constituencyMap;
    public String constituencyState;
    public String constituencyCountry;
    public String constituencyDistrict;
    public String successCode;

    public String getConstituencyName() {
        return constituencyName;
    }

    public void setConstituencyName(String constituencyName) {
        this.constituencyName = constituencyName;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

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

    public String getConstituencyCountry() {
        return constituencyCountry;
    }

    public void setConstituencyCountry(String constituencyCountry) {
        this.constituencyCountry = constituencyCountry;
    }

    public String getConstituencyDistrict() {
        return constituencyDistrict;
    }

    public void setConstituencyDistrict(String constituencyDistrict) {
        this.constituencyDistrict = constituencyDistrict;
    }

    public String getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(String successCode) {
        this.successCode = successCode;
    }

    @Override
    public String toString() {
        return "ConstituencyMapResponse{" +
                "constituencyName='" + constituencyName + '\'' +
                ", success=" + success +
                ", constituencyMap='" + constituencyMap + '\'' +
                ", constituencyState='" + constituencyState + '\'' +
                ", constituencyCountry='" + constituencyCountry + '\'' +
                ", constituencyDistrict='" + constituencyDistrict + '\'' +
                ", successCode='" + successCode + '\'' +
                '}';
    }
}
