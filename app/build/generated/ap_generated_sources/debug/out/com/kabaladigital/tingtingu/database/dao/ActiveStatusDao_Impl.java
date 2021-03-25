package com.kabaladigital.tingtingu.database.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.kabaladigital.tingtingu.database.entity.ActiveStatus;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings("unchecked")
public final class ActiveStatusDao_Impl implements ActiveStatusDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfActiveStatus;

  private final SharedSQLiteStatement __preparedStmtOfUpdateLogSync;

  private final SharedSQLiteStatement __preparedStmtOfUpdateCountAndDate;

  public ActiveStatusDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfActiveStatus = new EntityInsertionAdapter<ActiveStatus>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `active_status`(`id`,`date`,`count`,`todayDate`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ActiveStatus value) {
        stmt.bindLong(1, value.getId());
        if (value.getDate() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDate());
        }
        stmt.bindLong(3, value.getCount());
        if (value.getTodayDate() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getTodayDate());
        }
      }
    };
    this.__preparedStmtOfUpdateLogSync = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE active_status set count = count+? , date =? WHERE id = 1";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateCountAndDate = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE active_status set count =? , todayDate =? WHERE id = 1";
        return _query;
      }
    };
  }

  @Override
  public long insert(ActiveStatus activeStatus) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfActiveStatus.insertAndReturnId(activeStatus);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateLogSync(String sDate, int espTime) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateLogSync.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, espTime);
      _argIndex = 2;
      if (sDate == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, sDate);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateLogSync.release(_stmt);
    }
  }

  @Override
  public void updateCountAndDate(String sDate, int espNightTime) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateCountAndDate.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, espNightTime);
      _argIndex = 2;
      if (sDate == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, sDate);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateCountAndDate.release(_stmt);
    }
  }

  @Override
  public String getLastDateTime() {
    final String _sql = "SELECT date from active_status where id = 1";
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
  public String getLastDate() {
    final String _sql = "SELECT todayDate from active_status where id = 1";
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
  public int getCount() {
    final String _sql = "SELECT count from active_status where id = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
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
