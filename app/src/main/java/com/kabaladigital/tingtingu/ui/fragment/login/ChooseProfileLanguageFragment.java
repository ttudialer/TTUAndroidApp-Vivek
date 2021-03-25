package com.kabaladigital.tingtingu.ui.fragment.login;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.adapter.ChooseProfileMultiLangAdapter;
import com.kabaladigital.tingtingu.databinding.ActivityChooseProfileLanguageFragmentBinding;
import com.kabaladigital.tingtingu.models.ChooseMultiLangModel;
import com.kabaladigital.tingtingu.models.ListHolderModel;

import java.util.ArrayList;
import java.util.List;

public class ChooseProfileLanguageFragment extends AppCompatActivity {
    private ActivityChooseProfileLanguageFragmentBinding binding;
    private ChooseProfileMultiLangAdapter chooseMultiLangAdapter;
    private List<ChooseMultiLangModel> chooseMultiLangModel = new ArrayList<>();
    private ArrayList<String> languageList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,
                           R.layout.activity_choose_profile_language_fragment);

        chooseMultiLangAdapter = new ChooseProfileMultiLangAdapter(this, chooseMultiLangModel);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        binding.rvMultiLanguage.setLayoutManager(mLayoutManager);
        binding.rvMultiLanguage.setItemAnimator(new DefaultItemAnimator());
        binding.rvMultiLanguage.setAdapter(chooseMultiLangAdapter);
        checkboxData();

        languageList.clear();
        binding.btnMultiLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListHolderModel.setList(languageList);
                finish();

            }
        });

        binding.checkEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.checkEng.isChecked()) {
                    getLanguageList(binding.checkEng.getText().toString());
                }
            }
        });

        binding.checkHindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.checkHindi.isChecked()) {
                    getLanguageList(binding.checkHindi.getText().toString());
                }
            }
        });

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
}
