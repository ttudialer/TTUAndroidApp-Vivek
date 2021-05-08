package com.kabaladigital.tingtingu.ui.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.databinding.InviteFriendFragmentBinding;
import com.kabaladigital.tingtingu.ui.activity.MainActivity;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.viewmodels.InviteFriendViewModel;


import static android.content.Context.CLIPBOARD_SERVICE;
import static android.os.Looper.getMainLooper;

public class InviteFriendFragment extends Fragment {

    private InviteFriendFragmentBinding binding;
    private InviteFriendViewModel mViewModel;
    private String code = "";
    private String langType;
    InstallReferrerClient referrerClient;
    String refrer = "";


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.invite_friend_fragment, container, false);

        code = PreferenceUtils.getInstance().getString(R.string.pref_refer_code_key);
        binding.tvReferalCode.setText(code);

        referrerClient = InstallReferrerClient.newBuilder(getContext()).build();


        PackageInfo pinfo = null;
        try {
            pinfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        binding.txtAppinfo.setText("TTU App Version Name "+pinfo.versionName+ " "+ "App Version Code "+pinfo.versionCode);
        String ShareText = "Use this Ting Ting U Application Invitation Referral Code to Earn " + code;
        langType = PreferenceUtils.getInstance().getString(R.string.pref_user_selected_language_key);

        if (langType.equals("hi")){
            binding.tvYou.setText("आप "+ PreferenceUtils.getInstance().getString(R.string.pref_referrer_points) +" अंक अर्जित करते हैं");
            binding.tvFriend.setText("आपका दोस्त "
                    + PreferenceUtils.getInstance().getString(R.string.pref_referee_points) +" अंक अर्जित करता है");
            binding.tvReferDayText.setText("जब आप रेफ़र करते हैं और आपका दोस्त TTU (टी टी यू) के साथ "
                    + PreferenceUtils.getInstance().getString(R.string.pref_referrer_reward_days) +" दिनों तक सक्रिय रहता है");
        }else {
            binding.tvYou.setText("You Earn "+ PreferenceUtils.getInstance().getString(R.string.pref_referrer_points) +" points");
            binding.tvFriend.setText("Your friend Earns "
                    + PreferenceUtils.getInstance().getString(R.string.pref_referee_points) +" points");
            binding.tvReferDayText.setText("When you Refer and friend complete "
                    + PreferenceUtils.getInstance().getString(R.string.pref_referrer_reward_days) +" active days with TTU");
        }


        binding.layoutShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                referfunction();
                //createlink();


                /*final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, ShareText);
                        sendIntent.setType("text/plain");

                        Intent shareIntent = Intent.createChooser(sendIntent, null);
                        startActivity(shareIntent);
                    }
                }, 3000);*/

            }
        });

        binding.llInviteFriendReferralCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager)getContext().getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", ShareText);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getContext(), "Copied", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        getActivity().setTitle("Invite");
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(InviteFriendViewModel.class);
    }


    private void referfunction() {
        // on below line we are starting its connection.
        referrerClient.startConnection(new InstallReferrerStateListener()
        {
            @Override
            public void onInstallReferrerSetupFinished(int responseCode) {
                // this method is called when install referer setup is finished.
                switch (responseCode) {
                    // we are using switch case to check the response.
                    case InstallReferrerClient.InstallReferrerResponse.OK:
                        // this case is called when the status is OK and
                        ReferrerDetails response = null;
                        try {
                            // on below line we are getting referrer details
                            // by calling get install referrer.
                            response = referrerClient.getInstallReferrer();

                            // on below line we are getting referrer url.
                            String referrerUrl = response.getInstallReferrer();
                            referrerUrl = referrerUrl ;

                            // on below line we are getting referrer click time.
                            long referrerClickTime = response.getReferrerClickTimestampSeconds();

                            // on below line we are getting app install time
                            long appInstallTime = response.getInstallBeginTimestampSeconds();

                            // on below line we are getting our time when
                            // user has used our apps instant experience.
                            boolean instantExperienceLaunched = response.getGooglePlayInstantParam();

                            // on below line we are getting our
                            // apps install referrer.
                            refrer = response.getInstallReferrer();

                            // on below line we are setting all detail to our text view.
                            //refrerTV.setText("Referrer is : \n" + referrerUrl + "\n" + "Referrer Click Time is : " + referrerClickTime + "\nApp Install Time : " + appInstallTime);
                            String str =  "Referrer is : \n" + referrerUrl + "\n" + "Referrer Click Time is : " + referrerClickTime + "\nApp Install Time : " + appInstallTime;
                            Log.d("String",str);

                            Bundle params = new Bundle();
                            params.putString("Referrer",str);
                            FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());
                            firebaseAnalytics.logEvent("referapi",params);


                        } catch (RemoteException e) {
                            // handling error case.
                            e.printStackTrace();
                        }
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED:
                        // API not available on the current Play Store app.
                        //Toast.makeText(getActivity(), "Feature not supported..", Toast.LENGTH_SHORT).show();
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE:
                        // Connection couldn't be established.
                        //Toast.makeText(getActivity(), "Fail to establish connection", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onInstallReferrerServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
                //Toast.makeText(getActivity(), "Service disconnected..", Toast.LENGTH_SHORT).show();
            }
        });
    }





    public void createlink()
    {
        Log.e("main","createlink");
        DynamicLink dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse("http://ifresh.co.in/"))
                .setDomainUriPrefix("tingtingu.page.link")
                // Open links with this app on Android
                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder().build())
                // Open links with com.example.ios on iOS
                .setIosParameters(new DynamicLink.IosParameters.Builder("com.example.ios").build())
                .buildDynamicLink();

        Uri dynamicLinkUri = dynamicLink.getUri();

        Log.e("main","long link"+ dynamicLink.getUri());

        Task<ShortDynamicLink> shortLinkTask = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLongLink(dynamicLink.getUri())
                .buildShortDynamicLink()
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {
                        // Short link created
                        Uri shortLink = task.getResult().getShortLink();
                        Uri flowchartLink = task.getResult().getPreviewLink();

                        Log.e("main_short","short link"+ shortLink);


                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, shortLink.toString());
                        sendIntent.setType("text/plain");

                        Intent shareIntent = Intent.createChooser(sendIntent, null);
                        startActivity(shareIntent);

                    } else {
                        Log.e("main_short_ex","short link"+ task.getException());
                        //Toast.makeText(getContext(), ""+task.getException(), Toast.LENGTH_SHORT).show();
                        // Error
                        // ...
                    }
                });




    }








}
