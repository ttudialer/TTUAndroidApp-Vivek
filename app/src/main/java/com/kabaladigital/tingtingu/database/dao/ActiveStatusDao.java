package com.kabaladigital.tingtingu.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.kabaladigital.tingtingu.database.entity.ActiveStatus;

@Dao
public interface ActiveStatusDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(ActiveStatus activeStatus);

    @Query("SELECT date from active_status where id = 1")
    String getLastDateTime();

    @Query("SELECT todayDate from active_status where id = 1")
    String getLastDate();

    @Query("SELECT count from active_status where id = 1")
    int getCount();

    @Query("UPDATE active_status set count = count+:espTime , date =:sDate WHERE id = 1")
    void updateLogSync(String sDate,int espTime);

    @Query("UPDATE active_status set count =:espNightTime , todayDate =:sDate WHERE id = 1")
    void updateCountAndDate(String sDate, int espNightTime);
}
