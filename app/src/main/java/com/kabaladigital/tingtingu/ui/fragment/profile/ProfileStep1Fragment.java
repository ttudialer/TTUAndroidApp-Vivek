package com.kabaladigital.tingtingu.ui.fragment.profile;

import android.os.Bundle;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.database.AppDatabase;
import com.kabaladigital.tingtingu.database.DataRepository;
import com.kabaladigital.tingtingu.database.entity.StateCityModel;
import com.kabaladigital.tingtingu.databinding.ProfileStep1FragmentBinding;
import com.kabaladigital.tingtingu.models.ProfileInformationModel;
import com.kabaladigital.tingtingu.models.UpdateProfileModel;
import com.kabaladigital.tingtingu.networking.ApiClient;
import com.kabaladigital.tingtingu.networking.ApiInterface;
import com.kabaladigital.tingtingu.networking.RequestFormatter;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.util.Utilities;
import com.kabaladigital.tingtingu.viewmodels.ProfileStep1ViewModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileStep1Fragment extends Fragment {
    private ProfileStep1FragmentBinding binding;
    private ProfileStep1ViewModel mViewModel;
    private List<String> age = new ArrayList<String>();

    private List<StateCityModel> stateCityModelList;
    private DataRepository mRepository;
    private List<String> allStateLists = new ArrayList<>();
    private List<String> allCityListStateWiseLists = new ArrayList<>();
    private ArrayList<String> language = new ArrayList<>();

    String getCity;

    public static ProfileStep1Fragment newInstance() {
        return new ProfileStep1Fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.profile_step1_fragment, container, false);

        mRepository = DataRepository.getInstance(AppDatabase.getDatabase(getActivity()));
        stateCityModelList = mRepository.getStateCityUri();

        allStateLists.clear();
        allStateLists.add("Select State");
        List<String> tempState = new ArrayList<>();
        tempState = mRepository.getAllStateList();

        for (int a =0 ; a<tempState.size(); a++){
            allStateLists.add(tempState.get(a));
        }

        ArrayAdapter<String> adapterState = new ArrayAdapter<String>(binding.spinnerState.getContext(),
                android.R.layout.simple_spinner_item, allStateLists);
        adapterState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerState.setAdapter(adapterState);

        for (int i =18; i <= 100; i++){
            age.add(String.valueOf(i));
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                binding.spinnerAge.getContext(), android.R.layout.simple_spinner_item, age);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerAge.setAdapter(spinnerArrayAdapter);


        binding.btnUpdateBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()){
                    updateProfileOne();
                    PreferenceUtils.getInstance().putString(
                            R.string.pref_is_registered_name_value,binding.etFullName.getText().toString());
                    PreferenceUtils.getInstance().putInt(
                            R.string.pref_is_registered_age_value,Integer.valueOf(binding.spinnerAge.getSelectedItem().toString()));
                    PreferenceUtils.getInstance().putInt(
                            R.string.pref_is_registered_gender_value,binding.spinnerGender.getSelectedItemPosition() - 1);
                    PreferenceUtils.getInstance().putString(
                            R.string.pref_is_registered_state_value,binding.spinnerState.getSelectedItem().toString());
                    PreferenceUtils.getInstance().putString(
                            R.string.pref_is_registered_city_value,binding.spinnerCity.getSelectedItem().toString());
                    PreferenceUtils.getInstance().putString(
                            R.string.pref_is_registered_pincode,binding.etPinCode.getText().toString());

                }
            }
        });

        binding.spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                allCityListStateWiseLists.clear();
                allCityListStateWiseLists.add("Select District");
                if (position != 0) {

                    allCityListStateWiseLists = mRepository.getCityListStateWise(binding.spinnerState.getSelectedItem().toString());

                    // todo city spinner
                    ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(binding.spinnerCity.getContext(),
                            android.R.layout.simple_spinner_item, allCityListStateWiseLists);
                    adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spinnerCity.setAdapter(adapterCity);
                    binding.spinnerCity.setSelection(allCityListStateWiseLists.indexOf(getCity));
                }else {
                    ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(binding.spinnerCity.getContext(),
                            android.R.layout.simple_spinner_item, allCityListStateWiseLists);
                    adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spinnerCity.setAdapter(adapterCity);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return binding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfileStep1ViewModel.class);
        mViewModel.hitProfileInformation();
        getProfileInformation();
    }

    private void getProfileInformation() {
        mViewModel.getProfileInformationModelLiveData().observe(getActivity(), new Observer<ProfileInformationModel>() {
            @Override
            public void onChanged(ProfileInformationModel profileInformationModel) {
                if (profileInformationModel != null){
                    binding.etMobileNumber.setText(String.valueOf(profileInformationModel.getMobileInfo().getMobileNumber()));
                    binding.etFullName.setText(profileInformationModel.getFullName());
                    binding.etPinCode.setText(String.valueOf(profileInformationModel.getPincode()));

                    String getAge = String.valueOf(profileInformationModel.getAge());
                    binding.spinnerAge.setSelection(age.indexOf(getAge));

                    PreferenceUtils.getInstance().putString(
                            R.string.pref_refer_code_key,profileInformationModel.getRefer().getReferId());

                    try {
                        int getGender = profileInformationModel.getGender();
                        binding.spinnerGender.setSelection(getGender+1);
                    }catch (Exception e){

                    }

                    String getState = profileInformationModel.getState();
                    binding.spinnerState.setSelection(allStateLists.indexOf(getState));

                    getCity = profileInformationModel.getCity();

                    if (profileInformationModel.getKnownLanguages()!=null && profileInformationModel.getKnownLanguages().size()>0){
                        language.addAll(profileInformationModel.getKnownLanguages());
                    }

                }
            }
        });
    }

    private void updateProfileOne() {
        Gson gson = new Gson();
        String json = PreferenceUtils.getInstance().getString(
                          R.string.pref_is_registered_step_two_language_value);

        if (json != null && !json.equals("[]")){
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            language = gson.fromJson(json, type);
        }


        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<UpdateProfileModel> call = apiInterface.updateProfile(RequestFormatter
                .jsonUpdateProfile(PreferenceUtils.getInstance().getString(
                                           R.string.pref_is_registered_step_two_email_value),
                        binding.etFullName.getText().toString(),
                        Integer.parseInt(binding.spinnerAge.getSelectedItem().toString()),
                        binding.spinnerGender.getSelectedItemPosition() - 1,
                        binding.spinnerState.getSelectedItem().toString(),
                        binding.spinnerCity.getSelectedItem().toString(),
                        binding.etPinCode.getText().toString(),
                        language,
                        PreferenceUtils.getInstance().getString(
                                    R.string.pref_is_registered_step_two_profession_value),
                        PreferenceUtils.getInstance().getString(
                                    R.string.pref_is_registered_step_two_education_value)));

        call.enqueue(new Callback<UpdateProfileModel>() {
            @Override
            public void onResponse(Call<UpdateProfileModel> call,
                                   Response<UpdateProfileModel> response) {
                if (response.code() == 200) {
                    Toast.makeText(getActivity(), "Update basic profile!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    mViewModel.hitProfileInformation();
                    getProfileInformation();
                }
            }
            @Override
            public void onFailure(Call<UpdateProfileModel> call, Throwable t) {
                Toast.makeText(getContext(), "onFailure= "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean validation() {
        String name=binding.etFullName.getText().toString();
        String age = binding.spinnerAge.getSelectedItem().toString();
        String gender=binding.spinnerGender.getSelectedItem().toString();
        String state=binding.spinnerState.getSelectedItem().toString();
        String city=binding.spinnerCity.getSelectedItem().toString();
        String pincode=binding.etPinCode.getText().toString();
        String ageValue= null;

        if (!Utilities.isNetworkAvailable(getActivity())){
            showSnackbar("No Internet Connection");
            return false;
        }

        if (name.length() < 4){
            //binding.etFullName.setError("please enter full name");
            showSnackbar("please enter full name");
            return false;
        }

        if (age.equals("Age")){
            Snackbar.make(binding.getRoot(),"please select valid age"
                    ,Snackbar.LENGTH_SHORT).show();
            return false;
        }

        if (gender.equals("Gender")){
            Snackbar.make(binding.getRoot(),"please choose valid gender"
                    ,Snackbar.LENGTH_SHORT).show();
            return false;
        }

        if (pincode.length()!=6){
            //binding.etPinCode.setError("please enter valid pincode");
            showSnackbar("please enter valid pincode");
            return false;
        }

        if (state.equals("Select State")){
            showSnackbar("please select valid state");
            return false;
        }

        if (city.equals("Select District")){
            showSnackbar("please select valid District");
            return false;
        }

        return true;
    }

    private void showSnackbar(String errorMsg){
        Snackbar.make(binding.getRoot(),errorMsg,Snackbar.LENGTH_SHORT).show();
    }
}
