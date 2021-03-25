package com.kabaladigital.tingtingu.ui.fragment.points;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.adapter.ExpandEarnedPointHistoryAdapter;
import com.kabaladigital.tingtingu.databinding.EarnedPointCreditHistoryFragmentBinding;
import com.kabaladigital.tingtingu.models.ChildObject;
import com.kabaladigital.tingtingu.models.ParentObject;
import com.kabaladigital.tingtingu.models.RewardModel;
import com.kabaladigital.tingtingu.util.NumberFormat;
import com.kabaladigital.tingtingu.viewmodels.EarnedPointCreditHistoryViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EarnedPointCreditHistoryFragment extends Fragment {
    private EarnedPointCreditHistoryFragmentBinding binding;
    private EarnedPointCreditHistoryViewModel mViewModel;
    ExpandEarnedPointHistoryAdapter listAdapter;
    List<ParentObject> listDataHeader = new ArrayList<>();

    public static EarnedPointCreditHistoryFragment newInstance() {
        return new EarnedPointCreditHistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.earned_point_credit_history_fragment, container, false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EarnedPointCreditHistoryViewModel.class);
        mViewModel.hitRewardApi();
        getRewardApi();
    }

    private void getRewardApi() {
        mViewModel.getRewardModelLiveData().observe(getActivity(), new Observer<List<RewardModel>>() {
            @Override
            public void onChanged(List<RewardModel> rewardModel) {
                if (rewardModel != null) {
                    if (rewardModel.size()>0){
                        setHeaders(rewardModel);
                    }
                }
            }
        });
    }

    private void setHeaders(List<RewardModel> rewardModel) {
        listDataHeader = new ArrayList<ParentObject>();
        for (int a = 0; a < rewardModel.size(); a++) {

            String date = returnDateFormat(rewardModel.get(a).getDate());

            boolean availabe = false;
            for (int i=0; i<listDataHeader.size();i++){
                if (listDataHeader.get(i).date.equals(date)){
                    availabe = true;
                    break;
                }
            }
            if (!availabe){
                ParentObject parentObjectNew = new ParentObject();
                parentObjectNew.setDate(date);
                parentObjectNew.setPoint(String.valueOf(0));
                parentObjectNew.setChildren(new ArrayList<ChildObject>());
                listDataHeader.add(parentObjectNew);
            }
        }

        prepareListData(rewardModel);
    }



    private void prepareListData(List<RewardModel> rewardModel) {

        for (int a = 0; a < listDataHeader.size(); a++) {

            ArrayList<ChildObject> childObjects = new ArrayList<ChildObject>();
            float point = 0;
            for (int i=0; i<rewardModel.size();i++){
                String date = returnDateFormat(rewardModel.get(i).getDate());

                if (listDataHeader.get(a).date.equals(date)){
                    ChildObject childObject = new ChildObject();
                    childObject.setChildDate(returnDateFormat2(rewardModel.get(i).getDate()));
                    childObject.setChildPoint(NumberFormat.decimalFormat(rewardModel.get(i).getPoints()));
                    childObject.setChildType(rewardModel.get(i).gettType());
                    childObject.setChildDesc(rewardModel.get(i).getDesc());
                    childObject.setChildMobileNumber(String.valueOf(rewardModel.get(i).getMobileNumber()));
                    childObject.setSurveyId(rewardModel.get(i).getSurveyId());
                    childObjects.add(childObject);
                    point = point + rewardModel.get(i).getPoints();
                }
            }
            listDataHeader.get(a).setChildren(childObjects);
            listDataHeader.get(a).setPoint(NumberFormat.decimalFormat(point));
        }

        listAdapter = new ExpandEarnedPointHistoryAdapter(getContext(), listDataHeader);
        binding.expandlistviewEarned.setAdapter(listAdapter);
    }

    private String returnDateFormat(String dateWithTime){

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat monthYear = new SimpleDateFormat("MMM yyyy");
        SimpleDateFormat dayMonth = new SimpleDateFormat("dd/MM");

        Date d = null;
        Date d2 = null;

        try {
            d = input.parse(dateWithTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = output.format(d);
        Log.d("formattedDate", formattedDate);

        try {
            d2 = output.parse(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String getMonthYear = monthYear.format(d2);
        Log.d("getMonthYear...", getMonthYear);

        return getMonthYear;
    }

    private String returnDateFormat2(String dateWithTime){

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat output = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat monthYear = new SimpleDateFormat("MM/yyyy");
        SimpleDateFormat dayMonth = new SimpleDateFormat("dd/MM");

        Date d = null;
        Date d2 = null;

        try {
            d = input.parse(dateWithTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = output.format(d);
        Log.d("formattedDate", formattedDate);

        return formattedDate;
    }
}
