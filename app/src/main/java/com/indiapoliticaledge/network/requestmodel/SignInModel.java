package com.indiapoliticaledge.network.requestmodel;

public class SignInModel {

    public String mobileNumber;


    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "SignInModel{" +
                "mobileNumber='" + mobileNumber + '\'' +
                '}';
    }
}
