package com.kabaladigital.tingtingu.networking;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kabaladigital.tingtingu.models.SurveyModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurveyListRepository {

    private ApiInterface apiInterface;
    private static SurveyListRepository surveyListRepository;

    public static SurveyListRepository getInstance(){
        if (surveyListRepository == null){
            surveyListRepository = new SurveyListRepository();
        }
        return surveyListRepository;
    }

    public SurveyListRepository() {
        apiInterface = ApiClient.createService(ApiInterface.class);
    }

    public LiveData<SurveyModel> hitSurveyList(){
        final MutableLiveData<SurveyModel> data = new MutableLiveData<>();
        apiInterface.getSurveyList()
                .enqueue(new Callback<SurveyModel>() {
                    @Override
                    public void onResponse(Call<SurveyModel> call,
                                           Response<SurveyModel> response) {
                        if (response.code() == 200){
                            Log.d("onResponseMember", "onResponse response:: " + response);
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<SurveyModel> call, Throwable t) {
                        data.setValue(null);
                        Log.d("onFailureMember","onFailure ::" + t.getMessage());
                    }
                });

        return data;
    }
}
