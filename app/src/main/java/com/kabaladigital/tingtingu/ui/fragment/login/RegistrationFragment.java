package com.kabaladigital.tingtingu.ui.fragment.login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

import com.google.android.material.snackbar.Snackbar;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.database.AppDatabase;
import com.kabaladigital.tingtingu.database.DataRepository;
import com.kabaladigital.tingtingu.database.entity.StateCityModel;
import com.kabaladigital.tingtingu.databinding.RegistrationFragmentBinding;
import com.kabaladigital.tingtingu.models.UpdateProfileModel;
import com.kabaladigital.tingtingu.networking.ApiClient;
import com.kabaladigital.tingtingu.networking.ApiInterface;
import com.kabaladigital.tingtingu.networking.RequestFormatter;
import com.kabaladigital.tingtingu.ui.activity.LoginActivity;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.util.Utilities;
import com.kabaladigital.tingtingu.viewmodels.RegistrationViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationFragment extends Fragment {

    private RegistrationFragmentBinding binding;
    private RegistrationViewModel mViewModel;

    private List<String> allStateLists = new ArrayList<>();
    private List<String> allCityListStateWiseLists = new ArrayList<>();

    private List<StateCityModel> stateCityModelList;
    private DataRepository mRepository;

    private List<String> age = new ArrayList<String>();
    String selected;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.registration_fragment, container, false);

        try {
            ((LoginActivity)getActivity()).showHideLogo(View.VISIBLE);
        }catch (Exception e){

        }

        mRepository = DataRepository.getInstance(AppDatabase.getDatabase(getActivity()));
        stateCityModelList = mRepository.getStateCityUri();

        allStateLists.clear();
        allStateLists.add("Select State");
        List<String> tempState = new ArrayList<>();
        tempState = mRepository.getAllStateList();

        for (int a =0 ; a<tempState.size(); a++){
            allStateLists.add(tempState.get(a));
        }

        binding.etMobileNumber.setText("+91 "+ PreferenceUtils.getInstance().getString(R.string.pref_user_mobile_number_key));

        age.add("Age");
        for (int i=18; i<=100;i++) {
            age.add(String.valueOf(i));
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                    binding.spinnerAge.getContext(), android.R.layout.simple_spinner_item, age);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerAge.setAdapter(spinnerArrayAdapter);


        binding.spinnerAge.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Utilities.hideKeyboard(binding.getRoot(),getActivity());
                return false;
            }
        });

        ArrayAdapter<String> adapterState = new ArrayAdapter<String>(binding.spinnerState.getContext(),
                         android.R.layout.simple_spinner_item, allStateLists);
        adapterState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerState.setAdapter(adapterState);

        binding.btnRegistrationNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilities.hideKeyboard(binding.getRoot(),getActivity());
                if (validation()){
                    updateProfileOne();
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
                    ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(binding.spinnerCity.getContext(),
                            android.R.layout.simple_spinner_item, allCityListStateWiseLists);
                    adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spinnerCity.setAdapter(adapterCity);
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
        mViewModel = ViewModelProviders.of(this).get(RegistrationViewModel.class);
        // TODO: Use the ViewModel
    }

    private boolean validation() {
        String name=binding.etFullName.getText().toString();
        String age = binding.spinnerAge.getSelectedItem().toString();
        String gender=binding.spinnerGender.getSelectedItem().toString();
        String state=binding.spinnerState.getSelectedItem().toString();
        String city=binding.spinnerCity.getSelectedItem().toString();
        String pincode=binding.etPinCode.getText().toString();
        String ageValue= null;

        if (name.length() < 4){
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
            showSnackbar("please select valid State");
            return false;
        }

        if (city.equals("Select District")){
            showSnackbar("please select valid District");
            return false;
        }

        return true;
    }

    private void nextFragment() {
        PreferenceUtils.getInstance().putBoolean(
                            R.string.pref_is_registered_step_one_key,true);
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

        NavDirections navDirections = RegistrationFragmentDirections
                .actionRegistrationFragmentToRegistrationStep2Fragment2();
        Navigation.findNavController(binding.getRoot()).navigate(navDirections);
    }

    private void showSnackbar(String errorMsg){
        Snackbar.make(binding.getRoot(),errorMsg,Snackbar.LENGTH_SHORT).show();
    }


    private void updateProfileOne() {
        ArrayList<String> language=new ArrayList<String>();

        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<UpdateProfileModel> call = apiInterface.updateProfile(RequestFormatter
                      .jsonUpdateProfile("",
                                        binding.etFullName.getText().toString(),
                                        Integer.parseInt(binding.spinnerAge.getSelectedItem().toString()),
                                        binding.spinnerGender.getSelectedItemPosition() - 1,
                                        binding.spinnerState.getSelectedItem().toString(),
                                        binding.spinnerCity.getSelectedItem().toString(),
                                        binding.etPinCode.getText().toString(),
                                        language,
                                       "",
                                       ""));

        call.enqueue(new Callback<UpdateProfileModel>() {
            @Override
            public void onResponse(Call<UpdateProfileModel> call,
                                           Response<UpdateProfileModel> response) {
                if (response.code() == 200) {
                    Toast.makeText(getContext(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                  nextFragment();
               }
            }

            @Override
            public void onFailure(Call<UpdateProfileModel> call, Throwable t) {
                Log.d("onFailure",t.getMessage());
                Toast.makeText(getContext(), "onFailure= "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        }

}
