package com.kabaladigital.tingtingu.networking;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.kabaladigital.tingtingu.models.TrailMembershipCheck;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceCheckRepository {

    private ApiInterface apiInterface;
    private static DeviceCheckRepository deviceCheckRepository;

    public static DeviceCheckRepository getInstance(){
        if (deviceCheckRepository == null){
            deviceCheckRepository = new DeviceCheckRepository();
        }
        return deviceCheckRepository;
    }

    public DeviceCheckRepository(){
        apiInterface = ApiClient.createService(ApiInterface.class);

    }

    public LiveData<TrailMembershipCheck> CheckDeviceId(JsonObject jsonObject){
        final MutableLiveData<TrailMembershipCheck> responseMutableLiveData = new MutableLiveData<>();

        apiInterface.checkDevice(jsonObject)
                .enqueue(new Callback<TrailMembershipCheck>() {
            @Override
            public void onResponse(Call<TrailMembershipCheck> call,
                                   Response<TrailMembershipCheck> response) {
                if (response.code() == 200){
                    responseMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<TrailMembershipCheck> call, Throwable t) {
                Log.d("Errorrr", "onFailure: "+t.getMessage());

            }
        });
        return responseMutableLiveData;
    }
}
