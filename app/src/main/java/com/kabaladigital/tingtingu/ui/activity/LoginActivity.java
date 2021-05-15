package com.kabaladigital.tingtingu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.kabaladigital.tingtingu.Class.Global;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.database.AppDatabase;
import com.kabaladigital.tingtingu.database.DataRepository;
import com.kabaladigital.tingtingu.databinding.ActivityLoginBinding;
import com.kabaladigital.tingtingu.util.CallManager;
import com.kabaladigital.tingtingu.util.GetStateCityFromJson;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.util.Utilities;

import java.io.File;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Instances
        PreferenceUtils.getInstance(this,true);
        DataRepository.getInstance(AppDatabase.getDatabase(this));
        Utilities.setUpLocale(this);
        this.mContext = this;
        Global.TTULibraryImage(this) ;
        Global.TTULibraryVideo(this) ;
        Global.TTULibraryProfile(this) ;


        //startActivity(new Intent(this, MainActivity.class));
        //finish();



        //Log.d("state$$$", ""+CallManager.getState());



        if(CallManager.getState() == 4)
        {
            startActivity(new Intent(this, OngoingCallActivity.class));
        }
        else if(CallManager.getState() == 7){
            if (PreferenceUtils.getInstance().getBoolean(R.string.pref_is_login_key)){
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        }
        else{
            if (PreferenceUtils.getInstance().getBoolean(R.string.pref_is_login_key)){
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
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
