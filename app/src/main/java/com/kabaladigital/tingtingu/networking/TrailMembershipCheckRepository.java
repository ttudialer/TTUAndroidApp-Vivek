package com.kabaladigital.tingtingu.networking;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kabaladigital.tingtingu.models.TrailMembershipCheck;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrailMembershipCheckRepository {

    private ApiInterface apiInterface;
    private static TrailMembershipCheckRepository trailMembershipCheckRepository;

    public static TrailMembershipCheckRepository getInstance(){
        if (trailMembershipCheckRepository == null){
            trailMembershipCheckRepository = new TrailMembershipCheckRepository();
        }
        return trailMembershipCheckRepository;
    }

    public TrailMembershipCheckRepository(){
        apiInterface = ApiClient.createService(ApiInterface.class);

    }

    public LiveData<TrailMembershipCheck> CheckMembership(){
        final MutableLiveData<TrailMembershipCheck> responseMutableLiveData = new MutableLiveData<>();

        apiInterface.checkMembership().enqueue(new Callback<TrailMembershipCheck>() {
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
