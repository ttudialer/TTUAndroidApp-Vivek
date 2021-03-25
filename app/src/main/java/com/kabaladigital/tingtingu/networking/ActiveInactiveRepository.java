package com.kabaladigital.tingtingu.networking;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kabaladigital.tingtingu.models.ActiveInactiveModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActiveInactiveRepository {

    private ApiInterface apiInterface;
    private static ActiveInactiveRepository activeInactiveRepository;

    public static ActiveInactiveRepository getInstance(){
        if (activeInactiveRepository == null){
            activeInactiveRepository = new ActiveInactiveRepository();
        }
        return activeInactiveRepository;
    }

    public ActiveInactiveRepository(){
        apiInterface = ApiClient.createService(ApiInterface.class);

    }

    public LiveData<ActiveInactiveModel> getProfileInfo(){

        final MutableLiveData<ActiveInactiveModel> responseMutableLiveData = new MutableLiveData<>();

        apiInterface.getUserStatusInformation()
                .enqueue(new Callback<ActiveInactiveModel>() {
                    @Override
                    public void onResponse(Call<ActiveInactiveModel> call,
                                           Response<ActiveInactiveModel> response) {
                        if (response.code() == 200){
                            responseMutableLiveData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<ActiveInactiveModel> call, Throwable t) {
                        Log.d("Error", "onFailure: "+t.getMessage());

                    }
                });
        return responseMutableLiveData;
    }
}
