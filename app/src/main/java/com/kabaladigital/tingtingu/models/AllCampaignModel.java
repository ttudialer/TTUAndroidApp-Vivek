package com.kabaladigital.tingtingu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllCampaignModel {

    @SerializedName("incCallAdvs")
    @Expose
    private List<IncomingCallAdData> incCallAds = null;
    @SerializedName("popupAdvs")
    @Expose
    private List<IncomingCallAdData> popupAdvs = null;
    @SerializedName("outgoingAdvs")
    @Expose
    private List<IncomingCallAdData> outgoingAdvs = null;
    @SerializedName("inprogressAdvs")
    @Expose
    private List<IncomingCallAdData> inprogressAdvs = null;

    public List<IncomingCallAdData> getIncCallAds() {
        return incCallAds;
    }

    public void setIncCallAds(List<IncomingCallAdData> incCallAds) {
        this.incCallAds = incCallAds;
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
}
