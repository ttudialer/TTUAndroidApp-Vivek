package com.kabaladigital.tingtingu.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.kabaladigital.tingtingu.database.dao.ActiveStatusDao;
import com.kabaladigital.tingtingu.database.dao.ActiveStatusDao_Impl;
import com.kabaladigital.tingtingu.database.dao.CGroupDao;
import com.kabaladigital.tingtingu.database.dao.CGroupDao_Impl;
import com.kabaladigital.tingtingu.database.dao.CampaignAdLogsDao;
import com.kabaladigital.tingtingu.database.dao.CampaignAdLogsDao_Impl;
import com.kabaladigital.tingtingu.database.dao.CampaignAdsDao;
import com.kabaladigital.tingtingu.database.dao.CampaignAdsDao_Impl;
import com.kabaladigital.tingtingu.database.dao.CampaignAdsPlayOrderDao;
import com.kabaladigital.tingtingu.database.dao.CampaignAdsPlayOrderDao_Impl;
import com.kabaladigital.tingtingu.database.dao.ContactDao;
import com.kabaladigital.tingtingu.database.dao.ContactDao_Impl;
import com.kabaladigital.tingtingu.database.dao.IncomingCallAdDao;
import com.kabaladigital.tingtingu.database.dao.IncomingCallAdDao_Impl;
import com.kabaladigital.tingtingu.database.dao.StateCityDao;
import com.kabaladigital.tingtingu.database.dao.StateCityDao_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class AppDatabase_Impl extends AppDatabase {
  private volatile CGroupDao _cGroupDao;

  private volatile ContactDao _contactDao;

  private volatile IncomingCallAdDao _incomingCallAdDao;

  private volatile CampaignAdsDao _campaignAdsDao;

  private volatile StateCityDao _stateCityDao;

  private volatile CampaignAdsPlayOrderDao _campaignAdsPlayOrderDao;

  private volatile CampaignAdLogsDao _campaignAdLogsDao;

  private volatile ActiveStatusDao _activeStatusDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(8) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `cgroup_table` (`list_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `contact_table` (`contact_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `list_id` INTEGER NOT NULL, `full_name` TEXT, `phone_numbers` TEXT NOT NULL, FOREIGN KEY(`list_id`) REFERENCES `cgroup_table`(`list_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE  INDEX `index_contact_table_list_id` ON `contact_table` (`list_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `campaign_ad_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `adURL` TEXT, `adURI` TEXT, `adType` INTEGER NOT NULL, `status` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `ad_table` (`campId` TEXT NOT NULL, `id` TEXT, `name` TEXT, `clientName` TEXT, `adCategory` TEXT, `startDate` TEXT, `endDate` TEXT, `agentName` TEXT, `adType` TEXT, `videoSize` TEXT, `usersadPublished` INTEGER, `adDuration` INTEGER, `maxplayDuration` INTEGER, `uploadFile` TEXT, `v` INTEGER, `status` INTEGER, `campaignName` TEXT, `filterAds` TEXT, `publicId` TEXT, `uri` TEXT, `callToAction` TEXT, `adCountPerUser` INTEGER, `adMaxDurUserPerDay` INTEGER, `adTotalCount` INTEGER, `advSource` TEXT, `adPlayDurForEachPlay` INTEGER, `callerId` INTEGER NOT NULL, PRIMARY KEY(`campId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `state_city_table` (`autoId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `id` TEXT, `state` TEXT, `city` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `campaign_ad_play_order_table` (`campId` TEXT NOT NULL, `status` INTEGER, `playCount` INTEGER, `startDate` INTEGER NOT NULL, `endDate` INTEGER NOT NULL, `CurrentDurationCount` INTEGER, `MaxDurationCount` INTEGER, `adDuration` INTEGER, `todayAdCount` INTEGER, `todayDate` TEXT, `CampCategory` TEXT, `callerId` INTEGER NOT NULL, `clientName` TEXT, PRIMARY KEY(`campId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `campaign_ad_logs_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `campId` TEXT, `adCategory` TEXT, `adType` TEXT, `instance` INTEGER, `callTime` TEXT, `callDate` TEXT, `startDateTime` TEXT, `endDateTime` TEXT, `callType` TEXT, `action` TEXT, `callStatus` TEXT, `playDuration` INTEGER, `isSynced` INTEGER NOT NULL, `campType` TEXT, `callToActionTime` TEXT, `callToAction` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `active_status` (`id` INTEGER NOT NULL, `date` TEXT, `count` INTEGER NOT NULL, `todayDate` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"73b808d76c3a3296f3e4b310d463fada\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `cgroup_table`");
        _db.execSQL("DROP TABLE IF EXISTS `contact_table`");
        _db.execSQL("DROP TABLE IF EXISTS `campaign_ad_table`");
        _db.execSQL("DROP TABLE IF EXISTS `ad_table`");
        _db.execSQL("DROP TABLE IF EXISTS `state_city_table`");
        _db.execSQL("DROP TABLE IF EXISTS `campaign_ad_play_order_table`");
        _db.execSQL("DROP TABLE IF EXISTS `campaign_ad_logs_table`");
        _db.execSQL("DROP TABLE IF EXISTS `active_status`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        _db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsCgroupTable = new HashMap<String, TableInfo.Column>(2);
        _columnsCgroupTable.put("list_id", new TableInfo.Column("list_id", "INTEGER", true, 1));
        _columnsCgroupTable.put("name", new TableInfo.Column("name", "TEXT", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCgroupTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCgroupTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCgroupTable = new TableInfo("cgroup_table", _columnsCgroupTable, _foreignKeysCgroupTable, _indicesCgroupTable);
        final TableInfo _existingCgroupTable = TableInfo.read(_db, "cgroup_table");
        if (! _infoCgroupTable.equals(_existingCgroupTable)) {
          throw new IllegalStateException("Migration didn't properly handle cgroup_table(com.kabaladigital.tingtingu.database.entity.CGroup).\n"
                  + " Expected:\n" + _infoCgroupTable + "\n"
                  + " Found:\n" + _existingCgroupTable);
        }
        final HashMap<String, TableInfo.Column> _columnsContactTable = new HashMap<String, TableInfo.Column>(4);
        _columnsContactTable.put("contact_id", new TableInfo.Column("contact_id", "INTEGER", true, 1));
        _columnsContactTable.put("list_id", new TableInfo.Column("list_id", "INTEGER", true, 0));
        _columnsContactTable.put("full_name", new TableInfo.Column("full_name", "TEXT", false, 0));
        _columnsContactTable.put("phone_numbers", new TableInfo.Column("phone_numbers", "TEXT", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysContactTable = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysContactTable.add(new TableInfo.ForeignKey("cgroup_table", "CASCADE", "NO ACTION",Arrays.asList("list_id"), Arrays.asList("list_id")));
        final HashSet<TableInfo.Index> _indicesContactTable = new HashSet<TableInfo.Index>(1);
        _indicesContactTable.add(new TableInfo.Index("index_contact_table_list_id", false, Arrays.asList("list_id")));
        final TableInfo _infoContactTable = new TableInfo("contact_table", _columnsContactTable, _foreignKeysContactTable, _indicesContactTable);
        final TableInfo _existingContactTable = TableInfo.read(_db, "contact_table");
        if (! _infoContactTable.equals(_existingContactTable)) {
          throw new IllegalStateException("Migration didn't properly handle contact_table(com.kabaladigital.tingtingu.database.entity.Contact).\n"
                  + " Expected:\n" + _infoContactTable + "\n"
                  + " Found:\n" + _existingContactTable);
        }
        final HashMap<String, TableInfo.Column> _columnsCampaignAdTable = new HashMap<String, TableInfo.Column>(5);
        _columnsCampaignAdTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsCampaignAdTable.put("adURL", new TableInfo.Column("adURL", "TEXT", false, 0));
        _columnsCampaignAdTable.put("adURI", new TableInfo.Column("adURI", "TEXT", false, 0));
        _columnsCampaignAdTable.put("adType", new TableInfo.Column("adType", "INTEGER", true, 0));
        _columnsCampaignAdTable.put("status", new TableInfo.Column("status", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCampaignAdTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCampaignAdTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCampaignAdTable = new TableInfo("campaign_ad_table", _columnsCampaignAdTable, _foreignKeysCampaignAdTable, _indicesCampaignAdTable);
        final TableInfo _existingCampaignAdTable = TableInfo.read(_db, "campaign_ad_table");
        if (! _infoCampaignAdTable.equals(_existingCampaignAdTable)) {
          throw new IllegalStateException("Migration didn't properly handle campaign_ad_table(com.kabaladigital.tingtingu.database.entity.CampaignAds).\n"
                  + " Expected:\n" + _infoCampaignAdTable + "\n"
                  + " Found:\n" + _existingCampaignAdTable);
        }
        final HashMap<String, TableInfo.Column> _columnsAdTable = new HashMap<String, TableInfo.Column>(27);
        _columnsAdTable.put("campId", new TableInfo.Column("campId", "TEXT", true, 1));
        _columnsAdTable.put("id", new TableInfo.Column("id", "TEXT", false, 0));
        _columnsAdTable.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsAdTable.put("clientName", new TableInfo.Column("clientName", "TEXT", false, 0));
        _columnsAdTable.put("adCategory", new TableInfo.Column("adCategory", "TEXT", false, 0));
        _columnsAdTable.put("startDate", new TableInfo.Column("startDate", "TEXT", false, 0));
        _columnsAdTable.put("endDate", new TableInfo.Column("endDate", "TEXT", false, 0));
        _columnsAdTable.put("agentName", new TableInfo.Column("agentName", "TEXT", false, 0));
        _columnsAdTable.put("adType", new TableInfo.Column("adType", "TEXT", false, 0));
        _columnsAdTable.put("videoSize", new TableInfo.Column("videoSize", "TEXT", false, 0));
        _columnsAdTable.put("usersadPublished", new TableInfo.Column("usersadPublished", "INTEGER", false, 0));
        _columnsAdTable.put("adDuration", new TableInfo.Column("adDuration", "INTEGER", false, 0));
        _columnsAdTable.put("maxplayDuration", new TableInfo.Column("maxplayDuration", "INTEGER", false, 0));
        _columnsAdTable.put("uploadFile", new TableInfo.Column("uploadFile", "TEXT", false, 0));
        _columnsAdTable.put("v", new TableInfo.Column("v", "INTEGER", false, 0));
        _columnsAdTable.put("status", new TableInfo.Column("status", "INTEGER", false, 0));
        _columnsAdTable.put("campaignName", new TableInfo.Column("campaignName", "TEXT", false, 0));
        _columnsAdTable.put("filterAds", new TableInfo.Column("filterAds", "TEXT", false, 0));
        _columnsAdTable.put("publicId", new TableInfo.Column("publicId", "TEXT", false, 0));
        _columnsAdTable.put("uri", new TableInfo.Column("uri", "TEXT", false, 0));
        _columnsAdTable.put("callToAction", new TableInfo.Column("callToAction", "TEXT", false, 0));
        _columnsAdTable.put("adCountPerUser", new TableInfo.Column("adCountPerUser", "INTEGER", false, 0));
        _columnsAdTable.put("adMaxDurUserPerDay", new TableInfo.Column("adMaxDurUserPerDay", "INTEGER", false, 0));
        _columnsAdTable.put("adTotalCount", new TableInfo.Column("adTotalCount", "INTEGER", false, 0));
        _columnsAdTable.put("advSource", new TableInfo.Column("advSource", "TEXT", false, 0));
        _columnsAdTable.put("adPlayDurForEachPlay", new TableInfo.Column("adPlayDurForEachPlay", "INTEGER", false, 0));
        _columnsAdTable.put("callerId", new TableInfo.Column("callerId", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAdTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAdTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAdTable = new TableInfo("ad_table", _columnsAdTable, _foreignKeysAdTable, _indicesAdTable);
        final TableInfo _existingAdTable = TableInfo.read(_db, "ad_table");
        if (! _infoAdTable.equals(_existingAdTable)) {
          throw new IllegalStateException("Migration didn't properly handle ad_table(com.kabaladigital.tingtingu.models.IncomingCallAdData).\n"
                  + " Expected:\n" + _infoAdTable + "\n"
                  + " Found:\n" + _existingAdTable);
        }
        final HashMap<String, TableInfo.Column> _columnsStateCityTable = new HashMap<String, TableInfo.Column>(4);
        _columnsStateCityTable.put("autoId", new TableInfo.Column("autoId", "INTEGER", true, 1));
        _columnsStateCityTable.put("id", new TableInfo.Column("id", "TEXT", false, 0));
        _columnsStateCityTable.put("state", new TableInfo.Column("state", "TEXT", false, 0));
        _columnsStateCityTable.put("city", new TableInfo.Column("city", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStateCityTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStateCityTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStateCityTable = new TableInfo("state_city_table", _columnsStateCityTable, _foreignKeysStateCityTable, _indicesStateCityTable);
        final TableInfo _existingStateCityTable = TableInfo.read(_db, "state_city_table");
        if (! _infoStateCityTable.equals(_existingStateCityTable)) {
          throw new IllegalStateException("Migration didn't properly handle state_city_table(com.kabaladigital.tingtingu.database.entity.StateCityModel).\n"
                  + " Expected:\n" + _infoStateCityTable + "\n"
                  + " Found:\n" + _existingStateCityTable);
        }
        final HashMap<String, TableInfo.Column> _columnsCampaignAdPlayOrderTable = new HashMap<String, TableInfo.Column>(13);
        _columnsCampaignAdPlayOrderTable.put("campId", new TableInfo.Column("campId", "TEXT", true, 1));
        _columnsCampaignAdPlayOrderTable.put("status", new TableInfo.Column("status", "INTEGER", false, 0));
        _columnsCampaignAdPlayOrderTable.put("playCount", new TableInfo.Column("playCount", "INTEGER", false, 0));
        _columnsCampaignAdPlayOrderTable.put("startDate", new TableInfo.Column("startDate", "INTEGER", true, 0));
        _columnsCampaignAdPlayOrderTable.put("endDate", new TableInfo.Column("endDate", "INTEGER", true, 0));
        _columnsCampaignAdPlayOrderTable.put("CurrentDurationCount", new TableInfo.Column("CurrentDurationCount", "INTEGER", false, 0));
        _columnsCampaignAdPlayOrderTable.put("MaxDurationCount", new TableInfo.Column("MaxDurationCount", "INTEGER", false, 0));
        _columnsCampaignAdPlayOrderTable.put("adDuration", new TableInfo.Column("adDuration", "INTEGER", false, 0));
        _columnsCampaignAdPlayOrderTable.put("todayAdCount", new TableInfo.Column("todayAdCount", "INTEGER", false, 0));
        _columnsCampaignAdPlayOrderTable.put("todayDate", new TableInfo.Column("todayDate", "TEXT", false, 0));
        _columnsCampaignAdPlayOrderTable.put("CampCategory", new TableInfo.Column("CampCategory", "TEXT", false, 0));
        _columnsCampaignAdPlayOrderTable.put("callerId", new TableInfo.Column("callerId", "INTEGER", true, 0));
        _columnsCampaignAdPlayOrderTable.put("clientName", new TableInfo.Column("clientName", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCampaignAdPlayOrderTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCampaignAdPlayOrderTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCampaignAdPlayOrderTable = new TableInfo("campaign_ad_play_order_table", _columnsCampaignAdPlayOrderTable, _foreignKeysCampaignAdPlayOrderTable, _indicesCampaignAdPlayOrderTable);
        final TableInfo _existingCampaignAdPlayOrderTable = TableInfo.read(_db, "campaign_ad_play_order_table");
        if (! _infoCampaignAdPlayOrderTable.equals(_existingCampaignAdPlayOrderTable)) {
          throw new IllegalStateException("Migration didn't properly handle campaign_ad_play_order_table(com.kabaladigital.tingtingu.database.entity.CampaignAdsPlayOrder).\n"
                  + " Expected:\n" + _infoCampaignAdPlayOrderTable + "\n"
                  + " Found:\n" + _existingCampaignAdPlayOrderTable);
        }
        final HashMap<String, TableInfo.Column> _columnsCampaignAdLogsTable = new HashMap<String, TableInfo.Column>(17);
        _columnsCampaignAdLogsTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsCampaignAdLogsTable.put("campId", new TableInfo.Column("campId", "TEXT", false, 0));
        _columnsCampaignAdLogsTable.put("adCategory", new TableInfo.Column("adCategory", "TEXT", false, 0));
        _columnsCampaignAdLogsTable.put("adType", new TableInfo.Column("adType", "TEXT", false, 0));
        _columnsCampaignAdLogsTable.put("instance", new TableInfo.Column("instance", "INTEGER", false, 0));
        _columnsCampaignAdLogsTable.put("callTime", new TableInfo.Column("callTime", "TEXT", false, 0));
        _columnsCampaignAdLogsTable.put("callDate", new TableInfo.Column("callDate", "TEXT", false, 0));
        _columnsCampaignAdLogsTable.put("startDateTime", new TableInfo.Column("startDateTime", "TEXT", false, 0));
        _columnsCampaignAdLogsTable.put("endDateTime", new TableInfo.Column("endDateTime", "TEXT", false, 0));
        _columnsCampaignAdLogsTable.put("callType", new TableInfo.Column("callType", "TEXT", false, 0));
        _columnsCampaignAdLogsTable.put("action", new TableInfo.Column("action", "TEXT", false, 0));
        _columnsCampaignAdLogsTable.put("callStatus", new TableInfo.Column("callStatus", "TEXT", false, 0));
        _columnsCampaignAdLogsTable.put("playDuration", new TableInfo.Column("playDuration", "INTEGER", false, 0));
        _columnsCampaignAdLogsTable.put("isSynced", new TableInfo.Column("isSynced", "INTEGER", true, 0));
        _columnsCampaignAdLogsTable.put("campType", new TableInfo.Column("campType", "TEXT", false, 0));
        _columnsCampaignAdLogsTable.put("callToActionTime", new TableInfo.Column("callToActionTime", "TEXT", false, 0));
        _columnsCampaignAdLogsTable.put("callToAction", new TableInfo.Column("callToAction", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCampaignAdLogsTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCampaignAdLogsTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCampaignAdLogsTable = new TableInfo("campaign_ad_logs_table", _columnsCampaignAdLogsTable, _foreignKeysCampaignAdLogsTable, _indicesCampaignAdLogsTable);
        final TableInfo _existingCampaignAdLogsTable = TableInfo.read(_db, "campaign_ad_logs_table");
        if (! _infoCampaignAdLogsTable.equals(_existingCampaignAdLogsTable)) {
          throw new IllegalStateException("Migration didn't properly handle campaign_ad_logs_table(com.kabaladigital.tingtingu.database.entity.CampaignLogs).\n"
                  + " Expected:\n" + _infoCampaignAdLogsTable + "\n"
                  + " Found:\n" + _existingCampaignAdLogsTable);
        }
        final HashMap<String, TableInfo.Column> _columnsActiveStatus = new HashMap<String, TableInfo.Column>(4);
        _columnsActiveStatus.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsActiveStatus.put("date", new TableInfo.Column("date", "TEXT", false, 0));
        _columnsActiveStatus.put("count", new TableInfo.Column("count", "INTEGER", true, 0));
        _columnsActiveStatus.put("todayDate", new TableInfo.Column("todayDate", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysActiveStatus = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesActiveStatus = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoActiveStatus = new TableInfo("active_status", _columnsActiveStatus, _foreignKeysActiveStatus, _indicesActiveStatus);
        final TableInfo _existingActiveStatus = TableInfo.read(_db, "active_status");
        if (! _infoActiveStatus.equals(_existingActiveStatus)) {
          throw new IllegalStateException("Migration didn't properly handle active_status(com.kabaladigital.tingtingu.database.entity.ActiveStatus).\n"
                  + " Expected:\n" + _infoActiveStatus + "\n"
                  + " Found:\n" + _existingActiveStatus);
        }
      }
    }, "73b808d76c3a3296f3e4b310d463fada", "d001cc53cf0ff82687ebb12c4bbdd569");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "cgroup_table","contact_table","campaign_ad_table","ad_table","state_city_table","campaign_ad_play_order_table","campaign_ad_logs_table","active_status");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `cgroup_table`");
      _db.execSQL("DELETE FROM `contact_table`");
      _db.execSQL("DELETE FROM `campaign_ad_table`");
      _db.execSQL("DELETE FROM `ad_table`");
      _db.execSQL("DELETE FROM `state_city_table`");
      _db.execSQL("DELETE FROM `campaign_ad_play_order_table`");
      _db.execSQL("DELETE FROM `campaign_ad_logs_table`");
      _db.execSQL("DELETE FROM `active_status`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public CGroupDao getCGroupDao() {
    if (_cGroupDao != null) {
      return _cGroupDao;
    } else {
      synchronized(this) {
        if(_cGroupDao == null) {
          _cGroupDao = new CGroupDao_Impl(this);
        }
        return _cGroupDao;
      }
    }
  }

  @Override
  public ContactDao getContactDao() {
    if (_contactDao != null) {
      return _contactDao;
    } else {
      synchronized(this) {
        if(_contactDao == null) {
          _contactDao = new ContactDao_Impl(this);
        }
        return _contactDao;
      }
    }
  }

  @Override
  public IncomingCallAdDao getIncomingCallAdDao() {
    if (_incomingCallAdDao != null) {
      return _incomingCallAdDao;
    } else {
      synchronized(this) {
        if(_incomingCallAdDao == null) {
          _incomingCallAdDao = new IncomingCallAdDao_Impl(this);
        }
        return _incomingCallAdDao;
      }
    }
  }

  @Override
  public CampaignAdsDao getCampaignAdsDao() {
    if (_campaignAdsDao != null) {
      return _campaignAdsDao;
    } else {
      synchronized(this) {
        if(_campaignAdsDao == null) {
          _campaignAdsDao = new CampaignAdsDao_Impl(this);
        }
        return _campaignAdsDao;
      }
    }
  }

  @Override
  public StateCityDao getStateCityDao() {
    if (_stateCityDao != null) {
      return _stateCityDao;
    } else {
      synchronized(this) {
        if(_stateCityDao == null) {
          _stateCityDao = new StateCityDao_Impl(this);
        }
        return _stateCityDao;
      }
    }
  }

  @Override
  public CampaignAdsPlayOrderDao getCampaignAdsPlayOrderDao() {
    if (_campaignAdsPlayOrderDao != null) {
      return _campaignAdsPlayOrderDao;
    } else {
      synchronized(this) {
        if(_campaignAdsPlayOrderDao == null) {
          _campaignAdsPlayOrderDao = new CampaignAdsPlayOrderDao_Impl(this);
        }
        return _campaignAdsPlayOrderDao;
      }
    }
  }

  @Override
  public CampaignAdLogsDao getCampaignAdLogsDao() {
    if (_campaignAdLogsDao != null) {
      return _campaignAdLogsDao;
    } else {
      synchronized(this) {
        if(_campaignAdLogsDao == null) {
          _campaignAdLogsDao = new CampaignAdLogsDao_Impl(this);
        }
        return _campaignAdLogsDao;
      }
    }
  }

  @Override
  public ActiveStatusDao getActiveStatusDao() {
    if (_activeStatusDao != null) {
      return _activeStatusDao;
    } else {
      synchronized(this) {
        if(_activeStatusDao == null) {
          _activeStatusDao = new ActiveStatusDao_Impl(this);
        }
        return _activeStatusDao;
      }
    }
  }
}
