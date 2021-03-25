package com.kabaladigital.tingtingu.ui.fragment;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.databinding.SettingFragmentBinding;
import com.kabaladigital.tingtingu.models.ActiveInactiveModel;
import com.kabaladigital.tingtingu.networking.ApiClient;
import com.kabaladigital.tingtingu.networking.ApiInterface;
import com.kabaladigital.tingtingu.ui.activity.MainActivity;
import com.kabaladigital.tingtingu.util.Operator;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.viewmodels.SettingViewModel;

import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingFragment extends Fragment {

    private SettingViewModel mViewModel;
    private SettingFragmentBinding binding;
    private String langType;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.setting_fragment, container, false);
        langType = PreferenceUtils.getInstance().getString(R.string.pref_user_selected_language_key);

        try {
            String select_Operator = PreferenceUtils.getInstance()
                    .getString(R.string.pref_user_mobile_number_operator_name);
            if (langType.equals("en")){
                binding.tvChangeOperatorSubHeading.setText("Current Operator - " + select_Operator);
            }else {
                binding.tvChangeOperatorSubHeading.setText("वर्तमान ऑपरेटर - " + select_Operator);
            }
            binding.ivOperator.setImageResource(Operator.getOperatorIcon((select_Operator)));
        }catch (Exception e){
            if (langType.equals("en")){
                binding.tvChangeOperatorSubHeading.setText("Current Operator - Unknown");
            }else {
                binding.tvChangeOperatorSubHeading.setText("वर्तमान ऑपरेटर - Unknown");
            }
        }

        try {
            if (langType.equals("en")){
                binding.tvChangeLanguageSubHeading.setText("Current Language - " + returnLanguage(PreferenceUtils.getInstance()
                        .getString(R.string.pref_user_selected_language_key)));
            }else {
                binding.tvChangeLanguageSubHeading.setText("वर्तमान भाषा - " + returnLanguage(PreferenceUtils.getInstance()
                        .getString(R.string.pref_user_selected_language_key)));
            }
        }catch (Exception e){
            if (langType.equals("en")){
                binding.tvChangeLanguageSubHeading.setText("Current Language - Unknown");
            }else {
                binding.tvChangeLanguageSubHeading.setText("वर्तमान भाषा - Unknown");
            }
        }

        try {
            if (langType.equals("en")){
                binding.tvChangePlanTypeSubHeading.setText("Current - " + returnLanguage(PreferenceUtils.getInstance()
                        .getString(R.string.pref_user_mobile_number_type_key)));
            }else {
                binding.tvChangePlanTypeSubHeading.setText("वर्तमान - " + returnLanguage(PreferenceUtils.getInstance()
                        .getString(R.string.pref_user_mobile_number_type_key)));
            }
        }catch (Exception e){
            if (langType.equals("en")){
                binding.tvChangePlanTypeSubHeading.setText("Current - Unknown");
            }else {
                binding.tvChangePlanTypeSubHeading.setText("वर्तमान - Unknown");
            }
        }

        binding.tvChangeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLanguageSelectionDialog();
            }
        });

        binding.tvChangeLanguageSubHeading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLanguageSelectionDialog();
            }
        });

        binding.switchTemporaryDisable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableDisable();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SettingViewModel.class);
        mViewModel.hitProfileInformation();
        getProfileInformation();
    }

    private void getProfileInformation() {
        mViewModel.getProfileInformationModelLiveData().observe(getActivity(), new Observer<ActiveInactiveModel>() {
            @Override
            public void onChanged(ActiveInactiveModel activeInactiveModel) {
                if (activeInactiveModel != null){
                     binding.switchTemporaryDisable.setChecked(false);
                     binding.seekBarTemporaryDisable.setProgress(activeInactiveModel.getTempDisabled());
                }
            }
        });
    }

    private String returnLanguage(String lang){
        if (lang.equals("en")){
            return "English";
        }
        if (lang.equals("hi")){
            return "हिन्दी";
        }
        return "";
    }


    private void showLanguageSelectionDialog(){
        int selected = -1;
        if (PreferenceUtils.getInstance()
                .getString(R.string.pref_user_selected_language_key).equals("en")){
            selected = 0;
        }
        if (PreferenceUtils.getInstance()
                .getString(R.string.pref_user_selected_language_key).equals("hi")){
            selected = 1;
        }
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
        CharSequence items[] = new CharSequence[] {"English", "हिन्दी"};
        adb.setSingleChoiceItems(items, selected, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface d, int position) {
                if (position == 0){
                    setLocale("en");
                }
                if (position == 1){
                    setLocale("hi");
                }
            }
        });
        adb.setNegativeButton("Close", null);
        adb.setTitle("Choose Language");
        adb.show();
    }

    private void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics disMetrics = res.getDisplayMetrics();
        Configuration config = res.getConfiguration();
        config.locale = myLocale;
        res.updateConfiguration(config,disMetrics);
        PreferenceUtils.getInstance()
                .putString(R.string.pref_user_selected_language_key, lang);

        PreferenceUtils.getInstance()
                .putBoolean(R.string.pref_is_language_selected_key, true);

        if (langType.equals("en")){
            binding.tvChangeLanguageSubHeading.setText("Current Language - " + returnLanguage(PreferenceUtils.getInstance()
                    .getString(R.string.pref_user_selected_language_key)));
        }else {
            binding.tvChangeLanguageSubHeading.setText("वर्तमान भाषा - " + returnLanguage(PreferenceUtils.getInstance()
                    .getString(R.string.pref_user_selected_language_key)));
        }

    }

    private void enableDisable() {
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.changeStatus(binding.seekBarTemporaryDisable.getProgress());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                if (response.code() == 200) {
                    Toast.makeText(getActivity(), "Update basic profile!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "Error while updating status error code : " + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(), "onFailure= "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onResume() {
        getActivity().setTitle("Settings");
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        super.onResume();
    }
}
