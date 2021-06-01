package com.kabaladigital.tingtingu.database.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.InvalidationTracker.Observer;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.kabaladigital.tingtingu.database.Converters;
import com.kabaladigital.tingtingu.database.entity.Contact;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public final class ContactDao_Impl implements ContactDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfContact;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfContact;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByPhoneNumber;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  public ContactDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfContact = new EntityInsertionAdapter<Contact>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `contact_table`(`contact_id`,`list_id`,`full_name`,`phone_numbers`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Contact value) {
        stmt.bindLong(1, value.getContactId());
        stmt.bindLong(2, value.getListId());
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        final String _tmp;
        _tmp = Converters.listToString(value.getPhoneNumbers());
        if (_tmp == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, _tmp);
        }
      }
    };
    this.__updateAdapterOfContact = new EntityDeletionOrUpdateAdapter<Contact>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `contact_table` SET `contact_id` = ?,`list_id` = ?,`full_name` = ?,`phone_numbers` = ? WHERE `contact_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Contact value) {
        stmt.bindLong(1, value.getContactId());
        stmt.bindLong(2, value.getListId());
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        final String _tmp;
        _tmp = Converters.listToString(value.getPhoneNumbers());
        if (_tmp == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, _tmp);
        }
        stmt.bindLong(5, value.getContactId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM contact_table";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteByPhoneNumber = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM contact_table WHERE phone_numbers LIKE '%' || ? || '%'";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM contact_table WHERE contact_id = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(Contact... contacts) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfContact.insert(contacts);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insert(List<Contact> contacts) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfContact.insert(contacts);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(Contact... contacts) {
    __db.beginTransaction();
    try {
      __updateAdapterOfContact.handleMultiple(contacts);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public int deleteByPhoneNumber(String phoneNumber) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByPhoneNumber.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (phoneNumber == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, phoneNumber);
      }
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteByPhoneNumber.release(_stmt);
    }
  }

  @Override
  public int deleteById(long id) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, id);
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteById.release(_stmt);
    }
  }

  @Override
  public LiveData<List<Contact>> getContactsByPhoneNumber(String phoneNumber) {
    final String _sql = "SELECT * from contact_table WHERE phone_numbers LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (phoneNumber == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, phoneNumber);
    }
    return new ComputableLiveData<List<Contact>>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected List<Contact> compute() {
        if (_observer == null) {
          _observer = new Observer("contact_table") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfContactId = _cursor.getColumnIndexOrThrow("contact_id");
          final int _cursorIndexOfListId = _cursor.getColumnIndexOrThrow("list_id");
          final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("full_name");
          final int _cursorIndexOfPhoneNumbers = _cursor.getColumnIndexOrThrow("phone_numbers");
          final List<Contact> _result = new ArrayList<Contact>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Contact _item;
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final List<String> _tmpPhoneNumbers;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfPhoneNumbers);
            _tmpPhoneNumbers = Converters.stringToList(_tmp);
            _item = new Contact(_tmpName,_tmpPhoneNumbers);
            final long _tmpContactId;
            _tmpContactId = _cursor.getLong(_cursorIndexOfContactId);
            _item.setContactId(_tmpContactId);
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
  public LiveData<List<Contact>> getAllContacts() {
    final String _sql = "SELECT * from contact_table ORDER BY contact_id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<Contact>>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected List<Contact> compute() {
        if (_observer == null) {
          _observer = new Observer("contact_table") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfContactId = _cursor.getColumnIndexOrThrow("contact_id");
          final int _cursorIndexOfListId = _cursor.getColumnIndexOrThrow("list_id");
          final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("full_name");
          final int _cursorIndexOfPhoneNumbers = _cursor.getColumnIndexOrThrow("phone_numbers");
          final List<Contact> _result = new ArrayList<Contact>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Contact _item;
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final List<String> _tmpPhoneNumbers;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfPhoneNumbers);
            _tmpPhoneNumbers = Converters.stringToList(_tmp);
            _item = new Contact(_tmpName,_tmpPhoneNumbers);
            final long _tmpContactId;
            _tmpContactId = _cursor.getLong(_cursorIndexOfContactId);
            _item.setContactId(_tmpContactId);
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
  public LiveData<List<Contact>> getContactsInList(long listId) {
    final String _sql = "SELECT * from contact_table WHERE list_id LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, listId);
    return new ComputableLiveData<List<Contact>>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected List<Contact> compute() {
        if (_observer == null) {
          _observer = new Observer("contact_table") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfContactId = _cursor.getColumnIndexOrThrow("contact_id");
          final int _cursorIndexOfListId = _cursor.getColumnIndexOrThrow("list_id");
          final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("full_name");
          final int _cursorIndexOfPhoneNumbers = _cursor.getColumnIndexOrThrow("phone_numbers");
          final List<Contact> _result = new ArrayList<Contact>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Contact _item;
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final List<String> _tmpPhoneNumbers;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfPhoneNumbers);
            _tmpPhoneNumbers = Converters.stringToList(_tmp);
            _item = new Contact(_tmpName,_tmpPhoneNumbers);
            final long _tmpContactId;
            _tmpContactId = _cursor.getLong(_cursorIndexOfContactId);
            _item.setContactId(_tmpContactId);
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
