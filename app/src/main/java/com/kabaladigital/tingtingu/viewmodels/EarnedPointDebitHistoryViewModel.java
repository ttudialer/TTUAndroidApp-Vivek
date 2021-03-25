package com.kabaladigital.tingtingu.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.kabaladigital.tingtingu.models.RechargeHistoryModel;
import com.kabaladigital.tingtingu.networking.RechargeHistoryRepository;

import java.util.List;

public class EarnedPointDebitHistoryViewModel extends ViewModel {

    private LiveData<List<RechargeHistoryModel>> historyModelLiveData;

    public void hitHistoryApi() {
        this.historyModelLiveData = RechargeHistoryRepository.getInstance().hitHistoryApi();
    }

    public LiveData<List<RechargeHistoryModel>> getHistoryModelLiveData() {
        return historyModelLiveData;
    }

}
