package com.kabaladigital.tingtingu.ui.fragment.login;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.databinding.SplashScreenFragmentBinding;
import com.kabaladigital.tingtingu.ui.activity.LoginActivity;
import com.kabaladigital.tingtingu.ui.activity.MainActivity;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.viewmodels.SplashScreenViewModel;

import java.util.Locale;

public class SplashScreenFragment extends Fragment {

    private SplashScreenViewModel mViewModel;
    private SplashScreenFragmentBinding binding;

     int stopPosition;

     boolean is_language_selected,
            is_otp_verify_key,
            is_operator_detail_filled,
            is_registration_stepOne,
            is_registration_stepTwo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (PreferenceUtils.getInstance()
                .getBoolean(R.string.pref_is_login_key)){

            setLocale(PreferenceUtils.getInstance()
                    .getString(R.string.pref_user_selected_language_key));

            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.splash_screen_fragment
                 , container, false);



        ((LoginActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((LoginActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);

        MediaController mediaController= new MediaController(getContext());
        mediaController.setAnchorView(binding.video);

        String path = "android.resource://"+ getActivity().getPackageName() +"/" + R.raw.merged;
        Uri u = Uri.parse(path);
        binding.video.setVideoURI(u);
        binding.video.start();

        binding.video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);

                binding.video.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mediaPlayer.isPlaying()){
                            mediaPlayer.pause();
                        }else {
                            mediaPlayer.start();
                        }
                    }
                });


            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextFragment();
            }
        });

        return binding.getRoot();
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SplashScreenViewModel.class);
    }



    private void nextFragment(){
        NavDirections navDirections = SplashScreenFragmentDirections.actionSplashFragmentToChooseLanguageFragment();
        Navigation.findNavController(binding.getRoot()).navigate(navDirections);
    }

    @Override
    public void onPause() {
        super.onPause();
        stopPosition=binding.video.getCurrentPosition();
        if (binding.video.isPlaying()){
            binding.video.pause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (binding.video != null) {
            binding.video.seekTo(stopPosition);
        }

        is_language_selected  = PreferenceUtils.getInstance()
                    .getBoolean(R.string.pref_is_language_selected_key);
        is_otp_verify_key  = PreferenceUtils.getInstance()
                .getBoolean(R.string.pref_is_otp_verify_key);
        is_operator_detail_filled  = PreferenceUtils.getInstance()
                .getBoolean(R.string.pref_is_operator_detail_filled_key);
        is_registration_stepOne  = PreferenceUtils.getInstance()
                .getBoolean(R.string.pref_is_registered_step_one_key);
        is_registration_stepTwo  = PreferenceUtils.getInstance()
                .getBoolean(R.string.pref_is_registered_step_two_key);

        if (is_language_selected){

            if (is_otp_verify_key){
                if (is_operator_detail_filled){
                    if (is_registration_stepOne){
                         if (!is_registration_stepTwo){
                             NavDirections navDirection_otpq = SplashScreenFragmentDirections.actionSplashFragmentToRegistrationStep2Fragment2();
                             Navigation.findNavController(binding.getRoot()).navigate(navDirection_otpq);
                         }
                    }else {
                        NavDirections navDirection_otpq = SplashScreenFragmentDirections.actionSplashFragmentToRegistrationFragment();
                        Navigation.findNavController(binding.getRoot()).navigate(navDirection_otpq);
                    }
                }else {
                    NavDirections navDirection_otpq = SplashScreenFragmentDirections.actionSplashFragmentToOperatorDetailFragment();
                    Navigation.findNavController(binding.getRoot()).navigate(navDirection_otpq);
                }
            }else {
                NavDirections navDirections = SplashScreenFragmentDirections.actionSplashFragmentToLoginFragment();
                Navigation.findNavController(binding.getRoot()).navigate(navDirections);
            }
        }
    }

    private void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics disMetrics = res.getDisplayMetrics();
        Configuration config = res.getConfiguration();
        config.locale = myLocale;
        res.updateConfiguration(config,disMetrics);
    }
}
