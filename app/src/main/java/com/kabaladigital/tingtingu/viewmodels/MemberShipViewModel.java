package com.kabaladigital.tingtingu.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.kabaladigital.tingtingu.models.MemberShipTypeModel;
import com.kabaladigital.tingtingu.networking.MemberShipRepository;

public class MemberShipViewModel extends ViewModel {
    private LiveData<MemberShipTypeModel> memberShipTypeLiveData;

    public void hitMemberShipType() {
        this.memberShipTypeLiveData = MemberShipRepository.getInstance().hitMemberShipType();
    }

    public LiveData<MemberShipTypeModel> getMemberShipTypeLiveData() {
        return memberShipTypeLiveData;
    }
}
