package com.indiapoliticaledge.network.responsemodel;

import java.io.Serializable;
import java.util.ArrayList;

public class LeaderRatingResponse implements Serializable {

    public boolean success;
    public ArrayList<UserRatingsList> userRatingsList;
    public double userRating;
    public String successCode;
}
