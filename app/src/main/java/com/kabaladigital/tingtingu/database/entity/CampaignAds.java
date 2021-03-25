package com.kabaladigital.tingtingu.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "campaign_ad_table")
public class CampaignAds {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String adURL;
    private String adURI;
    private int adType;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdURL() {
        return adURL;
    }

    public void setAdURL(String adURL) {
        this.adURL = adURL;
    }

    public String getAdURI() {
        return adURI;
    }

    public void setAdURI(String adURI) {
        this.adURI = adURI;
    }

    public int getAdType() {
        return adType;
    }

    public void setAdType(int adType) {
        this.adType = adType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
