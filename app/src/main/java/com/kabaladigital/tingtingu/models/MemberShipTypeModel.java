package com.kabaladigital.tingtingu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberShipTypeModel {
    @SerializedName("membership")
    @Expose
    private Integer membership;
    @SerializedName("activeDays")
    @Expose
    private Integer activeDays;
    @SerializedName("daysToPrem")
    @Expose
    private Integer daysToPrem;

    public Integer getMembership() {
        return membership;
    }

    public void setMembership(Integer membership) {
        this.membership = membership;
    }

    public Integer getActiveDays() {
        return activeDays;
    }

    public void setActiveDays(Integer activeDays) {
        this.activeDays = activeDays;
    }

    public Integer getDaysToPrem() {
        return daysToPrem;
    }

    public void setDaysToPrem(Integer daysToPrem) {
        this.daysToPrem = daysToPrem;
    }
}
