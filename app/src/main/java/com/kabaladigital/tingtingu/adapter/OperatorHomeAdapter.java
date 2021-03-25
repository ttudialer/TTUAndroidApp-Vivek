package com.kabaladigital.tingtingu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.databinding.LayoutOperatorHomeBinding;
import com.kabaladigital.tingtingu.models.OperatorHomeModel;
import com.kabaladigital.tingtingu.ui.fragment.OperatorHomeFragment;

import java.util.ArrayList;
import java.util.List;

public class OperatorHomeAdapter extends RecyclerView.Adapter<OperatorHomeAdapter.ViewHolder> {
    List<OperatorHomeModel>operatorHomeModelList=new ArrayList<>();
    Context context;
    OperatorHomeFragment operatorHomeFragment;
    LayoutOperatorHomeBinding binding;

    public OperatorHomeAdapter(List<OperatorHomeModel> operatorHomeModelList, Context context
                                           ,OperatorHomeFragment operatorHomeFragment) {
        this.operatorHomeModelList = operatorHomeModelList;
        this.context = context;
        this.operatorHomeFragment=operatorHomeFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                           R.layout.layout_operator_home, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            OperatorHomeModel operatorHomeModel=operatorHomeModelList.get(position);
            holder.itemRowBinding.imageOperatorHome.setImageResource(operatorHomeModel.getIcon());
            holder.itemRowBinding.tvOperatorHome.setText(operatorHomeModel.getName());
            String name=holder.itemRowBinding.tvOperatorHome.getText().toString();

            holder.itemRowBinding.relativeLayoutListOperatorHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     operatorHomeFragment.getInviteEarn(operatorHomeModel.getId());
                }
            });

    }

    @Override
    public int getItemCount() {
        return operatorHomeModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LayoutOperatorHomeBinding itemRowBinding;

        public ViewHolder(LayoutOperatorHomeBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

    }
}
