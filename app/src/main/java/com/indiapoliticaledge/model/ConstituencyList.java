package com.indiapoliticaledge.model;

public class ConstituencyList {


    public int id;
    public String name;
    public String pincode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        return "ConstituencyList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pincode='" + pincode + '\'' +
                '}';
    }
}
