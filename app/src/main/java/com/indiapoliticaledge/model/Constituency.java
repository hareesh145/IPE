package com.indiapoliticaledge.model;

import java.io.Serializable;

public class Constituency implements Serializable {
    public int constituencyId;
    public int districtId;
    public String constituencyName;
    public String deleteDate;

    public String deleteFlag;
    public String pincode;

    public int getConstituencyId() {
        return constituencyId;
    }

    public void setConstituencyId(int constituencyId) {
        this.constituencyId = constituencyId;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getConstituencyName() {
        return constituencyName;
    }

    public void setConstituencyName(String constituencyName) {
        this.constituencyName = constituencyName;
    }

    public String getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(String deleteDate) {
        this.deleteDate = deleteDate;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        return "Constituency{" +
                "constituencyId=" + constituencyId +
                ", districtId=" + districtId +
                ", constituencyName='" + constituencyName + '\'' +
                ", deleteDate='" + deleteDate + '\'' +
                ", deleteFlag='" + deleteFlag + '\'' +
                ", pincode='" + pincode + '\'' +
                '}';
    }
}
