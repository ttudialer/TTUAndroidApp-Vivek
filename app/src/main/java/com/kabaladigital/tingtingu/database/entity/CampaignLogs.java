package com.kabaladigital.tingtingu.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "campaign_ad_logs_table")
public class CampaignLogs {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer id;
    private String campId;
    private String adCategory;
    private String adType;
    private Integer instance;
    private String callTime;
    private String callDate;
    private String startDateTime;
    private String endDateTime;
    private String callType;
    private String action;
    private String callStatus;
    private Integer playDuration;
    private boolean isSynced;
    private String campType;

    private String callToActionTime;
    private int callToAction;

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public String getCampId() {
        return campId;
    }

    public void setCampId(@NonNull String campId) {
        this.campId = campId;
    }

    public String getAdCategory() {
        return adCategory;
    }

    public void setAdCategory(String adCategory) {
        this.adCategory = adCategory;
    }

    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }

    public Integer getInstance() {
        return instance;
    }

    public void setInstance(Integer instance) {
        this.instance = instance;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public String getCallDate() {
        return callDate;
    }

    public void setCallDate(String callDate) {
        this.callDate = callDate;
    }

    public Integer getPlayDuration() {
        return playDuration;
    }

    public void setPlayDuration(Integer playDuration) {
        this.playDuration = playDuration;
    }

    public boolean isSynced() {
        return isSynced;
    }

    public void setSynced(boolean synced) {
        isSynced = synced;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getCallStatus() {
        return callStatus;
    }

    public void setCallStatus(String callStatus) {
        this.callStatus = callStatus;
    }

    public String getCampType() {
        return campType;
    }

    public void setCampType(String campType) {
        this.campType = campType;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCallToActionTime() {
        return callToActionTime;
    }

    public void setCallToActionTime(String callToActionTime) {
        this.callToActionTime = callToActionTime;
    }

    public int getCallToAction() {
        return callToAction;
    }

    public void setCallToAction(int callToAction) {
        this.callToAction = callToAction;
    }
}
