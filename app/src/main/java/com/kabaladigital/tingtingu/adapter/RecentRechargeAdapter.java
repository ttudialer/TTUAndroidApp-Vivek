package com.kabaladigital.tingtingu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.databinding.LayoutRecentRechargesBinding;
import com.kabaladigital.tingtingu.models.RechargeHistoryModel;
import com.kabaladigital.tingtingu.util.DateUtility;
import com.kabaladigital.tingtingu.util.Operator;

import java.util.ArrayList;
import java.util.List;

public class RecentRechargeAdapter extends RecyclerView.Adapter<RecentRechargeAdapter.ViewHolder> {

    private LayoutRecentRechargesBinding binding;
    private List<RechargeHistoryModel>recentRechargeModelList = new ArrayList<>();
    private Context context;

    public RecentRechargeAdapter(List<RechargeHistoryModel> recentRechargeModelList,
                                     Context context) {
        this.recentRechargeModelList = recentRechargeModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
       binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
               R.layout.layout_recent_recharges,viewGroup,false);

       return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RechargeHistoryModel rechargeModel = recentRechargeModelList.get(position);
        binding.imgIcon.setImageResource(Operator.getOperatorIcon((rechargeModel.getOperator())));
        binding.tvNumber.setText(String.valueOf(rechargeModel.getMobileNumber()));
        String price = "\u20B9 " + String.valueOf(rechargeModel.getPoints());
        String date = DateUtility.convertRechargeHistory(rechargeModel.getDate());
        binding.tvAmountOrDate.setText("Recharge of " + price + " done on " + date);
        binding.tvStatus.setText(rechargeModel.getStatus());
    }

    @Override
    public int getItemCount() {
        return recentRechargeModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull LayoutRecentRechargesBinding binding) {
            super(binding.getRoot());
        }
    }
}
