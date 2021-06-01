package com.kabaladigital.tingtingu.database.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.InvalidationTracker.Observer;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.kabaladigital.tingtingu.database.entity.CGroup;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public final class CGroupDao_Impl implements CGroupDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfCGroup;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByName;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  public CGroupDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCGroup = new EntityInsertionAdapter<CGroup>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `cgroup_table`(`list_id`,`name`) VALUES (nullif(?, 0),?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CGroup value) {
        stmt.bindLong(1, value.getListId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM cgroup_table";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteByName = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM cgroup_table WHERE name LIKE ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM cgroup_table WHERE list_id LIKE ?";
        return _query;
      }
    };
  }

  @Override
  public long[] insert(CGroup... lists) {
    __db.beginTransaction();
    try {
      long[] _result = __insertionAdapterOfCGroup.insertAndReturnIdsArray(lists);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int deleteAll() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public int deleteByName(String name) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByName.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (name == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, name);
      }
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteByName.release(_stmt);
    }
  }

  @Override
  public int deleteById(long listId) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, listId);
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteById.release(_stmt);
    }
  }

  @Override
  public LiveData<List<CGroup>> getCGroupById(long listId) {
    final String _sql = "SELECT * from cgroup_table WHERE list_id LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, listId);
    return new ComputableLiveData<List<CGroup>>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected List<CGroup> compute() {
        if (_observer == null) {
          _observer = new Observer("cgroup_table") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfListId = _cursor.getColumnIndexOrThrow("list_id");
          final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
          final List<CGroup> _result = new ArrayList<CGroup>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final CGroup _item;
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _item = new CGroup(_tmpName);
            final long _tmpListId;
            _tmpListId = _cursor.getLong(_cursorIndexOfListId);
            _item.setListId(_tmpListId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public LiveData<List<CGroup>> getCGroupByName(String name) {
    final String _sql = "SELECT * from cgroup_table WHERE name LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, name);
    }
    return new ComputableLiveData<List<CGroup>>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected List<CGroup> compute() {
        if (_observer == null) {
          _observer = new Observer("cgroup_table") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfListId = _cursor.getColumnIndexOrThrow("list_id");
          final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
          final List<CGroup> _result = new ArrayList<CGroup>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final CGroup _item;
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _item = new CGroup(_tmpName);
            final long _tmpListId;
            _tmpListId = _cursor.getLong(_cursorIndexOfListId);
            _item.setListId(_tmpListId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public LiveData<List<CGroup>> getAllCGroups() {
    final String _sql = "SELECT * from cgroup_table ORDER BY list_id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<CGroup>>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected List<CGroup> compute() {
        if (_observer == null) {
          _observer = new Observer("cgroup_table") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfListId = _cursor.getColumnIndexOrThrow("list_id");
          final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
          final List<CGroup> _result = new ArrayList<CGroup>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final CGroup _item;
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _item = new CGroup(_tmpName);
            final long _tmpListId;
            _tmpListId = _cursor.getLong(_cursorIndexOfListId);
            _item.setListId(_tmpListId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }
}
