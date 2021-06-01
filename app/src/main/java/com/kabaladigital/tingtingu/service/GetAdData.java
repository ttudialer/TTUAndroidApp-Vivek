package com.kabaladigital.tingtingu.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.preference.PreferenceManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.models.ProfileResponse;
import com.kabaladigital.tingtingu.networking.ApiClient2;
import com.kabaladigital.tingtingu.ui.activity.MainActivity;


import com.kabaladigital.tingtingu.Class.Global;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.models.ProfileResponse;
import com.kabaladigital.tingtingu.networking.ApiClient2;
import com.kabaladigital.tingtingu.networking.ImageVideoDownload;
import com.kabaladigital.tingtingu.ui.activity.MainActivity;

import java.io.File;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAdData extends Worker {

    Context context;
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


    // Other keys
    public static final long DELAY_TIME_MILLIS = 3000;

    public static final String TAG_SYNC_DATA = "TAG_SYNC_DATA";

    public GetAdData(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d("call----->", "method");
        //getprofile();

        Call<ProfileResponse> call = ApiClient2.getProfile_new().getProfile_new();
        call.enqueue(new Callback<ProfileResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        SharesPreference.saveprofile(getApplicationContext(), response.body());

                        SharesPreference.saveprofile(context, response.body());

                        //download_Profile2();

                        Log.d("download", "profile2");
                        //removeArrayList("phone_no");

                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.remove("phone_no");
                        editor.apply();


                        ArrayList<String> list = new ArrayList<>();
                        for (int i = 0; i < SharesPreference.getprofile(getApplicationContext()).getProfileAdvs().size(); i++) {
                            String phone_no = SharesPreference.getprofile(getApplicationContext()).getProfileAdvs().get(i).getMobileNumber();
                            String file_type = SharesPreference.getprofile(getApplicationContext()).getProfileAdvs().get(i).getFileType();
                            String url = SharesPreference.getprofile(getApplicationContext()).getProfileAdvs().get(i).getFileUrl();

                            Toast.makeText(context, "" + SharesPreference.getprofile(context).getProfileAdvs().size(), Toast.LENGTH_SHORT).show();



                                Toast.makeText(context, "M : " + phone_no, Toast.LENGTH_SHORT).show();


                                if (file_type == null) {
                                    file_type = "Image";
                                }

                                Log.d("mobile", phone_no + "@" + file_type + "@" + url);
                                list.add(phone_no + "@" + file_type + "@" + url);

                                //saveArrayList(list, "phone_no");

                                String _savepath = "";
                                if (file_type.equalsIgnoreCase("video")) {
                                    _savepath = Global.TTULibraryTTUPROFILE_path(context) + File.separator + phone_no + ".mp4";
                                } else if (file_type.equalsIgnoreCase("image")) {
                                    _savepath = Global.TTULibraryTTUPROFILE_path(context) + File.separator + phone_no + ".jpg";
                                }

                                File futureStudioIconFile = null;
                                futureStudioIconFile = new File(_savepath);
                                if (futureStudioIconFile.exists()) {
                                    futureStudioIconFile.delete();
                                }

                                new ImageVideoDownload(context, url, _savepath, file_type);

                                Log.d("mobile", phone_no + "@" + file_type + "@" + _savepath);
                                list.add(phone_no + "@" + file_type + "@" + _savepath);


                                SharedPreferences prefs_n = PreferenceManager.getDefaultSharedPreferences(context);
                                SharedPreferences.Editor editor_n = prefs_n.edit();
                                Gson gson = new Gson();
                                String json = gson.toJson(list);
                                editor_n.putString("phone_no", json);
                                editor_n.apply();     // This line is IMPORTANT !!!

                            }


                        }

                    } else {
                        //Toast.makeText(MainActivity.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                    }


                }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

            }
        });





//        DataRepository repository = DataRepository
//                .getInstance(AppDatabase.getDatabase(context));
//        PreferenceUtils.getInstance(context,true);
//
////        String auth = PreferenceUtils.getInstance().getString(R.string.pref_user_token_value);
//
//        ApiClient.createService(ApiInterface.class).getAdData().enqueue(new Callback<List<IncomingCallAdData>>() {
//            @Override
//            public void onResponse(Call<List<IncomingCallAdData>> call, Response<List<IncomingCallAdData>> response) {
//                if (response.code()==200){
//                    if (response.body().size()>0){
//                        List<CampaignAdsPlayOrder> campaignAdsPlayOrderList = new ArrayList<>();
//                        for (int i=0; i<response.body().size();i++){
//
//                            CampaignAdsPlayOrder campaignAdsPlayOrder = new CampaignAdsPlayOrder();
//                            campaignAdsPlayOrder.setCampId(response.body().get(i).getCampId());
//                            campaignAdsPlayOrder.setStatus(response.body().get(i).getStatus());
//                            campaignAdsPlayOrder.setStartDate(DateUtility.convertDbDate(response.body().get(i).getStartDate()));
//                            campaignAdsPlayOrder.setEndDate(DateUtility.convertDbDate(response.body().get(i).getEndDate()));
//                            campaignAdsPlayOrder.setMaxDurationCount(response.body().get(i).getMaxplayDuration());
//                            campaignAdsPlayOrder.setPlayCount(0);
//                            campaignAdsPlayOrder.setCurrentDurationCount(0);
//                            if (response.body().get(i).getMaxplayDuration()==null || response.body().get(i).getMaxplayDuration()==0){
//                                campaignAdsPlayOrder.setMaxDurationCount(1440);
//                            }
//                            campaignAdsPlayOrderList.add(campaignAdsPlayOrder);
//                        }
//                        repository.insertAd(response.body(),campaignAdsPlayOrderList);
////                        new ImageVideoDownloadManager(context);
//                    }
//                }
//                if (response.code()==500){
//                    Log.i("Error", "Response: " + response.code());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<IncomingCallAdData>> call, Throwable t) {
//                Log.i("Failed", "Error: " + t.getMessage());
//            }
//        });
//
//
//        List<CampaignLogs> campaignLogs = repository.getAllNonSyncLogs();
//
//        if (campaignLogs.size()>0){
//            for (int i=0; i<campaignLogs.size();i++){
//                int finalI = i;
//                ApiClient.createService(ApiInterface.class).saveLogsOnServer(campaignLogs.get(i).getCampId(),RequestFormatter
//                        .jsonObjectLogs(campaignLogs.get(i).getStartDateTime(),
//                                campaignLogs.get(i).getEndDateTime(),
//                                "Incoming Call",
//                                campaignLogs.get(i).getCallStatus())).enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        if (response.code()==200){
//                            Log.i("Success", "Response: " + response.code());
//                            repository.updateLogsSyncCheck(campaignLogs.get(finalI).getId());
//                        }
//                        if (response.code()==500){
//                            Log.i("Error", "Response: " + response.code());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        Log.i("Failed", "Error: " + t.getMessage());
//                    }
//                });
//            }
//        }
//        makeStatusNotification("Complete", context);

        return Result.success();
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

    @Override
    public void onStopped() {
        super.onStopped();
        Log.i(TAG, "OnStopped called for this worker");
    }


}