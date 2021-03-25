package com.kabaladigital.tingtingu.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonObject;
import com.kabaladigital.tingtingu.models.MobileInfoModel;
import com.kabaladigital.tingtingu.networking.MobileNumberUpdateRepository;

public class OperatorDetailViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private LiveData<MobileInfoModel> voidOperatorDetailLiveData;

    public void updateOperatorDetail(JsonObject jsonObject) {
        this.voidOperatorDetailLiveData = MobileNumberUpdateRepository.getInstance().getMobileNumberInfo(jsonObject);
    }

    public LiveData<MobileInfoModel> getVoidOperatorDetailLiveData(){
        return voidOperatorDetailLiveData;
    }

}
