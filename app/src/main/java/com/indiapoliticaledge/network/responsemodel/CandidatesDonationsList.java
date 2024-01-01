package com.indiapoliticaledge.network.responsemodel;

import java.io.Serializable;

public class CandidatesDonationsList implements Serializable {

    public int candidatesDonationId;
    public int candidatesId;
    public int userId;
    public int constituencyId;
    public double donationAmount;
    public String donationPurpose;
    public String createdDate;
    public Candidates candidates;
}
