package com.kabaladigital.tingtingu.networking;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kabaladigital.tingtingu.models.ProfileInformationModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileInformationRepository {

    private ApiInterface apiInterface;
    private static ProfileInformationRepository profileInformationRepository;

    public static ProfileInformationRepository getInstance(){
        if (profileInformationRepository == null){
            profileInformationRepository = new ProfileInformationRepository();
        }
        return profileInformationRepository;
    }

    public ProfileInformationRepository(){
        apiInterface = ApiClient.createService(ApiInterface.class);

    }

    public LiveData<ProfileInformationModel> getProfileInfo(){
        final MutableLiveData<ProfileInformationModel> responseMutableLiveData = new MutableLiveData<>();
        apiInterface.getUserProfileInformation()
                .enqueue(new Callback<ProfileInformationModel>() {
            @Override
            public void onResponse(Call<ProfileInformationModel> call,
                                   Response<ProfileInformationModel> response) {
                if (response.code() == 200){
                    responseMutableLiveData.setValue(response.body());
                    Log.d("result::: ", "onResult::: "+response.body().getKnownLanguages() + " or "+response.body().getProfession());

                }
            }

            @Override
            public void onFailure(Call<ProfileInformationModel> call, Throwable t) {
                Log.d("Errorrr", "onFailure: "+t.getMessage());

            }
        });
        return responseMutableLiveData;
    }

}
