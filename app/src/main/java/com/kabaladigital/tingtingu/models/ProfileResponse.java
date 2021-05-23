package com.kabaladigital.tingtingu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileResponse {
    @SerializedName("incCallAdvs")
    @Expose
    private List<IncomingCallAdData> incCallAdvs = null;
    @SerializedName("popupAdvs")
    @Expose
    private List<IncomingCallAdData> popupAdvs = null;
    @SerializedName("outgoingAdvs")
    @Expose
    private List<IncomingCallAdData> outgoingAdvs = null;
    @SerializedName("inprogressAdvs")
    @Expose
    private List<IncomingCallAdData> inprogressAdvs = null;

    @SerializedName("profileAdvs")
    @Expose
    private List<ProfileAdv> profileAdvs = null;



    public List<IncomingCallAdData> getIncCallAdvs() {
        return incCallAdvs;
    }

    public void setIncCallAdvs(List<IncomingCallAdData> incCallAdvs) {
        this.incCallAdvs = incCallAdvs;
    }

    public List<IncomingCallAdData> getPopupAdvs() {
        return popupAdvs;
    }

    public void setPopupAdvs(List<IncomingCallAdData> popupAdvs) {
        this.popupAdvs = popupAdvs;
    }

    public List<IncomingCallAdData> getOutgoingAdvs() {
        return outgoingAdvs;
    }

    public void setOutgoingAdvs(List<IncomingCallAdData> outgoingAdvs) {
        this.outgoingAdvs = outgoingAdvs;
    }

    public List<IncomingCallAdData> getInprogressAdvs() {
        return inprogressAdvs;
    }

    public void setInprogressAdvs(List<IncomingCallAdData> inprogressAdvs) {
        this.inprogressAdvs = inprogressAdvs;
    }

    public List<ProfileAdv> getProfileAdvs() {
        return profileAdvs;
    }

    public void setProfileAdvs(List<ProfileAdv> profileAdvs) {
        this.profileAdvs = profileAdvs;
    }


}

