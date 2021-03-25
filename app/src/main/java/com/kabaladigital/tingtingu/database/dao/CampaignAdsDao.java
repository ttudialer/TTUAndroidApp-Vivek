package com.kabaladigital.tingtingu.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.kabaladigital.tingtingu.database.entity.CampaignAds;

import java.util.List;

@Dao
public interface CampaignAdsDao {

    @Insert
    void insert(CampaignAds campaignAds);

    @Query("SELECT * from campaign_ad_table ORDER BY id ASC")
    List<CampaignAds> getAllCampaignAds();
}
