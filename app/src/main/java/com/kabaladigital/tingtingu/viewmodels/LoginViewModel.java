package com.kabaladigital.tingtingu.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonObject;
import com.kabaladigital.tingtingu.networking.MobileNumberRepository;
import com.kabaladigital.tingtingu.response_model.MobileNumberResponse;

public class LoginViewModel extends ViewModel {

    private LiveData<MobileNumberResponse> mobileNumberResponseLiveData;

    public void saveMobileNumber(JsonObject jsonObject){
        this.mobileNumberResponseLiveData = MobileNumberRepository.getInstance().getMobileNumber(jsonObject);
    }

    public LiveData<MobileNumberResponse> getMobileNumberResponseLiveData(){
        return mobileNumberResponseLiveData;
    }



}
