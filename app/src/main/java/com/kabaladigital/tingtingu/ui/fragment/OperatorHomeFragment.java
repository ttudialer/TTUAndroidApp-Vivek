package com.kabaladigital.tingtingu.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.adapter.OperatorHomeAdapter;
import com.kabaladigital.tingtingu.database.AppDatabase;
import com.kabaladigital.tingtingu.database.DataRepository;
import com.kabaladigital.tingtingu.databinding.OperatorHomeFragmentBinding;
import com.kabaladigital.tingtingu.models.GlobalVariableModel;
import com.kabaladigital.tingtingu.models.OperatorHomeModel;
import com.kabaladigital.tingtingu.models.RewardModel;
import com.kabaladigital.tingtingu.models.TrailMembershipCheck;
import com.kabaladigital.tingtingu.networking.RequestFormatter;
import com.kabaladigital.tingtingu.ui.activity.LoginActivity;
import com.kabaladigital.tingtingu.ui.activity.MainActivity;
import com.kabaladigital.tingtingu.util.Installation;
import com.kabaladigital.tingtingu.util.NumberFormat;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.util.Utilities;
import com.kabaladigital.tingtingu.viewmodels.OperatorHomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class OperatorHomeFragment extends Fragment {

    private OperatorHomeViewModel mViewModel;
    private OperatorHomeFragmentBinding binding;
    private List<OperatorHomeModel>operatorHomeModelList=new ArrayList<>();
    private OperatorHomeAdapter operatorHomeAdapter;
    private String langType;
    private String membershipType;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.operator_home_fragment, container, false);

        operatorHomeAdapter=new OperatorHomeAdapter(operatorHomeModelList,getContext(),
                      OperatorHomeFragment.this);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        binding.rvOperatorHome.setLayoutManager(layoutManager);
        binding.rvOperatorHome.setAdapter(operatorHomeAdapter);
        operatorHomeData();

        binding.tvRedeemPoint.setOnClickListener(v -> {
            if (membershipType!=null){
//                if (membershipType.equals("3")){
                    if (Utilities.isNetworkAvailable(getActivity())){
                        NavDirections navDirections = OperatorHomeFragmentDirections.actionOperatorHomeFragmentToReedemPointFragment();
                        Navigation.findNavController(binding.getRoot()).navigate(navDirections);
                    }else {
                        Toast.makeText(getActivity(), "Internet Required", Toast.LENGTH_SHORT).show();
                    }
//                }else {
//                    Toast.makeText(getActivity(), "You cannot Redeem points only "
//                            + getString(R.string.membership_premium) + " can redeem.", Toast.LENGTH_SHORT).show();
//                }
            }else {
                Toast.makeText(getActivity(), "Please wait and try again..", Toast.LENGTH_SHORT).show();
            }
        });

        showCount();

        return binding.getRoot();
    }

    private void showCount() {
        DataRepository repository = DataRepository
                .getInstance(AppDatabase.getDatabase(getActivity()));
        int activeCount = repository.getCount();
        String lastSyncDate = repository.getLastSyncDate();
        String lastSyncDateTime = repository.getLastSyncDateTime();

        binding.tvDailyCount.setText("Active count : "+ String.valueOf(activeCount)
                + " Last count update datetime : " + lastSyncDateTime + " Count date "+ lastSyncDate);
    }

    @Override
    public void onResume() {
        getActivity().setTitle("");
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OperatorHomeViewModel.class);

        mViewModel.checkDeviceId(RequestFormatter.jsonObjectCheckDeviceID(Installation.id(getActivity())));
        getCheckDeviceId();

        mViewModel.hitMemberShipType();
        getMemberShip();

        mViewModel.hitWalletBalanceApi();
        getWalletBalanceApi();

        mViewModel.hitGlobalVariableApi();
        getGlobalApi();


    }

    private void getCheckDeviceId() {
        mViewModel.getCheckDeviceIdLiveData().observe(getActivity(), new Observer<TrailMembershipCheck>() {
            @Override
            public void onChanged(TrailMembershipCheck trailMembershipCheck) {
                if (trailMembershipCheck != null) {
                    if (!trailMembershipCheck.getResponse()){
                        PreferenceUtils.getInstance().clear();
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        getActivity().startActivity(intent);
                        getActivity().finish();
                    }
                }
            }
        });
    }

    private void getGlobalApi() {
        mViewModel.getGlobalVariableModelLiveData().observe(getActivity(), new Observer<GlobalVariableModel>() {
            @Override
            public void onChanged(GlobalVariableModel globalVariableModels) {
                if (globalVariableModels != null) {
                    PreferenceUtils.getInstance().putInt(R.string.pref_daily_threshold_value
                            , globalVariableModels.getDailyThreshold());

                    PreferenceUtils.getInstance().putString(R.string.pref_referrer_points
                            , String.valueOf(globalVariableModels.getRefer().getReferrerPoints()));

                    PreferenceUtils.getInstance().putString(R.string.pref_referee_points
                            , String.valueOf(globalVariableModels.getRefer().getRefereePoints()));

                    PreferenceUtils.getInstance().putString(R.string.pref_referrer_reward_days
                            , String.valueOf(globalVariableModels.getRefer().getReferrerRewardDays()));

                }
            }
        });
    }

    private void getWalletBalanceApi() {
        mViewModel.getWalletBalanceModelLiveData().observe(getActivity(), new Observer<RewardModel>() {
            @Override
            public void onChanged(RewardModel rewardModel) {
                if (rewardModel != null) {
                    binding.tvRedeemValue.setText(NumberFormat.decimalFormat(rewardModel.getBalance()));
                }else {
                    binding.tvRedeemValue.setText("0");
                }
            }
        });
    }

    private void getMemberShip() {
        mViewModel.getMemberShipTypeLiveData().observe(getActivity(), memberShipTypeModel -> {
            if (memberShipTypeModel != null){
                String getType = String.valueOf(memberShipTypeModel.getMembership());
                membershipType = getType;
                if (getType.equals("1")){
                    binding.tvOperatorTitle.setText(R.string.membership_trial);
                }
                if (getType.equals("2")){
                    binding.tvOperatorTitle.setText(R.string.membership_basic);
                }
                if (getType.equals("3")){
                    binding.tvOperatorTitle.setText(R.string.membership_premium);
                }
            }
        });
    }

    private void operatorHomeData() {
        langType=PreferenceUtils.getInstance().getString(R.string.pref_user_selected_language_key);
        if (langType.equals("en")) {
            operatorHomeModelList.clear();

            OperatorHomeModel operator0= new OperatorHomeModel("0", "Your Caller Photo/Video TTU ID", R.drawable.ic_membership_home);
            operatorHomeModelList.add(operator0);

            OperatorHomeModel operator1 = new OperatorHomeModel("1", "View Membership Details", R.drawable.ic_membership_home);
            operatorHomeModelList.add(operator1);

            OperatorHomeModel operator2 = new OperatorHomeModel("2", "Invite Friend & Earn", R.drawable.share);
            operatorHomeModelList.add(operator2);

            OperatorHomeModel operator3 = new OperatorHomeModel("3", "Earned Points History", R.drawable.icon);
            operatorHomeModelList.add(operator3);

            OperatorHomeModel operator4 = new OperatorHomeModel("4", "Update Profile", R.drawable.user);
            operatorHomeModelList.add(operator4);

            OperatorHomeModel operator5 = new OperatorHomeModel("5", "Setting", R.drawable.settings);
            operatorHomeModelList.add(operator5);

            OperatorHomeModel operator6 = new OperatorHomeModel("6", "Survey", R.drawable.survey);
            operatorHomeModelList.add(operator6);

        }else if (langType.equals("hi")){
            operatorHomeModelList.clear();

            OperatorHomeModel operator0 = new OperatorHomeModel("0", "आपका कॉलर फोटो/वीडियो टीटीयू आईडी", R.drawable.ic_membership_home);
            operatorHomeModelList.add(operator0);

            OperatorHomeModel operator1 = new OperatorHomeModel("1", "सदस्यता विवरण देखें", R.drawable.ic_membership_home);
            operatorHomeModelList.add(operator1);

            OperatorHomeModel operator2 = new OperatorHomeModel("2", "दोस्त को आमंत्रित करें और कमाएं", R.drawable.share);
            operatorHomeModelList.add(operator2);

            OperatorHomeModel operator3 = new OperatorHomeModel("3", "कमाए पॉइंट्स की जानकारी", R.drawable.icon);
            operatorHomeModelList.add(operator3);

            OperatorHomeModel operator4 = new OperatorHomeModel("4", "प्रोफ़ाइल अपडेट करें", R.drawable.user);
            operatorHomeModelList.add(operator4);

            OperatorHomeModel operator5 = new OperatorHomeModel("5", "सेटिंग करें", R.drawable.settings);
            operatorHomeModelList.add(operator5);

            OperatorHomeModel operator6 = new OperatorHomeModel("6", "सर्वेक्षण", R.drawable.survey);
            operatorHomeModelList.add(operator6);
        }
    }

    public void getInviteEarn(String pos){
        if (Utilities.isNetworkAvailable(getActivity())){
            if (pos.equals("0")){
                NavDirections navDirections = OperatorHomeFragmentDirections.actionOperatorHomeFragmentToViewcallerphotovideo();
                Navigation.findNavController(binding.getRoot()).navigate(navDirections);
            }
            if (pos.equals("1")){
                NavDirections navDirections = OperatorHomeFragmentDirections.actionOperatorHomeFragmentToMemberShipFragment();
                Navigation.findNavController(binding.getRoot()).navigate(navDirections);
            }
            if (pos.equals("2")){
                NavDirections navDirections = OperatorHomeFragmentDirections.actionOperatorHomeFragmentToInviteFriendFragment();
                Navigation.findNavController(binding.getRoot()).navigate(navDirections);
            }
            if (pos.equals("3")){
                NavDirections navDirections = OperatorHomeFragmentDirections.actionOperatorHomeFragmentToEarnedPointHistoryFragment();
                Navigation.findNavController(binding.getRoot()).navigate(navDirections);
            }
            if (pos.equals("4")){
                NavDirections navDirections = OperatorHomeFragmentDirections.actionOperatorHomeFragmentToProfileFragment();
                Navigation.findNavController(binding.getRoot()).navigate(navDirections);
            }
            if (pos.equals("6")){
                NavDirections navDirections = OperatorHomeFragmentDirections.actionOperatorHomeFragmentToSurveyFragment();
                Navigation.findNavController(binding.getRoot()).navigate(navDirections);
            }
        }else {
            Toast.makeText(getActivity(), "Internet Required", Toast.LENGTH_SHORT).show();
        }

        if (pos.equals("5")){
            NavDirections navDirections = OperatorHomeFragmentDirections.actionOperatorHomeFragmentToSettingFragment();
            Navigation.findNavController(binding.getRoot()).navigate(navDirections);
        }

    }

}
