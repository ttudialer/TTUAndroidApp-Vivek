package com.kabaladigital.tingtingu.networking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kabaladigital.tingtingu.models.RechargeHistoryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RechargeHistoryRepository {

    private ApiInterface apiInterface;
    private static RechargeHistoryRepository rechargeHistoryRepository;

    public static RechargeHistoryRepository getInstance(){
        if (rechargeHistoryRepository == null){
            rechargeHistoryRepository = new RechargeHistoryRepository();
        }
        return rechargeHistoryRepository;
    }

    public RechargeHistoryRepository() {
        apiInterface = ApiClient.createService(ApiInterface.class);
    }

    public LiveData<List<RechargeHistoryModel>> hitHistoryApi(){

        final MutableLiveData<List<RechargeHistoryModel>> data = new MutableLiveData<>();

        apiInterface.getRechargeHistory().enqueue(new Callback<List<RechargeHistoryModel>>() {
            @Override
            public void onResponse(Call<List<RechargeHistoryModel>> call, Response<List<RechargeHistoryModel>> response) {
                if (response.code() == 200){
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<RechargeHistoryModel>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
