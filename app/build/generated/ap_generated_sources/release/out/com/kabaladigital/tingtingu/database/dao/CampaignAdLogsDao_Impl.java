package com.kabaladigital.tingtingu.database.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.kabaladigital.tingtingu.database.entity.CampaignLogs;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class CampaignAdLogsDao_Impl implements CampaignAdLogsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfCampaignLogs;

  private final SharedSQLiteStatement __preparedStmtOfUpdateLogSync;

  public CampaignAdLogsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCampaignLogs = new EntityInsertionAdapter<CampaignLogs>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `campaign_ad_logs_table`(`id`,`campId`,`adCategory`,`adType`,`instance`,`callTime`,`callDate`,`startDateTime`,`endDateTime`,`callType`,`action`,`callStatus`,`playDuration`,`isSynced`,`campType`,`callToActionTime`,`callToAction`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CampaignLogs value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getCampId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getCampId());
        }
        if (value.getAdCategory() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAdCategory());
        }
        if (value.getAdType() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getAdType());
        }
        if (value.getInstance() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, value.getInstance());
        }
        if (value.getCallTime() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getCallTime());
        }
        if (value.getCallDate() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getCallDate());
        }
        if (value.getStartDateTime() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getStartDateTime());
        }
        if (value.getEndDateTime() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getEndDateTime());
        }
        if (value.getCallType() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getCallType());
        }
        if (value.getAction() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getAction());
        }
        if (value.getCallStatus() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getCallStatus());
        }
        if (value.getPlayDuration() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindLong(13, value.getPlayDuration());
        }
        final int _tmp;
        _tmp = value.isSynced() ? 1 : 0;
        stmt.bindLong(14, _tmp);
        if (value.getCampType() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getCampType());
        }
        if (value.getCallToActionTime() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getCallToActionTime());
        }
        stmt.bindLong(17, value.getCallToAction());
      }
    };
    this.__preparedStmtOfUpdateLogSync = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE campaign_ad_logs_table set isSynced = 1 WHERE id =?";
        return _query;
      }
    };
  }

  @Override
  public void insert(CampaignLogs campaignLogs) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfCampaignLogs.insert(campaignLogs);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateLogSync(int id) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateLogSync.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, id);
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateLogSync.release(_stmt);
    }
  }

  @Override
  public List<CampaignLogs> getAllNonSyncLogs(String CampType) {
    final String _sql = "SELECT * from campaign_ad_logs_table WHERE isSynced = 0 and campType =?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (CampType == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, CampType);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfCampId = _cursor.getColumnIndexOrThrow("campId");
      final int _cursorIndexOfAdCategory = _cursor.getColumnIndexOrThrow("adCategory");
      final int _cursorIndexOfAdType = _cursor.getColumnIndexOrThrow("adType");
      final int _cursorIndexOfInstance = _cursor.getColumnIndexOrThrow("instance");
      final int _cursorIndexOfCallTime = _cursor.getColumnIndexOrThrow("callTime");
      final int _cursorIndexOfCallDate = _cursor.getColumnIndexOrThrow("callDate");
      final int _cursorIndexOfStartDateTime = _cursor.getColumnIndexOrThrow("startDateTime");
      final int _cursorIndexOfEndDateTime = _cursor.getColumnIndexOrThrow("endDateTime");
      final int _cursorIndexOfCallType = _cursor.getColumnIndexOrThrow("callType");
      final int _cursorIndexOfAction = _cursor.getColumnIndexOrThrow("action");
      final int _cursorIndexOfCallStatus = _cursor.getColumnIndexOrThrow("callStatus");
      final int _cursorIndexOfPlayDuration = _cursor.getColumnIndexOrThrow("playDuration");
      final int _cursorIndexOfIsSynced = _cursor.getColumnIndexOrThrow("isSynced");
      final int _cursorIndexOfCampType = _cursor.getColumnIndexOrThrow("campType");
      final int _cursorIndexOfCallToActionTime = _cursor.getColumnIndexOrThrow("callToActionTime");
      final int _cursorIndexOfCallToAction = _cursor.getColumnIndexOrThrow("callToAction");
      final List<CampaignLogs> _result = new ArrayList<CampaignLogs>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final CampaignLogs _item;
        _item = new CampaignLogs();
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        _item.setId(_tmpId);
        final String _tmpCampId;
        _tmpCampId = _cursor.getString(_cursorIndexOfCampId);
        _item.setCampId(_tmpCampId);
        final String _tmpAdCategory;
        _tmpAdCategory = _cursor.getString(_cursorIndexOfAdCategory);
        _item.setAdCategory(_tmpAdCategory);
        final String _tmpAdType;
        _tmpAdType = _cursor.getString(_cursorIndexOfAdType);
        _item.setAdType(_tmpAdType);
        final Integer _tmpInstance;
        if (_cursor.isNull(_cursorIndexOfInstance)) {
          _tmpInstance = null;
        } else {
          _tmpInstance = _cursor.getInt(_cursorIndexOfInstance);
        }
        _item.setInstance(_tmpInstance);
        final String _tmpCallTime;
        _tmpCallTime = _cursor.getString(_cursorIndexOfCallTime);
        _item.setCallTime(_tmpCallTime);
        final String _tmpCallDate;
        _tmpCallDate = _cursor.getString(_cursorIndexOfCallDate);
        _item.setCallDate(_tmpCallDate);
        final String _tmpStartDateTime;
        _tmpStartDateTime = _cursor.getString(_cursorIndexOfStartDateTime);
        _item.setStartDateTime(_tmpStartDateTime);
        final String _tmpEndDateTime;
        _tmpEndDateTime = _cursor.getString(_cursorIndexOfEndDateTime);
        _item.setEndDateTime(_tmpEndDateTime);
        final String _tmpCallType;
        _tmpCallType = _cursor.getString(_cursorIndexOfCallType);
        _item.setCallType(_tmpCallType);
        final String _tmpAction;
        _tmpAction = _cursor.getString(_cursorIndexOfAction);
        _item.setAction(_tmpAction);
        final String _tmpCallStatus;
        _tmpCallStatus = _cursor.getString(_cursorIndexOfCallStatus);
        _item.setCallStatus(_tmpCallStatus);
        final Integer _tmpPlayDuration;
        if (_cursor.isNull(_cursorIndexOfPlayDuration)) {
          _tmpPlayDuration = null;
        } else {
          _tmpPlayDuration = _cursor.getInt(_cursorIndexOfPlayDuration);
        }
        _item.setPlayDuration(_tmpPlayDuration);
        final boolean _tmpIsSynced;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsSynced);
        _tmpIsSynced = _tmp != 0;
        _item.setSynced(_tmpIsSynced);
        final String _tmpCampType;
        _tmpCampType = _cursor.getString(_cursorIndexOfCampType);
        _item.setCampType(_tmpCampType);
        final String _tmpCallToActionTime;
        _tmpCallToActionTime = _cursor.getString(_cursorIndexOfCallToActionTime);
        _item.setCallToActionTime(_tmpCallToActionTime);
        final int _tmpCallToAction;
        _tmpCallToAction = _cursor.getInt(_cursorIndexOfCallToAction);
        _item.setCallToAction(_tmpCallToAction);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
