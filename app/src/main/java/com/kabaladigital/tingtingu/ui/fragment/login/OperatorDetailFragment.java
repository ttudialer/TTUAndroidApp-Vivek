package com.kabaladigital.tingtingu.ui.fragment.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.databinding.OperatorDetailFragmentBinding;
import com.kabaladigital.tingtingu.models.MobileInfoModel;
import com.kabaladigital.tingtingu.networking.RequestFormatter;
import com.kabaladigital.tingtingu.ui.activity.LoginActivity;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.viewmodels.OperatorDetailViewModel;

public class OperatorDetailFragment extends Fragment {

    private OperatorDetailFragmentBinding binding;
    private OperatorDetailViewModel mViewModel;

    private String mNum,newNum,select_Operator;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.operator_detail_fragment, container, false);
       // getImgOperator();

        try {
            ((LoginActivity)getActivity()).showHideLogo(View.VISIBLE);
        }catch (Exception e){

        }

        select_Operator = PreferenceUtils.getInstance().getString(R.string.pref_user_mobile_number_operator_name);


        mNum = PreferenceUtils.getInstance().getString(R.string.pref_user_mobile_number_key);
        binding.tvOperatorMobile.setText("+91 "+ mNum);

        binding.btnChangeOperator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("operatorFor", 0);
                Navigation.findNavController(binding.getRoot()).navigate(R.id.operatorSelectFragment,bundle);
            }
        });

        binding.imgOperator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("operatorFor", 0);
                Navigation.findNavController(binding.getRoot()).navigate(R.id.operatorSelectFragment,bundle);
            }
        });

     //select Operator show and their name:
        if (select_Operator.equals("Jio")){
            binding.imgOperator.setImageResource(R.drawable.jioicon);
            binding.tvTextOperatorName.setText("Jio");
        }else if (select_Operator.equals("Airtel")){
            binding.imgOperator.setImageResource(R.drawable.airtel);
            binding.tvTextOperatorName.setText("Airtel");
        }else if (select_Operator.equals("BSNL")){
            binding.imgOperator.setImageResource(R.drawable.bsnl);
            binding.tvTextOperatorName.setText("BSNL");
        }else if (select_Operator.equals("Vodafone")){
            binding.imgOperator.setImageResource(R.drawable.voda);
            binding.tvTextOperatorName.setText("Vodafone");
        }else if (select_Operator.equals("Idea")){
            binding.imgOperator.setImageResource(R.drawable.idea);
            binding.tvTextOperatorName.setText("Idea");
        }else if (select_Operator.equals("MNTL")){
            binding.imgOperator.setImageResource(R.drawable.mtnl);
            binding.tvTextOperatorName.setText("MTNL");
        }else {
            binding.imgOperator.setImageResource(R.drawable.mtnl);
        }

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()){
                    sendUpdateMobileNumberInformationApi();

                    // nextFragments();
                }
            }
        });

        binding.radioGroupPrepaidPostpaid.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = binding.radioGroupPrepaidPostpaid.findViewById(i);
                int index = binding.radioGroupPrepaidPostpaid.indexOfChild(radioButton);
                switch (index) {
                    case 0: // prepaid
                        PreferenceUtils.getInstance().putInt(R.string.pref_user_mobile_number_type_key
                                , 0);
                        break;
                    case 1: // postpaid
                        PreferenceUtils.getInstance().putInt(R.string.pref_user_mobile_number_type_key
                                , 1);
                }
            }
        });

        return binding.getRoot();
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OperatorDetailViewModel.class);
        // TODO: Use the ViewModel
    }

    private boolean isValid() {
        if (binding.radioGroupPrepaidPostpaid.getCheckedRadioButtonId() == -1){
            Toast.makeText(getContext(), "Choose Prepaid or Postpaid!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void sendUpdateMobileNumberInformationApi() {
        mViewModel.updateOperatorDetail(
                RequestFormatter.jsonUpdateMobileNumberInfo(select_Operator,
                        binding.tvTextOperatorName.getText().toString(),
                        PreferenceUtils.getInstance().getInt(R.string.pref_user_mobile_number_type_key)));

        getMobileNumberInformationApi();

    }

    private void getMobileNumberInformationApi() {
        mViewModel.getVoidOperatorDetailLiveData().observe(this, new Observer<MobileInfoModel>() {
            @Override
            public void onChanged(MobileInfoModel mobileInfoModel) {
                if (mobileInfoModel != null){
                    Toast.makeText(getActivity(),""+mobileInfoModel.getMessage(),
                            Toast.LENGTH_SHORT).show();

                    nextFragments();
                }
            }
        });
    }

    private void nextFragments() {
        PreferenceUtils.getInstance().putBoolean(
                            R.string.pref_is_operator_detail_filled_key,true);

        NavDirections navDirections = OperatorDetailFragmentDirections
                .actionOperatorDetailFragmentToRegistrationFragment();
        Navigation.findNavController(binding.getRoot()).navigate(navDirections);
    }


}
