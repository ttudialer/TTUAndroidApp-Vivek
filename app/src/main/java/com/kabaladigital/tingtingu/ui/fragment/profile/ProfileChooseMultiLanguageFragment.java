package com.kabaladigital.tingtingu.ui.fragment.profile;

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
import com.kabaladigital.tingtingu.databinding.ProfileChooseMultiLanguageFragmentBinding;
import com.kabaladigital.tingtingu.viewmodels.ProfileChooseMultiLanguageViewModel;

public class ProfileChooseMultiLanguageFragment extends Fragment {
    private ProfileChooseMultiLanguageFragmentBinding binding;
    private ProfileChooseMultiLanguageViewModel mViewModel;

    public static ProfileChooseMultiLanguageFragment newInstance() {
        return new ProfileChooseMultiLanguageFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                          @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.profile_choose_multi_language_fragment, container, false);

        binding.textViewLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                NavDirections navDirections = ProfileChooseMultiLanguageFragmentDirections
//                                         .actionProfileChooseMultiLanguageFragment2ToProfileStep2Fragment2();
//                Navigation.findNavController(binding.getRoot())
//                        .navigate(navDirections);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfileChooseMultiLanguageViewModel.class);
        // TODO: Use the ViewModel
    }

}
