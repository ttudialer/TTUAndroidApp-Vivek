package com.kabaladigital.tingtingu.database.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.kabaladigital.tingtingu.database.entity.CampaignAdsPlayOrder;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.List;

@SuppressWarnings("unchecked")
public final class CampaignAdsPlayOrderDao_Impl implements CampaignAdsPlayOrderDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfCampaignAdsPlayOrder;

  private final SharedSQLiteStatement __preparedStmtOfUpdatePlayCount;

  private final SharedSQLiteStatement __preparedStmtOfUpdateCampPlayOrderRecord;

  private final SharedSQLiteStatement __preparedStmtOfUpdateDurationCount;

  private final SharedSQLiteStatement __preparedStmtOfUpdateDurationCount_1;

  private final SharedSQLiteStatement __preparedStmtOfUpdateTodayDateCount;

  public CampaignAdsPlayOrderDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCampaignAdsPlayOrder = new EntityInsertionAdapter<CampaignAdsPlayOrder>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `campaign_ad_play_order_table`(`campId`,`status`,`playCount`,`startDate`,`endDate`,`CurrentDurationCount`,`MaxDurationCount`,`adDuration`,`todayAdCount`,`todayDate`,`CampCategory`,`callerId`,`clientName`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CampaignAdsPlayOrder value) {
        if (value.getCampId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getCampId());
        }
        if (value.getStatus() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getStatus());
        }
        if (value.getPlayCount() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, value.getPlayCount());
        }
        stmt.bindLong(4, value.getStartDate());
        stmt.bindLong(5, value.getEndDate());
        if (value.getCurrentDurationCount() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, value.getCurrentDurationCount());
        }
        if (value.getMaxDurationCount() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindLong(7, value.getMaxDurationCount());
        }
        if (value.getAdDuration() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, value.getAdDuration());
        }
        if (value.getTodayAdCount() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindLong(9, value.getTodayAdCount());
        }
        if (value.getTodayDate() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getTodayDate());
        }
        if (value.getCampCategory() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getCampCategory());
        }
        stmt.bindLong(12, value.getCallerId());
        if (value.getClientName() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getClientName());
        }
      }
    };
    this.__preparedStmtOfUpdatePlayCount = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE campaign_ad_play_order_table set playCount = playCount+1 WHERE campId =?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateCampPlayOrderRecord = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE campaign_ad_play_order_table set status = ?, startDate =?, endDate =?, MaxDurationCount =?, adDuration =? WHERE campId =?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateDurationCount = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE campaign_ad_play_order_table set CurrentDurationCount = CurrentDurationCount+1, todayAdCount = todayAdCount+1  WHERE campId =?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateDurationCount_1 = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE campaign_ad_play_order_table set CurrentDurationCount = CurrentDurationCount+ ?, todayAdCount = todayAdCount+ ?  WHERE campId =?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateTodayDateCount = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE campaign_ad_play_order_table set todayDate = ?, todayAdCount = 0";
        return _query;
      }
    };
  }

  @Override
  public void insert(List<CampaignAdsPlayOrder> campaignAdsPlayOrder) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfCampaignAdsPlayOrder.insert(campaignAdsPlayOrder);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public long insert(CampaignAdsPlayOrder campaignAdsPlayOrder) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfCampaignAdsPlayOrder.insertAndReturnId(campaignAdsPlayOrder);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updatePlayCount(String sCampId) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdatePlayCount.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (sCampId == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, sCampId);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdatePlayCount.release(_stmt);
    }
  }

  @Override
  public void updateCampPlayOrderRecord(String sCampId, Integer status, long sDate, long eDate,
      Integer maxPlayDuration, Integer adDuration) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateCampPlayOrderRecord.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (status == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindLong(_argIndex, status);
      }
      _argIndex = 2;
      _stmt.bindLong(_argIndex, sDate);
      _argIndex = 3;
      _stmt.bindLong(_argIndex, eDate);
      _argIndex = 4;
      if (maxPlayDuration == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindLong(_argIndex, maxPlayDuration);
      }
      _argIndex = 5;
      if (adDuration == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindLong(_argIndex, adDuration);
      }
      _argIndex = 6;
      if (sCampId == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, sCampId);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateCampPlayOrderRecord.release(_stmt);
    }
  }

  @Override
  public void updateDurationCount(String sCampId) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateDurationCount.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (sCampId == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, sCampId);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateDurationCount.release(_stmt);
    }
  }

  @Override
  public void updateDurationCount(String sCampId, int timeDiff) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateDurationCount_1.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, timeDiff);
      _argIndex = 2;
      _stmt.bindLong(_argIndex, timeDiff);
      _argIndex = 3;
      if (sCampId == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, sCampId);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateDurationCount_1.release(_stmt);
    }
  }

  @Override
  public void updateTodayDateCount(String DateToday) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateTodayDateCount.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (DateToday == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, DateToday);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateTodayDateCount.release(_stmt);
    }
  }

  @Override
  public CampaignAdsPlayOrder getItemById(String sCampId) {
    final String _sql = "SELECT * from campaign_ad_play_order_table WHERE campId =?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (sCampId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, sCampId);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfCampId = _cursor.getColumnIndexOrThrow("campId");
      final int _cursorIndexOfStatus = _cursor.getColumnIndexOrThrow("status");
      final int _cursorIndexOfPlayCount = _cursor.getColumnIndexOrThrow("playCount");
      final int _cursorIndexOfStartDate = _cursor.getColumnIndexOrThrow("startDate");
      final int _cursorIndexOfEndDate = _cursor.getColumnIndexOrThrow("endDate");
      final int _cursorIndexOfCurrentDurationCount = _cursor.getColumnIndexOrThrow("CurrentDurationCount");
      final int _cursorIndexOfMaxDurationCount = _cursor.getColumnIndexOrThrow("MaxDurationCount");
      final int _cursorIndexOfAdDuration = _cursor.getColumnIndexOrThrow("adDuration");
      final int _cursorIndexOfTodayAdCount = _cursor.getColumnIndexOrThrow("todayAdCount");
      final int _cursorIndexOfTodayDate = _cursor.getColumnIndexOrThrow("todayDate");
      final int _cursorIndexOfCampCategory = _cursor.getColumnIndexOrThrow("CampCategory");
      final int _cursorIndexOfCallerId = _cursor.getColumnIndexOrThrow("callerId");
      final int _cursorIndexOfClientName = _cursor.getColumnIndexOrThrow("clientName");
      final CampaignAdsPlayOrder _result;
      if(_cursor.moveToFirst()) {
        _result = new CampaignAdsPlayOrder();
        final String _tmpCampId;
        _tmpCampId = _cursor.getString(_cursorIndexOfCampId);
        _result.setCampId(_tmpCampId);
        final Integer _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = _cursor.getInt(_cursorIndexOfStatus);
        }
        _result.setStatus(_tmpStatus);
        final Integer _tmpPlayCount;
        if (_cursor.isNull(_cursorIndexOfPlayCount)) {
          _tmpPlayCount = null;
        } else {
          _tmpPlayCount = _cursor.getInt(_cursorIndexOfPlayCount);
        }
        _result.setPlayCount(_tmpPlayCount);
        final long _tmpStartDate;
        _tmpStartDate = _cursor.getLong(_cursorIndexOfStartDate);
        _result.setStartDate(_tmpStartDate);
        final long _tmpEndDate;
        _tmpEndDate = _cursor.getLong(_cursorIndexOfEndDate);
        _result.setEndDate(_tmpEndDate);
        final Integer _tmpCurrentDurationCount;
        if (_cursor.isNull(_cursorIndexOfCurrentDurationCount)) {
          _tmpCurrentDurationCount = null;
        } else {
          _tmpCurrentDurationCount = _cursor.getInt(_cursorIndexOfCurrentDurationCount);
        }
        _result.setCurrentDurationCount(_tmpCurrentDurationCount);
        final Integer _tmpMaxDurationCount;
        if (_cursor.isNull(_cursorIndexOfMaxDurationCount)) {
          _tmpMaxDurationCount = null;
        } else {
          _tmpMaxDurationCount = _cursor.getInt(_cursorIndexOfMaxDurationCount);
        }
        _result.setMaxDurationCount(_tmpMaxDurationCount);
        final Integer _tmpAdDuration;
        if (_cursor.isNull(_cursorIndexOfAdDuration)) {
          _tmpAdDuration = null;
        } else {
          _tmpAdDuration = _cursor.getInt(_cursorIndexOfAdDuration);
        }
        _result.setAdDuration(_tmpAdDuration);
        final Integer _tmpTodayAdCount;
        if (_cursor.isNull(_cursorIndexOfTodayAdCount)) {
          _tmpTodayAdCount = null;
        } else {
          _tmpTodayAdCount = _cursor.getInt(_cursorIndexOfTodayAdCount);
        }
        _result.setTodayAdCount(_tmpTodayAdCount);
        final String _tmpTodayDate;
        _tmpTodayDate = _cursor.getString(_cursorIndexOfTodayDate);
        _result.setTodayDate(_tmpTodayDate);
        final String _tmpCampCategory;
        _tmpCampCategory = _cursor.getString(_cursorIndexOfCampCategory);
        _result.setCampCategory(_tmpCampCategory);
        final long _tmpCallerId;
        _tmpCallerId = _cursor.getLong(_cursorIndexOfCallerId);
        _result.setCallerId(_tmpCallerId);
        final String _tmpClientName;
        _tmpClientName = _cursor.getString(_cursorIndexOfClientName);
        _result.setClientName(_tmpClientName);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Integer getPlayCount(String campCategory) {
    final String _sql = "SELECT playCount from campaign_ad_play_order_table where CampCategory =? and status = 4 ORDER BY playCount ASC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (campCategory == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, campCategory);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final Integer _result;
      if(_cursor.moveToFirst()) {
        if (_cursor.isNull(0)) {
          _result = null;
        } else {
          _result = _cursor.getInt(0);
        }
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public CampaignAdsPlayOrder getAllCampaignAdsOrderByCount(long currentDate, String campCategory,
      String status) {
    final String _sql = "SELECT * from campaign_ad_play_order_table WHERE status =? and startDate <= ? and endDate >= ? and MaxDurationCount > todayAdCount and adDuration > CurrentDurationCount  and CampCategory =?  ORDER BY playCount ASC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 4);
    int _argIndex = 1;
    if (status == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, status);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, currentDate);
    _argIndex = 3;
    _statement.bindLong(_argIndex, currentDate);
    _argIndex = 4;
    if (campCategory == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, campCategory);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfCampId = _cursor.getColumnIndexOrThrow("campId");
      final int _cursorIndexOfStatus = _cursor.getColumnIndexOrThrow("status");
      final int _cursorIndexOfPlayCount = _cursor.getColumnIndexOrThrow("playCount");
      final int _cursorIndexOfStartDate = _cursor.getColumnIndexOrThrow("startDate");
      final int _cursorIndexOfEndDate = _cursor.getColumnIndexOrThrow("endDate");
      final int _cursorIndexOfCurrentDurationCount = _cursor.getColumnIndexOrThrow("CurrentDurationCount");
      final int _cursorIndexOfMaxDurationCount = _cursor.getColumnIndexOrThrow("MaxDurationCount");
      final int _cursorIndexOfAdDuration = _cursor.getColumnIndexOrThrow("adDuration");
      final int _cursorIndexOfTodayAdCount = _cursor.getColumnIndexOrThrow("todayAdCount");
      final int _cursorIndexOfTodayDate = _cursor.getColumnIndexOrThrow("todayDate");
      final int _cursorIndexOfCampCategory = _cursor.getColumnIndexOrThrow("CampCategory");
      final int _cursorIndexOfCallerId = _cursor.getColumnIndexOrThrow("callerId");
      final int _cursorIndexOfClientName = _cursor.getColumnIndexOrThrow("clientName");
      final CampaignAdsPlayOrder _result;
      if(_cursor.moveToFirst()) {
        _result = new CampaignAdsPlayOrder();
        final String _tmpCampId;
        _tmpCampId = _cursor.getString(_cursorIndexOfCampId);
        _result.setCampId(_tmpCampId);
        final Integer _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = _cursor.getInt(_cursorIndexOfStatus);
        }
        _result.setStatus(_tmpStatus);
        final Integer _tmpPlayCount;
        if (_cursor.isNull(_cursorIndexOfPlayCount)) {
          _tmpPlayCount = null;
        } else {
          _tmpPlayCount = _cursor.getInt(_cursorIndexOfPlayCount);
        }
        _result.setPlayCount(_tmpPlayCount);
        final long _tmpStartDate;
        _tmpStartDate = _cursor.getLong(_cursorIndexOfStartDate);
        _result.setStartDate(_tmpStartDate);
        final long _tmpEndDate;
        _tmpEndDate = _cursor.getLong(_cursorIndexOfEndDate);
        _result.setEndDate(_tmpEndDate);
        final Integer _tmpCurrentDurationCount;
        if (_cursor.isNull(_cursorIndexOfCurrentDurationCount)) {
          _tmpCurrentDurationCount = null;
        } else {
          _tmpCurrentDurationCount = _cursor.getInt(_cursorIndexOfCurrentDurationCount);
        }
        _result.setCurrentDurationCount(_tmpCurrentDurationCount);
        final Integer _tmpMaxDurationCount;
        if (_cursor.isNull(_cursorIndexOfMaxDurationCount)) {
          _tmpMaxDurationCount = null;
        } else {
          _tmpMaxDurationCount = _cursor.getInt(_cursorIndexOfMaxDurationCount);
        }
        _result.setMaxDurationCount(_tmpMaxDurationCount);
        final Integer _tmpAdDuration;
        if (_cursor.isNull(_cursorIndexOfAdDuration)) {
          _tmpAdDuration = null;
        } else {
          _tmpAdDuration = _cursor.getInt(_cursorIndexOfAdDuration);
        }
        _result.setAdDuration(_tmpAdDuration);
        final Integer _tmpTodayAdCount;
        if (_cursor.isNull(_cursorIndexOfTodayAdCount)) {
          _tmpTodayAdCount = null;
        } else {
          _tmpTodayAdCount = _cursor.getInt(_cursorIndexOfTodayAdCount);
        }
        _result.setTodayAdCount(_tmpTodayAdCount);
        final String _tmpTodayDate;
        _tmpTodayDate = _cursor.getString(_cursorIndexOfTodayDate);
        _result.setTodayDate(_tmpTodayDate);
        final String _tmpCampCategory;
        _tmpCampCategory = _cursor.getString(_cursorIndexOfCampCategory);
        _result.setCampCategory(_tmpCampCategory);
        final long _tmpCallerId;
        _tmpCallerId = _cursor.getLong(_cursorIndexOfCallerId);
        _result.setCallerId(_tmpCallerId);
        final String _tmpClientName;
        _tmpClientName = _cursor.getString(_cursorIndexOfClientName);
        _result.setClientName(_tmpClientName);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public CampaignAdsPlayOrder getIncomingCampaignAdsOrderByCount(long currentDate,
      String campCategory, String status, String sClientName) {
    final String _sql = "SELECT * from campaign_ad_play_order_table WHERE status =? and startDate <= ? and endDate >= ? and MaxDurationCount > todayAdCount and adDuration > CurrentDurationCount  and CampCategory =? and clientName =? ORDER BY playCount ASC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 5);
    int _argIndex = 1;
    if (status == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, status);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, currentDate);
    _argIndex = 3;
    _statement.bindLong(_argIndex, currentDate);
    _argIndex = 4;
    if (campCategory == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, campCategory);
    }
    _argIndex = 5;
    if (sClientName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, sClientName);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfCampId = _cursor.getColumnIndexOrThrow("campId");
      final int _cursorIndexOfStatus = _cursor.getColumnIndexOrThrow("status");
      final int _cursorIndexOfPlayCount = _cursor.getColumnIndexOrThrow("playCount");
      final int _cursorIndexOfStartDate = _cursor.getColumnIndexOrThrow("startDate");
      final int _cursorIndexOfEndDate = _cursor.getColumnIndexOrThrow("endDate");
      final int _cursorIndexOfCurrentDurationCount = _cursor.getColumnIndexOrThrow("CurrentDurationCount");
      final int _cursorIndexOfMaxDurationCount = _cursor.getColumnIndexOrThrow("MaxDurationCount");
      final int _cursorIndexOfAdDuration = _cursor.getColumnIndexOrThrow("adDuration");
      final int _cursorIndexOfTodayAdCount = _cursor.getColumnIndexOrThrow("todayAdCount");
      final int _cursorIndexOfTodayDate = _cursor.getColumnIndexOrThrow("todayDate");
      final int _cursorIndexOfCampCategory = _cursor.getColumnIndexOrThrow("CampCategory");
      final int _cursorIndexOfCallerId = _cursor.getColumnIndexOrThrow("callerId");
      final int _cursorIndexOfClientName = _cursor.getColumnIndexOrThrow("clientName");
      final CampaignAdsPlayOrder _result;
      if(_cursor.moveToFirst()) {
        _result = new CampaignAdsPlayOrder();
        final String _tmpCampId;
        _tmpCampId = _cursor.getString(_cursorIndexOfCampId);
        _result.setCampId(_tmpCampId);
        final Integer _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = _cursor.getInt(_cursorIndexOfStatus);
        }
        _result.setStatus(_tmpStatus);
        final Integer _tmpPlayCount;
        if (_cursor.isNull(_cursorIndexOfPlayCount)) {
          _tmpPlayCount = null;
        } else {
          _tmpPlayCount = _cursor.getInt(_cursorIndexOfPlayCount);
        }
        _result.setPlayCount(_tmpPlayCount);
        final long _tmpStartDate;
        _tmpStartDate = _cursor.getLong(_cursorIndexOfStartDate);
        _result.setStartDate(_tmpStartDate);
        final long _tmpEndDate;
        _tmpEndDate = _cursor.getLong(_cursorIndexOfEndDate);
        _result.setEndDate(_tmpEndDate);
        final Integer _tmpCurrentDurationCount;
        if (_cursor.isNull(_cursorIndexOfCurrentDurationCount)) {
          _tmpCurrentDurationCount = null;
        } else {
          _tmpCurrentDurationCount = _cursor.getInt(_cursorIndexOfCurrentDurationCount);
        }
        _result.setCurrentDurationCount(_tmpCurrentDurationCount);
        final Integer _tmpMaxDurationCount;
        if (_cursor.isNull(_cursorIndexOfMaxDurationCount)) {
          _tmpMaxDurationCount = null;
        } else {
          _tmpMaxDurationCount = _cursor.getInt(_cursorIndexOfMaxDurationCount);
        }
        _result.setMaxDurationCount(_tmpMaxDurationCount);
        final Integer _tmpAdDuration;
        if (_cursor.isNull(_cursorIndexOfAdDuration)) {
          _tmpAdDuration = null;
        } else {
          _tmpAdDuration = _cursor.getInt(_cursorIndexOfAdDuration);
        }
        _result.setAdDuration(_tmpAdDuration);
        final Integer _tmpTodayAdCount;
        if (_cursor.isNull(_cursorIndexOfTodayAdCount)) {
          _tmpTodayAdCount = null;
        } else {
          _tmpTodayAdCount = _cursor.getInt(_cursorIndexOfTodayAdCount);
        }
        _result.setTodayAdCount(_tmpTodayAdCount);
        final String _tmpTodayDate;
        _tmpTodayDate = _cursor.getString(_cursorIndexOfTodayDate);
        _result.setTodayDate(_tmpTodayDate);
        final String _tmpCampCategory;
        _tmpCampCategory = _cursor.getString(_cursorIndexOfCampCategory);
        _result.setCampCategory(_tmpCampCategory);
        final long _tmpCallerId;
        _tmpCallerId = _cursor.getLong(_cursorIndexOfCallerId);
        _result.setCallerId(_tmpCallerId);
        final String _tmpClientName;
        _tmpClientName = _cursor.getString(_cursorIndexOfClientName);
        _result.setClientName(_tmpClientName);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public CampaignAdsPlayOrder getB2BPopupAdsOrderByCount(long currentDate, String campCategory,
      String status, long mobileNum) {
    final String _sql = "SELECT * from campaign_ad_play_order_table WHERE status =? and startDate <= ? and endDate >= ? and MaxDurationCount > todayAdCount and adDuration > CurrentDurationCount  and CampCategory =? and callerId =?  ORDER BY playCount ASC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 5);
    int _argIndex = 1;
    if (status == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, status);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, currentDate);
    _argIndex = 3;
    _statement.bindLong(_argIndex, currentDate);
    _argIndex = 4;
    if (campCategory == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, campCategory);
    }
    _argIndex = 5;
    _statement.bindLong(_argIndex, mobileNum);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfCampId = _cursor.getColumnIndexOrThrow("campId");
      final int _cursorIndexOfStatus = _cursor.getColumnIndexOrThrow("status");
      final int _cursorIndexOfPlayCount = _cursor.getColumnIndexOrThrow("playCount");
      final int _cursorIndexOfStartDate = _cursor.getColumnIndexOrThrow("startDate");
      final int _cursorIndexOfEndDate = _cursor.getColumnIndexOrThrow("endDate");
      final int _cursorIndexOfCurrentDurationCount = _cursor.getColumnIndexOrThrow("CurrentDurationCount");
      final int _cursorIndexOfMaxDurationCount = _cursor.getColumnIndexOrThrow("MaxDurationCount");
      final int _cursorIndexOfAdDuration = _cursor.getColumnIndexOrThrow("adDuration");
      final int _cursorIndexOfTodayAdCount = _cursor.getColumnIndexOrThrow("todayAdCount");
      final int _cursorIndexOfTodayDate = _cursor.getColumnIndexOrThrow("todayDate");
      final int _cursorIndexOfCampCategory = _cursor.getColumnIndexOrThrow("CampCategory");
      final int _cursorIndexOfCallerId = _cursor.getColumnIndexOrThrow("callerId");
      final int _cursorIndexOfClientName = _cursor.getColumnIndexOrThrow("clientName");
      final CampaignAdsPlayOrder _result;
      if(_cursor.moveToFirst()) {
        _result = new CampaignAdsPlayOrder();
        final String _tmpCampId;
        _tmpCampId = _cursor.getString(_cursorIndexOfCampId);
        _result.setCampId(_tmpCampId);
        final Integer _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = _cursor.getInt(_cursorIndexOfStatus);
        }
        _result.setStatus(_tmpStatus);
        final Integer _tmpPlayCount;
        if (_cursor.isNull(_cursorIndexOfPlayCount)) {
          _tmpPlayCount = null;
        } else {
          _tmpPlayCount = _cursor.getInt(_cursorIndexOfPlayCount);
        }
        _result.setPlayCount(_tmpPlayCount);
        final long _tmpStartDate;
        _tmpStartDate = _cursor.getLong(_cursorIndexOfStartDate);
        _result.setStartDate(_tmpStartDate);
        final long _tmpEndDate;
        _tmpEndDate = _cursor.getLong(_cursorIndexOfEndDate);
        _result.setEndDate(_tmpEndDate);
        final Integer _tmpCurrentDurationCount;
        if (_cursor.isNull(_cursorIndexOfCurrentDurationCount)) {
          _tmpCurrentDurationCount = null;
        } else {
          _tmpCurrentDurationCount = _cursor.getInt(_cursorIndexOfCurrentDurationCount);
        }
        _result.setCurrentDurationCount(_tmpCurrentDurationCount);
        final Integer _tmpMaxDurationCount;
        if (_cursor.isNull(_cursorIndexOfMaxDurationCount)) {
          _tmpMaxDurationCount = null;
        } else {
          _tmpMaxDurationCount = _cursor.getInt(_cursorIndexOfMaxDurationCount);
        }
        _result.setMaxDurationCount(_tmpMaxDurationCount);
        final Integer _tmpAdDuration;
        if (_cursor.isNull(_cursorIndexOfAdDuration)) {
          _tmpAdDuration = null;
        } else {
          _tmpAdDuration = _cursor.getInt(_cursorIndexOfAdDuration);
        }
        _result.setAdDuration(_tmpAdDuration);
        final Integer _tmpTodayAdCount;
        if (_cursor.isNull(_cursorIndexOfTodayAdCount)) {
          _tmpTodayAdCount = null;
        } else {
          _tmpTodayAdCount = _cursor.getInt(_cursorIndexOfTodayAdCount);
        }
        _result.setTodayAdCount(_tmpTodayAdCount);
        final String _tmpTodayDate;
        _tmpTodayDate = _cursor.getString(_cursorIndexOfTodayDate);
        _result.setTodayDate(_tmpTodayDate);
        final String _tmpCampCategory;
        _tmpCampCategory = _cursor.getString(_cursorIndexOfCampCategory);
        _result.setCampCategory(_tmpCampCategory);
        final long _tmpCallerId;
        _tmpCallerId = _cursor.getLong(_cursorIndexOfCallerId);
        _result.setCallerId(_tmpCallerId);
        final String _tmpClientName;
        _tmpClientName = _cursor.getString(_cursorIndexOfClientName);
        _result.setClientName(_tmpClientName);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public String getTodayDate() {
    final String _sql = "SELECT todayDate from campaign_ad_play_order_table LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final String _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getString(0);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int checkCallerId(long number) {
    final String _sql = "Select COUNT(*) from campaign_ad_play_order_table WHERE callerId = ? AND status = 4 AND CampCategory = 'Popup Reject'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, number);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
