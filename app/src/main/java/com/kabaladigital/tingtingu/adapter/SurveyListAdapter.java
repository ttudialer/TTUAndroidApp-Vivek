package com.kabaladigital.tingtingu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.databinding.LayoutSurveyListBinding;
import com.kabaladigital.tingtingu.models.SurveyModel;
import com.kabaladigital.tingtingu.ui.fragment.SurveyFragment;
import com.kabaladigital.tingtingu.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

public class SurveyListAdapter extends RecyclerView.Adapter<SurveyListAdapter.ViewHolder> {

    List<SurveyModel.Survey> surveyModelList=new ArrayList<>();
    Context context;
    SurveyFragment surveyFragment;
    LayoutSurveyListBinding binding;
    private final String langType;

    public SurveyListAdapter(List<SurveyModel.Survey> surveyModelList, Context context, SurveyFragment surveyFragment) {
        this.surveyModelList = surveyModelList;
        this.context = context;
        this.surveyFragment=surveyFragment;
        this.langType = PreferenceUtils.getInstance().getString(R.string.pref_user_selected_language_key);
    }

    @NonNull
    @Override
    public SurveyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.layout_survey_list, viewGroup, false);
        return new SurveyListAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SurveyListAdapter.ViewHolder holder, int position) {
        SurveyModel.Survey surveyList = surveyModelList.get(position);

        holder.itemRowBinding.tvName.setText(surveyList.getName());
        holder.itemRowBinding.tvSurveyId.setText(surveyList.getCode());


        String benefit = "";
        switch (surveyList.getBenefitType()){
            case 1:
                if (langType.equals("hi")){
                    benefit = "अपने TTU वॉलेट बैलेंस में "+surveyList.getBenefitValue1() + " पॉइंट प्राप्त करें";
                }else {
                    benefit = "Get "+surveyList.getBenefitValue1() + " points in your TTU wallet balance";
                }
                break;
            case 2:
                if (langType.equals("hi")){
                    benefit = "अगले "+surveyList.getBenefitValue1() + " दिनों के लिए "
                            + surveyList.getBenefitValue2() + " अतिरिक्त खेल खेलने के मौके प्राप्त करें";
                }else {
                    benefit = "Get "+surveyList.getBenefitValue1() + " additional daily Game Play chances for next "
                            + surveyList.getBenefitValue2() + " days";
                }
                break;
            case 3:
                if (langType.equals("hi")){
                    benefit = "अपनी TTU सदस्यता को "+surveyList.getBenefitValue2() + " स्तर पर अपग्रेड करें";
                }else {
                    benefit = "Get your TTU Membership upgraded to "+surveyList.getBenefitValue2() + " level";
                }
                break;
            case 4:
                if (langType.equals("hi")){
                    benefit = surveyList.getBenefitValue2() + "  से एक उपहार: "
                            + surveyList.getBenefitValue1() + " प्राप्त  करें";
                }else {
                    benefit = "You will receive "+surveyList.getBenefitValue1() + " from "
                            + surveyList.getBenefitValue2();
                }
                break;
        }
        holder.itemRowBinding.tvBenefit.setText("Benefit: " + benefit);

        holder.itemRowBinding.relativeLayoutListOperatorHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surveyFragment.openSurvey(surveyList.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return surveyModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LayoutSurveyListBinding itemRowBinding;

        public ViewHolder(LayoutSurveyListBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

    }
}
