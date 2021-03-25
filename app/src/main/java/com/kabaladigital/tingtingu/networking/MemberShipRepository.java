package com.kabaladigital.tingtingu.networking;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kabaladigital.tingtingu.models.MemberShipTypeModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberShipRepository {

    private ApiInterface apiInterface;
    private static MemberShipRepository memberShipRepository;

    public static MemberShipRepository getInstance(){
        if (memberShipRepository == null){
            memberShipRepository = new MemberShipRepository();
        }
        return memberShipRepository;
    }

    public MemberShipRepository() {
        apiInterface = ApiClient.createService(ApiInterface.class);
    }

    public LiveData<MemberShipTypeModel> hitMemberShipType(){
       final MutableLiveData<MemberShipTypeModel> data = new MutableLiveData<>();
        apiInterface.getMemberShipType()
                .enqueue(new Callback<MemberShipTypeModel>() {
                    @Override
                    public void onResponse(Call<MemberShipTypeModel> call,
                                                    Response<MemberShipTypeModel> response) {
                          if (response.code() == 200){
                              Log.d("onResponseMember", "onResponse response:: " + response);
                              data.setValue(response.body());
                          }
                    }

                    @Override
                    public void onFailure(Call<MemberShipTypeModel> call, Throwable t) {
                        data.setValue(null);
                        Log.d("onFailureMember","onFailure ::" + t.getMessage());
                    }
                });

        return data;
    }

}
