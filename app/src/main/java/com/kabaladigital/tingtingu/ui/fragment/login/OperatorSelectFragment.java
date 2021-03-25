package com.kabaladigital.tingtingu.ui.fragment.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.adapter.OperatorSelectAdapter;
import com.kabaladigital.tingtingu.databinding.OperatorSelectFragmentBinding;
import com.kabaladigital.tingtingu.models.OperatorSelectModel;
import com.kabaladigital.tingtingu.util.Operator;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.viewmodels.OperatorSelectViewModel;

import java.util.ArrayList;
import java.util.List;

public class OperatorSelectFragment extends Fragment {

    private OperatorSelectFragmentBinding binding;
    private OperatorSelectViewModel mViewModel;
    private OperatorSelectAdapter operatorSelectAdapter;
    private List<OperatorSelectModel> operatorSelectModels = new ArrayList<>();

    private int operatorFor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            operatorFor = getArguments().getInt("operatorFor");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.operator_select_fragment, container, false);


        operatorSelectAdapter = new OperatorSelectAdapter(operatorSelectModels,OperatorSelectFragment.this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(),3);
        binding.rvSelectOperator.setLayoutManager(mLayoutManager);
        binding.rvSelectOperator.setItemAnimator(new DefaultItemAnimator());
        binding.rvSelectOperator.setAdapter(operatorSelectAdapter);

        if (operatorFor == 0 || operatorFor == 1){
            operatorSelectModels.addAll(Operator.getMobileOperator());
        }

        if (operatorFor == 2){
            operatorSelectModels.addAll(Operator.getDTHOperator());
        }

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OperatorSelectViewModel.class);
        // TODO: Use the ViewModel
    }


    public void getOperator(String operatorName){
        if (operatorFor == 0){
            PreferenceUtils.getInstance().putString(R.string.pref_user_mobile_number_operator_name,operatorName);
        }
        if (operatorFor == 1){
            PreferenceUtils.getInstance().putString(R.string.pref_recharge_select_operator_name,operatorName);
        }
        if (operatorFor == 2){
            PreferenceUtils.getInstance().putString(R.string.pref_recharge_select_dth_operator_name,operatorName);
        }

        NavController navController = Navigation.findNavController(binding.getRoot());
        navController.popBackStack();
    }

}
