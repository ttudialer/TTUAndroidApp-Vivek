package com.kabaladigital.tingtingu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.databinding.EarnedPointDebitItemsBinding;
import com.kabaladigital.tingtingu.models.EarnedPointDebitHistoryModel;

import java.util.ArrayList;
import java.util.List;

public class EarnedPointDebitHistoryAdapter extends RecyclerView.Adapter<EarnedPointDebitHistoryAdapter.ViewHolder> {
    private List<EarnedPointDebitHistoryModel> debitHistoryModelList = new ArrayList<>();
    private Context context;
    EarnedPointDebitItemsBinding binding;

    public EarnedPointDebitHistoryAdapter(List<EarnedPointDebitHistoryModel> debitHistoryModelList,
                                                        Context context) {
        this.debitHistoryModelList = debitHistoryModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                  R.layout.earned_point_debit_items, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EarnedPointDebitHistoryModel debitHistoryModel = debitHistoryModelList.get(position);
        binding.tvDebitMobile.setText(debitHistoryModel.getMobileNumber());
        binding.tvDebitDate.setText(debitHistoryModel.getDebitDate());
        binding.tvDebitPoints.setText(debitHistoryModel.getDebitPoints());
    }

    @Override
    public int getItemCount() {
        return debitHistoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull EarnedPointDebitItemsBinding earnedPointDebitItemsBinding) {
            super(earnedPointDebitItemsBinding.getRoot());
        }
    }
}
