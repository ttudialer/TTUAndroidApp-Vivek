package com.kabaladigital.tingtingu.ui.fragment.login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.adapter.ChooseMultiLangAdapter;
import com.kabaladigital.tingtingu.databinding.ChooseMultiLanguageFragmentBinding;
import com.kabaladigital.tingtingu.models.ChooseMultiLangModel;
import com.kabaladigital.tingtingu.ui.activity.LoginActivity;
import com.kabaladigital.tingtingu.viewmodels.ChooseMultiLanguageViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChooseMultiLanguageFragment extends Fragment {

    private ChooseMultiLanguageFragmentBinding binding;
    private ChooseMultiLanguageViewModel mViewModel;
    private ChooseMultiLangAdapter chooseMultiLangAdapter;
    private List<ChooseMultiLangModel> chooseMultiLangModel = new ArrayList<>();
    private ArrayList<String> languageList = new ArrayList<String>();
    String emailId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        emailId = getArguments().getString("emailId");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.choose_multi_language_fragment, container, false);

        ((LoginActivity) getActivity()).showHideLogo(View.VISIBLE);

        chooseMultiLangAdapter = new ChooseMultiLangAdapter(this, chooseMultiLangModel, getContext());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
        binding.rvMultiLanguage.setLayoutManager(mLayoutManager);
        binding.rvMultiLanguage.setItemAnimator(new DefaultItemAnimator());
        binding.rvMultiLanguage.setAdapter(chooseMultiLangAdapter);
        checkboxData();

        binding.btnMultiLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("languageList", languageList);
                bundle.putString("emailId",emailId);
                Log.d("languageList", bundle.toString());
//              NavDirections navDirections = ChooseMultiLanguageFragmentDirections
//                                       .actionChooseMultiLanguageFragmentToRegistrationStep2Fragment2();
                Navigation.findNavController(binding.getRoot())
                        .navigate(R.id.action_chooseMultiLanguageFragment_to_registrationStep2Fragment2, bundle);

            }
        });

        binding.checkEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.checkEng.isChecked()) {
                    //languageList.add("English");
                    getLanguageList(binding.checkEng.getText().toString());

                }
            }
        });

        binding.checkHindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.checkHindi.isChecked()) {
                    // languageList.add("Hindi");
                    getLanguageList(binding.checkHindi.getText().toString());

                }

            }
        });

        return binding.getRoot();
    }

    public void getLanguageList(String language) {
        languageList.add(language);
    }

    private void checkboxData() {
        ChooseMultiLangModel one = new ChooseMultiLangModel("874201", "Telgu ", "తెలుగు", "checked");
        chooseMultiLangModel.add(one);

        ChooseMultiLangModel two = new ChooseMultiLangModel("874202", "Marathi", "मराठी", "unchecked");
        chooseMultiLangModel.add(two);

        ChooseMultiLangModel three = new ChooseMultiLangModel("874203", "Tamil ", "தமிழ்", "unchecked");
        chooseMultiLangModel.add(three);

        ChooseMultiLangModel four = new ChooseMultiLangModel("874204", "Punjabi", "ਪੰਜਾਬੀ", "checked");
        chooseMultiLangModel.add(four);

        ChooseMultiLangModel five = new ChooseMultiLangModel("874205", "Urdu", "اردو", "unchecked");
        chooseMultiLangModel.add(five);

        ChooseMultiLangModel six = new ChooseMultiLangModel("874206", "Gujrati", "ગુજરાતી", "unchecked");
        chooseMultiLangModel.add(six);

        ChooseMultiLangModel seven = new ChooseMultiLangModel("874207", "Karnataka", "ಕರ್ನಾಟಕ", "unchecked");
        chooseMultiLangModel.add(seven);

        ChooseMultiLangModel eight = new ChooseMultiLangModel("874208", "Bangali", "বাঙালি", "unchecked");
        chooseMultiLangModel.add(eight);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ChooseMultiLanguageViewModel.class);
        // TODO: Use the ViewModel
    }


}
