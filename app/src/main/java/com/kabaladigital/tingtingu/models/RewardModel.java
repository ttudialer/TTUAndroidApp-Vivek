package com.kabaladigital.tingtingu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RewardModel {
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("points")
    @Expose
    private float points;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("balance")
    @Expose
    private double balance;

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("tType")
    @Expose
    private Integer tType;
    @SerializedName("mobileNumber")
    @Expose
    private long mobileNumber;
    @SerializedName("surveyId")
    @Expose
    private String surveyId;


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer gettType() {
        return tType;
    }

    public void settType(Integer tType) {
        this.tType = tType;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }
}
