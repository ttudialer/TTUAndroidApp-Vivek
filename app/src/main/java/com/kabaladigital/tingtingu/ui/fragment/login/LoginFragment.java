package com.kabaladigital.tingtingu.ui.fragment.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.login_fragment, container, false);

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
                }
            }
        });

        return binding.getRoot();
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

