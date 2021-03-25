package com.kabaladigital.tingtingu.ui.fragment.points;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.adapter.EarnedPointDebitHistoryAdapter;
import com.kabaladigital.tingtingu.databinding.EarnedPointDebitHistoryFragmentBinding;
import com.kabaladigital.tingtingu.models.EarnedPointDebitHistoryModel;
import com.kabaladigital.tingtingu.models.RechargeHistoryModel;
import com.kabaladigital.tingtingu.util.DateUtility;
import com.kabaladigital.tingtingu.util.NumberFormat;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.viewmodels.EarnedPointDebitHistoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class EarnedPointDebitHistoryFragment extends Fragment {

    private List<EarnedPointDebitHistoryModel>debitHistoryModelList = new ArrayList<>();
    private EarnedPointDebitHistoryAdapter pointDebitHistoryAdapter;
    private EarnedPointDebitHistoryViewModel mViewModel;
    private EarnedPointDebitHistoryFragmentBinding binding;
    private String langType;

    public static EarnedPointDebitHistoryFragment newInstance() {
        return new EarnedPointDebitHistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.earned_point_debit_history_fragment,
                                           container, false);
        langType = PreferenceUtils.getInstance().getString(R.string.pref_user_selected_language_key);
        pointDebitHistoryAdapter = new EarnedPointDebitHistoryAdapter(debitHistoryModelList,getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        binding.recyclerViewDebit.setLayoutManager(mLayoutManager);
        binding.recyclerViewDebit.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerViewDebit.addItemDecoration(new DividerItemDecoration(
                             binding.recyclerViewDebit.getContext(), DividerItemDecoration.VERTICAL));
        binding.recyclerViewDebit.setAdapter(pointDebitHistoryAdapter);


        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EarnedPointDebitHistoryViewModel.class);
        mViewModel.hitHistoryApi();
        getHistoryApi();

    }

    private void getHistoryApi() {
        mViewModel.getHistoryModelLiveData().observe(getActivity(), new Observer<List<RechargeHistoryModel>>() {
            @Override
            public void onChanged(List<RechargeHistoryModel> rechargeHistoryModels) {
                if (rechargeHistoryModels != null && rechargeHistoryModels.size()>0) {
                    List<RechargeHistoryModel> rechargeHistoryModelList = new ArrayList<>();
                    for (int i=0; i<rechargeHistoryModels.size(); i++){
                        if (rechargeHistoryModels.get(i).getStatusCode()==200
                                && rechargeHistoryModels.get(i).getStatus().equals("rechargeHistoryModels")){
                            rechargeHistoryModelList.add(rechargeHistoryModels.get(i));
                        }
                    }
                    getDebitHistoryData(rechargeHistoryModelList);
                }else {
                    debitHistoryModelList.clear();
                    pointDebitHistoryAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void getDebitHistoryData(List<RechargeHistoryModel> rechargeHistoryModels) {
        debitHistoryModelList.clear();

        for (int i = 0;i<rechargeHistoryModels.size();i++){

            if (rechargeHistoryModels.get(i).getPoints()>0){
                String desc;
                if (langType.equals("hi")){
                    desc = rechargeHistoryModels.get(i).getOperator() + " " + rechargeHistoryModels.get(i).getTitle()
                            + " on " + DateUtility.convertRechargeHistory(rechargeHistoryModels.get(i).getDate());
                } else {
                    desc = rechargeHistoryModels.get(i).getOperator() + " " + rechargeHistoryModels.get(i).getTitle()
                            + " on " + DateUtility.convertRechargeHistory(rechargeHistoryModels.get(i).getDate());
                }
                EarnedPointDebitHistoryModel debitHistoryModel = new EarnedPointDebitHistoryModel(
                        String.valueOf(i),
                        String.valueOf(rechargeHistoryModels.get(i).getMobileNumber()),
                        desc,
                        String.valueOf(NumberFormat.decimalFormat(rechargeHistoryModels.get(i).getPoints())));
                debitHistoryModelList.add(debitHistoryModel);
            }

        }
        pointDebitHistoryAdapter.notifyDataSetChanged();
    }

}
