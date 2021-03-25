package com.kabaladigital.tingtingu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.models.OperatorSelectModel;
import com.kabaladigital.tingtingu.ui.fragment.login.OperatorSelectFragment;

import java.util.ArrayList;
import java.util.List;

public class OperatorSelectAdapter extends RecyclerView.Adapter<OperatorSelectAdapter.ViewHolder> {

    private List<OperatorSelectModel> operatorSelectModelList = new ArrayList<>();
    private OperatorSelectFragment operatorSelectFragment;

    public OperatorSelectAdapter(List<OperatorSelectModel> operatorSelectModelList
            , OperatorSelectFragment context) {
        this.operatorSelectModelList = operatorSelectModelList;
        this.operatorSelectFragment = context;
    }

    @NonNull
    @Override
    public OperatorSelectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_selector_operator, viewGroup, false);
        return new OperatorSelectAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OperatorSelectAdapter.ViewHolder holder, int position) {
         OperatorSelectModel selectModel = operatorSelectModelList.get(position);
         holder.tvOperatorName.setText(selectModel.getName());
         holder.ivOperator.setImageResource(selectModel.getImage());

         holder.layoutOperatorSelection.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 operatorSelectFragment.getOperator(holder.tvOperatorName.getText().toString());
             }
         });
    }

    @Override
    public int getItemCount() {
        return operatorSelectModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvOperatorName;
        ImageView ivOperator;
        LinearLayout layoutOperatorSelection;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivOperator = itemView.findViewById(R.id.iv_operator);
            tvOperatorName = itemView.findViewById(R.id.tv_operator_name);
            layoutOperatorSelection = itemView.findViewById(R.id.layout_operator_selection);
        }
    }
}
