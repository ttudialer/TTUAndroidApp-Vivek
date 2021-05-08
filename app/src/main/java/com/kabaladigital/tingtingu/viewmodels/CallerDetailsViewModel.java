package com.kabaladigital.tingtingu.viewmodels;

import androidx.lifecycle.LiveData;

import com.kabaladigital.tingtingu.models.ProfileInformationModel;
import com.kabaladigital.tingtingu.networking.ProfileInformationRepository;

public class CallerDetailsViewModel {
    private LiveData<ProfileInformationModel> profileInformationModelLiveData;

    public void hitProfileInformation(){
        this.profileInformationModelLiveData = ProfileInformationRepository.getInstance().getProfileInfo();
    }

    public LiveData<ProfileInformationModel> getProfileInformationModelLiveData(){
        return profileInformationModelLiveData;
    }

}
