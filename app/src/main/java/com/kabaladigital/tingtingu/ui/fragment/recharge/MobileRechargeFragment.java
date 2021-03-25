package com.kabaladigital.tingtingu.ui.fragment.recharge;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.kabaladigital.tingtingu.databinding.MobileRechargeFragmentBinding;
import com.kabaladigital.tingtingu.models.RewardModel;
import com.kabaladigital.tingtingu.ui.activity.MainActivity;
import com.kabaladigital.tingtingu.util.NumberFormat;
import com.kabaladigital.tingtingu.util.Operator;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.util.Utilities;
import com.kabaladigital.tingtingu.viewmodels.RechargeViewModel;

public class MobileRechargeFragment extends Fragment {

    private MobileRechargeFragmentBinding binding;
    private RechargeViewModel mViewModel;
    private String select_Operator;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                           @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.mobile_recharge_fragment, container, false);

        select_Operator = PreferenceUtils.getInstance().getString(R.string.pref_recharge_select_operator_name);

        binding.tvCompany.setText(select_Operator);
        binding.imgOperator.setImageResource(Operator.getOperatorIcon(select_Operator));

        binding.tvDthRechargeAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) {
                binding.tvPaymentWalletText.setText(binding.tvDthRechargeAmount.getText().toString()
                        + " Points will be deducted from wallet balance points");
            }
        });

        binding.btnRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()){
                    Bundle bundle = new Bundle();
                    bundle.putString("rnum", binding.editMobileNumberOrVcNumber.getText().toString());
                    bundle.putString("ramt", binding.tvDthRechargeAmount.getText().toString());
                    bundle.putString("optr", select_Operator);
                    bundle.putString("type", "REC");
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.dthProcessRechargeFragment,bundle);
                }
            }
        });

        binding.btnChangeOperator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("operatorFor", 1);
                Navigation.findNavController(binding.getRoot()).navigate(R.id.operatorSelectFragment2,bundle);
            }
        });

        binding.imgOperator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("operatorFor", 1);
                Navigation.findNavController(binding.getRoot()).navigate(R.id.operatorSelectFragment2,bundle);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RechargeViewModel.class);

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

    private boolean isValid(){

        if (!Utilities.isNetworkAvailable(getActivity())){
            Toast.makeText(getActivity(),"No Internet Connection", Toast.LENGTH_LONG).show();
            return false;
        }

        if (binding.editMobileNumberOrVcNumber.getText().toString().length()<10){
            Toast.makeText(getActivity(), "Please Enter Correct Number", Toast.LENGTH_LONG).show();
            return false;
        }
        if (binding.tvDthRechargeAmount.getText().toString().length()<1){
            Toast.makeText(getActivity(), "Please Enter Correct Amount", Toast.LENGTH_LONG).show();
            return false;
        }

        double amount = Double.parseDouble(binding.tvDthRechargeAmount.getText().toString());
        double balance = Double.parseDouble(binding.layoutWb.tvWalletPrice.getText().toString());

        if (amount<=9){
            Toast.makeText(getActivity(), "Amount should be greater then or equal to 10", Toast.LENGTH_LONG).show();
            return false;
        }

        if (amount>balance){
            Toast.makeText(getActivity(), "Amount should be less then Balance", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    @Override
    public void onResume() {
        getActivity().setTitle("Recharge");
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        super.onResume();
    }
}
