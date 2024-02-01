package com.indiapoliticaledge.model;

import java.io.Serializable;
import java.util.ArrayList;

public class BoothLevelResponse implements Serializable {
    public ArrayList<BoothlevelMgmtInfo> boothlevelMgmtList;
    public boolean success;
    public String successCode;
}
