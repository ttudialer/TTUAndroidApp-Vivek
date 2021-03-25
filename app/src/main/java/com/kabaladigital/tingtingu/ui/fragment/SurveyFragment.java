package com.kabaladigital.tingtingu.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.adapter.SurveyListAdapter;
import com.kabaladigital.tingtingu.models.SurveyModel;
import com.kabaladigital.tingtingu.ui.activity.MainActivity;
import com.kabaladigital.tingtingu.ui.activity.SurveyWebViewActivity;
import com.kabaladigital.tingtingu.viewmodels.SurveyViewModel;

import java.util.ArrayList;
import java.util.List;

public class SurveyFragment extends Fragment {

    private SurveyViewModel viewModel;
    private SurveyListAdapter surveyListAdapter;
    private List<SurveyModel.Survey> surveyModelList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_survey, container, false);

        getActivity().setTitle("Available Surveys");

        RecyclerView recyclerView = view.findViewById(R.id.rv_survey);
        surveyListAdapter = new SurveyListAdapter(surveyModelList,getContext(),
                SurveyFragment.this);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(surveyListAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(SurveyViewModel.class);
    }

    @Override
    public void onResume() {
        getActivity().setTitle("Available Survey");
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        super.onResume();
        viewModel.hitSurveyList();
        getSurveyList();
    }

    private void getSurveyList() {
        viewModel.getSurveyListLiveData().observe(getActivity(), new Observer<SurveyModel>() {
            @Override
            public void onChanged(SurveyModel surveyModel) {
                surveyModelList.clear();
                if (surveyModel != null && surveyModel.getSurveys().size()>0) {
                    surveyModelList.addAll(surveyModel.getSurveys());
                }
                surveyListAdapter.notifyDataSetChanged();
            }
        });
    }

    public void openSurvey(String id){
        Intent intent = new Intent(getActivity(), SurveyWebViewActivity.class);
        intent.putExtra("survey_id",id);
        getActivity().startActivity(intent);
    }
}