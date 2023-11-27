package com.indiapoliticaledge.network.requestmodel;

public class CDevelopmentRequest {

    public int userId;
    public int constituencyId;
    public int departmentId;
    public int year;
    public String workDescription;
    public double workAmount;
    public String workLocation;
    public String remarks;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getConstituencyId() {
        return constituencyId;
    }

    public void setConstituencyId(int constituencyId) {
        this.constituencyId = constituencyId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    public double getWorkAmount() {
        return workAmount;
    }

    public void setWorkAmount(double workAmount) {
        this.workAmount = workAmount;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "CDevelopmentRequest{" +
                "userId=" + userId +
                ", constituencyId=" + constituencyId +
                ", departmentId=" + departmentId +
                ", year=" + year +
                ", workDescription='" + workDescription + '\'' +
                ", workAmount=" + workAmount +
                ", workLocation='" + workLocation + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
