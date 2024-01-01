package com.indiapoliticaledge.network.responsemodel;

import java.io.Serializable;
import java.util.ArrayList;

public class LatestNewsResponse implements Serializable {

    public ArrayList<NewsList> newsList;
    public boolean success;
    public String constituencyInfo;
    public String successCode;
}
