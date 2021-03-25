package com.kabaladigital.tingtingu.ui.fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.databinding.CongoRegistrartionFragmentBinding;
import com.kabaladigital.tingtingu.ui.activity.LoginActivity;
import com.kabaladigital.tingtingu.ui.activity.MainActivity;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.viewmodels.CongoRegistrartionViewModel;

public class CongoRegistrartionFragment extends Fragment {

    private CongoRegistrartionFragmentBinding binding;
    private CongoRegistrartionViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.congo_registrartion_fragment, container, false);

        ((LoginActivity)getActivity()).showHideLogo(View.GONE);

        binding.btnOpenLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceUtils.getInstance().putBoolean(
                        R.string.pref_is_login_key,true);
                getActivity().finish();
            }
        });

        Glide.with(this).asGif().load(R.raw.animated_logo).into(binding.imageGif);


        binding.btnOpenNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceUtils.getInstance().putBoolean(
                        R.string.pref_is_login_key,true);
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

       return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CongoRegistrartionViewModel.class);
        // TODO: Use the ViewModel
    }

}
