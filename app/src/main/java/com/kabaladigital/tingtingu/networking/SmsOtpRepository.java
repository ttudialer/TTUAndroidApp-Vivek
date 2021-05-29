package com.kabaladigital.tingtingu.networking;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.kabaladigital.tingtingu.response_model.SmsOtpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SmsOtpRepository {
    private ApiInterface apiInterface;
    public static SmsOtpRepository smsOtpRepository;

    public static SmsOtpRepository getInstance(){
        if (smsOtpRepository == null){
           smsOtpRepository = new SmsOtpRepository();
        }
        return smsOtpRepository;
    }

    public SmsOtpRepository(){
         apiInterface = ApiClient.createSMSOTPService(ApiInterface.class);
    }

   public LiveData<SmsOtpResponse>getSmsOtpResponse(JsonObject jsonObject)
   {
       MutableLiveData<SmsOtpResponse> smsOtpResponseMutableLiveData = new MutableLiveData<>();

       apiInterface.smsOtpResponse(jsonObject).enqueue(new Callback<SmsOtpResponse>() {
           @Override
           public void onResponse(Call<SmsOtpResponse> call,
                                         Response<SmsOtpResponse> response) {
                if (response.code() == 200){
                    smsOtpResponseMutableLiveData.setValue(response.body());
                }

               if (response.code() == 201){
                   smsOtpResponseMutableLiveData.setValue(response.body());
               }
           }

           @Override
           public void onFailure(Call<SmsOtpResponse> call, Throwable t) {
               Log.d("onFailure","onFailureResponse"+t.getMessage());
           }
       });

       return smsOtpResponseMutableLiveData;
   }

}
