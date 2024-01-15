package com.indiapoliticaledge.model;

import java.io.Serializable;

public class ConstituencyDepartmentsList implements Serializable {

    public int constituencyDepartmentId;

    public int constituencyId;
    public int departmentId;
    public int year;
    public String addedDate;
    public String workDescription;
    public double workAmount;
    public String workLocation;
    public String remarks;
    public String modifiedDate;
    public String deleteFlag;
    public String filterYear;
    public Departments departments;


    public int currentYear;
    public String currentWorkDescription;
    public double currentWorkAmount;
    public String currentWorkLocation;


    public int getConstituencyDepartmentId() {
        return constituencyDepartmentId;
    }

    public void setConstituencyDepartmentId(int constituencyDepartmentId) {
        this.constituencyDepartmentId = constituencyDepartmentId;
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

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
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

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getFilterYear() {
        return filterYear;
    }

    public void setFilterYear(String filterYear) {
        this.filterYear = filterYear;
    }

    public Departments getDepartments() {
        return departments;
    }

    public void setDepartments(Departments departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "ConstituencyDepartmentsList{" +
                "constituencyDepartmentId=" + constituencyDepartmentId +
                ", constituencyId=" + constituencyId +
                ", departmentId=" + departmentId +
                ", year=" + year +
                ", addedDate='" + addedDate + '\'' +
                ", workDescription='" + workDescription + '\'' +
                ", workAmount=" + workAmount +
                ", workLocation='" + workLocation + '\'' +
                ", remarks='" + remarks + '\'' +
                ", modifiedDate='" + modifiedDate + '\'' +
                ", deleteFlag='" + deleteFlag + '\'' +
                ", filterYear=" + filterYear +
                ", departments=" + departments +
                '}';
    }
}
