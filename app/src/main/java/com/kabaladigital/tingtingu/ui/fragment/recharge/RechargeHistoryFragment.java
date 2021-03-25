package com.kabaladigital.tingtingu.ui.fragment.recharge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.adapter.RecentRechargeAdapter;
import com.kabaladigital.tingtingu.databinding.FragmentRechargeHistoryBinding;
import com.kabaladigital.tingtingu.models.RechargeHistoryModel;
import com.kabaladigital.tingtingu.models.RewardModel;
import com.kabaladigital.tingtingu.ui.activity.MainActivity;
import com.kabaladigital.tingtingu.util.NumberFormat;
import com.kabaladigital.tingtingu.viewmodels.RechargeHistoryViewModel;

import java.util.ArrayList;
import java.util.List;


public class RechargeHistoryFragment extends Fragment {

    private int rechargeFor;
    private FragmentRechargeHistoryBinding binding;
    private RechargeHistoryViewModel mViewModel;

    List<RechargeHistoryModel> recentRechargeModelList = new ArrayList<>();
    RecentRechargeAdapter recentRechargeAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rechargeFor = getArguments().getInt("rechargeFor");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_recharge_history, container, false);

        if (rechargeFor == 0){
            binding.tvNewRecharge.setText(R.string.new_prepaid_recharge);
            binding.tvSelectOne.setText(R.string.recent_prepaid_recharge_list);
        }

        if (rechargeFor == 1){
            binding.tvNewRecharge.setText(R.string.new_dth_recharge);
            binding.tvSelectOne.setText(R.string.recent_dth_recharge_list);
        }

        recentRechargeAdapter = new RecentRechargeAdapter(recentRechargeModelList,getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        binding.rvRecentRecharge.setLayoutManager(mLayoutManager);
        binding.rvRecentRecharge.setItemAnimator(new DefaultItemAnimator());
        binding.rvRecentRecharge.setAdapter(recentRechargeAdapter);

        binding.layoutNewRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                if (rechargeFor == 0){
                    bundle.putInt("rechargeFor", 0);
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.rechargeFragment,bundle);
                }else if (rechargeFor == 1){
                    bundle.putInt("rechargeFor", 1);
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.dthRechargeFragment,bundle);

                }
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RechargeHistoryViewModel.class);

        mViewModel.hitHistoryApi();
        getHistoryApi();

        mViewModel.hitWalletBalanceApi();
        getWalletBalanceApi();
    }

    private void getHistoryApi() {
        mViewModel.getHistoryModelLiveData().observe(getActivity(), new Observer<List<RechargeHistoryModel>>() {
            @Override
            public void onChanged(List<RechargeHistoryModel> rechargeHistoryModels) {
                if (rechargeHistoryModels != null && rechargeHistoryModels.size()>0) {
                    recentRechargeModelList.clear();
                    for (int i=0;i<rechargeHistoryModels.size();i++){
                        if (rechargeFor == 0){
                            if (rechargeHistoryModels.get(i).getTitle().equals("REC")){
                                recentRechargeModelList.add(rechargeHistoryModels.get(i));
                            }
                        }
                        if (rechargeFor == 1){
                            if (rechargeHistoryModels.get(i).getTitle().equals("DTH")){
                                recentRechargeModelList.add(rechargeHistoryModels.get(i));
                            }
                        }
                    }
                    recentRechargeAdapter.notifyDataSetChanged();
                }else {
                    recentRechargeModelList.clear();
                    recentRechargeAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void getWalletBalanceApi() {
        mViewModel.getWalletBalanceModelLiveData().observe(getActivity(), new Observer<RewardModel>() {
            @Override
            public void onChanged(RewardModel rewardModel) {
                if (rewardModel != null) {
                    binding.layoutWb.tvWalletPrice.setText(NumberFormat.decimalFormat(rewardModel.getBalance()));
                }else {
                    binding.layoutWb.tvWalletPrice.setText("0");
                }
            }
        });
    }

    @Override
    public void onResume() {
        getActivity().setTitle("Recharge History");
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        super.onResume();
    }

}