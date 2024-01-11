package com.indiapoliticaledge.model;

import java.io.Serializable;

public class NoticeMessagesList implements Serializable {

    public int noticeMessagesId;
    public int userId;
    public int constituencyId;
    public String noticeTitle;
    public String noticeMessage;
    public String createdDate;
    public String modifiedDate;
    public String showDate;
    public String noticeImageUrl;
    public String deleteFlag;

}
