package com.kabaladigital.tingtingu.networking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kabaladigital.tingtingu.models.GlobalVariableModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GlobalVariableRepository {

    private ApiInterface apiInterface;
    private static GlobalVariableRepository globalVariableRepository;

    public static GlobalVariableRepository getInstance(){
        if (globalVariableRepository == null){
            globalVariableRepository = new GlobalVariableRepository();
        }
        return globalVariableRepository;
    }

    public GlobalVariableRepository() {
        apiInterface = ApiClient.createService(ApiInterface.class);
    }

    public LiveData<GlobalVariableModel> hitGlobalVariableApi(){
        final MutableLiveData<GlobalVariableModel> data = new MutableLiveData<>();
        apiInterface.getGlobalVariables().enqueue(new Callback<GlobalVariableModel>() {
            @Override
            public void onResponse(Call<GlobalVariableModel> call, Response<GlobalVariableModel> response) {
                if (response.code() == 200){
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GlobalVariableModel> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
