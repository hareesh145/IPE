package com.indiapoliticaledge.model;

import java.io.Serializable;
import java.util.ArrayList;

public class VoterMandalResponse implements Serializable {

    public boolean success;
    public ArrayList<VotersList> candidatesList;
}
