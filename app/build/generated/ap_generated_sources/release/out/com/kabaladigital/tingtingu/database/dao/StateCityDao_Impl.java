package com.kabaladigital.tingtingu.database.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.kabaladigital.tingtingu.database.entity.StateCityModel;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class StateCityDao_Impl implements StateCityDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfStateCityModel;

  public StateCityDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfStateCityModel = new EntityInsertionAdapter<StateCityModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `state_city_table`(`autoId`,`id`,`state`,`city`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, StateCityModel value) {
        stmt.bindLong(1, value.getAutoId());
        if (value.getId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getId());
        }
        if (value.getState() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getState());
        }
        if (value.getCity() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getCity());
        }
      }
    };
  }

  @Override
  public void insert(List<StateCityModel> stateCityModel) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfStateCityModel.insert(stateCityModel);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<StateCityModel> getStateCityData() {
    final String _sql = "SELECT * from state_city_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfAutoId = _cursor.getColumnIndexOrThrow("autoId");
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfState = _cursor.getColumnIndexOrThrow("state");
      final int _cursorIndexOfCity = _cursor.getColumnIndexOrThrow("city");
      final List<StateCityModel> _result = new ArrayList<StateCityModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final StateCityModel _item;
        _item = new StateCityModel();
        final int _tmpAutoId;
        _tmpAutoId = _cursor.getInt(_cursorIndexOfAutoId);
        _item.setAutoId(_tmpAutoId);
        final String _tmpId;
        _tmpId = _cursor.getString(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpState;
        _tmpState = _cursor.getString(_cursorIndexOfState);
        _item.setState(_tmpState);
        final String _tmpCity;
        _tmpCity = _cursor.getString(_cursorIndexOfCity);
        _item.setCity(_tmpCity);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<String> getAllStateList() {
    final String _sql = "SELECT DISTINCT state from state_city_table ORDER BY state";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final List<String> _result = new ArrayList<String>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final String _item;
        _item = _cursor.getString(0);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<String> getCityListStateWise(String mState) {
    final String _sql = "SELECT DISTINCT city from state_city_table where state = ? ORDER BY city";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (mState == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, mState);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final List<String> _result = new ArrayList<String>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final String _item;
        _item = _cursor.getString(0);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
