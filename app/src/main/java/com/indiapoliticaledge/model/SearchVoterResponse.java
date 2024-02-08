package com.indiapoliticaledge.model;

import com.indiapoliticaledge.network.responsemodel.CandidatesList;

import java.io.Serializable;

public class SearchVoterResponse implements Serializable {

    public boolean success;
    public CandidatesList votersInfo;


}
