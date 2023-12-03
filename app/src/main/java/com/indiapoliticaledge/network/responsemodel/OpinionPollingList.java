package com.indiapoliticaledge.network.responsemodel;

import java.io.Serializable;

public class OpinionPollingList implements Serializable {

    public int opinionPollingId;
    public int userOpinionId;
    public int candidateId;
    public String opinionFlag;
    public String createdDate;
    public String modifiedDate;
    public String deleteFlag;

    public int getOpinionPollingId() {
        return opinionPollingId;
    }

    public void setOpinionPollingId(int opinionPollingId) {
        this.opinionPollingId = opinionPollingId;
    }

    public int getUserOpinionId() {
        return userOpinionId;
    }

    public void setUserOpinionId(int userOpinionId) {
        this.userOpinionId = userOpinionId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getOpinionFlag() {
        return opinionFlag;
    }

    public void setOpinionFlag(String opinionFlag) {
        this.opinionFlag = opinionFlag;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "OpinionPollingList{" +
                "opinionPollingId=" + opinionPollingId +
                ", userOpinionId=" + userOpinionId +
                ", candidateId=" + candidateId +
                ", opinionFlag='" + opinionFlag + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", modifiedDate='" + modifiedDate + '\'' +
                ", deleteFlag='" + deleteFlag + '\'' +
                '}';
    }
}
