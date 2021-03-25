package com.kabaladigital.tingtingu.networking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kabaladigital.tingtingu.models.RewardModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RewardRepository {

    private ApiInterface apiInterface;
    private static RewardRepository rewardRepository;

    public static RewardRepository getInstance(){
        if (rewardRepository == null){
            rewardRepository = new RewardRepository();
        }
        return rewardRepository;
    }

    public RewardRepository() {
        apiInterface = ApiClient.createService(ApiInterface.class);
    }

    public LiveData<List<RewardModel>> hitRewardApi(){
        final MutableLiveData<List<RewardModel>> data = new MutableLiveData<>();
        apiInterface.getReward().enqueue(new Callback<List<RewardModel>>() {
            @Override
            public void onResponse(Call<List<RewardModel>> call, Response<List<RewardModel>> response) {
                if (response.code() == 200){
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<RewardModel>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<RewardModel> getWalletBalanceApi(){
        final MutableLiveData<RewardModel> data = new MutableLiveData<>();
        apiInterface.getWalletBalance().enqueue(new Callback<RewardModel>() {
            @Override
            public void onResponse(Call<RewardModel> call, Response<RewardModel> response) {
                if (response.code() == 200){
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<RewardModel> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

}
