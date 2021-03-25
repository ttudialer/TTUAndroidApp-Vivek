package com.kabaladigital.tingtingu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MobileDetailModel {

//    @SerializedName("mobileNumber")
//    @Expose
//    private Integer mobileNumber;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("state")
    @Expose
    private String state;

//    public Integer getMobileNumber() {
//        return mobileNumber;
//    }
//
//    public void setMobileNumber(Integer mobileNumber) {
//        this.mobileNumber = mobileNumber;
//    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
