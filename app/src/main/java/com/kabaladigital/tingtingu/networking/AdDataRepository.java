package com.kabaladigital.tingtingu.networking;

import androidx.lifecycle.MutableLiveData;

import com.kabaladigital.tingtingu.models.IncomingCallDataModel;

public class AdDataRepository {

    private static final String TAG = "AdDataRepository";
    private static AdDataRepository adDataRepository;
    private ApiInterface apiInterface;

    public static AdDataRepository getInstance() {
        if (adDataRepository == null) {
            adDataRepository = new AdDataRepository();
        }
        return adDataRepository;
    }

    private AdDataRepository() {
        apiInterface = ApiClient.createService(ApiInterface.class);
    }

    public MutableLiveData<IncomingCallDataModel> getAdData() {
        final MutableLiveData<IncomingCallDataModel> incomingCallDataModelMutableLiveData = new MutableLiveData<>();

        return incomingCallDataModelMutableLiveData;
    }
}
