package com.kabaladigital.tingtingu.response_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MobileNumberResponse {
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("isNew")
    @Expose
    private Boolean isNew;
    @SerializedName("haveProfile")
    @Expose
    private Boolean haveProfile;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("refCodeApplied")
    @Expose
    private Boolean refCodeApplied;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public Boolean getHaveProfile() {
        return haveProfile;
    }

    public void setHaveProfile(Boolean haveProfile) {
        this.haveProfile = haveProfile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

    public Boolean getRefCodeApplied() {
        return refCodeApplied;
    }

    public void setRefCodeApplied(Boolean refCodeApplied) {
        this.refCodeApplied = refCodeApplied;
    }
}
