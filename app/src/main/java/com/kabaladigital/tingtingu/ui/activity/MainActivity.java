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
import androidx.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kabaladigital.tingtingu.database.AppDatabase;
import com.kabaladigital.tingtingu.models.ProfileResponse;
import com.kabaladigital.tingtingu.networking.ApiClient;
import com.kabaladigital.tingtingu.networking.ApiClient2;
import com.kabaladigital.tingtingu.networking.ApiInterface;
import com.kabaladigital.tingtingu.service.SharesPreference;
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


import org.json.JSONObject;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.File;
import java.util.ArrayList;

import java.util.Random;

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
    Activity activity_n = MainActivity.this;



    public static final String MESSAGE_STATUS = "MainActivity";
    DataRepository repository;



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
        Global.TTULibraryImage(this) ;
        Global.TTULibraryImageDraft(this) ;
        Global.TTULibraryVideo(this) ;
        Global.TTULibraryProfile(this) ;
        Global.TTULibraryTTUPROFILE(this) ;

//        File newfile=null;
//        String path_img = Environment.DIRECTORY_DOWNLOADS + Global.TTULibraryTTUPROFILE_path(getApplicationContext()) + File.separator +"9350043415.mp4";
//       // newfile = new File("/Download/storage/emulated/0/Android/data/com.kabaladigital.tingtingu/files/Pictures/TTULibrary/TTUPROFILE/9350043415.mp4");
//        newfile = new File(path_img);
//        if(newfile.exists()){
//            newfile.delete();
//        }

//        String path_img = Environment.DIRECTORY_DOWNLOADS + Global.TTULibraryTTUPROFILE_path(getApplicationContext()) + File.separator + phone_no + ".jpg";



        PreferenceUtils.getInstance(this,true); // Get the preferences
        Utilities.setUpLocale(this);

        repository = DataRepository
                .getInstance(AppDatabase.getDatabase(this));
        if (!DateUtility.getDateFormatted().equals(repository.getTodayDayFromPlayOrder())){
            repository.updateTodayDateCount();
        }


        //call download manager and load video and image
        downloadManager_2 = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        getprofile();


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



        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                String _ctime = new SimpleDateFormat("yyyyMMdd").format(new Date());
                String _DBtime=   PreferenceUtils.getInstance().getString(R.string.pref_c_upload_date);
                if(_ctime.equalsIgnoreCase(_DBtime)==false) {
                    new Thread(new Runnable() {
                        public void run() {
                            ReadContactDetailsJson();
                        }
                    }).start();
                }
            }
        }, 5000);
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


    public static boolean delete(final Context context, final File file) {
        final String where = MediaStore.MediaColumns.DATA + "=?";
        final String[] selectionArgs = new String[] {
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

    private void download_Profile1() {
        removeArrayList("phone_no");
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < SharesPreference.getprofile(getApplicationContext()).getProfileAdvs().size(); i++) {
            String phone_no = SharesPreference.getprofile(getApplicationContext()).getProfileAdvs().get(i).getMobileNumber();
            String file_type = SharesPreference.getprofile(getApplicationContext()).getProfileAdvs().get(i).getFileType();

            //download file
            list.add(phone_no + "@" + file_type);
            saveArrayList(list, "phone_no");


            String url = SharesPreference.getprofile(getApplicationContext()).getProfileAdvs().get(i).getFileUrl();
            if (file_type != null) {
                ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
                Call<ResponseBody> call = apiInterface.downloadFileWithDynamicUrlSync(url);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d(TAG, " server :" + response.code());
                        Log.d(TAG, " server :" + url);

                        if (response.code() == 200) {
                            Log.d(TAG, "server contacted and has file");
                            boolean writtenToDisk = writeResponseBodyToDisk(response.body(), file_type, phone_no);
                            Log.d(TAG, "file download was a success? " + writtenToDisk);
                        } else {
                            Log.d(TAG, response.code() + " : server contact failed");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e(TAG, "error");
                    }
                });
            }
        }
    }
    private boolean writeResponseBodyToDisk(ResponseBody body,String _fType,String _phone_no) {
        try {
            // todo change the file location/name according to your needs

            File futureStudioIconFile =null;
            if (_fType.equalsIgnoreCase("video")) {
                futureStudioIconFile=  new File( Global.TTULibraryTTUPROFILE_path(getApplicationContext()) + File.separator + _phone_no + ".mp4");
            } else if (_fType.equalsIgnoreCase("image")) {
                futureStudioIconFile=  new File( Global.TTULibraryTTUPROFILE_path(getApplicationContext()) + File.separator + _phone_no + ".jpg");
            }

            if(futureStudioIconFile.exists()){
                futureStudioIconFile.delete();
            }
            Log.d("delete:",futureStudioIconFile.getAbsolutePath());




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

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void download_Profile() {
        removeArrayList("phone_no");

        //ArrayList<String>phone_no_arr = new ArrayList<>();
        ArrayList<String> list= new ArrayList<>();
        for(int i = 0; i<SharesPreference.getprofile(getApplicationContext()).getProfileAdvs().size();i++)
        {
            String phone_no = SharesPreference.getprofile(getApplicationContext()).getProfileAdvs().get(i).getMobileNumber();
            String file_type = SharesPreference.getprofile(getApplicationContext()).getProfileAdvs().get(i).getFileType();

//            String path_img = Environment.DIRECTORY_DOWNLOADS + Global.TTULibraryTTUPROFILE_path(getApplicationContext()) + File.separator + phone_no + ".jpg";
//            String path_video = Environment.DIRECTORY_DOWNLOADS + Global.TTULibraryTTUPROFILE_path(getApplicationContext()) + File.separator + phone_no + ".mp4";

            String path_img =  Global.TTULibraryTTUPROFILE_path(getApplicationContext()) + File.separator + phone_no + ".jpg";
            String path_video =  Global.TTULibraryTTUPROFILE_path(getApplicationContext()) + File.separator + phone_no + ".mp4";


//            File newfile=null;
//            newfile = new File("Download/storage/emulated/0/Android/data/com.kabaladigital.tingtingu/files/Pictures/TTULibrary/TTUPROFILE/9350043415.mp4");
//
//
//
//            if(newfile.exists()){
//                newfile.delete();
//            }
//            Log.d("delete:",path_video);

//            Toast.makeText(MainActivity.this, ""+path_img, Toast.LENGTH_SHORT).show();

//            if( getArrayList("phone_no") != null &&  getArrayList("phone_no").contains(phone_no+"@"+file_type))
//            {
//                //file already downloaded
//            }
//            else
                {
                //download file
                list.add(phone_no + "@" + file_type);
                saveArrayList(list, "phone_no");


                String url = SharesPreference.getprofile(getApplicationContext()).getProfileAdvs().get(i).getFileUrl();
                Download_Uri = Uri.parse(url);
                DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                request.setAllowedOverRoaming(false);

                  //  Toast.makeText(MainActivity.this, ""+url, Toast.LENGTH_SHORT).show();

                    //Toast.makeText(MainActivity.this,"file_type : " + (Global.TTULibraryTTUPROFILE(getApplicationContext()).toString() + File.separator + phone_no + ".mp4"),Toast.LENGTH_SHORT).show();

                /*File vidFile = new  File(Environment.DIRECTORY_DOWNLOADS + "/TTUPROFILE/" + phone_no +  ".mp4");
                Log.d("path",Environment.DIRECTORY_DOWNLOADS + "/TTUPROFILE/" + phone_no +  ".mp4");
                if(vidFile.exists())
                {
                    //Toast.makeText(ctx,"exist",Toast.LENGTH_SHORT).show();
                }
                else{
                    //Toast.makeText(ctx,"not exist",Toast.LENGTH_SHORT).show();
                }*/

                if (file_type != null) {
                    if (file_type.equalsIgnoreCase("video")) {
                        //video type file
                        request.setTitle("TTUPROFILE" + phone_no + ".mp4");
                        request.setDescription("TTUPROFILE" + phone_no + ".mp4");
                        //request.setDestinationInExternalPublicDir(Environment.getExternalStorageDirectory().getAbsolutePath(), "/TTUPROFILE/" + phone_no + ".mp4");
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, Global.TTULibraryTTUPROFILE_path(getApplicationContext()) + File.separator + phone_no + ".mp4");
                        Log.d("path_video", Environment.DIRECTORY_DOWNLOADS + Global.TTULibraryTTUPROFILE_path(getApplicationContext()) + File.separator + phone_no + ".mp4");
                    } else if (file_type.equalsIgnoreCase("image")) {
                        //image type file
                        request.setTitle("TTUPROFILE" + phone_no + ".jpg");
                        request.setDescription("TTUPROFILE" + phone_no + ".jpg");
                        //request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/TTUPROFILE/"   + phone_no + ".jpg");
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, Global.TTULibraryTTUPROFILE_path(getApplicationContext()) + File.separator + phone_no + ".jpg");
                        Log.d("path_img", Environment.DIRECTORY_DOWNLOADS + Global.TTULibraryTTUPROFILE_path(getApplicationContext()) + File.separator + phone_no + ".jpg");

                    }
                    request.setVisibleInDownloadsUi(true);
                    refid = downloadManager_2.enqueue(request);
                    //list.add(refid);
                }
            }
            //String phone_no = "9461867672";
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
                        download_Profile1();

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

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
