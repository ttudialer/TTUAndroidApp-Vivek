package com.kabaladigital.tingtingu.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.telecom.TelecomManager;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.database.AppDatabase;
import com.kabaladigital.tingtingu.database.DataRepository;
import com.kabaladigital.tingtingu.database.entity.CampaignAdsPlayOrder;
import com.kabaladigital.tingtingu.database.entity.CampaignLogs;
import com.kabaladigital.tingtingu.models.AllCampaignModel;
import com.kabaladigital.tingtingu.models.IncomingCallAdData;
import com.kabaladigital.tingtingu.networking.ApiClient;
import com.kabaladigital.tingtingu.networking.ApiInterface;
import com.kabaladigital.tingtingu.networking.RequestFormatter;
import com.kabaladigital.tingtingu.ui.activity.MainActivity;
import com.kabaladigital.tingtingu.util.DateUtility;
import com.kabaladigital.tingtingu.util.ImageVideoDownloadManager;
import com.kabaladigital.tingtingu.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyBroadCastReceiver extends BroadcastReceiver {

    private Context mContext;
    private static final String WORK_RESULT = "work_result";

    private static final String TAG = GetAdData.class.getSimpleName();


    public static final CharSequence VERBOSE_NOTIFICATION_CHANNEL_NAME =
            "Verbose WorkManager Notifications";
    public static final String KEY_OUTPUT_DATA = "KEY_OUTPUT_DATA";
    public static String VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION =
            "Shows notifications whenever work starts";
    public static final CharSequence NOTIFICATION_TITLE = "TTU SYNC";
    public static final String CHANNEL_ID = "VERBOSE_NOTIFICATION";
    public static final int NOTIFICATION_ID = 1;

    // The name of the Sync Data work
    public static final String SYNC_DATA_WORK_NAME = "sync_data_work_name";

    DataRepository repository;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;

        repository = DataRepository
                .getInstance(AppDatabase.getDatabase(context));

        if (!DateUtility.getDateFormatted().equals(repository.getTodayDayFromPlayOrder())){
            repository.updateTodayDateCount();
        }

        setActiveStatus();

        PreferenceUtils.getInstance(context,true);

        String auth = PreferenceUtils.getInstance().getString(R.string.pref_user_token_value);

        ApiClient.createService(ApiInterface.class).getAdDatas().enqueue(new Callback<AllCampaignModel>() {
            @Override
            public void onResponse(Call<AllCampaignModel> call, Response<AllCampaignModel> response) {
                if (response.code()==200){

                    Integer playCountIncoming = repository.getPlayCount("Incoming");
                    Integer playCountPopUp = repository.getPlayCount("Popup");
                    Integer playCountOutgoing = repository.getPlayCount("Outgoing");
                    Integer playCountInProgress = repository.getPlayCount("InProgress");

                    List<CampaignAdsPlayOrder> campaignAdsPlayOrderList = new ArrayList<>();

                    if (response.body().getIncCallAds().size()>0){
                        for (int i=0; i<response.body().getIncCallAds().size();i++){
                            CampaignAdsPlayOrder campaignAdsPlayOrder = new CampaignAdsPlayOrder();
                            campaignAdsPlayOrder.setCampId(response.body().getIncCallAds().get(i).getId());
                            campaignAdsPlayOrder.setStatus(response.body().getIncCallAds().get(i).getStatus());
                            campaignAdsPlayOrder.setStartDate(DateUtility.convertDbDate(response.body().getIncCallAds().get(i).getStartDate()));
                            campaignAdsPlayOrder.setEndDate(DateUtility.convertDbEndDate(response.body().getIncCallAds().get(i).getEndDate()));
                            campaignAdsPlayOrder.setPlayCount(playCountIncoming);
                            campaignAdsPlayOrder.setCurrentDurationCount(0);
                            campaignAdsPlayOrder.setAdDuration(response.body().getIncCallAds().get(i).getAdDuration() * 60);
                            campaignAdsPlayOrder.setTodayDate(DateUtility.getDateFormatted());
                            campaignAdsPlayOrder.setTodayAdCount(0);
                            campaignAdsPlayOrder.setClientName(response.body().getIncCallAds().get(i).getClientName());
                            if (response.body().getIncCallAds().get(i).getClientName().contains("B2B")){
                                campaignAdsPlayOrder.setCampCategory("Incoming Caller");
                            }else {
                                campaignAdsPlayOrder.setCampCategory("Incoming");
                            }
                            if (response.body().getIncCallAds().get(i).getMaxplayDuration()==null || response.body().getIncCallAds().get(i).getMaxplayDuration()==0){
                                campaignAdsPlayOrder.setMaxDurationCount(1440 * 60);
                            }else {
                                campaignAdsPlayOrder.setMaxDurationCount(response.body().getIncCallAds().get(i).getMaxplayDuration() * 60);
                            }
                            campaignAdsPlayOrderList.add(campaignAdsPlayOrder);
                        }
                    }

                    if (response.body().getPopupAdvs().size()>0){
                        for (int i=0; i<response.body().getPopupAdvs().size();i++){
                            CampaignAdsPlayOrder campaignAdsPlayOrder = new CampaignAdsPlayOrder();
                            campaignAdsPlayOrder.setCampId(response.body().getPopupAdvs().get(i).getId());
                            campaignAdsPlayOrder.setStatus(response.body().getPopupAdvs().get(i).getStatus());
                            campaignAdsPlayOrder.setStartDate(DateUtility.convertDbDate(response.body().getPopupAdvs().get(i).getStartDate()));
                            campaignAdsPlayOrder.setEndDate(DateUtility.convertDbEndDate(response.body().getPopupAdvs().get(i).getEndDate()));
                            campaignAdsPlayOrder.setPlayCount(playCountPopUp);
                            campaignAdsPlayOrder.setCurrentDurationCount(0);
                            campaignAdsPlayOrder.setAdDuration(response.body().getPopupAdvs().get(i).getAdCountPerUser());
                            campaignAdsPlayOrder.setTodayDate(DateUtility.getDateFormatted());
                            campaignAdsPlayOrder.setTodayAdCount(0);

                            campaignAdsPlayOrder.setCallerId(response.body().getPopupAdvs().get(i).getCallerId());
                            if (response.body().getPopupAdvs().get(i).getCallerId() != 0){
                                campaignAdsPlayOrder.setCampCategory("Popup Reject");
                            }else {
                                campaignAdsPlayOrder.setCampCategory("Popup");
                            }
                            if (response.body().getPopupAdvs().get(i).getAdMaxDurUserPerDay()==null){
                                campaignAdsPlayOrder.setMaxDurationCount(1440);
                            }else {
                                campaignAdsPlayOrder.setMaxDurationCount(response.body().getPopupAdvs().get(i).getAdMaxDurUserPerDay());
                            }

                            campaignAdsPlayOrderList.add(campaignAdsPlayOrder);
                        }
                    }

                    if (response.body().getOutgoingAdvs().size()>0){
                        for (int i=0; i<response.body().getOutgoingAdvs().size();i++){
                            CampaignAdsPlayOrder campaignAdsPlayOrder = new CampaignAdsPlayOrder();
                            campaignAdsPlayOrder.setCampId(response.body().getOutgoingAdvs().get(i).getId());
                            campaignAdsPlayOrder.setStatus(response.body().getOutgoingAdvs().get(i).getStatus());
                            campaignAdsPlayOrder.setStartDate(DateUtility.convertDbDate(response.body().getOutgoingAdvs().get(i).getStartDate()));
                            campaignAdsPlayOrder.setEndDate(DateUtility.convertDbEndDate(response.body().getOutgoingAdvs().get(i).getEndDate()));
                            campaignAdsPlayOrder.setPlayCount(playCountOutgoing);
                            campaignAdsPlayOrder.setCurrentDurationCount(0);
                            campaignAdsPlayOrder.setAdDuration(response.body().getOutgoingAdvs().get(i).getAdDuration() * 60);
                            campaignAdsPlayOrder.setTodayDate(DateUtility.getDateFormatted());
                            campaignAdsPlayOrder.setTodayAdCount(0);
                            campaignAdsPlayOrder.setCampCategory("Outgoing");
                            if (response.body().getOutgoingAdvs().get(i).getMaxplayDuration()==null || response.body().getOutgoingAdvs().get(i).getMaxplayDuration()==0){
                                campaignAdsPlayOrder.setMaxDurationCount(1440 * 60);
                            }else {
                                campaignAdsPlayOrder.setMaxDurationCount(response.body().getOutgoingAdvs().get(i).getMaxplayDuration() * 60);
                            }
                            campaignAdsPlayOrderList.add(campaignAdsPlayOrder);
                        }
                    }

                    if (response.body().getInprogressAdvs().size()>0){
                        for (int i=0; i<response.body().getInprogressAdvs().size();i++){
                            CampaignAdsPlayOrder campaignAdsPlayOrder = new CampaignAdsPlayOrder();
                            campaignAdsPlayOrder.setCampId(response.body().getInprogressAdvs().get(i).getId());
                            campaignAdsPlayOrder.setStatus(response.body().getInprogressAdvs().get(i).getStatus());
                            campaignAdsPlayOrder.setStartDate(DateUtility.convertDbDate(response.body().getInprogressAdvs().get(i).getStartDate()));
                            campaignAdsPlayOrder.setEndDate(DateUtility.convertDbEndDate(response.body().getInprogressAdvs().get(i).getEndDate()));
                            campaignAdsPlayOrder.setPlayCount(playCountInProgress);
                            campaignAdsPlayOrder.setCurrentDurationCount(0);
                            campaignAdsPlayOrder.setAdDuration(response.body().getInprogressAdvs().get(i).getAdDuration() * 60);
                            campaignAdsPlayOrder.setTodayDate(DateUtility.getDateFormatted());
                            campaignAdsPlayOrder.setTodayAdCount(0);
                            campaignAdsPlayOrder.setCampCategory("InProgress");
                            if (response.body().getInprogressAdvs().get(i).getMaxplayDuration()==null || response.body().getInprogressAdvs().get(i).getMaxplayDuration()==0){
                                campaignAdsPlayOrder.setMaxDurationCount(1440 * 60);
                            }else {
                                campaignAdsPlayOrder.setMaxDurationCount(response.body().getInprogressAdvs().get(i).getMaxplayDuration() * 60);
                            }
                            campaignAdsPlayOrderList.add(campaignAdsPlayOrder);
                        }
                    }

                    List<IncomingCallAdData> allCamp = new ArrayList<>();

                    List<IncomingCallAdData> incomingCamp = response.body().getIncCallAds();
                    for (int i=0;i<incomingCamp.size();i++){
                        incomingCamp.get(i).setCampId(incomingCamp.get(i).getId());
                        incomingCamp.get(i).setAdvSource(incomingCamp.get(i).getUploadFile());
                    }

                    List<IncomingCallAdData> popUpCamp = response.body().getPopupAdvs();
                    for (int i=0;i<popUpCamp.size();i++){
                        popUpCamp.get(i).setCampId(popUpCamp.get(i).getId());
                        popUpCamp.get(i).setUploadFile(popUpCamp.get(i).getAdvSource());
                        popUpCamp.get(i).setAdType("Image");
                    }

                    List<IncomingCallAdData> outGoingCamp = response.body().getOutgoingAdvs();
                    for (int i=0;i<outGoingCamp.size();i++){
                        outGoingCamp.get(i).setCampId(outGoingCamp.get(i).getId());
                        outGoingCamp.get(i).setAdvSource(outGoingCamp.get(i).getAdvSource());
                    }

                    List<IncomingCallAdData> inProgressCamp = response.body().getInprogressAdvs();
                    for (int i=0;i<inProgressCamp.size();i++){
                        inProgressCamp.get(i).setCampId(inProgressCamp.get(i).getId());
                        inProgressCamp.get(i).setAdvSource(inProgressCamp.get(i).getAdvSource());
                    }

                    allCamp.addAll(incomingCamp);
                    allCamp.addAll(popUpCamp);
                    allCamp.addAll(outGoingCamp);
                    allCamp.addAll(inProgressCamp);

                    repository.insertAd(allCamp,campaignAdsPlayOrderList);
                    new ImageVideoDownloadManager(context);
                }
                if (response.code()==500){
                    Log.i("Error", "Response: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<AllCampaignModel> call, Throwable t) {
                Log.i("Failed", "Error: " + t.getMessage());
            }
        });


        List<CampaignLogs> campaignLogs = repository.getAllNonSyncLogs("Incoming");

        if (campaignLogs.size()>0){
            for (int i=0; i<campaignLogs.size();i++){
                int finalI = i;
                ApiClient.createService(ApiInterface.class).saveLogsOnServer(campaignLogs.get(i).getCampId(), RequestFormatter
                        .jsonObjectLogs(campaignLogs.get(i).getStartDateTime(),
                                campaignLogs.get(i).getEndDateTime(),
                                campaignLogs.get(i).getCallType(),
                                campaignLogs.get(i).getCallStatus())).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.code()==200){
                            Log.i("Success", "Response: " + response.code());
                            repository.updateLogsSyncCheck(campaignLogs.get(finalI).getId());
                        }
                        if (response.code()==500){
                            Log.i("Error", "Response: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.i("Failed", "Error: " + t.getMessage());
                    }
                });
            }
        }


        List<CampaignLogs> popUpCampaignLogs = repository.getAllNonSyncLogs("Popup");

        if (popUpCampaignLogs.size()>0){
            for (int i=0; i<popUpCampaignLogs.size();i++){
                int finalI = i;
                ApiClient.createService(ApiInterface.class).savePopUpLogsOnServer(popUpCampaignLogs.get(i).getCampId(), RequestFormatter
                        .jsonObjectLogs(popUpCampaignLogs.get(i).getStartDateTime(),
                                popUpCampaignLogs.get(i).getEndDateTime(),
                                popUpCampaignLogs.get(i).getCallType(),
                                popUpCampaignLogs.get(i).getCallStatus(),
                                popUpCampaignLogs.get(i).getAction(),
                                popUpCampaignLogs.get(i).getCallToAction(),
                                popUpCampaignLogs.get(i).getCallToActionTime())).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.code()==200){
                            Log.i("Success", "Response: " + response.code());
                            repository.updateLogsSyncCheck(popUpCampaignLogs.get(finalI).getId());
                        }
                        if (response.code()==500){
                            Log.i("Error", "Response: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.i("Failed", "Error: " + t.getMessage());
                    }
                });
            }
        }

        List<CampaignLogs> outgoingCampaignLogs = repository.getAllNonSyncLogs("Outgoing");

        if (outgoingCampaignLogs.size()>0){
            for (int i=0; i<outgoingCampaignLogs.size();i++){
                int finalI = i;
                ApiClient.createService(ApiInterface.class).saveOutgoingLogsOnServer(outgoingCampaignLogs.get(i).getCampId(), RequestFormatter
                        .jsonObjectOutgoingLogs(outgoingCampaignLogs.get(i).getStartDateTime(),
                                outgoingCampaignLogs.get(i).getEndDateTime(),
                                "Outing Call",
                                outgoingCampaignLogs.get(i).getCallStatus())).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.code()==200){
                            Log.i("Success", "Response: " + response.code());
                            repository.updateLogsSyncCheck(outgoingCampaignLogs.get(finalI).getId());
                        }
                        if (response.code()==500){
                            Log.i("Error", "Response: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.i("Failed", "Error: " + t.getMessage());
                    }
                });
            }
        }

        List<CampaignLogs> inProgressCampaignLogs = repository.getAllNonSyncLogs("InProgress");

        if (inProgressCampaignLogs.size()>0){
            for (int i=0; i<inProgressCampaignLogs.size();i++){
                int finalI = i;
                ApiClient.createService(ApiInterface.class).saveInProgressLogsOnServer(inProgressCampaignLogs.get(i).getCampId(), RequestFormatter
                        .jsonObjectInProgressLogs(inProgressCampaignLogs.get(i).getCallTime()
                                ,inProgressCampaignLogs.get(i).getStartDateTime(),
                                inProgressCampaignLogs.get(i).getEndDateTime(),
                                inProgressCampaignLogs.get(i).getAdType(),
                                inProgressCampaignLogs.get(i).getCallStatus())).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.code()==200){
                            Log.i("Success", "Response: " + response.code());
                            repository.updateLogsSyncCheck(inProgressCampaignLogs.get(finalI).getId());
                        }
                        if (response.code()==500){
                            Log.i("Error", "Response: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.i("Failed", "Error: " + t.getMessage());
                    }
                });
            }
        }
//        makeStatusNotification("Complete", context);
    }

    private void setActiveStatus() {
        int espTime = DateUtility.checkDateTimeDifference(repository.getLastSyncDateTime());
        if (espTime>0){
            String packageName = mContext.getPackageName();
            if (mContext.getSystemService(TelecomManager.class).getDefaultDialerPackage().equals(packageName)){
                repository.updateActiveStatus(espTime);
                int activeCount = repository.getCount();
                int dailyThreshHold = PreferenceUtils.getInstance().getInt(R.string.pref_daily_threshold_value);
                if (activeCount>=dailyThreshHold){
                    updateActiveCount(18);
                }
            }
        }
        if (DateUtility.checkDateDifference(repository.getLastSyncDate())){
            int espNightTime = DateUtility.checkDateTimeDifference(DateUtility.getCurrentMidNightDate());
            repository.updateActiveCountAndDate(espNightTime);
        }
    }

    private void makeStatusNotification(String message, Context context) {

        // Make a channel if necessary
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            CharSequence name = VERBOSE_NOTIFICATION_CHANNEL_NAME;
            String description = VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION;
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel =
                    new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Add the channel
            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        // Create the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_round)
                .setContentTitle(NOTIFICATION_TITLE)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setVibrate(new long[0])
                .setAutoCancel(true);

        // Show the notification
        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build());
    }

    private void updateActiveCount(int activeCount){
        ApiClient.createService(ApiInterface.class).saveActiveCount(activeCount).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code()==200){
                    Log.i("Success", "Response: " + response.code());
                }
                if (response.code()==500){
                    Log.i("Error", "Response: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("Failed", "Error: " + t.getMessage());
            }
        });
    }
}