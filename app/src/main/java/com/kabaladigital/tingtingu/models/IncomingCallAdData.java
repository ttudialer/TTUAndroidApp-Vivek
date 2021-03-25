package com.kabaladigital.tingtingu.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "ad_table")
public class IncomingCallAdData {


    @PrimaryKey()
    @NonNull
    @SerializedName("campId")
    @Expose
    private String campId;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("clientName")
    @Expose
    private String clientName;
    @SerializedName("adCategory")
    @Expose
    private String adCategory;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("agentName")
    @Expose
    private String agentName;
    @SerializedName("adType")
    @Expose
    private String adType;
    @SerializedName("videoSize")
    @Expose
    private String videoSize;
    @SerializedName("usersadPublished")
    @Expose
    private Integer usersadPublished;
    @SerializedName("adDuration")
    @Expose
    private Integer adDuration;
    @SerializedName("maxplayDuration")
    @Expose
    private Integer maxplayDuration;
    @SerializedName("uploadFile")
    @Expose
    private String uploadFile;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("campaignName")
    @Expose
    private String campaignName;
    @SerializedName("filterAds")
    @Expose
    private String filterAds;
    @SerializedName("publicId")
    @Expose
    private String publicId;
    private String uri;
    @SerializedName("callToAction")
    @Expose
    private String callToAction;
    @SerializedName("adCountPerUser")
    @Expose
    private Integer adCountPerUser;
    @SerializedName("adMaxDurUserPerDay")
    @Expose
    private Integer adMaxDurUserPerDay;
    @SerializedName("adTotalCount")
    @Expose
    private Integer adTotalCount;
    @SerializedName("advSource")
    @Expose
    private String advSource;
    @SerializedName("adPlayDurForEachPlay")
    @Expose
    private Integer adPlayDurForEachPlay;
    @SerializedName("callerId")
    @Expose
    private long callerId;

    public IncomingCallAdData() {
    }

    public IncomingCallAdData(@NonNull String campId, String id, String name, String clientName, String adCategory, String startDate, String endDate, String agentName, String adType, String videoSize, Integer usersadPublished, Integer adDuration, Integer maxplayDuration, String uploadFile, Integer v, Integer status, String campaignName, String filterAds, String publicId, String uri, String callToAction, Integer adCountPerUser, Integer adMaxDurUserPerDay, Integer adTotalCount, String advSource) {
        this.campId = campId;
        this.id = id;
        this.name = name;
        this.clientName = clientName;
        this.adCategory = adCategory;
        this.startDate = startDate;
        this.endDate = endDate;
        this.agentName = agentName;
        this.adType = adType;
        this.videoSize = videoSize;
        this.usersadPublished = usersadPublished;
        this.adDuration = adDuration;
        this.maxplayDuration = maxplayDuration;
        this.uploadFile = uploadFile;
        this.v = v;
        this.status = status;
        this.campaignName = campaignName;
        this.filterAds = filterAds;
        this.publicId = publicId;
        this.uri = uri;
        this.callToAction = callToAction;
        this.adCountPerUser = adCountPerUser;
        this.adMaxDurUserPerDay = adMaxDurUserPerDay;
        this.adTotalCount = adTotalCount;
        this.advSource = advSource;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getCampId() {
        return campId;
    }

    public void setCampId(String campId) {
        this.campId = campId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAdCategory() {
        return adCategory;
    }

    public void setAdCategory(String adCategory) {
        this.adCategory = adCategory;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }

    public String getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(String videoSize) {
        this.videoSize = videoSize;
    }

    public Integer getUsersadPublished() {
        return usersadPublished;
    }

    public void setUsersadPublished(Integer usersadPublished) {
        this.usersadPublished = usersadPublished;
    }

    public Integer getAdDuration() {
        return adDuration;
    }

    public void setAdDuration(Integer adDuration) {
        this.adDuration = adDuration;
    }

    public Integer getMaxplayDuration() {
        return maxplayDuration;
    }

    public void setMaxplayDuration(Integer maxplayDuration) {
        this.maxplayDuration = maxplayDuration;
    }

    public String getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(String uploadFile) {
        this.uploadFile = uploadFile;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer v) {
        this.status = status;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getFilterAds() {
        return filterAds;
    }

    public void setFilterAds(String filterAds) {
        this.filterAds = filterAds;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public Integer getAdCountPerUser() {
        return adCountPerUser;
    }

    public void setAdCountPerUser(Integer adCountPerUser) {
        this.adCountPerUser = adCountPerUser;
    }

    public Integer getAdMaxDurUserPerDay() {
        return adMaxDurUserPerDay;
    }

    public void setAdMaxDurUserPerDay(Integer adMaxDurUserPerDay) {
        this.adMaxDurUserPerDay = adMaxDurUserPerDay;
    }

    public Integer getAdTotalCount() {
        return adTotalCount;
    }

    public void setAdTotalCount(Integer adTotalCount) {
        this.adTotalCount = adTotalCount;
    }

    public String getAdvSource() {
        return advSource;
    }

    public void setAdvSource(String advSource) {
        this.advSource = advSource;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCallToAction() {
        return callToAction;
    }

    public void setCallToAction(String callToAction) {
        this.callToAction = callToAction;
    }

    public Integer getAdPlayDurForEachPlay() {
        return adPlayDurForEachPlay;
    }

    public void setAdPlayDurForEachPlay(Integer adPlayDurForEachPlay) {
        this.adPlayDurForEachPlay = adPlayDurForEachPlay;
    }

    public long getCallerId() {
        return callerId;
    }

    public void setCallerId(long callerId) {
        this.callerId = callerId;
    }
}
