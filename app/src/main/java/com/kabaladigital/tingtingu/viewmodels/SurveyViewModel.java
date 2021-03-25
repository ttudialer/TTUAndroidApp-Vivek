package com.kabaladigital.tingtingu.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.kabaladigital.tingtingu.models.SurveyModel;
import com.kabaladigital.tingtingu.networking.SurveyListRepository;

public class SurveyViewModel extends ViewModel {

    private LiveData<SurveyModel> surveyListLiveData;

    public void hitSurveyList() {
        this.surveyListLiveData = SurveyListRepository.getInstance().hitSurveyList();
    }

    public LiveData<SurveyModel> getSurveyListLiveData() {
        return surveyListLiveData;
    }
}
