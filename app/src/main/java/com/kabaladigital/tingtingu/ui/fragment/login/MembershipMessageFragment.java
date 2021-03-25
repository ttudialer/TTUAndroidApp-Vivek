package com.kabaladigital.tingtingu.ui.fragment.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.databinding.FragmentMembershipMessageBinding;
import com.kabaladigital.tingtingu.ui.activity.LoginActivity;

public class MembershipMessageFragment extends Fragment {

    FragmentMembershipMessageBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_membership_message, container, false);

        ((LoginActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((LoginActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.btnNext.setOnClickListener(v -> {
            nextFragment();
        });

        return binding.getRoot();

    }

    private void nextFragment(){
        NavDirections navDirections = MembershipMessageFragmentDirections.actionMembershipMessageFragmentToLoginFragment();
        Navigation.findNavController(binding.getRoot()).navigate(navDirections);
    }
}