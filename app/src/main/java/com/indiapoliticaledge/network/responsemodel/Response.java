package com.indiapoliticaledge.network.responsemodel;

public class Response {

    public boolean success;
    public String successCode;

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
        return "Response{" +
                "success=" + success +
                ", successCode='" + successCode + '\'' +
                '}';
    }
}
