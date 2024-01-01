package com.indiapoliticaledge.network.responsemodel;

import java.io.Serializable;

public class NewsList implements Serializable {

    public int newsId;
    public int userId;
    public int constituencyId;
    public String newsTitle;
    public String newsDescription;
    public String createdDate;
    public Object deleteDate;
    public String deleteFlag;
    public int candidateId;
    public String reviewFlag;
    public String newsPic;
    public Candidates candidates;
    public Users users;

    @Override
    public String toString() {
        return "NewsList{" +
                "newsId=" + newsId +
                ", userId=" + userId +
                ", constituencyId=" + constituencyId +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsDescription='" + newsDescription + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", deleteDate=" + deleteDate +
                ", deleteFlag='" + deleteFlag + '\'' +
                ", candidateId=" + candidateId +
                ", reviewFlag='" + reviewFlag + '\'' +
                ", newsPic='" + newsPic + '\'' +
                ", candidates=" + candidates +
                ", users=" + users +
                '}';
    }
}
