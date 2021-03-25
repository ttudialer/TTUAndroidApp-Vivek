package com.kabaladigital.tingtingu.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.kabaladigital.tingtingu.models.ActiveInactiveModel;
import com.kabaladigital.tingtingu.networking.ActiveInactiveRepository;

public class SettingViewModel extends ViewModel {

    private LiveData<ActiveInactiveModel> activeInactiveModelLiveData;

    public void hitProfileInformation(){
        this.activeInactiveModelLiveData = ActiveInactiveRepository.getInstance().getProfileInfo();
    }

    public LiveData<ActiveInactiveModel> getProfileInformationModelLiveData(){
        return activeInactiveModelLiveData;
    }

}
