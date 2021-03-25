package com.kabaladigital.tingtingu.ui.fragment.recharge;

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
import com.kabaladigital.tingtingu.databinding.RechargeAmountFragmentBinding;
import com.kabaladigital.tingtingu.viewmodels.RechargeAmountViewModel;

public class RechargeAmountFragment extends Fragment {

    private RechargeAmountViewModel mViewModel;
    RechargeAmountFragmentBinding binding;

    public static RechargeAmountFragment newInstance() {
        return new RechargeAmountFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.recharge_amount_fragment, container, false);

        binding.tvMobileNumber.setText(getArguments().getString("recharge_mobileNumber"));
       // binding.etEnterRechargeAmount.setHint(R.string.redeem_rs_symbol + "Amount");

        return binding.getRoot();
     }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RechargeAmountViewModel.class);
        // TODO: Use the ViewModel
    }

}
