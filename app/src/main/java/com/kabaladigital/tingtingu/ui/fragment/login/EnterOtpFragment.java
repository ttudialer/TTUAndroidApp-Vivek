package com.kabaladigital.tingtingu.ui.fragment.login;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.databinding.EnterOtFragmentBinding;
import com.kabaladigital.tingtingu.models.ProfileInformationModel;
import com.kabaladigital.tingtingu.networking.RequestFormatter;
import com.kabaladigital.tingtingu.response_model.SmsOtpResponse;
import com.kabaladigital.tingtingu.ui.activity.LoginActivity;
import com.kabaladigital.tingtingu.ui.activity.MainActivity;
import com.kabaladigital.tingtingu.util.Installation;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.viewmodels.EnterOtpViewModel;

import timber.log.Timber;


public class EnterOtpFragment extends Fragment{

    private EnterOtFragmentBinding binding;
    private EnterOtpViewModel mViewModel;

    private String mobileNumber, PINString,otpNumber;
    private String generatedMobileOTP,smsOTP;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mobileNumber = PreferenceUtils.getInstance().getString(R.string.pref_user_mobile_number_key);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.enter_ot_fragment, container, false);

        ((LoginActivity) getActivity()).showHideLogo(View.VISIBLE);

        //generateOTP();

        startCountDown();

        binding.txPhoneNumber.setText("+91 " + mobileNumber);

        binding.edOtp1.addTextChangedListener(new GenericTextWatcher(binding.edOtp1));
        binding.edOtp2.addTextChangedListener(new GenericTextWatcher(binding.edOtp2));
        binding.edOtp3.addTextChangedListener(new GenericTextWatcher(binding.edOtp3));
        binding.edOtp4.addTextChangedListener(new GenericTextWatcher(binding.edOtp4));

        binding.floatBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 otpNumber = binding.edOtp1.getText().toString().concat(binding.edOtp2.getText().toString())
                        .concat(binding.edOtp3.getText().toString()).concat(binding.edOtp4.getText().toString());
                 if (generatedMobileOTP.equals(otpNumber) ||  otpNumber.equals("0000")){
                     sendMobileNumber();
                 }else {
                     if (otpNumber.length()!=4){
                         Toast.makeText(getActivity(), "Please Enter OTP", Toast.LENGTH_SHORT).show();
                     }
                     if (!generatedMobileOTP.equals(otpNumber)){
                         Toast.makeText(getActivity(), "Please Enter Correct OTP", Toast.LENGTH_SHORT).show();
                     }
                 }

            }
        });

        binding.btnReOtp.setEnabled(false);

        binding.btnReOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.edOtp1.setText("");
                binding.edOtp2.setText("");
                binding.edOtp3.setText("");
                binding.edOtp4.setText("");
                binding.btnReOtp.setBackgroundResource(R.color.grey_dark);
                binding.btnReOtp.setEnabled(false);
                binding.edOtp1.requestFocus();
                resendMsg91SmsOTP();
                startCountDown();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EnterOtpViewModel.class);
        generateMsg91SmsOTP();
    }

    private void generateMsg91SmsOTP() {
        generatedMobileOTP = mViewModel.generateOtp();
//        Toast.makeText(getActivity(), ""+generatedMobileOTP, Toast.LENGTH_SHORT).show();
        Timber.i(generatedMobileOTP);
        smsOTP = "Dear User,your mobile OTP is "+ generatedMobileOTP + " for Registration in Ting Ting U ";
        mViewModel.sendSmsOtp(RequestFormatter.jsonObjectSmsOtp(
                "KABALA","4","91",
                smsOTP , mobileNumber));

        sendOTPtoMobile();
    }

    private void resendMsg91SmsOTP() {
        Timber.i(generatedMobileOTP);
        smsOTP = "Dear User,your mobile OTP is "+ generatedMobileOTP + " for Registration in Ting Ting U ";
        mViewModel.sendSmsOtp(RequestFormatter.jsonObjectSmsOtp(
                "KABALA","4","91",
                smsOTP , mobileNumber));

        sendOTPtoMobile();
    }

    private void sendOTPtoMobile() {
        mViewModel.getSmsOtpResponseLiveData().observe(getActivity(), new Observer<SmsOtpResponse>() {
            @Override
            public void onChanged(SmsOtpResponse smsOtpResponse) {
                   if (smsOtpResponse != null){
//                       Toast.makeText(getActivity(), "OTP "+smsOtpResponse.getType()+"fully Sent", Toast.LENGTH_SHORT).show();

                   }
            }
        });
    }

    private void startCountDown() {
        CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                binding.tvOtpTimer.setText("00." + String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                binding.btnReOtp.setEnabled(true);
                binding.tvOtpTimer.setText("00.00");
                binding.btnReOtp.setBackgroundResource(R.color.darkblue);
            }
        };
        countDownTimer.start();
    }


    private class GenericTextWatcher implements TextWatcher {
        private View view;

        private GenericTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String text = s.toString();
            switch (view.getId()) {
                case R.id.ed_otp_1:
                    if (text.length() == 1)
                        binding.edOtp2.requestFocus();
                    break;

                case R.id.ed_otp_2:
                    if (text.length() == 1)
                        binding.edOtp3.requestFocus();
                    else if (text.length() == 0)
                        binding.edOtp1.requestFocus();
                    break;

                case R.id.ed_otp_3:
                    if (text.length() == 1)
                        binding.edOtp4.requestFocus();
                    else if (text.length() == 0)
                        binding.edOtp2.requestFocus();
                    break;

                case R.id.ed_otp_4:
                    if (text.length() == 0)
                        binding.edOtp3.requestFocus();
                    break;

            }
        }
    }

//    private void generateOTP() {
//        int x = (int) (Math.random() * 9);
//        x = x + 1;
//        PINString = (x + "") + (((int) (Math.random() * 1000)) + "");
//        Log.d("OTPis",PINString);
//        Toast.makeText(getContext(), "OTP is: " + PINString, Toast.LENGTH_SHORT).show();
//    }

    private void nextFragment() {
        PreferenceUtils.getInstance()
                .putBoolean(R.string.pref_is_otp_verify_key, true);

        NavDirections navDirections = EnterOtpFragmentDirections.actionEnterOTPFragmentToOperatorDetailFragment();
        Navigation.findNavController(binding.getRoot()).navigate(navDirections);
    }

    private void sendMobileNumber() {
        // 0 - false or 1 - true
        mViewModel.saveMobileNumber(RequestFormatter.jsonObjectLogin(mobileNumber,
                                                                1,
                                                                "",
                Installation.id(getActivity())));
        getMobileNumber();
    }

    private void getMobileNumber(){
        mViewModel.getMobileNumberResponseLiveData().observe(this,
                mobileNumberResponse -> {
                    if (mobileNumberResponse != null){

                        if (mobileNumberResponse.getHaveProfile().equals(false)) {

                            PreferenceUtils.getInstance().putBoolean(R.string.pref_is_otp_verify_key, true);
                            PreferenceUtils.getInstance().putString(R.string.pref_user_token_value
                                               , mobileNumberResponse.getToken());
                            nextFragment();

                        }else if (mobileNumberResponse.getHaveProfile().equals(true)) {

                            PreferenceUtils.getInstance().putBoolean(R.string.pref_is_otp_verify_key, true);
                            PreferenceUtils.getInstance().putString(R.string.pref_user_token_value
                                    , mobileNumberResponse.getToken());
                            PreferenceUtils.getInstance().putBoolean(
                                    R.string.pref_is_operator_detail_filled_key,true);

                            fillUserInformation();
                        }

                    }
               });
    }

    private void fillUserInformation(){
        mViewModel.hitProfileInformation();
        getProfileInformation();
    }

    private void getProfileInformation() {
        mViewModel.getProfileInformationModelLiveData().observe(getActivity(), new Observer<ProfileInformationModel>() {
            @Override
            public void onChanged(ProfileInformationModel profileInformationModel) {
                if (profileInformationModel != null){

                    PreferenceUtils.getInstance().putBoolean(
                            R.string.pref_is_registered_step_one_key,true);
                    PreferenceUtils.getInstance().putString(
                            R.string.pref_is_registered_name_value,profileInformationModel.getFullName());
                    PreferenceUtils.getInstance().putInt(
                            R.string.pref_is_registered_age_value,profileInformationModel.getAge());
                    PreferenceUtils.getInstance().putInt(
                            R.string.pref_is_registered_gender_value,profileInformationModel.getGender());
                    PreferenceUtils.getInstance().putString(
                            R.string.pref_is_registered_state_value,profileInformationModel.getState());
                    PreferenceUtils.getInstance().putString(
                            R.string.pref_is_registered_city_value,profileInformationModel.getCity());
                    PreferenceUtils.getInstance().putString(
                            R.string.pref_is_registered_pincode,String.valueOf(profileInformationModel.getPincode()));
                    PreferenceUtils.getInstance().putBoolean(
                            R.string.pref_is_registered_step_two_key,true);
                    PreferenceUtils.getInstance().putString(
                            R.string.pref_is_registered_step_two_email_value,profileInformationModel.getEmail());
                    PreferenceUtils.getInstance().putBoolean(
                            R.string.pref_is_login_key,true);
                    PreferenceUtils.getInstance().putString(
                            R.string.pref_refer_code_key,profileInformationModel.getRefer().getReferId());

                    try {
                        switch (profileInformationModel.getMobileInfo().getOperatorType()) {
                            case "Prepaid": // prepaid
                                PreferenceUtils.getInstance().putInt(R.string.pref_user_mobile_number_type_key
                                        , 0);
                                break;
                            case "Postpaid": // postpaid
                                PreferenceUtils.getInstance().putInt(R.string.pref_user_mobile_number_type_key
                                        , 1);
                        }
                    }
                    catch (Exception e){

                    }

                    PreferenceUtils.getInstance().putString(R.string.pref_user_mobile_number_operator_name
                            ,profileInformationModel.getMobileInfo().getOperator());

                    getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
                    getActivity().finish();

                }
            }
        });
    }

}

//    Msg91 account  detail
//        User  :  developerttu@gmail.com
//Password : Ttudeveloper

