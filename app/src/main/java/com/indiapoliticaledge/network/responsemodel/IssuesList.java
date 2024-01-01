package com.indiapoliticaledge.network.responsemodel;

import java.io.Serializable;

public class IssuesList implements Serializable {
    public int issueId;
    public int candidateId;
    public int constituencyId;
    public String describeIssue;
    public String sarpanchName;
    public String mobileNumber;
    public String createdDate;
    public Object modifiedDate;
    public String deleteFlag;
    public String mandalName;
    public String villageName;
    public String reviewFlag;
    public Candidates candidates;

}
