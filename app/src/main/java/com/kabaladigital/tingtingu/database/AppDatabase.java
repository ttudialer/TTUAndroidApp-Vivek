package com.kabaladigital.tingtingu.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.kabaladigital.tingtingu.database.dao.ActiveStatusDao;
import com.kabaladigital.tingtingu.database.dao.CGroupDao;
import com.kabaladigital.tingtingu.database.dao.CampaignAdLogsDao;
import com.kabaladigital.tingtingu.database.dao.CampaignAdsDao;
import com.kabaladigital.tingtingu.database.dao.CampaignAdsPlayOrderDao;
import com.kabaladigital.tingtingu.database.dao.ContactDao;
import com.kabaladigital.tingtingu.database.dao.IncomingCallAdDao;
import com.kabaladigital.tingtingu.database.dao.StateCityDao;
import com.kabaladigital.tingtingu.database.entity.ActiveStatus;
import com.kabaladigital.tingtingu.database.entity.CGroup;
import com.kabaladigital.tingtingu.database.entity.CampaignAds;
import com.kabaladigital.tingtingu.database.entity.CampaignAdsPlayOrder;
import com.kabaladigital.tingtingu.database.entity.CampaignLogs;
import com.kabaladigital.tingtingu.database.entity.Contact;
import com.kabaladigital.tingtingu.database.entity.StateCityModel;
import com.kabaladigital.tingtingu.models.IncomingCallAdData;
import com.kabaladigital.tingtingu.util.DateUtility;

import java.util.concurrent.Executors;

@Database(entities = {CGroup.class, Contact.class
        , CampaignAds.class, IncomingCallAdData.class,
         StateCityModel.class, CampaignAdsPlayOrder.class, CampaignLogs.class, ActiveStatus.class}, version = 8)

public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sInstance;

    public static AppDatabase getDatabase(final Context context) {
        if (sInstance == null || !sInstance.isOpen()) {
            synchronized (AppDatabase.class) {
                if (sInstance == null || !sInstance.isOpen()) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "ttu_app_database")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            ActiveStatus activeStatus = new ActiveStatus();
                                            activeStatus.setCount(0);
                                            activeStatus.setDate(DateUtility.getCurrentDate());
                                            activeStatus.setId(1);
                                            activeStatus.setTodayDate(DateUtility.getDateFormatted());
                                            getDatabase(context).getActiveStatusDao().insert(activeStatus);
                                        }
                                    });
                                }
                            })
                            .build();
                }
            }
        }
        return sInstance;
    }

    // Contacts
    public abstract CGroupDao getCGroupDao();
    public abstract ContactDao getContactDao();
    public abstract IncomingCallAdDao getIncomingCallAdDao();


    //Ad
    public abstract CampaignAdsDao getCampaignAdsDao();
    public abstract StateCityDao getStateCityDao();
    public abstract CampaignAdsPlayOrderDao getCampaignAdsPlayOrderDao();
    public abstract CampaignAdLogsDao getCampaignAdLogsDao();
    public abstract ActiveStatusDao getActiveStatusDao();


}
