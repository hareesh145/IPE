package com.indiapoliticaledge.network.responsemodel;

import java.io.Serializable;

public class UserOpinionsList implements Serializable {

    public int userOpinionId;
    public int userId;
    public int constituencyId;
    public String opinionMessage;
    public String createdDate;
    public String mandalName;
    public String villageName;
    public String modifiedDate;
    public String deleteFlag;
}
