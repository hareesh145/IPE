package com.indiapoliticaledge.model;

import java.io.Serializable;

public class DepartmentsList implements Serializable {

    public int departmentId;
    public String departmentName;
    public String departmentNameLang;
    public String departmentDescription;
    public String deleteDate;
    public String deleteFlag;

    public String getDepartmentNameLang() {
        return departmentNameLang;
    }

    public void setDepartmentNameLang(String departmentNameLang) {
        this.departmentNameLang = departmentNameLang;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentDescription() {
        return departmentDescription;
    }

    public void setDepartmentDescription(String departmentDescription) {
        this.departmentDescription = departmentDescription;
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
        return "DepartmentsList{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", departmentDescription='" + departmentDescription + '\'' +
                ", deleteDate='" + deleteDate + '\'' +
                ", deleteFlag='" + deleteFlag + '\'' +
                '}';
    }
}
