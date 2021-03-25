package com.kabaladigital.tingtingu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GlobalVariableModel {

    @SerializedName("refer")
    @Expose
    private Refer refer;
    @SerializedName("dailyThreshold")
    @Expose
    private Integer dailyThreshold;

    public Refer getRefer() {
        return refer;
    }

    public void setRefer(Refer refer) {
        this.refer = refer;
    }

    public Integer getDailyThreshold() {
        return dailyThreshold;
    }

    public void setDailyThreshold(Integer dailyThreshold) {
        this.dailyThreshold = dailyThreshold;
    }


    public class Refer {

        @SerializedName("dailyRewardPoints")
        @Expose
        private Double dailyRewardPoints;
        @SerializedName("referrerRewardDays")
        @Expose
        private Integer referrerRewardDays;
        @SerializedName("referrerPoints")
        @Expose
        private Double referrerPoints;
        @SerializedName("refereeRewardDays")
        @Expose
        private Integer refereeRewardDays;
        @SerializedName("refereePoints")
        @Expose
        private Double refereePoints;

        public Double getDailyRewardPoints() {
            return dailyRewardPoints;
        }

        public void setDailyRewardPoints(Double dailyRewardPoints) {
            this.dailyRewardPoints = dailyRewardPoints;
        }

        public Integer getReferrerRewardDays() {
            return referrerRewardDays;
        }

        public void setReferrerRewardDays(Integer referrerRewardDays) {
            this.referrerRewardDays = referrerRewardDays;
        }

        public Double getReferrerPoints() {
            return referrerPoints;
        }

        public void setReferrerPoints(Double referrerPoints) {
            this.referrerPoints = referrerPoints;
        }

        public Integer getRefereeRewardDays() {
            return refereeRewardDays;
        }

        public void setRefereeRewardDays(Integer refereeRewardDays) {
            this.refereeRewardDays = refereeRewardDays;
        }

        public Double getRefereePoints() {
            return refereePoints;
        }

        public void setRefereePoints(Double refereePoints) {
            this.refereePoints = refereePoints;
        }

    }
}
