package com.kabaladigital.tingtingu.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.KeyguardManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.PowerManager;
import android.os.RemoteException;
import android.telecom.Call;
import android.telecom.CallAudioState;
import android.telecom.VideoProfile;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.adapter.ConferenceCallAdapter;
import com.kabaladigital.tingtingu.adapter.RejectCallMessagesAdapter;
import com.kabaladigital.tingtingu.database.AppDatabase;
import com.kabaladigital.tingtingu.database.DataRepository;
import com.kabaladigital.tingtingu.database.entity.CampaignAdsPlayOrder;
import com.kabaladigital.tingtingu.database.entity.CampaignLogs;
import com.kabaladigital.tingtingu.database.entity.Contact;
import com.kabaladigital.tingtingu.databinding.ActivityOngoingCallBinding;
import com.kabaladigital.tingtingu.listener.AllPurposeTouchListener;
import com.kabaladigital.tingtingu.listener.NotificationActionReceiver;
import com.kabaladigital.tingtingu.models.Caller_Id;
import com.kabaladigital.tingtingu.models.IncomingCallAdData;
import com.kabaladigital.tingtingu.networking.ApiClient;
import com.kabaladigital.tingtingu.networking.ApiInterface;
import com.kabaladigital.tingtingu.ui.fragment.DialpadFragment;
import com.kabaladigital.tingtingu.util.CallManager;
import com.kabaladigital.tingtingu.util.DateUtility;
import com.kabaladigital.tingtingu.util.ImageManager;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.util.Stopwatch;
import com.kabaladigital.tingtingu.util.Utilities;
import com.kabaladigital.tingtingu.util.VideoManager;
import com.kabaladigital.tingtingu.viewmodels.OnGoingCallViewModel;
import com.kabaladigital.tingtingu.viewmodels.SharedDialViewModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import timber.log.Timber;

import static android.app.Notification.EXTRA_NOTIFICATION_ID;
import static com.kabaladigital.tingtingu.service.CallService.inCallServiceInstance;
import static com.kabaladigital.tingtingu.util.BiometricUtils.showBiometricPrompt;

public class OngoingCallActivity extends AppCompatActivity implements DialpadFragment
        .OnKeyDownListener, View.OnClickListener {

    private ActivityOngoingCallBinding binding;
    private OnGoingCallViewModel mViewModel;
    public static ConferenceCallAdapter conferenceCallAdapter;

    // Finals
    private static final long END_CALL_MILLIS = 300000;
    private static final String CHANNEL_ID = "notification";
    private static final int NOTIFICATION_ID = 42069;
    public static final String ACTION_ANSWER = "ANSWER";
    public static final String ACTION_HANGUP = "HANGUP";

    boolean isOn = false;    // Handler variables
    private static final int TIME_START = 1;
    private static final int TIME_STOP = 0;
    private static final int TIME_UPDATE = 2;
    private static final int REFRESH_RATE = 100;

    // Call State
    private static int mState;
    private static int mMergeOne = 0;
    private static int mMergeTwo = 1;
    private static String mStateText;

    private int randomNumber = -1;

    // Fragments
    private DialpadFragment mDialpadFragment;

    // ViewModels
    private SharedDialViewModel mSharedDialViewModel;

    // Current states
    boolean mIsCallingUI = false;
    boolean mIsCreatingUI = true;

    boolean mIsCallMerge = false;

    boolean isRejectCall = false;


    private String callStartTime;
    private String callEndTime;
    private String outgoinCallStartTime;
    private String outgoinCallEndTime;

    private String InProgressCallStartTime;
    private String InProgressCallEndTime;
    private String InProgressCampStartTime;
    private String InProgressCampEndTime;

    private String callStatus = "";
    private String incomingCallStatus = "Incoming Call";
    private SimpleDateFormat sdf;

    private String callType = "";
    private String b2bMobileNumber = "";

    private boolean isB2BCallerId = false;

    // Utilities
    Stopwatch mCallTimer = new Stopwatch();
    Callback mCallback = new Callback();
//    ActionTimer mActionTimer = new ActionTimer();

    // PowerManager
    PowerManager powerManager;
    PowerManager.WakeLock wakeLock;
    private int field = 0x00000020;

    // Audio
    AudioManager mAudioManager;

    // Handlers
    Handler mCallTimeHandler = new CallTimeHandler();

    // Swipes Listeners
    AllPurposeTouchListener mIncomingCallAcceptSwipeListener;
    AllPurposeTouchListener mIncomingCallRejectSwipeListener;

    // Notification
    NotificationCompat.Builder mBuilder;
    NotificationManager mNotificationManager;

    List<Call> calls;




//    @Nullable ViewGroup mCurrentOverlay = null;

    private BottomSheetBehavior sheetBehavior;
    private RejectCallMessagesAdapter rejectCallMessagesAdapter;

    int videoType;
    IncomingCallAdData incomingCallAdData;
    IncomingCallAdData outgoingCallAdData;
    IncomingCallAdData inProgressCallAdData;

    DataRepository mRepository;

    boolean isRinging = false;

    private Caller_Id caller_id = null;

    CountDownTimer inProgressCounter;

    public static Activity activity ;

    Context ctx = OngoingCallActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = OngoingCallActivity.this;

        // This activity needs to show even if the screen is off or locked
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true);
            setTurnScreenOn(true);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            KeyguardManager km = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
            if (km != null) {
                km.requestDismissKeyguard(this, null);
            }
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        }

        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault());

        //Set View & Model
        binding = DataBindingUtil.setContentView(this,R.layout.activity_ongoing_call);
        mViewModel = ViewModelProviders.of(this).get(OnGoingCallViewModel.class);

        // Instances
        mRepository = DataRepository
                .getInstance(AppDatabase.getDatabase(this));
        PreferenceUtils.getInstance(this,true);
        Utilities.setUpLocale(this);

        sheetBehavior = BottomSheetBehavior.from(binding.overlaySendSms.overlaySendSms);

        // Audio Manager
        mAudioManager = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);

        createNotificationChannel();
        createNotification();

        // Display caller information
        if (CallManager.sCalls.size()<=2){
            displayInformation();
        }

        // Initiate PowerManager and WakeLock (turn screen on/off according to distance from face)
        try {
            field = PowerManager.class.getField("PROXIMITY_SCREEN_OFF_WAKE_LOCK").getInt(null);
        } catch (Throwable ignored) {
        }
        powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(field, getLocalClassName());


        // Initiate Swipe listener
        mIncomingCallAcceptSwipeListener = new AllPurposeTouchListener(this) {
            @Override
            public void onSwipeRight() {
                activateCall();
                addIncomingLog();
            }

            @Override
            public void onSwipeLeft() {
                activateCall();
                addIncomingLog();
            }

            @Override
            public void onSwipeTop() {
                activateCall();
                addIncomingLog();
            }

            @Override
            public boolean onSingleTapUp(View v) {
                activateCall();
                addIncomingLog();
                return super.onSingleTapUp(v);
            }
        };

        mIncomingCallRejectSwipeListener = new AllPurposeTouchListener(this) {
            @Override
            public void onSwipeRight() {
                endCallWithButton();
            }

            @Override
            public void onSwipeLeft() {
                endCallWithButton();
            }

            @Override
            public void onSwipeTop() {
                endCallWithButton();
            }

            @Override
            public boolean onSingleTapUp(View v) {
                endCallWithButton();
                return super.onSingleTapUp(v);
            }
        };
        binding.incomingCallLayout.btnAnswer.setOnTouchListener(mIncomingCallAcceptSwipeListener);
        binding.incomingCallLayout.btnReject.setOnTouchListener(mIncomingCallRejectSwipeListener);


        // Instantiate ViewModels
        mSharedDialViewModel = ViewModelProviders.of(this).get(SharedDialViewModel.class);
        mSharedDialViewModel.getNumber().observe(this, s -> {
            if (s != null && !s.isEmpty()) {
                char c = s.charAt(s.length() - 1);
//                CallManager.keypad(c);
            }
        });

        // Call Click Listeners
        binding.ongoingCallLayout.buttonSpeaker.setOnClickListener(this);
        binding.ongoingCallLayout.buttonHold.setOnClickListener(this);
        binding.ongoingCallLayout.buttonSwap.setOnClickListener(this);
        binding.ongoingCallLayout.buttonMute.setOnClickListener(this);
        binding.ongoingCallLayout.buttonAddCall.setOnClickListener(this);
        binding.ongoingCallLayout.buttonKeypad.setOnClickListener(this);
//        binding.ongoingCallLayout.optionBtn.setOnClickListener(this);
        binding.ongoingCallLayout.buttonMerge.setOnClickListener(this);
        binding.ongoingCallLayout.rejectBtn.setOnClickListener(this);

    }


    // Reject SMS Function
    private void setRejectCallWithMessage() {
        List<String> test = new ArrayList<>();
        List<String> test2 = new ArrayList<>();
        test = CallManager.getCallRejectMessages();

        if (test==null){
            test2.add("Write a new message");
            test2.add("In meeting");
            test2.add("I'll call you back");
            test2.add("On the way");
        }else{
            test2.add("Write a new message");
            for (int i=0;i<test.size();i++){
                test2.add(test.get(i));
            }
        }
        rejectCallMessagesAdapter = new RejectCallMessagesAdapter(test2
                ,OngoingCallActivity.this);
        binding.overlaySendSms.rvMessagesList.setHasFixedSize(true);
        binding.overlaySendSms.rvMessagesList.setLayoutManager(new LinearLayoutManager(this));
        binding.overlaySendSms.rvMessagesList.setAdapter(rejectCallMessagesAdapter);
    }


    // -- Overrides -- //
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        //Listen for call state changes
        CallManager.registerCallback(mCallback);
        updateUI(CallManager.getState());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mIsCreatingUI = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CallManager.unregisterCallback(mCallback); //The activity is gone, no need to listen to changes
//        mActionTimer.cancel();
        releaseWakeLock();
        if(inProgressCounter  != null){
            inProgressCounter.cancel();
        }
        cancelNotification();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == Utilities.PERMISSION_RC && Utilities.checkPermissionsGranted(grantResults)) {
//            setSmsOverlay(mFloatingSendSMSButton);
//        }
    }

    @Override
    public void onKeyPressed(int keyCode, KeyEvent event) {
        CallManager.keypad((char) event.getUnicodeChar());
    }



    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn_close_ad:
                finish();
                break;
            case R.id.button_merge:
                if (binding.ongoingCallLayout.buttonMerge.isEnabled())
                {
                    Call ActiveCall = null;
                    Call holdCall = null;
                    for (int i=0;i<CallManager.sCalls.size();i++){
                        if (CallManager.sCalls.get(i).getState() == Call.STATE_ACTIVE){
                            ActiveCall = CallManager.sCalls.get(i);
                        }
                        if (CallManager.sCalls.get(i).getState() == Call.STATE_HOLDING){
                            holdCall = CallManager.sCalls.get(i);
                        }
                    }
                    ActiveCall.conference(holdCall);
                    binding.ongoingCallLayout.buttonMerge.setEnabled(false);
                    binding.ongoingCallLayout.buttonMerge.setColorFilter(ContextCompat.getColor(this, R.color.grey_dark));
                    binding.ongoingCallLayout.buttonSwap.setEnabled(false);
                    binding.ongoingCallLayout.buttonSwap.setColorFilter(ContextCompat.getColor(this, R.color.grey_dark));
                }
            case R.id.btn_answer:
                activateCall();
                addIncomingLog();
                break;
            case R.id.reject_btn:
                if (!callStatus.equals("Picked")){
                    callEndTime = sdf.format(new Date());
                }
                cancelNotification();
                endCallWithButton();
                break;
            case R.id.btn_reject:
                endCallWithButton();
                break;
            case R.id.button_mute:
                Utilities.toggleViewActivation(view);
                mAudioManager.setMicrophoneMute(view.isActivated());
                if(!mAudioManager.isMicrophoneMute()){
                    //Toast.makeText(ctx,"hardware does not support",Toast.LENGTH_SHORT).show();
                    Log.d("Mute","Hardware Not Supported");
                }
                break;
            case R.id.button_speaker:
                Utilities.toggleViewActivation(view);
                if (view.isActivated()){
                    inCallServiceInstance.setAudioRoute(CallAudioState.ROUTE_SPEAKER);
                }else {
                    inCallServiceInstance.setAudioRoute(CallAudioState.ROUTE_EARPIECE);
                }
                break;
            case R.id.button_hold:
                Utilities.toggleViewActivation(view);
                CallManager.hold(view.isActivated());
                break;
            case R.id.button_swap:
                if (binding.ongoingCallLayout.buttonSwap.isEnabled()){
                    CallManager.hold(view.isActivated());
                }
                break;
            case R.id.button_keypad:
                if (getSupportFragmentManager().findFragmentById(R.id.dialer_fragment) != null){
                    getSupportFragmentManager().beginTransaction()
                            .remove(mDialpadFragment).commit();
                    binding.ongoingCallLayout.dialerLayout.setVisibility(View.GONE);
                }else {
                    mDialpadFragment = DialpadFragment.newInstance(false);
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.dialer_fragment, mDialpadFragment)
                            .commit();

                    mDialpadFragment.setDigitsCanBeEdited(true);
                    mDialpadFragment.setShowVoicemailButton(false);
                    mDialpadFragment.setOnKeyDownListener(this);
                    binding.ongoingCallLayout.dialerLayout.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.button_add_call:
//                if (binding.ongoingCallLayout.buttonAddCall.isEnabled()){
                addCallAction();
                addOutgoingLog();
//                    Intent intent =  new Intent(Intent.ACTION_CALL_BUTTON);
//                    startActivity(intent);
//                }
                break;
//            case R.id.option_btn:
//                PopupMenu popup = new PopupMenu(this, view,Gravity.TOP | Gravity.RIGHT);
//                MenuInflater inflater = popup.getMenuInflater();
//                inflater.inflate(R.menu.menu_on_going_call, popup.getMenu());
//                popup.show();
//                break;
        }

        if (view.getId()==R.id.button_speaker || view.getId()==R.id.button_hold || view.getId()==R.id.button_mute){
            changeColors(view);
        }

//        if (view.getId()==R.id.button_add_call){
//            changeColors(view);
//        }
    }


    private void setMicMuted(boolean state){
        mAudioManager.setMicrophoneMute(state);
        //mAudioManager.setMode(AudioManager.MODE_NORMAL);

        //mAudioManager.setMode(AudioManager.RINGER_MODE_SILENT);
       // mAudioManager.setStreamVolume(1,0,0);
       // mAudioManager.setSpeakerphoneOn(true);

        /*mAudioManager. setStreamMute(mAudioManager.STREAM_VOICE_CALL,true);
        mAudioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, 00, 0);
        mAudioManager.adjustVolume(AudioManager.ADJUST_LOWER,AudioManager.FLAG_PLAY_SOUND);
        mAudioManager.setMicrophoneMute(true);
        mAudioManager.setMode(AudioManager.MODE_NORMAL);
        mAudioManager.abandonAudioFocus(null);*/

        /*try {
            Intent buttonUp = new Intent(Intent.ACTION_MEDIA_BUTTON);
            buttonUp.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK));
            getBaseContext().sendOrderedBroadcast(buttonUp, "android.permission.CALL_PRIVILEGED");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }*/

        boolean isfalse = mAudioManager.isMicrophoneMute();
        Toast.makeText(ctx,""+isfalse,Toast.LENGTH_LONG).show();
        Log.d("flagval",""+isfalse);





    }


















    private void addCallAction() {
        List<Intent> callAppList = new ArrayList<Intent>();
        Intent callIntent = new Intent();
        callIntent.setAction(Intent.ACTION_CALL_BUTTON);
        List<ResolveInfo> resInfos = getPackageManager().queryIntentActivities(callIntent, 0);

        if (!resInfos.isEmpty()) {
            for (ResolveInfo resInfo : resInfos) {
                String packageName = resInfo.activityInfo.packageName;
                if (!packageName.equals(getApplicationContext().getPackageName())) {
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName(packageName, resInfo.activityInfo.name));
                    intent.setAction(Intent.ACTION_CALL_BUTTON);
                    intent.setPackage(packageName);
                    callAppList.add(intent);
                }
            }
            if (!callAppList.isEmpty()) {
                Intent chooserIntent = Intent.createChooser(callAppList.remove(0), "Select Calling Application");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, callAppList.toArray(new Parcelable[]{}));
                startActivity(chooserIntent);
            } else
                Log.e("Error", "No Apps can perform your task");
        }
    }

    public void changeColors(View view) {
        ImageView imageButton = (ImageView) view;
        if (view.isActivated())
            imageButton.setBackground(ContextCompat.getDrawable(this,R.drawable.green_circle));
        else
            imageButton.setBackground(ContextCompat.getDrawable(this,R.drawable.circle));
    }

//    public void enableDisableButton(View view) {
//        ImageView imageButton = (ImageView) view;
//        if (view.isActivated())
//            imageButton.setColorFilter(ContextCompat.getColor(this, R.color.color_line));
//        else
//            imageButton.setColorFilter(ContextCompat.getColor(this, R.color.white));
//    }

    private void activateCall() {
        if (CallManager.sCalls.size()>1 && CallManager.getState() == Call.STATE_RINGING){
            String[] colors = {"Put in progress call on hold & Answer", "Merge", "End in progress call & Answer"};

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Answer call");
            builder.setItems(colors, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case 0:
                            dialog.dismiss();
                            CallManager.answer();
                            binding.overlaySendSms.overlaySendSms.setVisibility(View.GONE);
                            break;
                        case 1:
                            dialog.dismiss();
                            CallManager.sCalls.get(1).answer(VideoProfile.STATE_AUDIO_ONLY);
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    CallManager.sCall.conference(CallManager.sCalls.get(0));
                                }
                            }, 2000);
                            binding.ongoingCallLayout.buttonMerge.setEnabled(false);
                            binding.ongoingCallLayout.buttonMerge.setColorFilter(ContextCompat.getColor(OngoingCallActivity.this, R.color.grey_dark));
                            binding.ongoingCallLayout.buttonSwap.setEnabled(false);
                            binding.ongoingCallLayout.buttonSwap.setColorFilter(ContextCompat.getColor(OngoingCallActivity.this, R.color.grey_dark));
                            binding.overlaySendSms.overlaySendSms.setVisibility(View.GONE);
                            break;
                        case 2:
                            dialog.dismiss();
                            CallManager.sCalls.get(0).disconnect();
                            CallManager.sCalls.get(1).answer(VideoProfile.STATE_AUDIO_ONLY);
                            binding.overlaySendSms.overlaySendSms.setVisibility(View.GONE);
                            break;
                    }
                }
            });
            builder.show();
        }else {
            CallManager.answer();
        }
    }

    private void endCallWithButton(){
        mCallTimeHandler.sendEmptyMessage(TIME_STOP);

        // if screen stuck
        if (CallManager.sCalls.size()==0){
            endCall();
        }


        if (CallManager.sCalls.size()>1){

            for (int i=0;i<CallManager.sCalls.size();i++){
                if (CallManager.sCalls.get(i).getState() == Call.STATE_RINGING || CallManager.sCalls.get(i).getState() == Call.STATE_DIALING){
                    isRinging = true;
                }
            }
            if (!isRinging){
                for (int i=0;i<CallManager.sCalls.size();i++){
                    CallManager.sCalls.get(i).disconnect();
                }
                UpdateScreenElements(12);
            }else {
                CallManager.sCalls.get(1).disconnect();
                UpdateScreenElements(Call.STATE_ACTIVE);
            }

        }else {
            CallManager.reject();
        }
    }

    public void endCall() {
        if(inProgressCounter  != null){
            inProgressCounter.cancel();
        }
        if (!isRinging){
            releaseWakeLock();
//            ShowNotificationAd.createNotification(this);
            if (CallManager.isAutoCalling()) {
                finish();
                CallManager.nextCall(this);
            } else {
                Intent intent = new Intent(OngoingCallActivity.this,EndCallActivity.class);
                intent.putExtra("isRejectCall", isRejectCall);
                intent.putExtra("b2bMobileNumber", b2bMobileNumber);
                intent.putExtra("isB2BCallerId", isB2BCallerId);
                startActivity(intent);
                isRejectCall = false;
                finish();
//            binding.ongoingCallLayout.ongoingCallLayout.setVisibility(View.GONE);
//            binding.layoutEndCallAd.layoutEndCallAd.setVisibility(View.VISIBLE);
//            (new Handler()).postDelayed(this::finish, END_CALL_MILLIS); // Delay the closing of the call
            }

            if (incomingCallAdData!=null){

                int test = (int) DateUtility.checkTimeDifference(callStartTime,callEndTime);
                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                CampaignLogs campaignLogs = new CampaignLogs();
                campaignLogs.setCampId(incomingCallAdData.getCampId());
                campaignLogs.setAdCategory(incomingCallAdData.getAdCategory());
                campaignLogs.setAdType(incomingCallAdData.getAdType());
                campaignLogs.setCallDate(date);
                campaignLogs.setCallTime(mCallTimer.getStringTime());
                campaignLogs.setPlayDuration(test / 60);
                campaignLogs.setInstance(test / 60);
                campaignLogs.setStartDateTime(callStartTime);
                campaignLogs.setEndDateTime(callEndTime);
                campaignLogs.setCallStatus(callStatus);
                campaignLogs.setSynced(false);
                campaignLogs.setCallType(incomingCallStatus);
                campaignLogs.setCampType("Incoming");

                mRepository.insertLog(campaignLogs);
                mRepository.increasePlayCount(incomingCallAdData.getCampId(),test);
                incomingCallAdData = null;
            }

            if (outgoingCallAdData!=null){
                int test = (int) DateUtility.checkTimeDifference(outgoinCallStartTime,callEndTime);
                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                CampaignLogs campaignLogs = new CampaignLogs();
                campaignLogs.setCampId(outgoingCallAdData.getCampId());
                campaignLogs.setAdCategory(outgoingCallAdData.getAdCategory());
                campaignLogs.setAdType(outgoingCallAdData.getAdType());
                campaignLogs.setCallDate(date);
                campaignLogs.setCallTime(mCallTimer.getStringTime());
                campaignLogs.setPlayDuration(test / 60);
                campaignLogs.setInstance(test / 60);
                campaignLogs.setStartDateTime(outgoinCallStartTime);
                campaignLogs.setEndDateTime(callEndTime);
                campaignLogs.setCallStatus(callStatus);
                campaignLogs.setSynced(false);
                campaignLogs.setCampType("Outgoing");

                mRepository.insertLog(campaignLogs);
                mRepository.increasePlayCount(outgoingCallAdData.getCampId(),test);
                outgoingCallAdData = null;
            }

            if (inProgressCallAdData!=null){
                int test = (int) DateUtility.checkTimeDifference(InProgressCampStartTime,callEndTime);
                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                CampaignLogs campaignLogs = new CampaignLogs();
                campaignLogs.setCampId(inProgressCallAdData.getCampId());
                campaignLogs.setAdCategory(inProgressCallAdData.getAdCategory());
                campaignLogs.setAdType(callType);
                campaignLogs.setCallDate(date);
                campaignLogs.setCallTime(InProgressCallStartTime);
                campaignLogs.setPlayDuration(test / 60);
                campaignLogs.setInstance(test / 60);
                campaignLogs.setStartDateTime(InProgressCampStartTime);
                campaignLogs.setEndDateTime(callEndTime);
                campaignLogs.setCallStatus(callStatus);
                campaignLogs.setSynced(false);
                campaignLogs.setCampType("InProgress");

                mRepository.insertLog(campaignLogs);
                mRepository.increasePlayCount(inProgressCallAdData.getCampId(),test);
                inProgressCallAdData = null;
            }
        }else {
//            switchToCallingUI();
            UpdateScreenElements(Call.STATE_DISCONNECTING);
        }

    }












    public void endCall(int pos) {
        if (CallManager.sCalls.size()>1){

            CallManager.sCalls.get(pos).disconnect();

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            CallManager.sCall = CallManager.sCalls.get(0);
                            CallManager.sCall.registerCallback(mCallback);
                            showUI();
                        }
                    });
                }
            }, 2000);
        }else {
            CallManager.reject();
        }
    }

    private void showUI() {
        binding.incomingCallLayout.incomingCallLayout.setVisibility(View.GONE);
        binding.ongoingCallLayout.ongoingCallLayout.setVisibility(View.VISIBLE);

        binding.ongoingCallLayout.rvCalls.setVisibility(View.GONE);

        Contact callerContact = CallManager.getDisplayContact(this);
        setRejectCallWithMessage();
        if (!callerContact.getName().isEmpty()) {
            String mNumberType = CallManager.getMobileNumberType(callerContact.getType());
            if (callerContact.getName() != null) {
                binding.ongoingCallLayout.textCaller.setText(callerContact.getName());
                if (!callerContact.getName().equals("")) binding.ongoingCallLayout.textCallerNumber.setText(mNumberType + callerContact.getMainPhoneNumber());
            }
            if (callerContact.getPhotoUri() != null) {
                binding.ongoingCallLayout.frameLayout.setVisibility(View.VISIBLE);
                binding.ongoingCallLayout.imagePlaceholder.setVisibility(View.VISIBLE);
                binding.ongoingCallLayout.imagePhoto.setVisibility(View.VISIBLE);
                binding.ongoingCallLayout.imagePhoto.setImageURI(Uri.parse(callerContact.getPhotoUri()));


                binding.ongoingCallLayout.textCallerNumber.setText(mNumberType + callerContact.getMainPhoneNumber());
            }
        } else {
            binding.ongoingCallLayout.textCaller.setText(callerContact.getMainPhoneNumber());
            binding.ongoingCallLayout.textCallerNumber.setVisibility(View.GONE);
        }

        binding.ongoingCallLayout.buttonHold.setVisibility(View.VISIBLE);
        binding.ongoingCallLayout.buttonMute.setVisibility(View.VISIBLE);
        binding.ongoingCallLayout.buttonKeypad.setVisibility(View.VISIBLE);
        binding.ongoingCallLayout.buttonSpeaker.setVisibility(View.VISIBLE);

        binding.ongoingCallLayout.buttonAddCall.setVisibility(View.VISIBLE);

        binding.ongoingCallLayout.buttonMerge.setVisibility(View.GONE);
        binding.ongoingCallLayout.buttonSwap.setVisibility(View.GONE);
        binding.overlaySendSms.overlaySendSms.setVisibility(View.GONE);


        binding.ongoingCallLayout.textCaller.setVisibility(View.VISIBLE);
        binding.ongoingCallLayout.textCallerNumber.setVisibility(View.VISIBLE);
        binding.ongoingCallLayout.textStopwatch.setVisibility(View.VISIBLE);
        binding.ongoingCallLayout.textStatus.setVisibility(View.VISIBLE);

        binding.ongoingCallLayout.textStatus.setText(getResources().getString(R.string.status_call_active));

        // Show Banner Ad
        binding.ongoingCallLayout.adImageBannerPlaceholder.setVisibility(View.VISIBLE);

        // Show Banner Ad
        binding.ongoingCallLayout.adImageBannerPlaceholder.setVisibility(View.VISIBLE);

        ImageManager.setBannerImageAd(binding.ongoingCallLayout.adImageBannerPlaceholder);

    }

    // -- UI -- //

    private void displayInformation() {
        // Display the information about the caller

        if (CallManager.getState()==Call.STATE_DIALING){

        }

        if (CallManager.getState()==Call.STATE_RINGING){
            Contact callerContact = CallManager.getDisplayContact(this);
            setRejectCallWithMessage();

            checkB2BPopUp(callerContact);

            if (!callerContact.getName().isEmpty()) {
                String mNumberType = CallManager.getMobileNumberType(callerContact.getType());
                if (callerContact.getName() != null) {
                    binding.incomingCallLayout.tvCallerName.setText(callerContact.getName());
                    if (!callerContact.getName().equals("")) binding.incomingCallLayout.tvCallerNumber.setText(mNumberType + callerContact.getMainPhoneNumber());
                }
                if (callerContact.getPhotoUri() != null) {
                    binding.incomingCallLayout.frameLayout.setVisibility(View.VISIBLE);
                    binding.incomingCallLayout.imagePlaceholder.setVisibility(View.VISIBLE);
                    binding.incomingCallLayout.imagePhoto.setVisibility(View.VISIBLE);
                    binding.incomingCallLayout.imagePhoto.setImageURI(Uri.parse(callerContact.getPhotoUri()));


                    binding.incomingCallLayout.tvCallerNumber.setText(mNumberType + callerContact.getMainPhoneNumber());
                }else {
                    binding.incomingCallLayout.frameLayout.setVisibility(View.GONE);
                    binding.incomingCallLayout.imagePlaceholder.setVisibility(View.GONE);
                    binding.incomingCallLayout.imagePhoto.setVisibility(View.GONE);
                }
            } else {
                binding.incomingCallLayout.tvCallerName.setText(callerContact.getMainPhoneNumber());
                binding.incomingCallLayout.tvCallerNumber.setVisibility(View.GONE);
            }

//            showAd();
        }

        if (CallManager.getState()==Call.STATE_ACTIVE || CallManager.getState()==Call.STATE_DIALING && CallManager.sCalls.size()<2 ){
            Contact callerContact = CallManager.getDisplayContact(this);
            setRejectCallWithMessage();
            // Hide SMS View
            binding.overlaySendSms.overlaySendSms.setVisibility(View.GONE);
            if (!callerContact.getName().isEmpty()) {
                String mNumberType = CallManager.getMobileNumberType(callerContact.getType());
                if (callerContact.getName() != null) {
                    binding.ongoingCallLayout.textCaller.setText(callerContact.getName());
                    if (!callerContact.getName().equals("")) binding.ongoingCallLayout.textCallerNumber.setText(mNumberType + callerContact.getMainPhoneNumber());
                }
                if (callerContact.getPhotoUri() != null) {
                    binding.ongoingCallLayout.frameLayout.setVisibility(View.VISIBLE);
                    binding.ongoingCallLayout.imagePlaceholder.setVisibility(View.VISIBLE);
                    binding.ongoingCallLayout.imagePhoto.setVisibility(View.VISIBLE);
                    binding.ongoingCallLayout.imagePhoto.setImageURI(Uri.parse(callerContact.getPhotoUri()));


                    binding.ongoingCallLayout.textCallerNumber.setText(mNumberType + callerContact.getMainPhoneNumber());
                }
            } else {
                binding.ongoingCallLayout.textCaller.setText(callerContact.getMainPhoneNumber());
                binding.ongoingCallLayout.textCallerNumber.setVisibility(View.GONE);
            }

//            showAd();
        }
//        else if (CallManager.sCalls.size()>1 ) {
//            List<Call> calls = new ArrayList<>();
//            for (int i=0;i<CallManager.sCalls.size();i++){
//                if (CallManager.sCalls.get(i).getState()==Call.STATE_ACTIVE){
//                    calls.add(CallManager.sCalls.get(i));
//                }
//            }
//
//            if (calls.size()>0){
//                conferenceCallAdapter = new ConferenceCallAdapter(CallManager.sCalls, this, OngoingCallActivity.this);
//                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//                binding.ongoingCallLayout.rvCalls.setLayoutManager(mLayoutManager);
//                binding.ongoingCallLayout.rvCalls.setItemAnimator(new DefaultItemAnimator());
//                binding.ongoingCallLayout.rvCalls.setAdapter(conferenceCallAdapter);
//
//                binding.ongoingCallLayout.frameLayout.setVisibility(View.INVISIBLE);
//                binding.ongoingCallLayout.textCaller.setVisibility(View.INVISIBLE);
//                binding.ongoingCallLayout.textCallerNumber.setVisibility(View.INVISIBLE);
//                binding.ongoingCallLayout.textStopwatch.setVisibility(View.INVISIBLE);
//                binding.ongoingCallLayout.textStatus.setVisibility(View.INVISIBLE);
//            }
//        }

    }



    //              1   = Call.STATE_DIALING
//              2   = Call.STATE_RINGING
//              3   = Call.STATE_HOLDING
//              4   = Call.STATE_ACTIVE
//              7   = Call.STATE_DISCONNECTED
//              8   = Call.STATE_SELECT_PHONE_ACCOUNT
//              9   = Call.STATE_CONNECTING
//              10  = Call.STATE_DISCONNECTING
//              11  = Call.STATE_PULLING_CALL
//              12  = Call.STATE_END_ALL
    private void UpdateScreenElements(int state) {

        if (state == 12){
            endCall();
        }
        if (state == Call.STATE_DISCONNECTED)
        {
            Log.d("size",""+CallManager.sCalls.size());
            if (CallManager.sCalls.size() > 1){
                binding.overlaySendSms.overlaySendSms.setVisibility(View.GONE);
//                showConference();
            }else {
                endCall();
            }
        }
        if (state == Call.STATE_RINGING){
            Contact callerContact = CallManager.getDisplayContact(this);
            setRejectCallWithMessage();
            boolean isInContactList = false;
            if (!callerContact.getName().isEmpty()) {
                String mNumberType = CallManager.getMobileNumberType(callerContact.getType());
                if (callerContact.getName() != null) {
                    binding.incomingCallLayout.tvCallerName.setText(callerContact.getName());
                    isInContactList = true;
                    if (!callerContact.getName().equals("")){
                        binding.incomingCallLayout.tvCallerNumber.setText(mNumberType + callerContact.getMainPhoneNumber());
                    }
                }
                if (callerContact.getPhotoUri() != null) {
                    binding.incomingCallLayout.frameLayout.setVisibility(View.VISIBLE);
                    binding.incomingCallLayout.imagePlaceholder.setVisibility(View.VISIBLE);
                    binding.incomingCallLayout.imagePhoto.setVisibility(View.VISIBLE);
                    binding.incomingCallLayout.imagePhoto.setImageURI(Uri.parse(callerContact.getPhotoUri()));


                    binding.incomingCallLayout.tvCallerNumber.setText(mNumberType + callerContact.getMainPhoneNumber());
                }
            } else {
                binding.incomingCallLayout.tvCallerName.setText(callerContact.getMainPhoneNumber());
                binding.incomingCallLayout.tvCallerNumber.setText(callerContact.getMainPhoneNumber());
                binding.incomingCallLayout.tvCallerNumber.setVisibility(View.GONE);
                isInContactList = false;
            }

            checkB2BCallerId(isInContactList,callerContact.getMainPhoneNumber());
        }

//        if (CallManager.sCalls.size()<2 && state==Call.STATE_ACTIVE || state==Call.STATE_DIALING ){
//            Contact callerContact = CallManager.getDisplayContact(this);
//            setRejectCallWithMessage();
//            if (!callerContact.getName().isEmpty()) {
//                String mNumberType = CallManager.getMobileNumberType(callerContact.getType());
//                if (callerContact.getName() != null) {
//                    binding.ongoingCallLayout.textCaller.setText(callerContact.getName());
//                    if (!callerContact.getName().equals("")) binding.ongoingCallLayout.textCallerNumber.setText(mNumberType + callerContact.getMainPhoneNumber());
//                }
//                if (callerContact.getPhotoUri() != null) {
//                    binding.ongoingCallLayout.frameLayout.setVisibility(View.VISIBLE);
//                    binding.ongoingCallLayout.imagePlaceholder.setVisibility(View.VISIBLE);
//                    binding.ongoingCallLayout.imagePhoto.setVisibility(View.VISIBLE);
//                    binding.ongoingCallLayout.imagePhoto.setImageURI(Uri.parse(callerContact.getPhotoUri()));
//
//
//                    binding.ongoingCallLayout.textCallerNumber.setText(mNumberType + callerContact.getMainPhoneNumber());
//                }
//
////                if (state==Call.STATE_ACTIVE){
////                    binding.ongoingCallLayout.buttonAddCall.setEnabled(true);
////                    binding.ongoingCallLayout.buttonAddCall.setColorFilter(ContextCompat.getColor(this, R.color.white));
////                }
//
//            } else {
//                binding.ongoingCallLayout.textCaller.setText(callerContact.getMainPhoneNumber());
//                binding.ongoingCallLayout.textCallerNumber.setVisibility(View.GONE);
//            }
//        }

        if (state==Call.STATE_CONNECTING || state==Call.STATE_ACTIVE || state==Call.STATE_DIALING){
            binding.incomingCallLayout.incomingCallLayout.setVisibility(View.GONE);
            binding.ongoingCallLayout.ongoingCallLayout.setVisibility(View.VISIBLE);

            if (CallManager.sCalls.size()<2){
                binding.ongoingCallLayout.rvCalls.setVisibility(View.GONE);
            }
            Contact callerContact = CallManager.getDisplayContact(this);
            setRejectCallWithMessage();

            if (CallManager.sCalls.size()<2){
                if (isB2BCallerId && caller_id!=null && callerContact.getName().isEmpty()){

                    binding.ongoingCallLayout.textCaller.setText(caller_id.getEmployeeData().getEmployeeName()
                            + " [from " + caller_id.getEmployeeData().getClientDisplayName() + "]");

                    Glide.with(OngoingCallActivity.this)
                            .load(Uri.parse(caller_id.getEmployeeData().getThumbnailPhotoUrl()))
                            .into(binding.ongoingCallLayout.imagePhoto);

                    binding.ongoingCallLayout.textCallerNumber.setText(callerContact.getMainPhoneNumber());
                    binding.ongoingCallLayout.textCallerNumber.setVisibility(View.VISIBLE);
                    binding.ongoingCallLayout.frameLayout.setVisibility(View.VISIBLE);
                    binding.ongoingCallLayout.imagePlaceholder.setVisibility(View.VISIBLE);
                    binding.ongoingCallLayout.imagePhoto.setVisibility(View.VISIBLE);

                }else {
                    if (!callerContact.getName().isEmpty()) {
                        String mNumberType = CallManager.getMobileNumberType(callerContact.getType());
                        if (callerContact.getName() != null) {
                            binding.ongoingCallLayout.textCaller.setText(callerContact.getName());
                            if (!callerContact.getName().equals("")) binding.ongoingCallLayout.textCallerNumber.setText(mNumberType + callerContact.getMainPhoneNumber());
                        }
                        if (callerContact.getPhotoUri() != null) {
                            binding.ongoingCallLayout.frameLayout.setVisibility(View.VISIBLE);
                            binding.ongoingCallLayout.imagePlaceholder.setVisibility(View.VISIBLE);
                            binding.ongoingCallLayout.imagePhoto.setVisibility(View.VISIBLE);
                            binding.ongoingCallLayout.imagePhoto.setImageURI(Uri.parse(callerContact.getPhotoUri()));


                            binding.ongoingCallLayout.textCallerNumber.setText(mNumberType + callerContact.getMainPhoneNumber());
                        }
                    } else {
                        binding.ongoingCallLayout.textCaller.setText(callerContact.getMainPhoneNumber());
                        binding.ongoingCallLayout.textCallerNumber.setVisibility(View.GONE);
                    }
                }
            }

            if (mIsCallingUI) return;
            else mIsCallingUI = true;
            mAudioManager.setMode(AudioManager.MODE_IN_CALL);
            acquireWakeLock();

            // Change the buttons layout
//        binding.ongoingCallLayout.answerBtn.hide();
            binding.ongoingCallLayout.buttonHold.setVisibility(View.VISIBLE);
            binding.ongoingCallLayout.buttonMute.setVisibility(View.VISIBLE);
            binding.ongoingCallLayout.buttonKeypad.setVisibility(View.VISIBLE);
            binding.ongoingCallLayout.buttonSpeaker.setVisibility(View.VISIBLE);


            binding.ongoingCallLayout.buttonAddCall.setVisibility(View.VISIBLE);
//            binding.ongoingCallLayout.buttonAddCall.setEnabled(false);

            if (CallManager.sCalls.size()>1){
                binding.ongoingCallLayout.buttonMerge.setVisibility(View.VISIBLE);
            }
//        moveRejectButtonToMiddle();

            // Hide SMS View
            binding.overlaySendSms.overlaySendSms.setVisibility(View.GONE);

            // Show Banner Ad
            binding.ongoingCallLayout.adImageBannerPlaceholder.setVisibility(View.VISIBLE);

            // Show Banner Ad
            binding.ongoingCallLayout.adImageBannerPlaceholder.setVisibility(View.VISIBLE);
//        binding.ongoingCallLayout.optionBtn.setVisibility(View.VISIBLE);
            ImageManager.setBannerImageAd(binding.ongoingCallLayout.adImageBannerPlaceholder);

            switchView();
        }

        if (state==Call.STATE_HOLDING && CallManager.sCalls.size()>1){
            binding.ongoingCallLayout.textStatus.setText(getResources().getString(R.string.status_call_dialing));
            binding.ongoingCallLayout.textStopwatch.setVisibility(View.GONE);
            binding.incomingCallLayout.incomingCallLayout.setVisibility(View.GONE);
            binding.ongoingCallLayout.ongoingCallLayout.setVisibility(View.VISIBLE);

            Contact callerContact = CallManager.getDisplayContact(this);
            setRejectCallWithMessage();
            if (!callerContact.getName().isEmpty()) {
                String mNumberType = CallManager.getMobileNumberType(callerContact.getType());
                if (callerContact.getName() != null) {
                    binding.ongoingCallLayout.textCaller.setText(callerContact.getName());
                    if (!callerContact.getName().equals("")) binding.ongoingCallLayout.textCallerNumber.setText(mNumberType + callerContact.getMainPhoneNumber());
                }
                if (callerContact.getPhotoUri() != null) {
                    binding.ongoingCallLayout.frameLayout.setVisibility(View.VISIBLE);
                    binding.ongoingCallLayout.imagePlaceholder.setVisibility(View.VISIBLE);
                    binding.ongoingCallLayout.imagePhoto.setVisibility(View.VISIBLE);
                    binding.ongoingCallLayout.imagePhoto.setImageURI(Uri.parse(callerContact.getPhotoUri()));


                    binding.ongoingCallLayout.textCallerNumber.setText(mNumberType + callerContact.getMainPhoneNumber());
                }else {
                    binding.ongoingCallLayout.frameLayout.setVisibility(View.GONE);
                    binding.ongoingCallLayout.imagePlaceholder.setVisibility(View.GONE);
                    binding.ongoingCallLayout.imagePhoto.setVisibility(View.GONE);
                }
            } else {
                binding.ongoingCallLayout.textCaller.setText(callerContact.getMainPhoneNumber());
                binding.ongoingCallLayout.textCallerNumber.setVisibility(View.GONE);
            }

            InProgressCampEndTime = sdf.format(new Date());
            addInProgressLog();
            if(inProgressCounter  != null){
                inProgressCounter.cancel();
            }
            callType = "Outgoing Call";
            switchView();

            if (mIsCallingUI) return;
            else mIsCallingUI = true;
            mAudioManager.setMode(AudioManager.MODE_IN_CALL);
            acquireWakeLock();

            // Change the buttons layout
//        binding.ongoingCallLayout.answerBtn.hide();
            binding.ongoingCallLayout.buttonHold.setVisibility(View.GONE);
            binding.ongoingCallLayout.buttonMute.setVisibility(View.VISIBLE);
            binding.ongoingCallLayout.buttonKeypad.setVisibility(View.VISIBLE);
            binding.ongoingCallLayout.buttonSpeaker.setVisibility(View.VISIBLE);
            binding.ongoingCallLayout.buttonAddCall.setVisibility(View.GONE);

//            if (CallManager.sCalls.size()>1){
            binding.ongoingCallLayout.buttonMerge.setVisibility(View.VISIBLE);
            binding.ongoingCallLayout.buttonSwap.setVisibility(View.VISIBLE);
//                binding.ongoingCallLayout.buttonHold.setVisibility(View.VISIBLE);
//            }
//        moveRejectButtonToMiddle();

            // Hide SMS View
            binding.overlaySendSms.overlaySendSms.setVisibility(View.GONE);

            // Show Banner Ad
            binding.ongoingCallLayout.adImageBannerPlaceholder.setVisibility(View.VISIBLE);

            // Show Banner Ad
            binding.ongoingCallLayout.adImageBannerPlaceholder.setVisibility(View.VISIBLE);
//        binding.ongoingCallLayout.optionBtn.setVisibility(View.VISIBLE);
            ImageManager.setBannerImageAd(binding.ongoingCallLayout.adImageBannerPlaceholder);


            switchView();
        }
    }


    public void showConference()
    {
        if (CallManager.sCalls.size()>1 )
        {
            calls = new ArrayList<>();
            for (int i=0;i<CallManager.sCalls.size();i++){
                Log.i("ConferenceCallStatus", String.valueOf(CallManager.sCalls.get(i).getState()));

                boolean a = CallManager.sCalls.get(i).getChildren().size() == 0;
                if (a){
                    if (CallManager.sCalls.get(i).getState()==Call.STATE_RINGING
                            || CallManager.sCalls.get(i).getState()==Call.STATE_DIALING
                            || CallManager.sCalls.get(i).getState()==Call.STATE_ACTIVE
                            || CallManager.sCalls.get(i).getState()==Call.STATE_HOLDING ){

                        calls.add(CallManager.sCalls.get(i));
                    }
                }
                /*else if (!a && CallManager.sCalls.get(i).getState()==Call.STATE_ACTIVE){
                    calls.clear();
                    calls =(CallManager.sCalls.get(i).getChildren());
                    break;
                }*/
            }

            if (calls.size()==1){
                UpdateScreenElements(calls.get(0).getState());
            }
            else if (calls.size()>0){

                CallManager.sCalls.get(0).registerCallback(mCallback);
                CallManager.sCalls.get(1).registerCallback(mCallback);

                binding.ongoingCallLayout.rvCalls.setVisibility(View.VISIBLE);

                conferenceCallAdapter = new ConferenceCallAdapter(calls, this, OngoingCallActivity.this);
                Log.d("before", ""+calls.size());

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                binding.ongoingCallLayout.rvCalls.setLayoutManager(mLayoutManager);
                binding.ongoingCallLayout.rvCalls.setItemAnimator(new DefaultItemAnimator());

                binding.ongoingCallLayout.rvCalls.setAdapter(conferenceCallAdapter);

                // Hide SMS View
                binding.overlaySendSms.overlaySendSms.setVisibility(View.GONE);

                binding.ongoingCallLayout.frameLayout.setVisibility(View.INVISIBLE);
                binding.ongoingCallLayout.textCaller.setVisibility(View.INVISIBLE);
                binding.ongoingCallLayout.textCallerNumber.setVisibility(View.INVISIBLE);
                binding.ongoingCallLayout.textStopwatch.setVisibility(View.INVISIBLE);
                binding.ongoingCallLayout.textStatus.setVisibility(View.INVISIBLE);

                binding.ongoingCallLayout.imagePlaceholder.setVisibility(View.INVISIBLE);
                binding.ongoingCallLayout.imagePhoto.setVisibility(View.INVISIBLE);

                binding.ongoingCallLayout.buttonMerge.setVisibility(View.VISIBLE);
                binding.ongoingCallLayout.buttonAddCall.setVisibility(View.INVISIBLE);

                binding.ongoingCallLayout.buttonSwap.setVisibility(View.VISIBLE);
                binding.ongoingCallLayout.buttonHold.setVisibility(View.VISIBLE);


                callStatus = "Picked";
                callEndTime = sdf.format(new Date());
                mCallTimeHandler.sendEmptyMessage(TIME_START);
                InProgressCallStartTime = sdf.format(new Date());
                changeAd();


            }
        }
    }





    /**
     * Updates the ui given the call state
     *
     * @param state the current call state
     */
    private void updateUI(int state) {
        @StringRes int statusTextRes;
        switch (state) {
            case Call.STATE_ACTIVE: // Ongoing
                statusTextRes = R.string.status_call_active;
                callStatus = "Picked";
                callEndTime = sdf.format(new Date());
                mCallTimeHandler.sendEmptyMessage(TIME_START);
                InProgressCallStartTime = sdf.format(new Date());
                changeAd();
                break;
            case Call.STATE_DISCONNECTED: // Ended
                if (!callStatus.equals("Picked")){
                    callEndTime = sdf.format(new Date());
                }
                if (CallManager.sCalls.size()<2){
                    statusTextRes = R.string.status_call_disconnected;

                }else {
                    statusTextRes = R.string.status_call_active;
                }

                break;
            case Call.STATE_RINGING: // Incoming
                statusTextRes = R.string.status_call_incoming;
                callType = "Incoming Call";
                callStatus = "Ringing";
                showBiometricPrompt(this);
                callStartTime = sdf.format(new Date());
                break;
            case Call.STATE_DIALING: // Outgoing
                statusTextRes = R.string.status_call_dialing;
                callStatus = "Dialing";
                callType = "Outgoing Call";
                outgoinCallStartTime = sdf.format(new Date());
                break;
            case Call.STATE_CONNECTING: // Connecting (probably outgoing)
                statusTextRes = R.string.status_call_dialing;
                callType = "Outgoing Call";
                outgoinCallStartTime = sdf.format(new Date());
                break;
            case Call.STATE_HOLDING: // On Hold
                statusTextRes = R.string.status_call_holding;
                break;
            default:
                statusTextRes = R.string.status_call_active;
                break;
        }
        binding.ongoingCallLayout.textStatus.setText(statusTextRes);
        UpdateScreenElements(state);

//        if (state != Call.STATE_RINGING && state != Call.STATE_DISCONNECTED){
//            switchToCallingUI();
//        }

//        if (CallManager.sCalls.size()<2){
//            if (state == Call.STATE_DISCONNECTED) endCall();
//        }
//        mState = state;
//        mStateText = getString(statusTextRes);
//        mBuilder.setContentText(mStateText);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
//        }

    }

    /**
     * Update the current call time ui
     */
    private void updateTimeUI() {
        binding.ongoingCallLayout.textStopwatch.setText(mCallTimer.getStringTime());
    }

//    /**
//     * Switches the ui to an active call ui.
//     */
//    private void switchToCallingUI() {
//        displayInformation();
//        binding.incomingCallLayout.incomingCallLayout.setVisibility(View.GONE);
//        binding.ongoingCallLayout.ongoingCallLayout.setVisibility(View.VISIBLE);
//
//        if (mIsCallingUI) return;
//        else mIsCallingUI = true;
//        mAudioManager.setMode(AudioManager.MODE_IN_CALL);
//        acquireWakeLock();
//
//        // Change the buttons layout
////        binding.ongoingCallLayout.answerBtn.hide();
//        binding.ongoingCallLayout.buttonHold.setVisibility(View.VISIBLE);
//        binding.ongoingCallLayout.buttonMute.setVisibility(View.VISIBLE);
//        binding.ongoingCallLayout.buttonKeypad.setVisibility(View.VISIBLE);
//        binding.ongoingCallLayout.buttonSpeaker.setVisibility(View.VISIBLE);
//        binding.ongoingCallLayout.buttonAddCall.setVisibility(View.VISIBLE);
//
//        if (CallManager.sCalls.size()>1){
//            binding.ongoingCallLayout.buttonMerge.setVisibility(View.VISIBLE);
//        }
////        moveRejectButtonToMiddle();
//
//        // Hide SMS View
//        binding.overlaySendSms.overlaySendSms.setVisibility(View.GONE);
//
//        // Show Banner Ad
//        binding.ongoingCallLayout.adImageBannerPlaceholder.setVisibility(View.VISIBLE);
////        binding.ongoingCallLayout.optionBtn.setVisibility(View.VISIBLE);
//        ImageManager.setBannerImageAd(binding.ongoingCallLayout.adImageBannerPlaceholder);
//
//        switchView();
//    }

    private void showIncomingCall(){
        acquireWakeLock();
        displayInformation();
        binding.incomingCallLayout.incomingCallLayout.setVisibility(View.VISIBLE);
        binding.overlaySendSms.overlaySendSms.setVisibility(View.VISIBLE);
        binding.ongoingCallLayout.ongoingCallLayout.setVisibility(View.GONE);
        incomingCallStatus = "Call Waiting";
        showAd(null);
    }


    // -- Overlays -- //

//    private void setLayerEnabled(@NotNull ViewGroup layer, boolean isEnabled) {
//        for (int i = 0; i < layer.getChildCount(); i++) {
//            View v = layer.getChildAt(i);
//            if (isEnabled) { // If we want to enable it
//                if (v instanceof FloatingActionButton) {
//                    ((FloatingActionButton) v).show();
//                } else {
//                    v.setVisibility(View.VISIBLE);
//                }
//            } else { // If we don't
//                if (v instanceof FloatingActionButton) { // Make it non-clickable
//                    ((FloatingActionButton) v).hide();
//                } else if (v instanceof Button) {
//                    v.setVisibility(View.GONE);
//                } else { // Don't move any other views that are constrained to it
//                    v.setVisibility(View.INVISIBLE);
//                }
//                v.setHovered(false);
//            }
//        }
//    }


    // -- Wake Lock -- //

    /**
     * Acquires the wake lock
     */
    private void acquireWakeLock() {
        if (!wakeLock.isHeld()) {
            wakeLock.acquire(10 * 60 * 1000L /*10 minutes*/);
        }
    }

    /**
     * Releases the wake lock
     */
    private void releaseWakeLock() {
        if (wakeLock.isHeld()) {
            wakeLock.release();
        }
    }


    public class Callback extends Call.Callback {

        @Override
        public void onStateChanged(Call call, int state) {
            /*
              Call states:

              1   = Call.STATE_DIALING
              2   = Call.STATE_RINGING
              3   = Call.STATE_HOLDING
              4   = Call.STATE_ACTIVE
              7   = Call.STATE_DISCONNECTED
              8   = Call.STATE_SELECT_PHONE_ACCOUNT
              9   = Call.STATE_CONNECTING
              10  = Call.STATE_DISCONNECTING
              11  = Call.STATE_PULLING_CALL
             */
            super.onStateChanged(call, state);
            Timber.i("State changed: %s", state);
            updateUI(state);
//            if (CallManager.sCalls.size()>1){
//                showConference();
//            }
        }

        @Override
        public void onCallDestroyed(Call call) {
            super.onCallDestroyed(call);
            if (CallManager.sCall != null){
                Log.d("size++++", ""+inCallServiceInstance.getCalls().size());

                if (inCallServiceInstance.getCalls().size()>0)
                {
                    for (int i=0;i<inCallServiceInstance.getCalls().size();i++)
                    {
                        if (inCallServiceInstance.getCalls().get(i).getState() == Call.STATE_ACTIVE){

                            inCallServiceInstance.getCalls().get(i).getDetails().getCallProperties();
                                CallManager.sCall = inCallServiceInstance.getCalls().get(i);
                                UpdateScreenElements(Call.STATE_ACTIVE);
                                break;

                        }
                    }
                }

                if (CallManager.sCalls.size() == 2 && CallManager.sCalls.get(0).getState() == Call.STATE_DISCONNECTED && CallManager.sCalls.get(1).getDetails().getCallProperties() == Call.Details.PROPERTY_CONFERENCE){
                    endCall();
                }

                if (CallManager.sCall.getState() == Call.STATE_RINGING || CallManager.sCall.getState() == Call.STATE_DISCONNECTED){
                    if (CallManager.sCalls.size()<2){
                        UpdateScreenElements(Call.STATE_DISCONNECTED);
                    }else {
                        UpdateScreenElements(Call.STATE_ACTIVE);
                    }
                }
            }
        }

        @Override
        public void onConferenceableCallsChanged(Call call, List<Call> conferenceableCalls) {
            super.onConferenceableCallsChanged(call, conferenceableCalls);
            call.registerCallback(mCallback);
            showConference();
        }
    }

    @SuppressLint("HandlerLeak")
    class CallTimeHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case TIME_START:
                    mCallTimer.start(); // Starts the timer
                    mCallTimeHandler.sendEmptyMessage(TIME_UPDATE); // Starts the time ui updates
                    break;
                case TIME_STOP:
                    mCallTimeHandler.removeMessages(TIME_UPDATE); // No more updates
                    mCallTimer.stop(); // Stops the timer
                    updateTimeUI(); // Updates the time ui
                    break;
                case TIME_UPDATE:
                    updateTimeUI(); // Updates the time ui
                    mCallTimeHandler.sendEmptyMessageDelayed(TIME_UPDATE, REFRESH_RATE); // Text view updates every milisecond (REFRESH RATE)
                    break;
                default:
                    break;
            }
        }

    }

    // -- Notification -- //
    public void createNotification() {

        Contact callerContact = CallManager.getDisplayContact(this);
        String callerName = callerContact.getName();
        Log.d("name",""+callerName);
        if (callerName.equals("")){
            callerName = callerContact.getMainPhoneNumber();
        }

        Intent touchNotification = new Intent(this, OngoingCallActivity.class);
        touchNotification.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, touchNotification, 0);

        // Answer Button Intent
        Intent answerIntent = new Intent(this, NotificationActionReceiver.class);
        answerIntent.setAction(ACTION_ANSWER);
        answerIntent.putExtra(EXTRA_NOTIFICATION_ID, 0);
        PendingIntent answerPendingIntent = PendingIntent.getBroadcast(this, 0, answerIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        // Hangup Button Intent
        Intent hangupIntent = new Intent(this, NotificationActionReceiver.class);
        hangupIntent.setAction(ACTION_HANGUP);
        hangupIntent.putExtra(EXTRA_NOTIFICATION_ID, 0);
        PendingIntent hangupPendingIntent = PendingIntent.getBroadcast(this, 1, hangupIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.tingtingu_logo)
                .setContentTitle(callerName)
                .setContentText(mStateText)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setColor(ContextCompat.getColor(OngoingCallActivity.this, R.color.colordth))
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(0, 1))
                .setAutoCancel(false);

        // Adding the action buttons
        mBuilder.addAction(R.drawable.ic_call_black_24dp, getString(R.string.action_answer), answerPendingIntent);
        mBuilder.addAction(R.drawable.ic_call_end_black_24dp, getString(R.string.action_hangup), hangupPendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

    /**
     * Creates the notification channel
     * Which allows and manages the displaying of the notification
     */
    public void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            mNotificationManager = getSystemService(NotificationManager.class);
            mNotificationManager.createNotificationChannel(channel);
        }
    }

    /**
     * Removes the notification
     */
    public void cancelNotification() {
        String ns = this.NOTIFICATION_SERVICE;
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(ns);
        Log.d("noti1",""+EXTRA_NOTIFICATION_ID);
        Log.d("noti2",""+NOTIFICATION_ID);

        notificationManager.cancel(NOTIFICATION_ID);
    }

    public void sendMessage(String message) {
        if (Utilities.checkPermissionGranted(this, Manifest.permission.SEND_SMS)) {
            String phoneNum = CallManager.getDisplayContact(this).getMainPhoneNumber();
            Utilities.sendSMS(this, phoneNum, message);
            endCallWithButton();
        } else {
            Utilities.askForPermission(this, Manifest.permission.SEND_SMS);
        }
    }

    int stopTime = 0;

    @Override
    protected void onResume() {
        super.onResume();
        if (CallManager.sCalls.size()>1 && CallManager.getState() == Call.STATE_RINGING){
            CallManager.registerCallback(mCallback);
            showIncomingCall();

        }
        if (videoType==1){
            binding.ongoingCallLayout.videoPlaceholder.seekTo(stopTime);
            binding.ongoingCallLayout.videoPlaceholder.start();
        }
        if (videoType==2) {
            binding.incomingCallLayout.videoFullscreenPlaceholder.seekTo(stopTime);
            binding.incomingCallLayout.videoFullscreenPlaceholder.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (videoType==1){
            stopTime = binding.ongoingCallLayout.videoPlaceholder.getCurrentPosition();
        }
        if (videoType==2) {
            stopTime = binding.incomingCallLayout.videoFullscreenPlaceholder.getCurrentPosition();
        }
    }


    private void showAd(Caller_Id callerId){

        CampaignAdsPlayOrder campaignAdsPlayOrderList;
        // Get Ad Camp
        if (callerId == null){
            campaignAdsPlayOrderList = mRepository.getAllCampaignAdsOrderByCount(DateUtility.getCurrentDateInLong(),"Incoming","4");
        }else {
            campaignAdsPlayOrderList = mRepository.getIncomingCampaignAdsOrderByCount(DateUtility.getCurrentDateInLong(),"Incoming Caller","4",callerId.getEmployeeData().getClientName());
        }

        if (campaignAdsPlayOrderList!=null){
            incomingCallAdData = mRepository.getAdByCampId(campaignAdsPlayOrderList.getCampId());

            // Show Video Ad or Full Screen Video Ad or Image Ad
            if (incomingCallAdData.getAdType().equals("Video") && incomingCallAdData.getVideoSize().equals("4:3")){
                VideoManager.play43IncomingAd(binding.incomingCallLayout.videoPlaceholder
                        ,this,false,incomingCallAdData);
                videoType = 1;
            }
            if (incomingCallAdData.getAdType().equals("Video") && incomingCallAdData.getVideoSize().equals("Full Screen")) {
                VideoManager.playFullScreenIncomingAd(binding.incomingCallLayout.videoFullscreenPlaceholder
                        ,this,false,incomingCallAdData);
                videoType = 2;
            }
            if (incomingCallAdData.getAdType().equals("Image")) {
                VideoManager.stopVideo(binding.ongoingCallLayout.videoPlaceholder);
                binding.incomingCallLayout.adImagePlaceholder.setVisibility(View.VISIBLE);
                ImageManager.setIncomingCallImageAd(binding.incomingCallLayout.adImagePlaceholder, incomingCallAdData);
            }

            mRepository.updatePlayCount(incomingCallAdData.getCampId());
        } else {
            VideoManager.stopVideo(binding.incomingCallLayout.videoPlaceholder);
            binding.incomingCallLayout.adImagePlaceholder.setVisibility(View.VISIBLE);
            ImageManager.setIncomingCallImageAd(binding.incomingCallLayout.adImagePlaceholder, null);
        }
    }

    private void switchView(){
        // Stop Full Screen Video
        VideoManager.stopVideo(binding.incomingCallLayout.videoFullscreenPlaceholder);
        binding.incomingCallLayout.videoFullscreenPlaceholder.setVisibility(View.GONE);

        // Show Video Ad
        if (incomingCallAdData!= null && !incomingCallAdData.getAdType().equals("Image")){
            //Remove Video Ad
            VideoManager.stopVideo(binding.ongoingCallLayout.videoPlaceholder);
        }

        if (callType.equals("Outgoing Call")){
            CampaignAdsPlayOrder campaignAdsPlayOrderList = mRepository
                    .getAllCampaignAdsOrderByCount(DateUtility.getCurrentDateInLong()
                            ,"Outgoing","4");

            if (campaignAdsPlayOrderList!=null) {
                outgoingCallAdData = mRepository.getAdByCampId(campaignAdsPlayOrderList.getCampId());


                // Show Video Ad or Full Screen Video Ad or Image Ad
                if (outgoingCallAdData.getAdType().equals("Video")){
                    VideoManager.play43IncomingAd(binding.ongoingCallLayout.videoPlaceholder
                            ,this,false,outgoingCallAdData);
                }

                if (outgoingCallAdData.getAdType().equals("Image")) {
                    VideoManager.stopVideo(binding.ongoingCallLayout.videoPlaceholder);
                    binding.ongoingCallLayout.adImagePlaceholder.setVisibility(View.VISIBLE);
                    ImageManager.setIncomingCallImageAd(binding.ongoingCallLayout.adImagePlaceholder
                            , outgoingCallAdData);
                }

                mRepository.updatePlayCount(outgoingCallAdData.getCampId());

            }else {
                binding.ongoingCallLayout.videoPlaceholder.setVisibility(View.GONE);

                //Set Image Ad
                binding.ongoingCallLayout.adImagePlaceholder.setVisibility(View.VISIBLE);
                ImageManager.setImageAd(binding
                        .ongoingCallLayout.adImagePlaceholder);
            }
        }
    }

    private void changeAd(){
        try{

            CampaignAdsPlayOrder campaignAdsPlayOrderList = mRepository
                    .getAllCampaignAdsOrderByCount(DateUtility.getCurrentDateInLong()
                            ,"InProgress","4");

            if (campaignAdsPlayOrderList!=null) {
                inProgressCallAdData = mRepository.getAdByCampId(campaignAdsPlayOrderList.getCampId());

                InProgressCampStartTime = sdf.format(new Date());

                // Show Video Ad or Full Screen Video Ad or Image Ad
                if (inProgressCallAdData.getAdType().equals("Video")){


                    try {
                        binding.ongoingCallLayout.videoPlaceholder.setVisibility(View.VISIBLE);
                        binding.ongoingCallLayout.adImagePlaceholder.setVisibility(View.GONE);

                        VideoManager.play43IncomingAd(binding.ongoingCallLayout.videoPlaceholder
                                , this, false, inProgressCallAdData);
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }


                }

                if (inProgressCallAdData.getAdType().equals("Image")) {
                    binding.ongoingCallLayout.videoPlaceholder.setVisibility(View.GONE);
                    binding.ongoingCallLayout.adImagePlaceholder.setVisibility(View.VISIBLE);

                    VideoManager.stopVideo(binding.ongoingCallLayout.videoPlaceholder);
                    ImageManager.setIncomingCallImageAd(binding.ongoingCallLayout.adImagePlaceholder
                            , inProgressCallAdData);
                }

                inProgressCounter = new CountDownTimer(inProgressCallAdData.getAdPlayDurForEachPlay() * 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        Log.i("Time", String.valueOf(millisUntilFinished));
                    }
                    @Override
                    public void onFinish() {
                        InProgressCampEndTime = sdf.format(new Date());
                        addInProgressLog();
                        changeAd();
                    }
                };
                inProgressCounter.start();

                mRepository.updatePlayCount(inProgressCallAdData.getCampId());

            }else {
                binding.ongoingCallLayout.videoPlaceholder.setVisibility(View.GONE);

                //Set Image Ad
                binding.ongoingCallLayout.adImagePlaceholder.setVisibility(View.VISIBLE);
                ImageManager.setImageAd(binding
                        .ongoingCallLayout.adImagePlaceholder);
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private void addInProgressLog(){
        if (inProgressCallAdData!=null){
            int test = (int) DateUtility.checkTimeDifference(InProgressCampStartTime,InProgressCampEndTime);
            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            CampaignLogs campaignLogs = new CampaignLogs();
            campaignLogs.setCampId(inProgressCallAdData.getCampId());
            campaignLogs.setAdCategory(inProgressCallAdData.getAdCategory());
            campaignLogs.setAdType(callType);
            campaignLogs.setCallDate(date);
            campaignLogs.setCallTime(InProgressCallStartTime);
            campaignLogs.setPlayDuration(test / 60);
            campaignLogs.setInstance(test / 60);
            campaignLogs.setStartDateTime(InProgressCampStartTime);
            campaignLogs.setEndDateTime(InProgressCampEndTime);
            campaignLogs.setCallStatus(callStatus);
            campaignLogs.setSynced(false);
            campaignLogs.setCampType("InProgress");

            mRepository.insertLog(campaignLogs);
            mRepository.increasePlayCount(inProgressCallAdData.getCampId(),test);
            inProgressCallAdData = null;
        }
    }

    private void addIncomingLog(){
        if (incomingCallAdData!=null){

            String callEndTimeLocal = sdf.format(new Date());
            int test = (int) DateUtility.checkTimeDifference(callStartTime,callEndTimeLocal);
            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            CampaignLogs campaignLogs = new CampaignLogs();
            campaignLogs.setCampId(incomingCallAdData.getCampId());
            campaignLogs.setAdCategory(incomingCallAdData.getAdCategory());
            campaignLogs.setAdType(incomingCallAdData.getAdType());
            campaignLogs.setCallDate(date);
            campaignLogs.setCallTime(mCallTimer.getStringTime());
            campaignLogs.setPlayDuration(test / 60);
            campaignLogs.setInstance(test / 60);
            campaignLogs.setStartDateTime(callStartTime);
            campaignLogs.setEndDateTime(callEndTimeLocal);
            campaignLogs.setCallStatus(callStatus);
            campaignLogs.setSynced(false);
            campaignLogs.setCallType(incomingCallStatus);
            campaignLogs.setCampType("Incoming");

            mRepository.insertLog(campaignLogs);
            mRepository.increasePlayCount(incomingCallAdData.getCampId(),test);
            incomingCallAdData = null;
        }
    }

    private void addOutgoingLog(){
        if (outgoingCallAdData!=null){

            String localOutTime =  sdf.format(new Date());
            int test = (int) DateUtility.checkTimeDifference(outgoinCallStartTime,localOutTime);
            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            CampaignLogs campaignLogs = new CampaignLogs();
            campaignLogs.setCampId(outgoingCallAdData.getCampId());
            campaignLogs.setAdCategory(outgoingCallAdData.getAdCategory());
            campaignLogs.setAdType(outgoingCallAdData.getAdType());
            campaignLogs.setCallDate(date);
            campaignLogs.setCallTime(mCallTimer.getStringTime());
            campaignLogs.setPlayDuration(test / 60);
            campaignLogs.setInstance(test / 60);
            campaignLogs.setStartDateTime(outgoinCallStartTime);
            campaignLogs.setEndDateTime(localOutTime);
            campaignLogs.setCallStatus(callStatus);
            campaignLogs.setSynced(false);
            campaignLogs.setCampType("Outgoing");

            mRepository.insertLog(campaignLogs);
            mRepository.increasePlayCount(outgoingCallAdData.getCampId(),test);
            outgoingCallAdData = null;
        }
    }

    private void checkB2BPopUp(Contact callerContact) {

        if (callerContact.getPhoneNumbers().size()==1){
            b2bMobileNumber = callerContact.getPhoneNumbers().get(0);
            b2bMobileNumber = b2bMobileNumber.replace("+91", "");

            int callerIdCount = mRepository
                    .checkPopUpCallerId(Long.parseLong(b2bMobileNumber));
            if (callerIdCount>0){
                isRejectCall = true;
                endCallWithButton();
            }
        }


    }

    private void checkB2BCallerId(boolean isInContactList, String mainPhoneNumber){

        b2bMobileNumber = mainPhoneNumber.replace("+91", "");

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
                        caller_id = response.body();
                        showAd(response.body());
                        isB2BCallerId = true;

                        if (!isInContactList){
                            binding.incomingCallLayout.tvCallerName.setText(response.body().getEmployeeData().getEmployeeName()
                                    + " [from " + response.body().getEmployeeData().getClientDisplayName() + "]");

                            Glide.with(OngoingCallActivity.this)
                                    .load(Uri.parse(response.body().getEmployeeData().getThumbnailPhotoUrl()))
                                    .into(binding.incomingCallLayout.imagePhoto);

                            binding.incomingCallLayout.tvCallerNumber.setVisibility(View.VISIBLE);
                            binding.incomingCallLayout.frameLayout.setVisibility(View.VISIBLE);
                            binding.incomingCallLayout.imagePlaceholder.setVisibility(View.VISIBLE);
                            binding.incomingCallLayout.imagePhoto.setVisibility(View.VISIBLE);
                        }
                    }
                    if (response.body().getStatus().equals("404")){
                        showAd(null);
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Caller_Id> call, Throwable t) {
                showAd(null);
            }
        });

    }
}
