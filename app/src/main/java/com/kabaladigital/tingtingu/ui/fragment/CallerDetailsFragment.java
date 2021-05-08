package com.kabaladigital.tingtingu.ui.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.databinding.CallerDetailsFragmentBinding;

import com.kabaladigital.tingtingu.models.ProfileInformationModel;

import com.kabaladigital.tingtingu.ui.activity.MainActivity;
import com.kabaladigital.tingtingu.ui.fragment.profile.ProfileStep1Fragment;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.viewmodels.ProfileStep1ViewModel;


import java.io.File;

import static com.kabaladigital.tingtingu.util.TitleCase.toTitleCase;

public class CallerDetailsFragment extends Fragment {

    private CallerDetailsFragmentBinding binding;
    private ProfileStep1ViewModel mViewModel;
    private String langType;
    private TextView tv_name;
    private TextView tv_mobile;

    public static ProfileStep1Fragment newInstance() {
        return new ProfileStep1Fragment();
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.caller_details_fragment, container, false);
        langType = PreferenceUtils.getInstance().getString(R.string.pref_user_selected_language_key);



        binding.btnUpdateTtuId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                Navigation.findNavController(binding.getRoot())
                        .navigate(R.id.action_viewcallerphotovideo_to_viewcalleridchoose);


            }
        });


        return binding.getRoot();
    }



    private void getProfileInformation() {
        mViewModel = ViewModelProviders.of(this).get(ProfileStep1ViewModel.class);
        mViewModel.hitProfileInformation();
        mViewModel.getProfileInformationModelLiveData().observe(getActivity(), new Observer<ProfileInformationModel>() {
            @Override
            public void onChanged(ProfileInformationModel profileInformationModel) {
                if (profileInformationModel != null){
                    binding.tvMobile.setText(String.valueOf(profileInformationModel.getMobileInfo().getMobileNumber()).toUpperCase());
                    binding.tvName.setText(toTitleCase(profileInformationModel.getFullName()));
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getProfileInformation();

        String p_path=PreferenceUtils.getInstance().getString(R.string.pref_profile_path);
        File fs=new File(p_path);
        if (p_path != null) {
            if (fs.exists()) {
                Uri uri = Uri.parse(p_path);
                binding.VideoView1.setVideoURI(uri);
                binding.VideoView1.requestFocus();
                binding.VideoView1.start();
            }
        }
    }

    @Override
    public void onResume() {
        getActivity().setTitle("Caller Details");
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        super.onResume();
    }
}
