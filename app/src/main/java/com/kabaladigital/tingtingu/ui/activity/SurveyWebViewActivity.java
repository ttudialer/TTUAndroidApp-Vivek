package com.kabaladigital.tingtingu.ui.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.util.PreferenceUtils;

import static com.kabaladigital.tingtingu.networking.ApiClient.URL;
import static com.kabaladigital.tingtingu.networking.ApiClient.URL1;

public class SurveyWebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_web_view);

        Bundle extras = getIntent().getExtras();
        String surveyId = "";

        if (extras != null) {
            surveyId = extras.getString("survey_id");
        }


        String surveyUrl = URL1+"userSurvey?surveyId="
                + surveyId + "&token=" + PreferenceUtils.getInstance().getString(R.string.pref_user_token_value);

        Log.i("SurveyUrl", surveyUrl);
        WebView webView = findViewById(R.id.web_view);
        ImageButton imageButton = findViewById(R.id.ib_cross);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(surveyUrl);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit Survey")
                .setMessage("Are you sure you want to close and exit this Survey without completing?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}