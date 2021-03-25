package com.kabaladigital.tingtingu.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.kabaladigital.tingtingu.models.TrailMembershipCheck;
import com.kabaladigital.tingtingu.networking.TrailMembershipCheckRepository;

public class ChooseLanguageViewModel extends ViewModel {

    private LiveData<TrailMembershipCheck> TrailMembershipCheckResponseLiveData;

    public void MembershipCheck(){
        this.TrailMembershipCheckResponseLiveData = TrailMembershipCheckRepository.getInstance().CheckMembership();
    }

    public LiveData<TrailMembershipCheck> getTrailMembershipCheckResponseLiveData(){
        return TrailMembershipCheckResponseLiveData;
    }
}
