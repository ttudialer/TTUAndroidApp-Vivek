package com.kabaladigital.tingtingu.networking;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.kabaladigital.tingtingu.models.MobileInfoModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobileNumberUpdateRepository {
    private ApiInterface apiInterface;
    private static MobileNumberUpdateRepository mobileNumberUpdateRepository;

    public static MobileNumberUpdateRepository getInstance(){
          if (mobileNumberUpdateRepository == null){
              mobileNumberUpdateRepository = new MobileNumberUpdateRepository();
          }
        return mobileNumberUpdateRepository;
    }

    public MobileNumberUpdateRepository(){
        apiInterface = ApiClient.createService(ApiInterface.class);
    }

    public LiveData<MobileInfoModel> getMobileNumberInfo(JsonObject jsonObject){
        MutableLiveData<MobileInfoModel> responseMutableLiveData = new MutableLiveData<>();

        apiInterface.updateMobileNumber(jsonObject).enqueue(new Callback<MobileInfoModel>() {
            @Override
            public void onResponse(Call<MobileInfoModel> call,
                                   Response<MobileInfoModel> response) {
                if (response.code() == 200){
                    responseMutableLiveData.setValue(response.body());
                }
                if (response.code() == 201){
                    responseMutableLiveData.setValue(response.body());
                }


            }

            @Override
            public void onFailure(Call<MobileInfoModel> call, Throwable t) {
                Log.d("onFailure",t.getMessage());
            }
        });

       return responseMutableLiveData;
    }

}
