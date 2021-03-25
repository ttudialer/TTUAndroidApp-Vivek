package com.kabaladigital.tingtingu.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.kabaladigital.tingtingu.models.RewardModel;
import com.kabaladigital.tingtingu.networking.RewardRepository;

import java.util.List;

public class EarnedPointCreditHistoryViewModel extends ViewModel {
    private LiveData<List<RewardModel>> rewardModelLiveData;

    public void hitRewardApi() {
        this.rewardModelLiveData = RewardRepository.getInstance().hitRewardApi();
    }

    public LiveData<List<RewardModel>> getRewardModelLiveData() {
        return rewardModelLiveData;
    }
}
