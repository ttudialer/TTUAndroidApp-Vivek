package com.kabaladigital.tingtingu.database;

import android.os.Process;

import com.kabaladigital.tingtingu.database.entity.ActiveStatus;
import com.kabaladigital.tingtingu.database.entity.CampaignAds;
import com.kabaladigital.tingtingu.database.entity.CampaignAdsPlayOrder;
import com.kabaladigital.tingtingu.database.entity.CampaignLogs;
import com.kabaladigital.tingtingu.database.entity.StateCityModel;
import com.kabaladigital.tingtingu.models.IncomingCallAdData;
import com.kabaladigital.tingtingu.util.DateUtility;
import com.kabaladigital.tingtingu.util.Utilities;

import java.util.List;

public class DataRepository {

    private static DataRepository sInstance;

    private final AppDatabase mDatabase;

    public static DataRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }

    private DataRepository(final AppDatabase database) {
        mDatabase = database;
    }

    public void insertAdCampaing(CampaignAds campaignAds) {
        if (Utilities.isInUIThread()) {
            //TODO start in thread
        } else {
            mDatabase.getCampaignAdsDao().insert(campaignAds);
        }
    }

    public List<CampaignAds> getAd() {
        if (Utilities.isInUIThread()) {
            //TODO start in thread
        } else {
            return mDatabase.getCampaignAdsDao().getAllCampaignAds();
        }
        return null;
    }

    // - Delete - //

    public void deleteContact(long contactId) {
        Thread thread = new Thread(() -> {
            Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
            mDatabase.getContactDao().deleteById(contactId);
        });
        thread.start();
    }

    public void deleteCGroup(long listId) {
        Thread thread = new Thread(() -> {
            Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
            mDatabase.getCGroupDao().deleteById(listId);
        });
        thread.start();
    }


    // Ad
    public void insertAd(List<IncomingCallAdData> incomingCallAdData, List<CampaignAdsPlayOrder> campaignAdsPlayOrderList) {
//        Thread thread = new Thread(() -> {
//            Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
            mDatabase.getIncomingCallAdDao().insert(incomingCallAdData);

            for (int i=0;i<campaignAdsPlayOrderList.size();i++){
                if (mDatabase.getCampaignAdsPlayOrderDao()
                        .insert(campaignAdsPlayOrderList.get(i)) == -1){
                    mDatabase.getCampaignAdsPlayOrderDao()
                            .updateCampPlayOrderRecord(campaignAdsPlayOrderList.get(i).getCampId()
                                    ,campaignAdsPlayOrderList.get(i).getStatus(),
                                    campaignAdsPlayOrderList.get(i).getStartDate(),
                                    campaignAdsPlayOrderList.get(i).getEndDate(),
                                    campaignAdsPlayOrderList.get(i).getMaxDurationCount(),
                                    campaignAdsPlayOrderList.get(i).getAdDuration());
                }
            }
//        });
//        thread.start();
    }

    public void increasePlayCount(String campId) {
        Thread thread = new Thread(() -> {
            Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
            mDatabase.getCampaignAdsPlayOrderDao().updateDurationCount(campId);
        });
        thread.start();
    }

    public void increasePlayCount(String campId, int timeDiff) {
        Thread thread = new Thread(() -> {
            Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
            mDatabase.getCampaignAdsPlayOrderDao().updateDurationCount(campId,timeDiff);
        });
        thread.start();
    }

//    public CampaignAdsPlayOrder getAllCampaignAdsOrderByCount() {
//        return mDatabase.getCampaignAdsPlayOrderDao().getAllCampaignAdsOrderByCount();
//    }
//
//    public CampaignAdsPlayOrder getAllCampaignAdsOrderByCount(long currentDate, String campType) {
//        return mDatabase.getCampaignAdsPlayOrderDao().getAllCampaignAdsOrderByCount(currentDate,campType);
//    }

    public CampaignAdsPlayOrder getAllCampaignAdsOrderByCount(long currentDate, String campType, String status) {
        return mDatabase.getCampaignAdsPlayOrderDao().getAllCampaignAdsOrderByCount(currentDate,campType,status);
    }

    public CampaignAdsPlayOrder getIncomingCampaignAdsOrderByCount(long currentDate, String campType, String status, String clientName) {
        return mDatabase.getCampaignAdsPlayOrderDao().getIncomingCampaignAdsOrderByCount(currentDate,campType,status,clientName);
    }

    public CampaignAdsPlayOrder getB2BPopUpOrderByCount(long currentDate, String campType, String status, long mobileNumb) {
        return mDatabase.getCampaignAdsPlayOrderDao().getB2BPopupAdsOrderByCount(currentDate,campType,status,mobileNumb);
    }

    public Integer getPlayCount(String category) {
        Integer count = mDatabase.getCampaignAdsPlayOrderDao().getPlayCount(category);
        if (count != null){
            return count;
        }
        return 0;
    }

    public void updatePlayCount(String campId) {
        mDatabase.getCampaignAdsPlayOrderDao().updatePlayCount(campId);
    }

//    public LiveData<List<IncomingCallAdData>> getAds() {
//        return mDatabase.getIncomingCallAdDao().getAllAds();
//    }
//
//    public List<IncomingCallAdData> getAdsList(String adType) {
//        return mDatabase.getIncomingCallAdDao().getAllAdsList(adType);
//    }
//
//    public List<IncomingCallAdData> getAdsList(String adType, String videoSize) {
//        return mDatabase.getIncomingCallAdDao().getAllAdsList(adType,videoSize);
//    }

    public IncomingCallAdData getAdByCampId(String campId) {
        return mDatabase.getIncomingCallAdDao().getAdDetailByCampId(campId);
    }

    public List<IncomingCallAdData> getAdsListForURi() {
        return mDatabase.getIncomingCallAdDao().getAllAdsForURI();
    }

    public void updateAd(String uri, String id) {
        if (Utilities.isInUIThread()) {
            Thread thread = new Thread(() -> {
                Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
                mDatabase.getIncomingCallAdDao().updateAds(uri,id);
            });
            thread.start();
        } else {
            mDatabase.getIncomingCallAdDao().updateAds(uri,id);
        }

    }

    public void insertStateCity(List<StateCityModel> stateCityModelList) {
        if (Utilities.isInUIThread()) {
            Thread thread = new Thread(() -> {
                Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
                mDatabase.getStateCityDao().insert(stateCityModelList);
            });
            thread.start();
        } else {
            mDatabase.getStateCityDao().insert(stateCityModelList);
        }
    }

    public List<StateCityModel> getStateCityUri() {
        return mDatabase.getStateCityDao().getStateCityData();
    }

    public List<String> getAllStateList() {
        return mDatabase.getStateCityDao().getAllStateList();
    }

    public List<String> getCityListStateWise(String state) {
        return mDatabase.getStateCityDao().getCityListStateWise(state);
    }


    // Logs
    public void insertLog(CampaignLogs campaignLog) {
        Thread thread = new Thread(() -> {
            Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
            mDatabase.getCampaignAdLogsDao().insert(campaignLog);
        });
        thread.start();
    }

    public List<CampaignLogs> getAllNonSyncLogs(String campType) {
        return mDatabase.getCampaignAdLogsDao().getAllNonSyncLogs(campType);
    }

    public void updateLogsSyncCheck(int id) {
        mDatabase.getCampaignAdLogsDao().updateLogSync(id);
    }



    // Active Status

    public String getLastSyncDateTime(){
        String time = mDatabase.getActiveStatusDao().getLastDateTime();
        if (time == null){
            String currentTime = DateUtility.getCurrentDate();
            ActiveStatus activeStatus = new ActiveStatus();
            activeStatus.setCount(0);
            activeStatus.setDate(currentTime);
            activeStatus.setId(1);
            activeStatus.setTodayDate(DateUtility.getDateFormatted());
            mDatabase.getActiveStatusDao().insert(activeStatus);
            return currentTime;
        }
        return time;
    }

    public String getLastSyncDate(){
        return mDatabase.getActiveStatusDao().getLastDate();
    }

    public int getCount(){
        return mDatabase.getActiveStatusDao().getCount();
    }

    public void updateActiveStatus(int espTime){
        mDatabase.getActiveStatusDao().updateLogSync(DateUtility.getCurrentDate(),espTime);
    }

    public void updateActiveCountAndDate(int espNightTime){
        mDatabase.getActiveStatusDao().updateCountAndDate(DateUtility.getDateFormatted(),espNightTime);
    }


    public String getTodayDayFromPlayOrder(){
        return mDatabase.getCampaignAdsPlayOrderDao().getTodayDate();
    }

    public void updateTodayDateCount() {
        Thread thread = new Thread(() -> {
            Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
            mDatabase.getCampaignAdsPlayOrderDao().updateTodayDateCount(DateUtility.getDateFormatted());
        });
        thread.start();
    }

    public int checkPopUpCallerId(long number){
        return mDatabase.getCampaignAdsPlayOrderDao().checkCallerId(number);
    }
}
