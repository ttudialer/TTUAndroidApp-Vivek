package com.kabaladigital.tingtingu.database.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.kabaladigital.tingtingu.models.IncomingCallAdData;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class IncomingCallAdDao_Impl implements IncomingCallAdDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfIncomingCallAdData;

  private final SharedSQLiteStatement __preparedStmtOfUpdateAds;

  public IncomingCallAdDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfIncomingCallAdData = new EntityInsertionAdapter<IncomingCallAdData>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `ad_table`(`campId`,`id`,`name`,`clientName`,`adCategory`,`startDate`,`endDate`,`agentName`,`adType`,`videoSize`,`usersadPublished`,`adDuration`,`maxplayDuration`,`uploadFile`,`v`,`status`,`campaignName`,`filterAds`,`publicId`,`uri`,`callToAction`,`adCountPerUser`,`adMaxDurUserPerDay`,`adTotalCount`,`advSource`,`adPlayDurForEachPlay`,`callerId`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, IncomingCallAdData value) {
        if (value.getCampId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getCampId());
        }
        if (value.getId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getId());
        }
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        if (value.getClientName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getClientName());
        }
        if (value.getAdCategory() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getAdCategory());
        }
        if (value.getStartDate() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getStartDate());
        }
        if (value.getEndDate() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getEndDate());
        }
        if (value.getAgentName() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getAgentName());
        }
        if (value.getAdType() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getAdType());
        }
        if (value.getVideoSize() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getVideoSize());
        }
        if (value.getUsersadPublished() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindLong(11, value.getUsersadPublished());
        }
        if (value.getAdDuration() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindLong(12, value.getAdDuration());
        }
        if (value.getMaxplayDuration() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindLong(13, value.getMaxplayDuration());
        }
        if (value.getUploadFile() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getUploadFile());
        }
        if (value.getV() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindLong(15, value.getV());
        }
        if (value.getStatus() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindLong(16, value.getStatus());
        }
        if (value.getCampaignName() == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, value.getCampaignName());
        }
        if (value.getFilterAds() == null) {
          stmt.bindNull(18);
        } else {
          stmt.bindString(18, value.getFilterAds());
        }
        if (value.getPublicId() == null) {
          stmt.bindNull(19);
        } else {
          stmt.bindString(19, value.getPublicId());
        }
        if (value.getUri() == null) {
          stmt.bindNull(20);
        } else {
          stmt.bindString(20, value.getUri());
        }
        if (value.getCallToAction() == null) {
          stmt.bindNull(21);
        } else {
          stmt.bindString(21, value.getCallToAction());
        }
        if (value.getAdCountPerUser() == null) {
          stmt.bindNull(22);
        } else {
          stmt.bindLong(22, value.getAdCountPerUser());
        }
        if (value.getAdMaxDurUserPerDay() == null) {
          stmt.bindNull(23);
        } else {
          stmt.bindLong(23, value.getAdMaxDurUserPerDay());
        }
        if (value.getAdTotalCount() == null) {
          stmt.bindNull(24);
        } else {
          stmt.bindLong(24, value.getAdTotalCount());
        }
        if (value.getAdvSource() == null) {
          stmt.bindNull(25);
        } else {
          stmt.bindString(25, value.getAdvSource());
        }
        if (value.getAdPlayDurForEachPlay() == null) {
          stmt.bindNull(26);
        } else {
          stmt.bindLong(26, value.getAdPlayDurForEachPlay());
        }
        stmt.bindLong(27, value.getCallerId());
      }
    };
    this.__preparedStmtOfUpdateAds = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE ad_table set uri = ? where campId = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(List<IncomingCallAdData> incomingCallAdData) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfIncomingCallAdData.insert(incomingCallAdData);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insert(IncomingCallAdData incomingCallAdData) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfIncomingCallAdData.insert(incomingCallAdData);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateAds(String fileUri, String iD) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateAds.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (fileUri == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, fileUri);
      }
      _argIndex = 2;
      if (iD == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, iD);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateAds.release(_stmt);
    }
  }

  @Override
  public List<IncomingCallAdData> getAllAdsForURI() {
    final String _sql = "SELECT * from ad_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfCampId = _cursor.getColumnIndexOrThrow("campId");
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfClientName = _cursor.getColumnIndexOrThrow("clientName");
      final int _cursorIndexOfAdCategory = _cursor.getColumnIndexOrThrow("adCategory");
      final int _cursorIndexOfStartDate = _cursor.getColumnIndexOrThrow("startDate");
      final int _cursorIndexOfEndDate = _cursor.getColumnIndexOrThrow("endDate");
      final int _cursorIndexOfAgentName = _cursor.getColumnIndexOrThrow("agentName");
      final int _cursorIndexOfAdType = _cursor.getColumnIndexOrThrow("adType");
      final int _cursorIndexOfVideoSize = _cursor.getColumnIndexOrThrow("videoSize");
      final int _cursorIndexOfUsersadPublished = _cursor.getColumnIndexOrThrow("usersadPublished");
      final int _cursorIndexOfAdDuration = _cursor.getColumnIndexOrThrow("adDuration");
      final int _cursorIndexOfMaxplayDuration = _cursor.getColumnIndexOrThrow("maxplayDuration");
      final int _cursorIndexOfUploadFile = _cursor.getColumnIndexOrThrow("uploadFile");
      final int _cursorIndexOfV = _cursor.getColumnIndexOrThrow("v");
      final int _cursorIndexOfStatus = _cursor.getColumnIndexOrThrow("status");
      final int _cursorIndexOfCampaignName = _cursor.getColumnIndexOrThrow("campaignName");
      final int _cursorIndexOfFilterAds = _cursor.getColumnIndexOrThrow("filterAds");
      final int _cursorIndexOfPublicId = _cursor.getColumnIndexOrThrow("publicId");
      final int _cursorIndexOfUri = _cursor.getColumnIndexOrThrow("uri");
      final int _cursorIndexOfCallToAction = _cursor.getColumnIndexOrThrow("callToAction");
      final int _cursorIndexOfAdCountPerUser = _cursor.getColumnIndexOrThrow("adCountPerUser");
      final int _cursorIndexOfAdMaxDurUserPerDay = _cursor.getColumnIndexOrThrow("adMaxDurUserPerDay");
      final int _cursorIndexOfAdTotalCount = _cursor.getColumnIndexOrThrow("adTotalCount");
      final int _cursorIndexOfAdvSource = _cursor.getColumnIndexOrThrow("advSource");
      final int _cursorIndexOfAdPlayDurForEachPlay = _cursor.getColumnIndexOrThrow("adPlayDurForEachPlay");
      final int _cursorIndexOfCallerId = _cursor.getColumnIndexOrThrow("callerId");
      final List<IncomingCallAdData> _result = new ArrayList<IncomingCallAdData>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final IncomingCallAdData _item;
        _item = new IncomingCallAdData();
        final String _tmpCampId;
        _tmpCampId = _cursor.getString(_cursorIndexOfCampId);
        _item.setCampId(_tmpCampId);
        final String _tmpId;
        _tmpId = _cursor.getString(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final String _tmpClientName;
        _tmpClientName = _cursor.getString(_cursorIndexOfClientName);
        _item.setClientName(_tmpClientName);
        final String _tmpAdCategory;
        _tmpAdCategory = _cursor.getString(_cursorIndexOfAdCategory);
        _item.setAdCategory(_tmpAdCategory);
        final String _tmpStartDate;
        _tmpStartDate = _cursor.getString(_cursorIndexOfStartDate);
        _item.setStartDate(_tmpStartDate);
        final String _tmpEndDate;
        _tmpEndDate = _cursor.getString(_cursorIndexOfEndDate);
        _item.setEndDate(_tmpEndDate);
        final String _tmpAgentName;
        _tmpAgentName = _cursor.getString(_cursorIndexOfAgentName);
        _item.setAgentName(_tmpAgentName);
        final String _tmpAdType;
        _tmpAdType = _cursor.getString(_cursorIndexOfAdType);
        _item.setAdType(_tmpAdType);
        final String _tmpVideoSize;
        _tmpVideoSize = _cursor.getString(_cursorIndexOfVideoSize);
        _item.setVideoSize(_tmpVideoSize);
        final Integer _tmpUsersadPublished;
        if (_cursor.isNull(_cursorIndexOfUsersadPublished)) {
          _tmpUsersadPublished = null;
        } else {
          _tmpUsersadPublished = _cursor.getInt(_cursorIndexOfUsersadPublished);
        }
        _item.setUsersadPublished(_tmpUsersadPublished);
        final Integer _tmpAdDuration;
        if (_cursor.isNull(_cursorIndexOfAdDuration)) {
          _tmpAdDuration = null;
        } else {
          _tmpAdDuration = _cursor.getInt(_cursorIndexOfAdDuration);
        }
        _item.setAdDuration(_tmpAdDuration);
        final Integer _tmpMaxplayDuration;
        if (_cursor.isNull(_cursorIndexOfMaxplayDuration)) {
          _tmpMaxplayDuration = null;
        } else {
          _tmpMaxplayDuration = _cursor.getInt(_cursorIndexOfMaxplayDuration);
        }
        _item.setMaxplayDuration(_tmpMaxplayDuration);
        final String _tmpUploadFile;
        _tmpUploadFile = _cursor.getString(_cursorIndexOfUploadFile);
        _item.setUploadFile(_tmpUploadFile);
        final Integer _tmpV;
        if (_cursor.isNull(_cursorIndexOfV)) {
          _tmpV = null;
        } else {
          _tmpV = _cursor.getInt(_cursorIndexOfV);
        }
        _item.setV(_tmpV);
        final Integer _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = _cursor.getInt(_cursorIndexOfStatus);
        }
        _item.setStatus(_tmpStatus);
        final String _tmpCampaignName;
        _tmpCampaignName = _cursor.getString(_cursorIndexOfCampaignName);
        _item.setCampaignName(_tmpCampaignName);
        final String _tmpFilterAds;
        _tmpFilterAds = _cursor.getString(_cursorIndexOfFilterAds);
        _item.setFilterAds(_tmpFilterAds);
        final String _tmpPublicId;
        _tmpPublicId = _cursor.getString(_cursorIndexOfPublicId);
        _item.setPublicId(_tmpPublicId);
        final String _tmpUri;
        _tmpUri = _cursor.getString(_cursorIndexOfUri);
        _item.setUri(_tmpUri);
        final String _tmpCallToAction;
        _tmpCallToAction = _cursor.getString(_cursorIndexOfCallToAction);
        _item.setCallToAction(_tmpCallToAction);
        final Integer _tmpAdCountPerUser;
        if (_cursor.isNull(_cursorIndexOfAdCountPerUser)) {
          _tmpAdCountPerUser = null;
        } else {
          _tmpAdCountPerUser = _cursor.getInt(_cursorIndexOfAdCountPerUser);
        }
        _item.setAdCountPerUser(_tmpAdCountPerUser);
        final Integer _tmpAdMaxDurUserPerDay;
        if (_cursor.isNull(_cursorIndexOfAdMaxDurUserPerDay)) {
          _tmpAdMaxDurUserPerDay = null;
        } else {
          _tmpAdMaxDurUserPerDay = _cursor.getInt(_cursorIndexOfAdMaxDurUserPerDay);
        }
        _item.setAdMaxDurUserPerDay(_tmpAdMaxDurUserPerDay);
        final Integer _tmpAdTotalCount;
        if (_cursor.isNull(_cursorIndexOfAdTotalCount)) {
          _tmpAdTotalCount = null;
        } else {
          _tmpAdTotalCount = _cursor.getInt(_cursorIndexOfAdTotalCount);
        }
        _item.setAdTotalCount(_tmpAdTotalCount);
        final String _tmpAdvSource;
        _tmpAdvSource = _cursor.getString(_cursorIndexOfAdvSource);
        _item.setAdvSource(_tmpAdvSource);
        final Integer _tmpAdPlayDurForEachPlay;
        if (_cursor.isNull(_cursorIndexOfAdPlayDurForEachPlay)) {
          _tmpAdPlayDurForEachPlay = null;
        } else {
          _tmpAdPlayDurForEachPlay = _cursor.getInt(_cursorIndexOfAdPlayDurForEachPlay);
        }
        _item.setAdPlayDurForEachPlay(_tmpAdPlayDurForEachPlay);
        final long _tmpCallerId;
        _tmpCallerId = _cursor.getLong(_cursorIndexOfCallerId);
        _item.setCallerId(_tmpCallerId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public IncomingCallAdData getAdDetailByCampId(String sCampId) {
    final String _sql = "SELECT * from ad_table where campId = ?";
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
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfClientName = _cursor.getColumnIndexOrThrow("clientName");
      final int _cursorIndexOfAdCategory = _cursor.getColumnIndexOrThrow("adCategory");
      final int _cursorIndexOfStartDate = _cursor.getColumnIndexOrThrow("startDate");
      final int _cursorIndexOfEndDate = _cursor.getColumnIndexOrThrow("endDate");
      final int _cursorIndexOfAgentName = _cursor.getColumnIndexOrThrow("agentName");
      final int _cursorIndexOfAdType = _cursor.getColumnIndexOrThrow("adType");
      final int _cursorIndexOfVideoSize = _cursor.getColumnIndexOrThrow("videoSize");
      final int _cursorIndexOfUsersadPublished = _cursor.getColumnIndexOrThrow("usersadPublished");
      final int _cursorIndexOfAdDuration = _cursor.getColumnIndexOrThrow("adDuration");
      final int _cursorIndexOfMaxplayDuration = _cursor.getColumnIndexOrThrow("maxplayDuration");
      final int _cursorIndexOfUploadFile = _cursor.getColumnIndexOrThrow("uploadFile");
      final int _cursorIndexOfV = _cursor.getColumnIndexOrThrow("v");
      final int _cursorIndexOfStatus = _cursor.getColumnIndexOrThrow("status");
      final int _cursorIndexOfCampaignName = _cursor.getColumnIndexOrThrow("campaignName");
      final int _cursorIndexOfFilterAds = _cursor.getColumnIndexOrThrow("filterAds");
      final int _cursorIndexOfPublicId = _cursor.getColumnIndexOrThrow("publicId");
      final int _cursorIndexOfUri = _cursor.getColumnIndexOrThrow("uri");
      final int _cursorIndexOfCallToAction = _cursor.getColumnIndexOrThrow("callToAction");
      final int _cursorIndexOfAdCountPerUser = _cursor.getColumnIndexOrThrow("adCountPerUser");
      final int _cursorIndexOfAdMaxDurUserPerDay = _cursor.getColumnIndexOrThrow("adMaxDurUserPerDay");
      final int _cursorIndexOfAdTotalCount = _cursor.getColumnIndexOrThrow("adTotalCount");
      final int _cursorIndexOfAdvSource = _cursor.getColumnIndexOrThrow("advSource");
      final int _cursorIndexOfAdPlayDurForEachPlay = _cursor.getColumnIndexOrThrow("adPlayDurForEachPlay");
      final int _cursorIndexOfCallerId = _cursor.getColumnIndexOrThrow("callerId");
      final IncomingCallAdData _result;
      if(_cursor.moveToFirst()) {
        _result = new IncomingCallAdData();
        final String _tmpCampId;
        _tmpCampId = _cursor.getString(_cursorIndexOfCampId);
        _result.setCampId(_tmpCampId);
        final String _tmpId;
        _tmpId = _cursor.getString(_cursorIndexOfId);
        _result.setId(_tmpId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _result.setName(_tmpName);
        final String _tmpClientName;
        _tmpClientName = _cursor.getString(_cursorIndexOfClientName);
        _result.setClientName(_tmpClientName);
        final String _tmpAdCategory;
        _tmpAdCategory = _cursor.getString(_cursorIndexOfAdCategory);
        _result.setAdCategory(_tmpAdCategory);
        final String _tmpStartDate;
        _tmpStartDate = _cursor.getString(_cursorIndexOfStartDate);
        _result.setStartDate(_tmpStartDate);
        final String _tmpEndDate;
        _tmpEndDate = _cursor.getString(_cursorIndexOfEndDate);
        _result.setEndDate(_tmpEndDate);
        final String _tmpAgentName;
        _tmpAgentName = _cursor.getString(_cursorIndexOfAgentName);
        _result.setAgentName(_tmpAgentName);
        final String _tmpAdType;
        _tmpAdType = _cursor.getString(_cursorIndexOfAdType);
        _result.setAdType(_tmpAdType);
        final String _tmpVideoSize;
        _tmpVideoSize = _cursor.getString(_cursorIndexOfVideoSize);
        _result.setVideoSize(_tmpVideoSize);
        final Integer _tmpUsersadPublished;
        if (_cursor.isNull(_cursorIndexOfUsersadPublished)) {
          _tmpUsersadPublished = null;
        } else {
          _tmpUsersadPublished = _cursor.getInt(_cursorIndexOfUsersadPublished);
        }
        _result.setUsersadPublished(_tmpUsersadPublished);
        final Integer _tmpAdDuration;
        if (_cursor.isNull(_cursorIndexOfAdDuration)) {
          _tmpAdDuration = null;
        } else {
          _tmpAdDuration = _cursor.getInt(_cursorIndexOfAdDuration);
        }
        _result.setAdDuration(_tmpAdDuration);
        final Integer _tmpMaxplayDuration;
        if (_cursor.isNull(_cursorIndexOfMaxplayDuration)) {
          _tmpMaxplayDuration = null;
        } else {
          _tmpMaxplayDuration = _cursor.getInt(_cursorIndexOfMaxplayDuration);
        }
        _result.setMaxplayDuration(_tmpMaxplayDuration);
        final String _tmpUploadFile;
        _tmpUploadFile = _cursor.getString(_cursorIndexOfUploadFile);
        _result.setUploadFile(_tmpUploadFile);
        final Integer _tmpV;
        if (_cursor.isNull(_cursorIndexOfV)) {
          _tmpV = null;
        } else {
          _tmpV = _cursor.getInt(_cursorIndexOfV);
        }
        _result.setV(_tmpV);
        final Integer _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = _cursor.getInt(_cursorIndexOfStatus);
        }
        _result.setStatus(_tmpStatus);
        final String _tmpCampaignName;
        _tmpCampaignName = _cursor.getString(_cursorIndexOfCampaignName);
        _result.setCampaignName(_tmpCampaignName);
        final String _tmpFilterAds;
        _tmpFilterAds = _cursor.getString(_cursorIndexOfFilterAds);
        _result.setFilterAds(_tmpFilterAds);
        final String _tmpPublicId;
        _tmpPublicId = _cursor.getString(_cursorIndexOfPublicId);
        _result.setPublicId(_tmpPublicId);
        final String _tmpUri;
        _tmpUri = _cursor.getString(_cursorIndexOfUri);
        _result.setUri(_tmpUri);
        final String _tmpCallToAction;
        _tmpCallToAction = _cursor.getString(_cursorIndexOfCallToAction);
        _result.setCallToAction(_tmpCallToAction);
        final Integer _tmpAdCountPerUser;
        if (_cursor.isNull(_cursorIndexOfAdCountPerUser)) {
          _tmpAdCountPerUser = null;
        } else {
          _tmpAdCountPerUser = _cursor.getInt(_cursorIndexOfAdCountPerUser);
        }
        _result.setAdCountPerUser(_tmpAdCountPerUser);
        final Integer _tmpAdMaxDurUserPerDay;
        if (_cursor.isNull(_cursorIndexOfAdMaxDurUserPerDay)) {
          _tmpAdMaxDurUserPerDay = null;
        } else {
          _tmpAdMaxDurUserPerDay = _cursor.getInt(_cursorIndexOfAdMaxDurUserPerDay);
        }
        _result.setAdMaxDurUserPerDay(_tmpAdMaxDurUserPerDay);
        final Integer _tmpAdTotalCount;
        if (_cursor.isNull(_cursorIndexOfAdTotalCount)) {
          _tmpAdTotalCount = null;
        } else {
          _tmpAdTotalCount = _cursor.getInt(_cursorIndexOfAdTotalCount);
        }
        _result.setAdTotalCount(_tmpAdTotalCount);
        final String _tmpAdvSource;
        _tmpAdvSource = _cursor.getString(_cursorIndexOfAdvSource);
        _result.setAdvSource(_tmpAdvSource);
        final Integer _tmpAdPlayDurForEachPlay;
        if (_cursor.isNull(_cursorIndexOfAdPlayDurForEachPlay)) {
          _tmpAdPlayDurForEachPlay = null;
        } else {
          _tmpAdPlayDurForEachPlay = _cursor.getInt(_cursorIndexOfAdPlayDurForEachPlay);
        }
        _result.setAdPlayDurForEachPlay(_tmpAdPlayDurForEachPlay);
        final long _tmpCallerId;
        _tmpCallerId = _cursor.getLong(_cursorIndexOfCallerId);
        _result.setCallerId(_tmpCallerId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
