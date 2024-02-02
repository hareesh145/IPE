package com.indiapoliticaledge.model;

import java.io.Serializable;
import java.util.ArrayList;

public class PollManagementResponse implements Serializable {
    public boolean success;
    public ArrayList<PollManagementList> pollManagementList;
    public String successCode;
}
