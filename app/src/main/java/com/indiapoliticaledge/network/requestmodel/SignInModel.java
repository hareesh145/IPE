package com.indiapoliticaledge.network.requestmodel;

import java.io.Serializable;

public class SignInModel implements Serializable {

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
