package com.kabaladigital.tingtingu.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.database.AppDatabase;
import com.kabaladigital.tingtingu.database.DataRepository;
import com.kabaladigital.tingtingu.databinding.ActivityLoginBinding;
import com.kabaladigital.tingtingu.util.GetStateCityFromJson;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.util.Utilities;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Instances
        PreferenceUtils.getInstance(this,true);
        DataRepository.getInstance(AppDatabase.getDatabase(this));
        Utilities.setUpLocale(this);

        if (PreferenceUtils.getInstance().getBoolean(R.string.pref_is_login_key)){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        //Toolbar Setup
        setSupportActionBar(binding.toolbarLogin);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        GetStateCityFromJson.getCityStateData(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void showHideLogo(int Visibility) {
        binding.ivToolbarLogo.setVisibility(Visibility);
    }
}
