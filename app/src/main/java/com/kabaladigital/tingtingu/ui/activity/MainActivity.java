package com.kabaladigital.tingtingu.ui.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.app.PendingIntent;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;

import android.os.Looper;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.preference.PreferenceManager;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kabaladigital.tingtingu.database.AppDatabase;
import com.kabaladigital.tingtingu.models.ProfileResponse;
import com.kabaladigital.tingtingu.networking.ApiClient;
import com.kabaladigital.tingtingu.networking.ApiClient2;
import com.kabaladigital.tingtingu.networking.ApiInterface;
import com.kabaladigital.tingtingu.networking.ImageVideoDownload;
import com.kabaladigital.tingtingu.service.ContactUpload;
import com.kabaladigital.tingtingu.service.GetAdData;
import com.kabaladigital.tingtingu.service.SharesPreference;
import com.kabaladigital.tingtingu.service.UploadContactService;
import com.kabaladigital.tingtingu.util.CallManager;
import com.kabaladigital.tingtingu.util.PreferenceUtils;



import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kabaladigital.tingtingu.Class.Global;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.database.DataRepository;
import com.kabaladigital.tingtingu.databinding.ActivityMainBinding;

import com.kabaladigital.tingtingu.models.ContactUploadModel;



import com.kabaladigital.tingtingu.service.MyBroadCastReceiver;
import com.kabaladigital.tingtingu.util.DateUtility;
import com.kabaladigital.tingtingu.util.Installation;
import com.kabaladigital.tingtingu.util.Utilities;


import org.json.JSONArray;
import org.json.JSONObject;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.io.File;
import java.util.ArrayList;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.firebase.crashlytics.internal.Logger.TAG;

public class MainActivity extends AppCompatActivity {
    // Integer _abc=0; // created by deepak
    private static final String TAG_CHANGELOG_DIALOG = "changelog";
    String jsonStr;
    JsonArray jsonArrayContact = new JsonArray();
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
    public static Activity activity_n;

    public static final String MESSAGE_STATUS = "MainActivity";
    DataRepository repository;

    public Context _mainContaxt;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        downloadManager_2 = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        registerReceiver(onComplete,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        Installation.id(this);
        setSupportActionBar(binding.toolbarMainActivity);
        Global.TTULibraryImage(this);
        Global.TTULibraryImageDraft(this);
        Global.TTULibraryVideo(this);
        Global.TTULibraryProfile(this);
        Global.TTULibraryTTUPROFILE(this);
        Global.TTUFOLDER(this);
        Global.TTUFOLDERDraft(this);


        _mainContaxt=this;

        PreferenceUtils.getInstance(this, true); // Get the preferences
        Utilities.setUpLocale(this);

        repository = DataRepository
                .getInstance(AppDatabase.getDatabase(this));
        if (!DateUtility.getDateFormatted().equals(repository.getTodayDayFromPlayOrder())) {
            repository.updateTodayDateCount();
        }

        //call download manager and load video and image
        downloadManager_2 = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Firebase_token", "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        String token = task.getResult();
                        JSONObject jsonObject = new JSONObject();
                        try {

                            jsonObject.put("token", token);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        JsonParser jsonParser = new JsonParser();
                        JsonObject gsonObject = (JsonObject) jsonParser.parse(jsonObject.toString());

                        updateToken(gsonObject);
                        Log.d("Firebase_token", token);
                    }
                });


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
        } else {
            //do something below Q android 7,8,9
            Utilities.checkDefaultDialer_2(this);
        }

//        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//        Toast.makeText(getApplicationContext(), timeStamp, Toast.LENGTH_SHORT).show();
//        String date1 = "20170717141000";
//        String date2 = "20170719175500";

        // Check for intents from others apps
        checkIncomingIntent();

        //Block 1 of work manager

        WorkManager manualWorkManager = WorkManager.getInstance(getApplicationContext());
        PeriodicWorkRequest.Builder myWorkBuilder = new PeriodicWorkRequest.Builder(GetAdData.class, 1, TimeUnit.MINUTES);
        PeriodicWorkRequest myWork = myWorkBuilder.build();
        manualWorkManager.getInstance(this).enqueue(myWork);

        WorkManager mWorkManager = WorkManager.getInstance(ctx);
        manualWorkManager.enqueueUniquePeriodicWork("Sync", ExistingPeriodicWorkPolicy.KEEP,myWork);
        manualWorkManager.enqueue(OneTimeWorkRequest.from(GetAdData.class));

        WorkManager manualWorkManager_contact = WorkManager.getInstance(getApplicationContext());
        PeriodicWorkRequest.Builder myWorkBuilder_contact = new PeriodicWorkRequest.Builder(UploadContactService.class, 1, TimeUnit.MINUTES);
        PeriodicWorkRequest myWork_contact = myWorkBuilder_contact.build();
        manualWorkManager_contact.getInstance(this).enqueue(myWork_contact);

        manualWorkManager_contact.enqueueUniquePeriodicWork("Sync", ExistingPeriodicWorkPolicy.KEEP,myWork_contact);
        manualWorkManager_contact.enqueue(OneTimeWorkRequest.from(UploadContactService.class));

        //Block 2 of work manager
//     WorkManager mWorkManager = WorkManager.getInstance(getApplicationContext());
//        PeriodicWorkRequest periodicSyncDataWork =
//                new PeriodicWorkRequest.Builder(GetAdData.class, 1, TimeUnit.MINUTES)
//                        .addTag("TAG_SYNC_DATA")
//                        .setConstraints(constraints)
//                        // setting a backoff on case the work needs to retry
//                        .setBackoffCriteria(BackoffPolicy.LINEAR, PeriodicWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
//                        .build();
//        mWorkManager.enqueueUniquePeriodicWork(
//                "SYNC_DATA_WORK_NAME",
//                ExistingPeriodicWorkPolicy.KEEP, //Existing Periodic Work policy
//                periodicSyncDataWork //work request
//        );

//        // //Block 3 of work manager
//        WorkManager mWorkManager = WorkManager.getInstance(getApplicationContext());
//        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(GetAdData.class , 10, TimeUnit.SECONDS).build();
//        mWorkManager.getWorkInfoByIdLiveData(workRequest.getId()).observe(this, new Observer<WorkInfo>() {
//            @Override
//            public void onChanged(@Nullable WorkInfo workInfo) {
//                if (workInfo != null) {
//                    WorkInfo.State state = workInfo.getState();
//                    Log.i("SyncChnage", state.toString() + "\n");
//                }
//            }
//        });
//        mWorkManager.enqueue(workRequest);


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
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP
                , SystemClock.elapsedRealtime()
                , 15 * 60 * 1000
                , pendingIntent);



//        final Handler handler = new Handler(Looper.getMainLooper());
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                String _ctime = new SimpleDateFormat("yyyyMMdd").format(new Date());
//                String _DBtime = PreferenceUtils.getInstance().getString(R.string.pref_c_upload_date);
//                if (_ctime.equalsIgnoreCase(_DBtime) == false) {
//                    new Thread(new Runnable() {
//                        public void run() {
//                            ContactUpload _cu=new ContactUpload(_mainContaxt);
//                            _cu.ContactUpload(_mainContaxt,"main_C");
//                        }
//                    }).start();
//                }
//            }
//        }, 5000);
    }


    public static boolean delete(final Context context, final File file) {
        final String where = MediaStore.MediaColumns.DATA + "=?";
        final String[] selectionArgs = new String[]{
                file.getAbsolutePath()
        };
        final ContentResolver contentResolver = context.getContentResolver();
        final Uri filesUri = MediaStore.Files.getContentUri("external");

        contentResolver.delete(filesUri, where, selectionArgs);

        if (file.exists()) {

            contentResolver.delete(filesUri, where, selectionArgs);
        }
        return !file.exists();
    }

    private boolean writeResponseBodyToDisk(ResponseBody body, String _fType, String _phone_no) {
        try {
            // todo change the file location/name according to your needs

            File futureStudioIconFile = null;
            if (_fType.equalsIgnoreCase("video")) {
                futureStudioIconFile = new File(Global.TTULibraryTTUPROFILE_path(getApplicationContext()) + File.separator + _phone_no + ".mp4");
            } else if (_fType.equalsIgnoreCase("image")) {
                futureStudioIconFile = new File(Global.TTULibraryTTUPROFILE_path(getApplicationContext()) + File.separator + _phone_no + ".jpg");
            }

            if (futureStudioIconFile.exists()) {
                futureStudioIconFile.delete();
            }
            Log.d("delete:", futureStudioIconFile.getAbsolutePath());


            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];
                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);
                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                    Log.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
                }
                outputStream.flush();
                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }



    public void saveArrayList(ArrayList<String> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }
    public void removeArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key);
        editor.apply();
    }

    public ArrayList<String> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        Toast.makeText(MainActivity.this, ""+key, Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, ""+json, Toast.LENGTH_SHORT).show();
        return gson.fromJson(json, type);
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


    private void updateToken(JsonObject token)    {
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
