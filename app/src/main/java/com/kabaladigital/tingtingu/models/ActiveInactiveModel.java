package com.kabaladigital.tingtingu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ActiveInactiveModel {

    @SerializedName("isActive")
    @Expose
    private Boolean isActive;
    @SerializedName("tempDisabled")
    @Expose
    private Integer tempDisabled;
    @SerializedName("dailyStatus")
    @Expose
    private List<Dailystatus> dailyStatus = null;

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getTempDisabled() {
        return tempDisabled;
    }

    public void setTempDisabled(Integer tempDisabled) {
        this.tempDisabled = tempDisabled;
    }

    public List<Dailystatus> getDailyStatus() {
        return dailyStatus;
    }

    public void setDailyStatus(List<Dailystatus> dailyStatus) {
        this.dailyStatus = dailyStatus;
    }

    public class Dailystatus {

        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("counts")
        @Expose
        private Integer counts;
        @SerializedName("isSynced")
        @Expose
        private Boolean isSynced;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Integer getCounts() {
            return counts;
        }

        public void setCounts(Integer counts) {
            this.counts = counts;
        }

        public Boolean getIsSynced() {
            return isSynced;
        }

        public void setIsSynced(Boolean isSynced) {
            this.isSynced = isSynced;
        }

    }
}
