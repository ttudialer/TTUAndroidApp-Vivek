package com.kabaladigital.tingtingu.VideoHelper;

import android.app.Activity;
import android.os.Bundle;
import android.widget.VideoView;

import com.kabaladigital.tingtingu.R;

public class Activity_galleryview extends Activity {

    String str_video;
    VideoView vv_video;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galleryview);
        init();
    }

    private void init() {
        vv_video = (VideoView) findViewById(R.id.vv_video);

        str_video = getIntent().getStringExtra("video");
        vv_video.setVideoPath(str_video);
        vv_video.start();

    }


}
