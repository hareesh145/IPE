package com.indiapoliticaledge.network.responsemodel;

public class CandidatesAVImagesList {

    public int candidatesAVImagesId;
    public int constituencyId;
    public int candidateId;
    public String fileType;
    public String fileName;
    public String filePath;
    public String createdDate;
    public String modifiedDate;
    public String deleteFlag;
    public String mandalName;
    public String villageName;

    public int getCandidatesAVImagesId() {
        return candidatesAVImagesId;
    }

    public void setCandidatesAVImagesId(int candidatesAVImagesId) {
        this.candidatesAVImagesId = candidatesAVImagesId;
    }

    public int getConstituencyId() {
        return constituencyId;
    }

    public void setConstituencyId(int constituencyId) {
        this.constituencyId = constituencyId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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

    public String getMandalName() {
        return mandalName;
    }

    public void setMandalName(String mandalName) {
        this.mandalName = mandalName;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    @Override
    public String toString() {
        return "CandidatesAVImagesList{" +
                "candidatesAVImagesId=" + candidatesAVImagesId +
                ", constituencyId=" + constituencyId +
                ", candidateId=" + candidateId +
                ", fileType='" + fileType + '\'' +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", modifiedDate='" + modifiedDate + '\'' +
                ", deleteFlag='" + deleteFlag + '\'' +
                ", mandalName='" + mandalName + '\'' +
                ", villageName='" + villageName + '\'' +
                '}';
    }
}
