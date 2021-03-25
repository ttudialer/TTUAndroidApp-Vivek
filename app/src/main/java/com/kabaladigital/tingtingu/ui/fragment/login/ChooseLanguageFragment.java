package com.kabaladigital.tingtingu.ui.fragment.login;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.databinding.ChooseLanguageFragmentBinding;
import com.kabaladigital.tingtingu.ui.activity.LoginActivity;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.viewmodels.ChooseLanguageViewModel;

import java.util.Locale;

public class ChooseLanguageFragment extends Fragment {

    private ChooseLanguageFragmentBinding binding;
    private ChooseLanguageViewModel mViewModel;

    boolean showTrial = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.choose_language_fragment, container, false);

        ((LoginActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((LoginActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        init();

        binding.spinnerChooseLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0){
                    if (position == 1){
                        setLocale("en");
                    }
                    if (position == 2){
                        setLocale("hi");
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        binding.btnNext.setOnClickListener(v -> {
                if (binding.spinnerChooseLanguage.getSelectedItemPosition()!=0) {
                    nextFragment();
                }else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.choose_language), Toast.LENGTH_SHORT).show();
                }
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ChooseLanguageViewModel.class);
        mViewModel.MembershipCheck();
        getResponse();
    }

    private void getResponse() {
        mViewModel.getTrailMembershipCheckResponseLiveData().observe(getActivity(),
                TrailMembershipCheck -> {
                    if (TrailMembershipCheck != null ){
                        showTrial = TrailMembershipCheck.getResponse();
                    }
                });
    }

    private void init(){
        CharSequence[] entries = getResources().getTextArray(R.array.choose_language);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(binding.spinnerChooseLanguage.getContext()
                , android.R.layout.simple_spinner_item, entries);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        binding.spinnerChooseLanguage.setAdapter(adapter);
    }

    // Method to Change Language
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

    }

    // Show Next Fragment
     private void nextFragment(){
         NavDirections navDirections;
         if (showTrial){
             navDirections = ChooseLanguageFragmentDirections.actionChooseLanguageFragmentToMembershipMessageFragment();
         }else {
             navDirections = ChooseLanguageFragmentDirections.actionChooseLanguageFragmentToLoginFragment();
         }
         Navigation.findNavController(binding.getRoot()).navigate(navDirections);
    }

}
