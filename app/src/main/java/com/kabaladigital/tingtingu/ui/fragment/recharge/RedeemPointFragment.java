package com.kabaladigital.tingtingu.ui.fragment.recharge;

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
import androidx.navigation.Navigation;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.databinding.RedeemPointFragmentBinding;
import com.kabaladigital.tingtingu.models.RewardModel;
import com.kabaladigital.tingtingu.ui.activity.MainActivity;
import com.kabaladigital.tingtingu.util.NumberFormat;
import com.kabaladigital.tingtingu.viewmodels.RedeemPointViewModel;

public class RedeemPointFragment extends Fragment {

    private RedeemPointViewModel mViewModel;
    RedeemPointFragmentBinding binding;

    public static RedeemPointFragment newInstance() {
        return new RedeemPointFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.redeem_point_fragment, container, false);

        binding.btnRedeemContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                if (binding.radioBtnMobileRecharge.isChecked()){
                    bundle.putInt("rechargeFor", 0);
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.rechargeHistoryFragment,bundle);
                }else if (binding.radioBtnDthRecharge.isChecked()){
                    bundle.putInt("rechargeFor", 1);
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.rechargeHistoryFragment,bundle);

                }else {
                    Toast.makeText(getActivity(), "Choose one of the Recharge!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RedeemPointViewModel.class);

        mViewModel.hitWalletBalanceApi();
        getWalletBalanceApi();
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
        getActivity().setTitle("Redeem Points");
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        super.onResume();
    }

}
