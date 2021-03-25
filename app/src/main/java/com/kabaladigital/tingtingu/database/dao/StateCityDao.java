package com.kabaladigital.tingtingu.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.kabaladigital.tingtingu.database.entity.StateCityModel;

import java.util.List;

@Dao
public interface StateCityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<StateCityModel> stateCityModel);

    @Query("SELECT * from state_city_table")
    List<StateCityModel> getStateCityData();

    @Query("SELECT DISTINCT state from state_city_table ORDER BY state")
    List<String> getAllStateList();

    @Query("SELECT DISTINCT city from state_city_table where state = :mState ORDER BY city")
    List<String> getCityListStateWise(String mState);

}
