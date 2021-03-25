package com.kabaladigital.tingtingu.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.kabaladigital.tingtingu.models.RechargeHistoryModel;
import com.kabaladigital.tingtingu.models.RewardModel;
import com.kabaladigital.tingtingu.networking.RechargeHistoryRepository;
import com.kabaladigital.tingtingu.networking.RewardRepository;

import java.util.List;

public class RechargeHistoryViewModel extends ViewModel {

    private LiveData<List<RechargeHistoryModel>> historyModelLiveData;
    private LiveData<RewardModel> rewardModelLiveData;

    public void hitHistoryApi() {
        this.historyModelLiveData = RechargeHistoryRepository.getInstance().hitHistoryApi();
    }

    public LiveData<List<RechargeHistoryModel>> getHistoryModelLiveData() {
        return historyModelLiveData;
    }

    public void hitWalletBalanceApi() {
        this.rewardModelLiveData = RewardRepository.getInstance().getWalletBalanceApi();
    }

    public LiveData<RewardModel> getWalletBalanceModelLiveData() {
        return rewardModelLiveData;
    }
}
