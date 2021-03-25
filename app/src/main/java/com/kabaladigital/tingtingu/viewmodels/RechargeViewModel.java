package com.kabaladigital.tingtingu.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.kabaladigital.tingtingu.models.RewardModel;
import com.kabaladigital.tingtingu.networking.RewardRepository;

public class RechargeViewModel extends ViewModel {

    private LiveData<RewardModel> rewardModelLiveData;

    public void hitWalletBalanceApi() {
        this.rewardModelLiveData = RewardRepository.getInstance().getWalletBalanceApi();
    }

    public LiveData<RewardModel> getWalletBalanceModelLiveData() {
        return rewardModelLiveData;
    }
}
