package com.kabaladigital.tingtingu.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonObject;
import com.kabaladigital.tingtingu.models.RechargeModel;
import com.kabaladigital.tingtingu.networking.RechargeRepository;

public class DthProcessRechargeViewModel extends ViewModel {

    private LiveData<RechargeModel> rechargeModelLiveData;

    public void hitRechargeApi(JsonObject jsonObject) {
        this.rechargeModelLiveData = RechargeRepository.getInstance().hitRechargeApi(jsonObject);
    }

    public LiveData<RechargeModel> getRechargeModelLiveData() {
        return rechargeModelLiveData;
    }
}
