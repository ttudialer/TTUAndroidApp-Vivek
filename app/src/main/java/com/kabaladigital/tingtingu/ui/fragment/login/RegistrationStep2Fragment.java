package com.kabaladigital.tingtingu.ui.fragment.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.databinding.RegistrationStep2FragmentBinding;
import com.kabaladigital.tingtingu.models.UpdateProfileModel;
import com.kabaladigital.tingtingu.networking.ApiClient;
import com.kabaladigital.tingtingu.networking.ApiInterface;
import com.kabaladigital.tingtingu.networking.RequestFormatter;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.util.Utilities;
import com.kabaladigital.tingtingu.viewmodels.RegistrationStep2ViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationStep2Fragment extends Fragment {

    private RegistrationStep2FragmentBinding binding;
    private RegistrationStep2ViewModel mViewModel;
    private ArrayList<String> myLanguageList = new ArrayList<>();
    String emailId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null){
            if (bundle.containsKey("languageList")) {
                myLanguageList = getArguments().getStringArrayList("languageList");
            }

            if (bundle.containsKey("emailId")){
                emailId = getArguments().getString("emailId");
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.registration_step2_fragment, container, false);

        if (myLanguageList.size()<1){
            binding.etLanguageSelection.setHint(getResources().getString(R.string.reg_lang));
        }else {
            binding.etLanguageSelection.setText(""+myLanguageList);
        }


        if (emailId != null){
            binding.etEmail.setText(emailId);
        }

        binding.etLanguageSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilities.hideKeyboard(binding.getRoot(),getActivity());
                if (binding.etEmail.isFocusable()){
                    binding.etEmail.setFocusable(false);
                }

                Bundle bundle = new Bundle();
                bundle.putString("emailId", binding.etEmail.getText().toString());

                NavDirections navDirections = RegistrationStep2FragmentDirections
                                     .actionRegistrationStep2Fragment2ToChooseMultiLanguageFragment();
                Navigation.findNavController(binding.getRoot()).navigate(
                         R.id.action_registrationStep2Fragment2_to_chooseMultiLanguageFragment,bundle
                );
            }
        });

        binding.btnProvideLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections navDirections = RegistrationStep2FragmentDirections
                            .actionRegistrationStep2Fragment2ToCongoRegistrartionFragment();
                Navigation.findNavController(binding.getRoot()).navigate(navDirections);
            }
        });

        binding.btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callUpdateProfileTwo();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RegistrationStep2ViewModel.class);
    }

    private void nextFragment() {
        PreferenceUtils.getInstance().putBoolean(
                R.string.pref_is_registered_step_two_key,true);

        PreferenceUtils.getInstance().putString(
                R.string.pref_is_registered_step_two_email_value,binding.etEmail.getText().toString());
        PreferenceUtils.getInstance().putString(
                R.string.pref_is_registered_step_two_language_value,binding.etLanguageSelection.getText().toString());
        PreferenceUtils.getInstance().putString(
                R.string.pref_is_registered_step_two_profession_value,binding.spinnerProfession.getSelectedItem().toString());
        PreferenceUtils.getInstance().putString(
                R.string.pref_is_registered_step_two_education_value,binding.spinnerEducation.getSelectedItem().toString());

        NavDirections navDirections = RegistrationStep2FragmentDirections
                            .actionRegistrationStep2Fragment2ToCongoRegistrartionFragment();
        Navigation.findNavController(binding.getRoot()).navigate(navDirections);
    }

    private void callUpdateProfileTwo(){
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<UpdateProfileModel> call = apiInterface.updateProfile2(RequestFormatter
                .jsonUpdateProfile2(binding.etEmail.getText().toString(),
                        PreferenceUtils.getInstance().getString(R.string.pref_is_registered_name_value),
                        PreferenceUtils.getInstance().getInt(R.string.pref_is_registered_age_value),
                        PreferenceUtils.getInstance().getInt(R.string.pref_is_registered_gender_value),
                        PreferenceUtils.getInstance().getString(R.string.pref_is_registered_state_value),
                        PreferenceUtils.getInstance().getString(R.string.pref_is_registered_city_value),
                        PreferenceUtils.getInstance().getString(R.string.pref_is_registered_pincode),
                        myLanguageList,
                        binding.spinnerProfession.getSelectedItem().toString(),
                        binding.spinnerEducation.getSelectedItem().toString(),
                        binding.etAddressOne.getText().toString(),
                        binding.etAddressTwo.getText().toString(),
                        binding.etAddressThree.getText().toString()));

        call.enqueue(new Callback<UpdateProfileModel>() {
            @Override
            public void onResponse(Call<UpdateProfileModel> call,
                                       Response<UpdateProfileModel> response) {
                if (response.code() == 200) {
                    Toast.makeText(getActivity(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    nextFragment();
                }

            }

            @Override
            public void onFailure(Call<UpdateProfileModel> call, Throwable t) {
                Toast.makeText(getContext(), "onFailure= "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



}
