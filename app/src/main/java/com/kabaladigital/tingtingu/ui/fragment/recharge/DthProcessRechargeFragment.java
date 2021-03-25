package com.kabaladigital.tingtingu.ui.fragment.recharge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.databinding.DthProcessRechargeFragmentBinding;
import com.kabaladigital.tingtingu.models.RechargeModel;
import com.kabaladigital.tingtingu.networking.RequestFormatter;
import com.kabaladigital.tingtingu.ui.activity.MainActivity;
import com.kabaladigital.tingtingu.util.Operator;
import com.kabaladigital.tingtingu.viewmodels.DthProcessRechargeViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DthProcessRechargeFragment extends Fragment {

    private DthProcessRechargeFragmentBinding binding;
    private DthProcessRechargeViewModel mViewModel;

    private String rnum,ramt,optr,type;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rnum = getArguments().getString("rnum");
            ramt = getArguments().getString("ramt");
            optr = getArguments().getString("optr");
            type = getArguments().getString("type");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.dth_process_recharge_fragment, container, false);

        binding.ivOperator.setImageResource(Operator.getOperatorIcon(optr));
        binding.tvOperatorDetail.setText(optr);

        if (type.equals("DTH")){
            binding.tvRechargeType.setText("DTH Recharge");
        }else {
            binding.tvRechargeType.setText("Prepaid Recharge");
        }
        binding.tvRechargeId.setText("Recharge No: " +rnum);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DthProcessRechargeViewModel.class);

        mViewModel.hitRechargeApi(RequestFormatter
                .jsonObjectRecharge(Long.valueOf(rnum),
                        Integer.parseInt(ramt),
                        optr,
                        type, "na"));
        getRechargeInfo();
    }

    private void getRechargeInfo() {
        mViewModel.getRechargeModelLiveData().observe(getActivity(), new Observer<RechargeModel>() {
            @Override
            public void onChanged(RechargeModel rechargeModel) {
                binding.tvPleaseWait.setVisibility(View.GONE);
                if (rechargeModel != null) {

                    if (rechargeModel.getStatusCode()==403){
                        binding.rechargeStatus.setText("Failed");
                        binding.rechargeStatus.setBackgroundColor(getActivity()
                                .getResources().getColor(R.color.red_phone));
                        binding.ivRechargeStatus.setImageResource(R.drawable.ic_cross);
//                        binding.tvRechargeId.setText("Recharge ID: " + rechargeModel.getTxnId());
                        binding.tvRechargeStatus.setText("Recharge Failed");
                    }
                    if (rechargeModel.getStatusCode()==200){
                        binding.rechargeStatus.setText("Success");
                        binding.rechargeStatus.setBackgroundColor(getActivity()
                                .getResources().getColor(R.color.green_phone));
                        binding.ivRechargeStatus.setImageResource(R.drawable.ic_success);
//                        binding.tvRechargeId.setText("Recharge No: " + rechargeModel.getTxnId());
                        binding.tvRechargeStatus.setText("Recharge Successful");
                    }
                    if (rechargeModel.getStatusCode()==201){
                        binding.rechargeStatus.setText("Pending");
                        binding.rechargeStatus.setBackgroundColor(getActivity()
                                .getResources().getColor(R.color.recharge_in_process));
                        binding.ivRechargeStatus.setImageResource(R.drawable.ic_recharge_process);
//                        binding.tvRechargeId.setText("Recharge ID: " + rechargeModel.getTxnId());
                        binding.tvRechargeStatus.setText("Recharge Pending");

                        binding.tvRechargeLineOne.setVisibility(View.VISIBLE);
                        binding.tvRechargeLineTwo.setVisibility(View.VISIBLE);
                    }
                }else {
                    binding.rechargeStatus.setText("Failed");
                    binding.rechargeStatus.setBackgroundColor(getActivity()
                            .getResources().getColor(R.color.red_phone));
                    binding.ivRechargeStatus.setImageResource(R.drawable.ic_cross);
                    binding.tvRechargeStatus.setText("Recharge Failed");
                }

                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

                binding.viewDthLine.setVisibility(View.VISIBLE);
                binding.tvRechargeDateTime.setText("Transaction Date and Time: " + currentDate + " , "+ currentTime);
            }
        });
    }

    @Override
    public void onResume() {
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        super.onResume();
    }
}
