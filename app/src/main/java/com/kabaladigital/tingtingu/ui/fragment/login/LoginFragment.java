package com.kabaladigital.tingtingu.ui.fragment.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.databinding.LoginFragmentBinding;
import com.kabaladigital.tingtingu.networking.RequestFormatter;
import com.kabaladigital.tingtingu.ui.activity.LoginActivity;
import com.kabaladigital.tingtingu.util.Installation;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.viewmodels.LoginViewModel;

public class LoginFragment extends Fragment {

    private String mCountryIso;
    private LoginFragmentBinding binding;
    private LoginViewModel mViewModel;
    private String langType;

    // variable for install referer client.
    InstallReferrerClient referrerClient;
    // creating an empty string for our referer.
    String refrer = "";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.login_fragment, container, false);
        // on below line we are building our install referrer client and building it.
        referrerClient = InstallReferrerClient.newBuilder(getContext()).build();


        ((LoginActivity)getActivity()).showHideLogo(View.GONE);

         langType=PreferenceUtils.getInstance().getString(R.string.pref_user_selected_language_key);
         if (langType.equals("hi")){
            SpannableString ss = new SpannableString("पंजीकरण करते ही मैं गोपनीयता नीति और उपयोग की शर्तों से सहमत हूं");
            ss.setSpan(new myClickableSpan(1), 20, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new myClickableSpan(2), 37, 52, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            binding.myTextView.setText(ss);
            binding.myTextView.setMovementMethod(LinkMovementMethod.getInstance());

         } else if (langType.equals("en")){
            SpannableString ss = new SpannableString("By signing up you agree to the Privacy Policy and Terms of use");
            ss.setSpan(new myClickableSpan(1), 31, 45, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new myClickableSpan(2), 50, 62, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            binding.myTextView.setText(ss);
            binding.myTextView.setMovementMethod(LinkMovementMethod.getInstance());

        }else {
             Toast.makeText(getContext(), "language!", Toast.LENGTH_SHORT).show();
         }

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.etMobileNumber.getText().toString().length() != 10){
                    Toast.makeText(getContext(), "Enter Valid mobile number", Toast.LENGTH_SHORT).show();
                } else {
                   submitMobileNumberAndCode(binding.etMobileNumber.getText().toString());
                    referfunction();
                }
            }
        });

        return binding.getRoot();
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
                            referrerUrl = referrerUrl +"/"+"456scorich";

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


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        // TODO: Use the ViewModel
    }

    private void submitMobileNumberAndCode(String mobileNumber){
        mViewModel.saveMobileNumber(RequestFormatter.jsonObjectLogin(mobileNumber,
                0,
                binding.etReferralCode.getText().toString()
                , Installation.id(getActivity())));
        getMobileNumber();
    }

    private void getMobileNumber(){
        mViewModel.getMobileNumberResponseLiveData().observe(this,
                mobileNumberResponse -> {
                    if (mobileNumberResponse != null ){
//                        if (mobileNumberResponse.getMessage() == null) {
//                            if (!mobileNumberResponse.getIsNew() && binding.etReferralCode.getText().toString().length()>1){
//                                Toast.makeText(getContext(), "Referral code is applicable only for new registration", Toast.LENGTH_SHORT).show();
//                            }
//                            if (!mobileNumberResponse.getHaveProfile() && !mobileNumberResponse.getRefCodeApplied()){
//                                Toast.makeText(getContext(), "Please Enter Valid Referral Code", Toast.LENGTH_SHORT).show();
//                            }else {
//                                if (!mobileNumberResponse.getHaveProfile() && mobileNumberResponse.getRefCodeApplied()){
//                                    Toast.makeText(getContext(), binding.etReferralCode.getText().toString()+ " Applied!", Toast.LENGTH_SHORT).show();
//                                }
                                PreferenceUtils.getInstance().putString(R.string.pref_user_mobile_number_key
                                        , binding.etMobileNumber.getText().toString());
                                nextFragment();
//                            }
//                        }else {
//                            Toast.makeText(getContext(), mobileNumberResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                        }

                    }
                });
    }

    private void nextFragment(){
        Navigation.findNavController(binding.getRoot())
                         .navigate(R.id.action_loginFragment_to_enterOTPFragment);
    }

     private class myClickableSpan extends ClickableSpan {
        private int pos;

        private myClickableSpan(int position) {
            this.pos=position;
        }

        @Override
        public void onClick(@NonNull View widget) {
            if (langType.equals("en")){
                if (pos==1){
                    Toast.makeText(getContext(), "Privacy Policy", Toast.LENGTH_LONG).show();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://tingtingu.com/ttu-privacy-policy.pdf"));
                    startActivity(browserIntent);
                }else {
                    Toast.makeText(getContext(), "Terms of use", Toast.LENGTH_LONG).show();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://tingtingu.com/ttu-terms-conditions.pdf"));
                    startActivity(browserIntent);
                }
            }else if (langType.equals("hi")){
                if (pos==1){
                    Toast.makeText(getContext(), "गोपनीयता नीति", Toast.LENGTH_LONG).show();
                     Intent browserIntent = new Intent(Intent.ACTION_VIEW,  Uri.parse("http://tingtingu.com/ttu-privacy-policy.pdf"));
                    startActivity(browserIntent);
                }else {
                    Toast.makeText(getContext(), "उपयोग की शर्तों", Toast.LENGTH_LONG).show();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://tingtingu.com/ttu-terms-conditions.pdf"));
                    startActivity(browserIntent);
                }
            }

        }

    }
}

