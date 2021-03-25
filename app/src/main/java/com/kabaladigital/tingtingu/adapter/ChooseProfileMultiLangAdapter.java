package com.kabaladigital.tingtingu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.models.ChooseMultiLangModel;
import com.kabaladigital.tingtingu.ui.fragment.login.ChooseProfileLanguageFragment;

import java.util.ArrayList;
import java.util.List;

public class ChooseProfileMultiLangAdapter extends RecyclerView.Adapter<ChooseProfileMultiLangAdapter.ViewHolder> {
    private List<ChooseMultiLangModel> chooseMultiLangModelList = new ArrayList<>();
    private Context context;
    private ChooseProfileLanguageFragment chooseProfileLanguageFragment;

    public ChooseProfileMultiLangAdapter(ChooseProfileLanguageFragment chooseProfileLanguageFragment, List<ChooseMultiLangModel> chooseMultiLangModelList) {
        this.chooseMultiLangModelList = chooseMultiLangModelList;
        this.chooseProfileLanguageFragment = chooseProfileLanguageFragment;
    }

    @NonNull
    @Override
    public ChooseProfileMultiLangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_choosemultilang, viewGroup, false);
        return new ChooseProfileMultiLangAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseProfileMultiLangAdapter.ViewHolder holder, int position) {
        ChooseMultiLangModel chooseMultiLangModel = chooseMultiLangModelList.get(position);
        holder.radioBtn.setText(chooseMultiLangModel.getRadioBtnName());
        String check_type = chooseMultiLangModel.getRadioBtnMode();
        String check_id = chooseMultiLangModel.getId();

        holder.radioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!holder.radioBtn.isSelected()) {
                    holder.radioBtn.setChecked(true);
                    holder.radioBtn.setSelected(true);

                    chooseProfileLanguageFragment.getLanguageList(chooseMultiLangModel.getRadioBtnName());
                } else {
                    holder.radioBtn.setChecked(false);
                    holder.radioBtn.setSelected(false);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return chooseMultiLangModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox radioBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            radioBtn = itemView.findViewById(R.id.radiobtn);
        }
    }
}

