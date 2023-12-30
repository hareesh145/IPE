package com.indiapoliticaledge.model;

import java.util.ArrayList;

public class NoticeBoardResponse {

    public boolean success;
    public ArrayList<NoticeMessagesList> noticeMessagesList;
    public String successCode;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<NoticeMessagesList> getNoticeMessagesList() {
        return noticeMessagesList;
    }

    public void setNoticeMessagesList(ArrayList<NoticeMessagesList> noticeMessagesList) {
        this.noticeMessagesList = noticeMessagesList;
    }

    public String getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(String successCode) {
        this.successCode = successCode;
    }
}
