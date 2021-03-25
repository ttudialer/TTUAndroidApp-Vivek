package com.kabaladigital.tingtingu.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.kabaladigital.tingtingu.models.IncomingCallAdData;

import java.util.List;

@Dao
public interface IncomingCallAdDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<IncomingCallAdData> incomingCallAdData);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(IncomingCallAdData incomingCallAdData);

//    @Query("SELECT * from ad_table")
//    LiveData<List<IncomingCallAdData>> getAllAds();

    @Query("SELECT * from ad_table")
    List<IncomingCallAdData> getAllAdsForURI();

//    @Query("SELECT * from ad_table where adType = :sAdType and status = 0")
//    List<IncomingCallAdData> getAllAdsList(String sAdType);
//
//    @Query("SELECT * from ad_table where adType = :sAdType and videoSize = :videoSize and status = 0")
//    List<IncomingCallAdData> getAllAdsList(String sAdType,String videoSize);

    @Query("SELECT * from ad_table where campId = :sCampId")
    IncomingCallAdData getAdDetailByCampId(String sCampId);

    @Query("UPDATE ad_table set uri = :fileUri where campId = :iD")
    void updateAds(String fileUri, String iD);
}
