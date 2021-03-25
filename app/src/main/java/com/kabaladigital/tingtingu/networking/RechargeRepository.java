package com.kabaladigital.tingtingu.networking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.kabaladigital.tingtingu.models.RechargeModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RechargeRepository {

    private ApiInterface apiInterface;
    private static RechargeRepository rechargeRepository;

    public static RechargeRepository getInstance(){
        if (rechargeRepository == null){
            rechargeRepository = new RechargeRepository();
        }
        return rechargeRepository;
    }

    public RechargeRepository() {
        apiInterface = ApiClient.createService(ApiInterface.class);
    }

    public LiveData<RechargeModel> hitRechargeApi(JsonObject jsonObject){

        final MutableLiveData<RechargeModel> data = new MutableLiveData<>();

        apiInterface.rechargeDetails(jsonObject).enqueue(new Callback<RechargeModel>() {
            @Override
            public void onResponse(Call<RechargeModel> call, Response<RechargeModel> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RechargeModel> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
