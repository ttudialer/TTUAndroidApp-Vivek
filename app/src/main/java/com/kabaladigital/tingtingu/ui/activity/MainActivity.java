package com.kabaladigital.tingtingu.ui.activity;

import android.app.AlarmManager;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kabaladigital.tingtingu.BuildConfig;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.database.AppDatabase;
import com.kabaladigital.tingtingu.database.DataRepository;
import com.kabaladigital.tingtingu.databinding.ActivityMainBinding;
import com.kabaladigital.tingtingu.models.ProfileAdv;
import com.kabaladigital.tingtingu.models.ProfileResponse;
import com.kabaladigital.tingtingu.networking.ApiClient;
import com.kabaladigital.tingtingu.networking.ApiClient2;
import com.kabaladigital.tingtingu.networking.ApiInterface;
import com.kabaladigital.tingtingu.service.MyBroadCastReceiver;
import com.kabaladigital.tingtingu.service.SharesPreference;
import com.kabaladigital.tingtingu.util.CallManager;
import com.kabaladigital.tingtingu.util.DateUtility;
import com.kabaladigital.tingtingu.util.Installation;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.util.Utilities;

import org.json.JSONObject;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_CHANGELOG_DIALOG = "changelog";

    // Intent
    Intent mIntent;
    String mIntentAction;
    String mIntentType;

    ActivityMainBinding binding;
    DownloadManager downloadManager_2;
    ArrayList<Long> list = new ArrayList<>();
    private Uri Download_Uri;
    private long refid;
    Context ctx = MainActivity.this;

    public static final String MESSAGE_STATUS = "MainActivity";

    DataRepository repository;

    private DownloadManager downloadManager;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        downloadManager_2 = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        registerReceiver(onComplete,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        Installation.id(this);
        setSupportActionBar(binding.toolbarMainActivity);

        PreferenceUtils.getInstance(this,true); // Get the preferences
        Utilities.setUpLocale(this);

        repository = DataRepository
                .getInstance(AppDatabase.getDatabase(this));
        if (!DateUtility.getDateFormatted().equals(repository.getTodayDayFromPlayOrder())){
            repository.updateTodayDateCount();
        }

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Firebase_token", "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        String token = task.getResult();
                        JSONObject jsonObject=new JSONObject();
                        try {

                            jsonObject.put("token",token);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        JsonParser jsonParser = new JsonParser();
                        JsonObject gsonObject = (JsonObject)jsonParser.parse(jsonObject.toString());

                        updateToken(gsonObject);
                        Log.d("Firebase_token", token);
                    }
                });

        //downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);


        getprofile();

        //Notification
//        ShowNotificationAd.createNotification(this);
//        ShowNotificationAd.createImageWithCallNotification(this);
//        ShowNotificationAd.createImageWithoutCallNotification(this);
//        ShowNotificationAd.createImageWithURLNotification(this);
//        ShowNotificationAd.createVideoWithURLNotification(this);

        // Check if first instance
        boolean isFirstInstance = PreferenceUtils.getInstance().getBoolean(R.string.pref_is_first_instance_key);
        if (!isFirstInstance) checkVersion();


        // Check whether this app was set as the default dialer
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            //Do something for android Q  and above versions
            Utilities.checkDefaultDialer(this);
        }
        else{
            //do something below Q android 7,8,9
            Utilities.checkDefaultDialer_2(this);
        }



        // Check for intents from others apps
        checkIncomingIntent();

        // Create Network constraint
//        Constraints constraints = new Constraints.Builder()
//                .setRequiredNetworkType(NetworkType.CONNECTED)
//                .build();

//        PeriodicWorkRequest.Builder myWorkBuilder =
//                new PeriodicWorkRequest.Builder(GetAdData.class, 15, TimeUnit.MINUTES);
//
//        PeriodicWorkRequest myWork = myWorkBuilder.build();
//        WorkManager mWorkManager = WorkManager.getInstance(getApplicationContext());
//        mWorkManager.enqueueUniquePeriodicWork("Sync", ExistingPeriodicWorkPolicy.KEEP
//                ,myWork);

//        WorkManager manualWorkManager = WorkManager.getInstance(getApplicationContext());
//        manualWorkManager.enqueue(OneTimeWorkRequest.from(GetAdData.class));


//
//        PeriodicWorkRequest periodicSyncDataWork =
//                new PeriodicWorkRequest.Builder(GetAdData.class, 15, TimeUnit.MINUTES)
//                        .addTag("TAG_SYNC_DATA")
//                        .setConstraints(constraints)
//                        // setting a backoff on case the work needs to retry
//                        .setBackoffCriteria(BackoffPolicy.LINEAR, PeriodicWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
//                        .build();
//
//        WorkManager mWorkManager = WorkManager.getInstance(getApplicationContext());
//
//        mWorkManager.enqueueUniquePeriodicWork(
//                "SYNC_DATA_WORK_NAME",
//                ExistingPeriodicWorkPolicy.KEEP, //Existing Periodic Work policy
//                periodicSyncDataWork //work request
//        );

//        WorkManager mWorkManager = WorkManager.getInstance(getApplicationContext());
//
//        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(GetAdData.class
//                , 15, TimeUnit.MINUTES).build();
//
//        mWorkManager.getWorkInfoByIdLiveData(workRequest.getId()).observe(this, new Observer<WorkInfo>() {
//            @Override
//            public void onChanged(@Nullable WorkInfo workInfo) {
//                if (workInfo != null) {
//                    WorkInfo.State state = workInfo.getState();
//                    Log.i("SyncChnage", state.toString() + "\n");
//                }
//            }
//        });
//
//        mWorkManager.enqueue(workRequest);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, MyBroadCastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP
                , SystemClock.elapsedRealtime()
                , 15*60*1000
                , pendingIntent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void download() {
        //ArrayList<String>phone_no_arr = new ArrayList<>();

        for(int i = 0; i<SharesPreference.getprofile(getApplicationContext()).getProfileAdvs().size();i++)
        {
            String phone_no = SharesPreference.getprofile(getApplicationContext()).getProfileAdvs().get(0).getMobileNumber();
            String url = SharesPreference.getprofile(getApplicationContext()).getProfileAdvs().get(0).getFileUrl();
            //String path = String.valueOf(Environment.DIRECTORY_DOWNLOADS +  "/TTUPROFILE/"  + "/" + phone_no + ".mp4");
            //String path = String.valueOf(ctx.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS +  "/TTUPROFILE"  + "/" + phone_no + ".mp4"));
            //Log.d("path",path);

           /* Download_Uri = Uri.parse(url);
            DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
            request.setAllowedOverRoaming(false);
            request.setTitle("TTUPROFILE" + phone_no + ".mp4");
            request.setDescription("TTUPROFILE" + phone_no + ".mp4");
            request.setVisibleInDownloadsUi(true);
            request.setDestinationInExternalPublicDir(Environment.getExternalStorageDirectory().getAbsolutePath(), "/TTUPROFILE/"   + phone_no + ".mp4");
            refid = downloadManager_2.enqueue(request);
            Log.e("OUTNM", "" + refid);
            list.add(refid);*/




        }


    }








    public void getprofile()
    {
        Call<ProfileResponse> call = ApiClient2.getProfile_new().getProfile_new();
        call.enqueue(new Callback<ProfileResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if(response.isSuccessful())
                {
                    if (response.body()!= null)
                    {
                        SharesPreference.saveprofile(getApplicationContext(),response.body());
                        download();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

            }
        });






    }








    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * Check for the app version
     */
    private void checkVersion() {
//        int lastVersionCode = PreferenceUtils.getInstance().getInt(R.string.pref_last_version_key);
//        if (lastVersionCode < BuildConfig.VERSION_CODE) {
//            PreferenceUtils.getInstance().putInt(R.string.pref_last_version_key, BuildConfig.VERSION_CODE);

            //Hide Change Log Dialog
//            new ChangelogDialog().show(getSupportFragmentManager(), TAG_CHANGELOG_DIALOG);
//        }
    }

    // -- Utilities -- //
    private void checkPermissions(@Nullable int[] grantResults) {
        if ((grantResults != null && Utilities.checkPermissionsGranted(grantResults)) ||
                        Utilities.checkPermissionsGranted(this, Utilities.MUST_HAVE_PERMISSIONS)) { //If granted
            checkVersion();
        } else {

            Utilities.askForPermissions(MainActivity.this, Utilities.MUST_HAVE_PERMISSIONS);

            /*final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    Utilities.askForPermissions(MainActivity.this, Utilities.MUST_HAVE_PERMISSIONS);
                }
            }, 5000);*/

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Utilities.DEFAULT_DIALER_RC) {
            checkPermissions(null);
        }
    }

    /**
     * Checking for incoming intents from other applications
     */
    private void checkIncomingIntent() {
        mIntent = getIntent();
        mIntentAction = mIntent.getAction();
        mIntentType = mIntent.getType();

        if (Intent.ACTION_VIEW.equals(mIntentAction)) {
            handleViewIntent(mIntent);
        }
    }

    /**
     * Handle a VIEW intent (For example when you click a number in whatsapp)
     *
     * @param intent
     */
    private void handleViewIntent(Intent intent) {
        String sharedText = intent.getData().toString();
        String number = "";
        if (sharedText.contains("tel:")) {
            number = sharedText.substring(4, sharedText.length() - 1);

            //TODO Check Intent Working
            if (number != null) {
//                mSharedDialViewModel.setNumber(number);
//                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        }
    }

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }


    private void updateToken(JsonObject token)
    {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("token", token.toString())
                .build();

//        jsonObject.toString().toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        ApiClient.createService(ApiInterface.class).updateTokenAccess(token).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code()==200){
                    Log.i("Success", "Response: " + response.code());
                }
                if (response.code()==500){
                    Log.i("Error", "Response: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.i("Failed", "Error: " + t.getMessage());
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(CallManager.getState() == 4)
        {
            startActivity(new Intent(this, OngoingCallActivity.class));
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        if(CallManager.getState() == 4)
        {
            startActivity(new Intent(this, OngoingCallActivity.class));
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(CallManager.getState() == 4)
        {
            startActivity(new Intent(this, OngoingCallActivity.class));
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(CallManager.getState() == 4)
        {
            startActivity(new Intent(this, OngoingCallActivity.class));
        }
        unregisterReceiver(onComplete);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(CallManager.getState() == 4)
        {
            startActivity(new Intent(this, OngoingCallActivity.class));
        }

    }


    BroadcastReceiver onComplete = new BroadcastReceiver()
    {

        public void onReceive(Context ctxt, Intent intent) {
            long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            Log.e("IN", "" + referenceId);
            list.remove(referenceId);
            if (list.isEmpty())
            {
                Log.e("INSIDE", "" + referenceId);
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(MainActivity.this)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("TTU")
                                .setContentText("All Download completed");
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(455, mBuilder.build());
            }
        }
    };


}
