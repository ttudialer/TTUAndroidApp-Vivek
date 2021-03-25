package com.kabaladigital.tingtingu.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.databinding.MemberShipFragmentBinding;
import com.kabaladigital.tingtingu.ui.activity.MainActivity;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.viewmodels.MemberShipViewModel;

public class MemberShipFragment extends Fragment {

    private MemberShipFragmentBinding binding;
    private MemberShipViewModel mViewModel;

    private String textTrial = "";
    private String textBasic = "";
    private String textPremium = "";

    private String langType;

    public static MemberShipFragment newInstance() {
        return new MemberShipFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.member_ship_fragment, container, false);
        langType = PreferenceUtils.getInstance().getString(R.string.pref_user_selected_language_key);

        if (langType.equals("hi")){
            textTrial = "ट्रायल सदस्यता जानकारी";
            textBasic = "बेसिक सदस्यता जानकारी";
            textPremium = "प्रीमियम सदस्यता जानकारी";
        }else {
            textTrial = "Trial membership information";
            textBasic = "Basic membership information";
            textPremium = "Premium membership information";
        }

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MemberShipViewModel.class);
        mViewModel.hitMemberShipType();
        getMemberShip();
    }

    private void getMemberShip() {
           mViewModel.getMemberShipTypeLiveData().observe(getActivity(), memberShipTypeModel -> {
                 if (memberShipTypeModel != null){
                     switch (memberShipTypeModel.getMembership()){
                         case 1 :
                             binding.tvMembershipCheck.setText(R.string.membership_trial);
                             binding.tvTrialTitle.setVisibility(View.VISIBLE);
                             binding.tvTrialDescription.setText(textTrial);
                             binding.tvTrialDescription.setVisibility(View.VISIBLE);
                             break;
                         case 2 :
                             binding.tvMembershipCheck.setText(R.string.membership_basic);

                             if (langType.equals("hi")){
                                 binding.tvActiveDaysToPremium.setText(String.valueOf(memberShipTypeModel.getDaysToPrem()) + " " + getString(R.string.after_days_membership_upgrade));
                             }else {
                                 binding.tvActiveDaysToPremium.setText("After " + memberShipTypeModel.getDaysToPrem() + " " + getString(R.string.after_days_membership_upgrade));
                             }
                             binding.tvActiveDaysToPremium.setVisibility(View.VISIBLE);

                             binding.tvTrialTitle.setVisibility(View.VISIBLE);
                             binding.tvTrialDescription.setText(textBasic);
                             binding.tvTrialDescription.setVisibility(View.VISIBLE);

                             binding.tvPremiumTitle.setVisibility(View.VISIBLE);
                             binding.tvPremiumDescription.setText(textPremium);
                             binding.tvPremiumDescription.setVisibility(View.VISIBLE);
                             break;
                         case 3 :
                             binding.tvMembershipCheck.setText(R.string.membership_premium);
                             binding.tvPremiumTitle.setVisibility(View.VISIBLE);
                             binding.tvPremiumDescription.setText(textPremium);
                             binding.tvPremiumDescription.setVisibility(View.VISIBLE);
                             break;
                     }

                     binding.tvActiveDays.setText(String.valueOf(memberShipTypeModel.getActiveDays()));
                 }
           });
    }

    @Override
    public void onResume() {
        getActivity().setTitle("Membership");
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        super.onResume();
    }

}
