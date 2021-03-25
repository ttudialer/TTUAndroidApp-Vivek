package com.kabaladigital.tingtingu.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonObject;
import com.kabaladigital.tingtingu.models.GlobalVariableModel;
import com.kabaladigital.tingtingu.models.MemberShipTypeModel;
import com.kabaladigital.tingtingu.models.RewardModel;
import com.kabaladigital.tingtingu.models.TrailMembershipCheck;
import com.kabaladigital.tingtingu.networking.DeviceCheckRepository;
import com.kabaladigital.tingtingu.networking.GlobalVariableRepository;
import com.kabaladigital.tingtingu.networking.MemberShipRepository;
import com.kabaladigital.tingtingu.networking.RewardRepository;

public class OperatorHomeViewModel extends ViewModel {

    private LiveData<MemberShipTypeModel> memberShipTypeLiveData;
    private LiveData<RewardModel> rewardModelLiveData;
    private LiveData<GlobalVariableModel> globalVariableLiveData;
    private LiveData<TrailMembershipCheck> trailMembershipCheckLiveData;

    public void hitMemberShipType() {
        this.memberShipTypeLiveData = MemberShipRepository.getInstance().hitMemberShipType();
    }

    public LiveData<MemberShipTypeModel> getMemberShipTypeLiveData() {
        return memberShipTypeLiveData;
    }

    public void hitWalletBalanceApi() {
        this.rewardModelLiveData = RewardRepository.getInstance().getWalletBalanceApi();
    }

    public LiveData<RewardModel> getWalletBalanceModelLiveData() {
        return rewardModelLiveData;
    }

    public void hitGlobalVariableApi() {
        this.globalVariableLiveData = GlobalVariableRepository.getInstance().hitGlobalVariableApi();
    }

    public LiveData<GlobalVariableModel> getGlobalVariableModelLiveData() {
        return globalVariableLiveData;
    }

    public void checkDeviceId(JsonObject jsonObject) {
        this.trailMembershipCheckLiveData = DeviceCheckRepository.getInstance().CheckDeviceId(jsonObject);
    }

    public LiveData<TrailMembershipCheck> getCheckDeviceIdLiveData() {
        return trailMembershipCheckLiveData;
    }
}
