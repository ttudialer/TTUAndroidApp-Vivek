package com.kabaladigital.tingtingu.database.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.kabaladigital.tingtingu.database.entity.CampaignAds;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class CampaignAdsDao_Impl implements CampaignAdsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfCampaignAds;

  public CampaignAdsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCampaignAds = new EntityInsertionAdapter<CampaignAds>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `campaign_ad_table`(`id`,`adURL`,`adURI`,`adType`,`status`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CampaignAds value) {
        stmt.bindLong(1, value.getId());
        if (value.getAdURL() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getAdURL());
        }
        if (value.getAdURI() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAdURI());
        }
        stmt.bindLong(4, value.getAdType());
        stmt.bindLong(5, value.getStatus());
      }
    };
  }

  @Override
  public void insert(CampaignAds campaignAds) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfCampaignAds.insert(campaignAds);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<CampaignAds> getAllCampaignAds() {
    final String _sql = "SELECT * from campaign_ad_table ORDER BY id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfAdURL = _cursor.getColumnIndexOrThrow("adURL");
      final int _cursorIndexOfAdURI = _cursor.getColumnIndexOrThrow("adURI");
      final int _cursorIndexOfAdType = _cursor.getColumnIndexOrThrow("adType");
      final int _cursorIndexOfStatus = _cursor.getColumnIndexOrThrow("status");
      final List<CampaignAds> _result = new ArrayList<CampaignAds>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final CampaignAds _item;
        _item = new CampaignAds();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpAdURL;
        _tmpAdURL = _cursor.getString(_cursorIndexOfAdURL);
        _item.setAdURL(_tmpAdURL);
        final String _tmpAdURI;
        _tmpAdURI = _cursor.getString(_cursorIndexOfAdURI);
        _item.setAdURI(_tmpAdURI);
        final int _tmpAdType;
        _tmpAdType = _cursor.getInt(_cursorIndexOfAdType);
        _item.setAdType(_tmpAdType);
        final int _tmpStatus;
        _tmpStatus = _cursor.getInt(_cursorIndexOfStatus);
        _item.setStatus(_tmpStatus);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
