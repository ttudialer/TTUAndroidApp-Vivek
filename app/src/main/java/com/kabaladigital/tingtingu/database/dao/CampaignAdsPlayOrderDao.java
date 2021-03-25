package com.kabaladigital.tingtingu.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.kabaladigital.tingtingu.database.entity.CampaignAdsPlayOrder;

import java.util.List;

@Dao
public interface CampaignAdsPlayOrderDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<CampaignAdsPlayOrder> campaignAdsPlayOrder);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(CampaignAdsPlayOrder campaignAdsPlayOrder);

    @Query("SELECT * from campaign_ad_play_order_table WHERE campId =:sCampId")
    CampaignAdsPlayOrder getItemById(String sCampId);

    @Query("SELECT playCount from campaign_ad_play_order_table where CampCategory =:campCategory and status = 4 ORDER BY playCount ASC LIMIT 1")
    Integer getPlayCount(String campCategory);

//    @Query("SELECT * from campaign_ad_play_order_table WHERE status = 2 ORDER BY playCount ASC LIMIT 1")
//    CampaignAdsPlayOrder getAllCampaignAdsOrderByCount();
//
//    @Query("SELECT * from campaign_ad_play_order_table WHERE status = 2 and startDate <= :currentDate and endDate >= :currentDate and MaxDurationCount >= CurrentDurationCount and CampCategory =:campCategory  ORDER BY playCount ASC LIMIT 1")
//    CampaignAdsPlayOrder getAllCampaignAdsOrderByCount(long currentDate, String campCategory);

    @Query("SELECT * from campaign_ad_play_order_table WHERE status =:status and startDate <= :currentDate and endDate >= :currentDate and MaxDurationCount > todayAdCount and adDuration > CurrentDurationCount  and CampCategory =:campCategory  ORDER BY playCount ASC LIMIT 1")
    CampaignAdsPlayOrder getAllCampaignAdsOrderByCount(long currentDate, String campCategory, String status);

    @Query("SELECT * from campaign_ad_play_order_table WHERE status =:status and startDate <= :currentDate and endDate >= :currentDate and MaxDurationCount > todayAdCount and adDuration > CurrentDurationCount  and CampCategory =:campCategory and clientName =:sClientName ORDER BY playCount ASC LIMIT 1")
    CampaignAdsPlayOrder getIncomingCampaignAdsOrderByCount(long currentDate, String campCategory, String status, String sClientName);

    @Query("SELECT * from campaign_ad_play_order_table WHERE status =:status and startDate <= :currentDate and endDate >= :currentDate and MaxDurationCount > todayAdCount and adDuration > CurrentDurationCount  and CampCategory =:campCategory and callerId =:mobileNum  ORDER BY playCount ASC LIMIT 1")
    CampaignAdsPlayOrder getB2BPopupAdsOrderByCount(long currentDate, String campCategory, String status, long mobileNum);

    @Query("UPDATE campaign_ad_play_order_table set playCount = playCount+1 WHERE campId =:sCampId")
    void updatePlayCount(String sCampId);

    @Query("UPDATE campaign_ad_play_order_table set status = :status, startDate =:sDate, endDate =:eDate, MaxDurationCount =:maxPlayDuration, adDuration =:adDuration WHERE campId =:sCampId")
    void updateCampPlayOrderRecord(String sCampId, Integer status , long sDate , long eDate, Integer maxPlayDuration, Integer adDuration);

    @Query("UPDATE campaign_ad_play_order_table set CurrentDurationCount = CurrentDurationCount+1, todayAdCount = todayAdCount+1  WHERE campId =:sCampId")
    void updateDurationCount(String sCampId);

    @Query("UPDATE campaign_ad_play_order_table set CurrentDurationCount = CurrentDurationCount+ :timeDiff, todayAdCount = todayAdCount+ :timeDiff  WHERE campId =:sCampId")
    void updateDurationCount(String sCampId, int timeDiff);

    @Query("SELECT todayDate from campaign_ad_play_order_table LIMIT 1")
    String getTodayDate();

    @Query("UPDATE campaign_ad_play_order_table set todayDate = :DateToday, todayAdCount = 0")
    void updateTodayDateCount(String DateToday);

    @Query("Select COUNT(*) from campaign_ad_play_order_table WHERE callerId = :number AND status = 4 AND CampCategory = 'Popup Reject'")
    int checkCallerId(long number);
}
