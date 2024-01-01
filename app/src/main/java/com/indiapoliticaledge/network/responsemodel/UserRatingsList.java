package com.indiapoliticaledge.network.responsemodel;

import java.io.Serializable;

public class UserRatingsList implements Serializable {

    public int userRatingId;
    public int userId;
    public int candidateId;
    public int rating;
    public String createdDate;
    public String modifiedDate;
    public Candidates candidates;
}
