package com.kabaladigital.tingtingu.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.kabaladigital.tingtingu.models.ProfileInformationModel;
import com.kabaladigital.tingtingu.networking.ProfileInformationRepository;

public class ProfileStep1ViewModel extends ViewModel {

    private LiveData<ProfileInformationModel> profileInformationModelLiveData;

    public void hitProfileInformation(){
        this.profileInformationModelLiveData = ProfileInformationRepository.getInstance().getProfileInfo();
    }

    public LiveData<ProfileInformationModel> getProfileInformationModelLiveData(){
        return profileInformationModelLiveData;
    }
}
