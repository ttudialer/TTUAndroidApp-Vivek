package com.kabaladigital.tingtingu.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.kabaladigital.tingtingu.database.entity.CampaignLogs;

import java.util.List;

@Dao
public interface CampaignAdLogsDao {

    @Insert()
    void insert(CampaignLogs campaignLogs);

    @Query("SELECT * from campaign_ad_logs_table WHERE isSynced = 0 and campType =:CampType")
    List<CampaignLogs> getAllNonSyncLogs(String CampType);

    @Query("UPDATE campaign_ad_logs_table set isSynced = 1 WHERE id =:id")
    void updateLogSync(int id);
}
