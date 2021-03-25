package com.kabaladigital.tingtingu.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "campaign_ad_play_order_table")
public class CampaignAdsPlayOrder {

    @PrimaryKey()
    @NonNull
    private String campId;
    private Integer status;
    private Integer playCount;
    private long startDate;
    private long endDate;
    private Integer CurrentDurationCount;
    private Integer MaxDurationCount;
    private Integer adDuration;
    private Integer todayAdCount;
    private String todayDate;
    private String CampCategory;
    private long callerId;
    private String clientName;

    @NonNull
    public String getCampId() {
        return campId;
    }

    public void setCampId(String campId) {
        this.campId = campId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public Integer getCurrentDurationCount() {
        return CurrentDurationCount;
    }

    public void setCurrentDurationCount(Integer currentDurationCount) {
        CurrentDurationCount = currentDurationCount;
    }

    public Integer getMaxDurationCount() {
        return MaxDurationCount;
    }

    public void setMaxDurationCount(Integer maxDurationCount) {
        MaxDurationCount = maxDurationCount;
    }

    public String getCampCategory() {
        return CampCategory;
    }

    public void setCampCategory(String campCategory) {
        CampCategory = campCategory;
    }

    public Integer getAdDuration() {
        return adDuration;
    }

    public void setAdDuration(Integer adDuration) {
        this.adDuration = adDuration;
    }

    public String getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(String todayDate) {
        this.todayDate = todayDate;
    }

    public Integer getTodayAdCount() {
        return todayAdCount;
    }

    public void setTodayAdCount(Integer todayAdCount) {
        this.todayAdCount = todayAdCount;
    }

    public long getCallerId() {
        return callerId;
    }

    public void setCallerId(long callerId) {
        this.callerId = callerId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
