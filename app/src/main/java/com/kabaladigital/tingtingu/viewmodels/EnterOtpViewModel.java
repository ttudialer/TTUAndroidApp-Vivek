package com.kabaladigital.tingtingu.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonObject;
import com.kabaladigital.tingtingu.models.ProfileInformationModel;
import com.kabaladigital.tingtingu.networking.MobileNumberRepository;
import com.kabaladigital.tingtingu.networking.ProfileInformationRepository;
import com.kabaladigital.tingtingu.networking.SmsOtpRepository;
import com.kabaladigital.tingtingu.response_model.MobileNumberResponse;
import com.kabaladigital.tingtingu.response_model.SmsOtpResponse;

import java.util.Random;

public class EnterOtpViewModel extends ViewModel {
    private LiveData<MobileNumberResponse> mobileNumberResponseLiveData;
    private LiveData<SmsOtpResponse> smsOtpResponseLiveData;
    private LiveData<ProfileInformationModel> profileInformationModelLiveData;

    public void saveMobileNumber(JsonObject jsonObject){
      this.mobileNumberResponseLiveData = MobileNumberRepository.getInstance().getMobileNumber(jsonObject);
    }

    public LiveData<MobileNumberResponse> getMobileNumberResponseLiveData(){
        return mobileNumberResponseLiveData;
    }

    public void sendSmsOtp(JsonObject jsonObject){
        this.smsOtpResponseLiveData = SmsOtpRepository.getInstance().getSmsOtpResponse(jsonObject);
    }

    public String generateOtp(){
        Random random = new Random();
        int otp1 = random.nextInt(10);
        int otp2 = random.nextInt(10);
        int otp3 = random.nextInt(10);
        int otp4 = random.nextInt(10);

        return otp1 + "" + otp2 + "" + otp3 + "" + otp4;
    }

    public LiveData<SmsOtpResponse>getSmsOtpResponseLiveData(){
        return smsOtpResponseLiveData;
    }

    public void hitProfileInformation(){
        this.profileInformationModelLiveData = ProfileInformationRepository.getInstance().getProfileInfo();
    }

    public LiveData<ProfileInformationModel> getProfileInformationModelLiveData(){
        return profileInformationModelLiveData;
    }

}
