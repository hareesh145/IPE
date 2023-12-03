package com.indiapoliticaledge.model;

public class CountriesList {
    public int countryId;
    public String countryName;
    public String deleteDate;
    public String deleteFlag;

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
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


    @Override
    public String toString() {
        return "CountriesList{" +
                "countryId=" + countryId +
                ", countryName='" + countryName + '\'' +
                ", deleteDate='" + deleteDate + '\'' +
                ", deleteFlag='" + deleteFlag + '\'' +
                '}';
    }
}
