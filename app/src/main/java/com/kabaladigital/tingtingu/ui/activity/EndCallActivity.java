package com.kabaladigital.tingtingu.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.bumptech.glide.Glide;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.crashlytics.internal.common.CrashlyticsCore;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.database.AppDatabase;
import com.kabaladigital.tingtingu.database.DataRepository;
import com.kabaladigital.tingtingu.database.entity.CampaignAdsPlayOrder;
import com.kabaladigital.tingtingu.database.entity.CampaignLogs;
import com.kabaladigital.tingtingu.database.entity.Contact;
import com.kabaladigital.tingtingu.database.entity.RecentCall;
import com.kabaladigital.tingtingu.databinding.ActivityEndCallBinding;
import com.kabaladigital.tingtingu.google.RecentsCursorLoader;
import com.kabaladigital.tingtingu.models.Caller_Id;
import com.kabaladigital.tingtingu.models.IncomingCallAdData;
import com.kabaladigital.tingtingu.models.MobileDetailModel;
import com.kabaladigital.tingtingu.networking.ApiClient;
import com.kabaladigital.tingtingu.networking.ApiInterface;
import com.kabaladigital.tingtingu.util.CallManager;
import com.kabaladigital.tingtingu.util.ContactUtils;
import com.kabaladigital.tingtingu.util.DateUtility;
import com.kabaladigital.tingtingu.util.Utilities;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class EndCallActivity extends AppCompatActivity
//        implements LoaderManager.LoaderCallbacks<Cursor>
{
    private ActivityEndCallBinding binding;


//    private static final int LOADER_ID = 1;
//    private static final String ARG_PHONE_NUMBER = "phone_number";
//    private static final String ARG_CONTACT_NAME = "contact_name";

    private String phoneNumber = "";
    private DataRepository mRepository;
    private String StartTime,EndTime;


    private String callType = "";
    private String callResponse = "";
    private String closeType = "Manual";
    private String callToActionTime = "";
    private int callToAction = 0;

    private String date = "";


    private boolean isRejectCall = false;
    private String b2bMobileNumber = "";
    private boolean isB2BCallerId = false;

    IncomingCallAdData popUpCallAdData;
    private SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_end_call);
        binding.ivFullScreenEndCallAd.setDrawingCacheEnabled(true);
        binding.ivFullScreenEndCallAd.buildDrawingCache();

        Intent intent = getIntent();
        if (intent!=null){
            isRejectCall = intent.getBooleanExtra("isRejectCall",false);
            isB2BCallerId = intent.getBooleanExtra("isB2BCallerId",false);
            b2bMobileNumber = intent.getStringExtra("b2bMobileNumber");
        }

        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault());
        mRepository = DataRepository
                .getInstance(AppDatabase.getDatabase(this));

        if (!isB2BCallerId){
            setEndCallImage();
        }


//        LoaderManager.getInstance(this).restartLoader(LOADER_ID, null, this);
//        tryRunningLoader();


        binding.layoutDetails.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phoneNumber.length()>2){
                    CallManager.call(EndCallActivity.this, phoneNumber);
                    finish();
                }
            }
        });

        binding.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareImage();
            }
        });

        binding.ivFullScreenEndCallAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popUpCallAdData!=null && !popUpCallAdData.getCallToAction().equals("none"))
                {
                    callToAction = 1;
                    closeType = "Auto";
                    callToActionTime = sdf.format(new Date());;
                    String url = popUpCallAdData.getCallToAction();
                    url = "http://"+url;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            }
        });

        binding.btnCloseAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeType = "Manual";
                EndTime = sdf.format(new Date());
//                createLogs();
                //throw new RuntimeException("Test Crash"); // Force a crash
                finishAndRemoveTask();
            }
        });
    }

    private void shareImage() {
//        if (popUpCallAdData!= null && popUpCallAdData.getUri()!= null){
//            Uri uri = Uri.parse(popUpCallAdData.getUri());
//
//            Intent intent = new Intent(Intent.ACTION_SEND);
//            intent.setType("image/jpeg");
//            intent.putExtra(Intent.EXTRA_STREAM, uri);
//            startActivity(Intent.createChooser(intent, "Share Image"));
//        }else{
            Bitmap b = binding.ivFullScreenEndCallAd.getDrawingCache();
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("image/jpeg");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(getContentResolver(), b, "Title", null);
            Uri imageUri =  Uri.parse(path);
            share.putExtra(Intent.EXTRA_STREAM, imageUri);
            startActivity(Intent.createChooser(share, "Share Image"));
//        }
    }

//    private void tryRunningLoader() {
//        if (!isLoaderRunning() && Utilities.checkPermissionGranted(this, Manifest.permission.READ_CALL_LOG)) {
//            runLoader();
//        }
//    }
//
//    private void runLoader() {
//        LoaderManager.getInstance(this).initLoader(LOADER_ID, null, this);
//    }
//
//    private boolean isLoaderRunning() {
//        Loader loader = LoaderManager.getInstance(this).getLoader(LOADER_ID);
//        return loader != null;
//    }

    private void setEndCallImage() {

        CampaignAdsPlayOrder campaignAdsPlayOrderList;
        // Get Ad Camp
        if (isRejectCall){
            campaignAdsPlayOrderList = mRepository
                    .getB2BPopUpOrderByCount(DateUtility.getCurrentDateInLong(),"Popup Reject", "4", Long.parseLong(b2bMobileNumber));
        }else {
            campaignAdsPlayOrderList = mRepository
                    .getAllCampaignAdsOrderByCount(DateUtility.getCurrentDateInLong(),"Popup", "4");
        }


        if (campaignAdsPlayOrderList!=null){
            popUpCallAdData = mRepository.getAdByCampId(campaignAdsPlayOrderList.getCampId());

            if (popUpCallAdData.getAdType().equals("Image")) {
                String format = popUpCallAdData.getUploadFile().substring(popUpCallAdData.getUploadFile().length() - 3);
                if (format.equals("gif")){
                    Glide.with(this).asGif().load(popUpCallAdData.getUploadFile())
                            .into(binding.ivFullScreenEndCallAd);
                }else {
                    try {
                        binding.ivFullScreenEndCallAd.setImageURI(Uri.parse(popUpCallAdData.getUri()));
                    }catch (Exception e){
                        try {
                            Glide.with(this).load(popUpCallAdData.getUri()).into(binding.ivFullScreenEndCallAd);
                        }catch (Exception ex){
                            Glide.with(this).asGif().load(R.drawable.default_popup).into(binding.ivFullScreenEndCallAd);
                        }
                    }

                }
            }

            mRepository.updatePlayCount(popUpCallAdData.getCampId());
        } else {
            Glide.with(this).asGif().load(R.drawable.default_popup).into(binding.ivFullScreenEndCallAd);
        }
        StartTime = sdf.format(new Date());
    }

//    @NonNull
//    @Override
//    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
//        String contactName = null;
//        String phoneNumber = null;
//        if (args != null && args.containsKey(ARG_PHONE_NUMBER)) {
//            phoneNumber = args.getString(ARG_PHONE_NUMBER);
//        }
//        if (args != null && args.containsKey(ARG_CONTACT_NAME)) {
//            contactName = args.getString(ARG_CONTACT_NAME);
//        }
//
//        RecentsCursorLoader recentsCursorLoader = new RecentsCursorLoader(this, phoneNumber, contactName);
//        return recentsCursorLoader;
//    }
//
//    @Override
//    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
//        setData(data);
//    }
//
//    @Override
//    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
//
//    }

    private void setData(Cursor data) {
//        boolean isDiff = true;
        if (data != null && data.getCount() > 0) {

            while (data.moveToFirst()) {
                RecentCall recentCall = new RecentCall(this, data);
                String callerName = recentCall.getCallerName();
                phoneNumber = recentCall.getCallerNumber();
                String callDate = recentCall.getCallDateString();

//                isDiff = !callDate.equals(date);
                date = callDate;

                String callerDuration = "";
                if (!recentCall.getCallDuration().equals("0")){
                    callerDuration = getDurationString(recentCall.getCallDuration());
                }


                // Set display name (phone number/name)
                boolean isAvailableInContact = false;
                if (callerName != null){
                    binding.layoutDetails.tvCallerName.setText(callerName);
                    isAvailableInContact = true;
                } else {
                    binding.layoutDetails.tvCallerName.setText("Unknown");
                    isAvailableInContact = false;
                }

                if (isB2BCallerId){
                    checkB2BCallerId(phoneNumber,isAvailableInContact);
                    if (isAvailableInContact){
                        getUserInformation(phoneNumber,isAvailableInContact,callerName);
                    }
                }else {
                    getUserInformation(phoneNumber,isAvailableInContact,callerName);
                }


                Contact contact = ContactUtils.getContactByPhoneNumber(EndCallActivity.this, phoneNumber);

                if (contact !=null && contact.getPhotoUri() != null) {
                    binding.layoutDetails.itemPhoto.setImageURI(Uri.parse(contact.getPhotoUri()));
                    binding.layoutDetails.itemPhoto.setVisibility(View.VISIBLE);
                }else {
                    binding.layoutDetails.itemPhoto.setVisibility(GONE);
                }

                // Set date
                binding.layoutDetails.tvDateTime.setText(date.substring(0, date.length() - 3));



                binding.layoutDetails.tvCallerNumber.setText(phoneNumber);


                switch (recentCall.getCallType()) {
                    case RecentCall.mIncomingCall:
                        callType = "Incoming Call";
                        if (callerDuration.equals("")){
                            binding.layoutDetails.tvCallStatus.setText("Incoming Call");
                        }else {
                            callResponse = "Picked";
                            binding.layoutDetails.tvCallStatus.setText("Incoming Call, " + callerDuration);
                        }
                        break;
                    case RecentCall.mOutgoingCall:
                        callType = "Outgoing Call";
                        if (callerDuration.equals("")){
                            callResponse = "Not Connected";
                            binding.layoutDetails.tvCallStatus.setText("Outgoing Call");
                        }else {
                            callResponse = "Picked";
                            binding.layoutDetails.tvCallStatus.setText("Outgoing Call, " + callerDuration);
                        }
                        break;
                    case RecentCall.mMissedCall:
                        callType = "Incoming Call";
                        callResponse = "Missed";
                        binding.layoutDetails.tvCallStatus.setText("Missed Call");
                        break;
                    case RecentCall.mRejectedCall:
                        callType = "Incoming Call";
                        callResponse = "Rejected";
                        binding.layoutDetails.tvCallStatus.setText("Rejected Call");
                        break;
                    default:
                        break;
                }
                break;
            }

        }
//        if (isDiff){
//            setEndCallImage();
//        }
    }

    @Override
    public void onBackPressed() {
        closeType = "Auto";
        finishAndRemoveTask();
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finishAndRemoveTask();
    }

    @Override
    protected void onDestroy() {
        EndTime = sdf.format(new Date());
        createLogs();
        super.onDestroy();
    }

    private void getUserInformation(String number, boolean isAvailableInContact, String callerName){

        number = number.replaceAll("[\\D]", "");
        number = number.substring(number.length() - 10);

        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);

        apiInterface.getMobileDetails(number)
                .enqueue(new Callback<MobileDetailModel>() {
                    @Override
                    public void onResponse(Call<MobileDetailModel> call,
                                           Response<MobileDetailModel> response) {
                        if (response.code() == 200){

                            binding.layoutDetails.ivTtuLogo.setVisibility(View.VISIBLE);

                            if (callerName != null){
                                binding.layoutDetails.tvCallerName.setText(callerName + ", " + response.body().getState()+ " ");
                            }else {
                                binding.layoutDetails.tvCallerName.setText(response.body().getFullName() + ", " + response.body().getState()+ " ");
                            }

                            if (!isAvailableInContact){
                                binding.layoutDetails.layoutName.setBackgroundColor(getColor(R.color.white));
                                binding.layoutDetails.tvCallerName.setTextColor(getColor(R.color.colorAccent));
                            }else {
                                binding.layoutDetails.layoutName.setBackgroundColor(getColor(R.color.black));
                                binding.layoutDetails.tvCallerName.setTextColor(getColor(R.color.white));
                            }
                        }else {
                            binding.layoutDetails.layoutName.setBackgroundColor(getColor(R.color.black));
                            binding.layoutDetails.tvCallerName.setTextColor(getColor(R.color.white));
                            binding.layoutDetails.ivTtuLogo.setVisibility(GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<MobileDetailModel> call, Throwable t) {
                        Log.d("onFailureMember","onFailure ::" + t.getMessage());
                        binding.layoutDetails.ivTtuLogo.setVisibility(GONE);
                    }
                });
    }

    private String getDurationString(String mSeconds) {

        int seconds = Integer.parseInt(mSeconds);

        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;

        if (hours<1){
            return twoDigitString(minutes) + " mins " + twoDigitString(seconds) + " sec ";
        }
        return twoDigitString(hours) + " hours " + twoDigitString(minutes) + " mins " + twoDigitString(seconds) + " sec ";
    }

    private String twoDigitString(int number) {

        if (number == 0) {
            return "00";
        }

        if (number / 10 == 0) {
            return "0" + number;
        }

        return String.valueOf(number);
    }

    private void createLogs(){
        if (popUpCallAdData!=null){
            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            CampaignLogs campaignLogs = new CampaignLogs();
            campaignLogs.setCampId(popUpCallAdData.getCampId());
            campaignLogs.setAdCategory(popUpCallAdData.getAdCategory());
            campaignLogs.setAdType(popUpCallAdData.getAdType());
            campaignLogs.setCallDate(date);
            campaignLogs.setCallTime("");
            campaignLogs.setPlayDuration(1);
            campaignLogs.setInstance(1);
            campaignLogs.setStartDateTime(StartTime);
            campaignLogs.setEndDateTime(EndTime);
            campaignLogs.setCallStatus(callResponse);
            campaignLogs.setCallType(callType);
            campaignLogs.setAction(closeType);
            campaignLogs.setSynced(false);
            campaignLogs.setCampType("Popup");
            campaignLogs.setCallToAction(callToAction);
            campaignLogs.setCallToActionTime(callToActionTime);

            mRepository.insertLog(campaignLogs);
            mRepository.increasePlayCount(popUpCallAdData.getCampId());
            callToAction = 0;

        }
    }

    private void checkB2BCallerId(String phoneNumber, boolean isAvailableInContact){

        b2bMobileNumber = phoneNumber;
        b2bMobileNumber = b2bMobileNumber.replace("+91", "");

        ApiInterface apiInterface = ApiClient.createVPService(ApiInterface.class);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("Phone_Number", b2bMobileNumber)
                .build();

        retrofit2.Call<Caller_Id> call = apiInterface.checkCallerId(requestBody);

        call.enqueue(new retrofit2.Callback<Caller_Id>() {
            @Override
            public void onResponse(retrofit2.Call<Caller_Id> call,
                                   Response<Caller_Id> response) {
                if (response.code() == 200) {
                    if (response.body().getStatus().equals("200")){

                        if (!isAvailableInContact){
                            binding.layoutDetails.tvCallerName.setText(response.body().getEmployeeData().getEmployeeName()
                                    + " [from " + response.body().getEmployeeData().getClientDisplayName() + "]");

                            binding.layoutDetails.ivTtuLogo.setVisibility(View.VISIBLE);

                            Glide.with(EndCallActivity.this)
                                    .load(Uri.parse(response.body().getEmployeeData().getThumbnailPhotoUrl()))
                                    .into(binding.layoutDetails.itemPhoto);
                            binding.layoutDetails.itemPhoto.setVisibility(View.VISIBLE);
                        }

                        Glide.with(EndCallActivity.this).load(Uri.parse(response.body().getEmployeeData().getMainPhotoUrl()))
                                .into(binding.ivFullScreenEndCallAd);
                    }else {
                        binding.layoutDetails.ivTtuLogo.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Caller_Id> call, Throwable t) {

            }
        });

    }
}
