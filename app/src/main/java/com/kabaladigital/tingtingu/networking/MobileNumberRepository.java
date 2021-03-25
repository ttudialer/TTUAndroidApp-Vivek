package com.kabaladigital.tingtingu.networking;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.kabaladigital.tingtingu.response_model.MobileNumberResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobileNumberRepository {
    private ApiInterface apiInterface;
    private static MobileNumberRepository mobileNumberRepository;

    public static MobileNumberRepository getInstance(){
          if (mobileNumberRepository == null){
             mobileNumberRepository = new MobileNumberRepository();
          }
       return mobileNumberRepository;
    }

    public MobileNumberRepository(){
        apiInterface = ApiClient.createService(ApiInterface.class);

    }

    public LiveData<MobileNumberResponse> getMobileNumber(JsonObject jsonObject){
         final MutableLiveData<MobileNumberResponse> responseMutableLiveData = new MutableLiveData<>();

           apiInterface.mobileRegister(jsonObject).enqueue(new Callback<MobileNumberResponse>() {
               @Override
               public void onResponse(Call<MobileNumberResponse> call,
                                      Response<MobileNumberResponse> response) {
                   if (response.code() == 200){
                       responseMutableLiveData.setValue(response.body());
                   }
                   if (response.code() == 201){
                       responseMutableLiveData.setValue(response.body());
                   }

               }

               @Override
               public void onFailure(Call<MobileNumberResponse> call, Throwable t) {
                   Log.d("Errorrr", "onFailure: "+t.getMessage());

               }
           });
        return responseMutableLiveData;
    }
}
