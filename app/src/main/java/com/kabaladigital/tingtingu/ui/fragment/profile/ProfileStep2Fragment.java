package com.kabaladigital.tingtingu.ui.fragment.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.databinding.ProfileStep2FragmentBinding;
import com.kabaladigital.tingtingu.models.ListHolderModel;
import com.kabaladigital.tingtingu.models.ProfileInformationModel;
import com.kabaladigital.tingtingu.models.UpdateProfileModel;
import com.kabaladigital.tingtingu.networking.ApiClient;
import com.kabaladigital.tingtingu.networking.ApiInterface;
import com.kabaladigital.tingtingu.networking.RequestFormatter;
import com.kabaladigital.tingtingu.ui.fragment.login.ChooseProfileLanguageFragment;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.util.Utilities;
import com.kabaladigital.tingtingu.viewmodels.ProfileStep2ViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileStep2Fragment extends Fragment {

    private ProfileStep2FragmentBinding binding;
    private ProfileStep2ViewModel mViewModel;

    private String langType;

    private ArrayList<String> myLanguageList = new ArrayList<>();

    private ArrayList<String> educationList = new ArrayList<>();
    private ArrayList<String> educationList2 = new ArrayList<>();

    private ArrayList<String> professionList = new ArrayList<>();
    private ArrayList<String> professionList2 = new ArrayList<>();


    public static ProfileStep2Fragment newInstance() {
        return new ProfileStep2Fragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.profile_step2_fragment, container, false);
        binding.etLanguageSelection.setHint(getResources().getString(R.string.reg_lang));


        langType = PreferenceUtils.getInstance().getString(R.string.pref_user_selected_language_key);

        binding.etLanguageSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilities.hideKeyboard(binding.getRoot(),getActivity());
                if (binding.etEmail.isFocusable()){
                    binding.etEmail.setFocusable(false);
                }
                Intent intent = new Intent(getActivity(), ChooseProfileLanguageFragment.class);
                startActivity(intent);
            }
        });

        binding.btnUpdateOptional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Utilities.isNetworkAvailable(getActivity())){
                    showSnackbar("No Internet Connection");
                }else {
                    callUpdateProfileTwo();

                    Gson gson = new Gson();
                    String json = gson.toJson(myLanguageList);
                    PreferenceUtils.getInstance().putString(
                            R.string.pref_is_registered_step_two_language_value,json);

                    PreferenceUtils.getInstance().putString(
                            R.string.pref_is_registered_step_two_email_value , binding.etEmail.getText().toString());
                    PreferenceUtils.getInstance().putString(
                            R.string.pref_is_registered_step_two_profession_value , binding.spinnerProfession.getSelectedItem().toString());
                    PreferenceUtils.getInstance().putString(
                            R.string.pref_is_registered_step_two_education_value , binding.spinnerEducation.getSelectedItem().toString());
                }
            }
        });

        educationList.clear();
        professionList.clear();
        if (langType.equals("en")) {
            educationList.add("Choose education");
            educationList.add("Non-metric");
            educationList.add("Metric");
            educationList.add("Inter");
            educationList.add("Graduation");
            educationList.add("Post Graduation");
            educationList.add("Others");

            professionList.add("Choose profession");
            professionList.add("Student");
            professionList.add("Businessman");
            professionList.add("Politician");
            professionList.add("Private employee");
            professionList.add("Govt. employee");
            professionList.add("Others");

        } else if (langType.equals("hi")) {
            educationList.add("शिक्षा चुनें");
            educationList.add("गैर मीट्रिक");
            educationList.add("मीट्रिक");
            educationList.add("इंटर");
            educationList.add("स्नातक");
            educationList.add("स्नातकोत्तर");
            educationList.add("अन्य");

            professionList.add("Choose profession");
            professionList.add("Student");
            professionList.add("Businessman");
            professionList.add("Politician");
            professionList.add("Private employee");
            professionList.add("Govt. employee");
            professionList.add("other");
        }
        for (int a = 0; a < educationList.size(); a++) {
            educationList2.add(educationList.get(a));
        }

        for (int a = 0; a < professionList.size(); a++) {
            professionList2.add(professionList.get(a));
        }

        ArrayAdapter<String> adapterEducation = new ArrayAdapter<String>(binding.spinnerEducation.getContext(),
                android.R.layout.simple_spinner_item, educationList2);
        adapterEducation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerEducation.setAdapter(adapterEducation);

        ArrayAdapter<String> adapterProfession = new ArrayAdapter<String>(binding.spinnerProfession.getContext(),
                android.R.layout.simple_spinner_item, professionList2);
        adapterProfession.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerProfession.setAdapter(adapterProfession);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfileStep2ViewModel.class);
        mViewModel.hitProfileInformation();
        getProfileInformation();
    }


    @Override
    public void onResume() {
        super.onResume();
        if (ListHolderModel.getList() != null && ListHolderModel.getList().size()>0) {
            myLanguageList.clear();
            myLanguageList.addAll(ListHolderModel.getList());
            binding.etLanguageSelection.setText(myLanguageList.toString());
        }
    }

    private void getProfileInformation() {
        mViewModel.getProfileInformationModelLiveData().observe(getActivity(), new Observer<ProfileInformationModel>() {
            @Override
            public void onChanged(ProfileInformationModel profileInformationModel) {
                if (profileInformationModel != null) {
                    binding.etEmail.setText(profileInformationModel.getEmail());

                    if (profileInformationModel.getKnownLanguages()!=null && profileInformationModel.getKnownLanguages().size()>0){
                        myLanguageList.clear();
                        myLanguageList.addAll(profileInformationModel.getKnownLanguages());
                        binding.etLanguageSelection.setText(myLanguageList.toString());
                    }

                    String getEducation = profileInformationModel.getEducation();
                    binding.spinnerEducation.setSelection(educationList2.indexOf(getEducation));

                    String getProfession = profileInformationModel.getProfession();
                    binding.spinnerProfession.setSelection(professionList2.indexOf(getProfession));

                    if (profileInformationModel.getAddress()!= null){
                        if (profileInformationModel.getAddress().getAddress1() != null)
                        binding.etAddressOne.setText(profileInformationModel.getAddress().getAddress1());

                        if (profileInformationModel.getAddress().getAddress2() != null)
                            binding.etAddressTwo.setText(profileInformationModel.getAddress().getAddress2());

                        if (profileInformationModel.getAddress().getAddress3() != null)
                            binding.etAddressThree.setText(profileInformationModel.getAddress().getAddress3());
                    }
                }
            }
        });
    }

    private void callUpdateProfileTwo() {
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
                    Toast.makeText(getActivity(), "Update Optional profile!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    mViewModel.hitProfileInformation();
                    getProfileInformation();
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileModel> call, Throwable t) {
                Toast.makeText(getContext(), "onFailure= " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showSnackbar(String errorMsg){
        Snackbar.make(binding.getRoot(),errorMsg,Snackbar.LENGTH_SHORT).show();
    }
}
