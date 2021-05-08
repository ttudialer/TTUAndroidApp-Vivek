package com.kabaladigital.tingtingu.ImageHelper;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.VideoView;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.util.PreferenceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Activity_Gallery_Image extends Activity {

    String str_image;
    ImageView _ImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_image);
        init();
    }

    private void init() {
        _ImageView = (ImageView) findViewById(R.id.vv_Image);

        str_image = getIntent().getStringExtra("video");
        BitmapFactory.Options bfOptions=new BitmapFactory.Options();
        Bitmap bm;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(new File(str_image));

            if(fs!=null) {
                bm= BitmapFactory.decodeFileDescriptor(fs.getFD(), null, bfOptions);
                _ImageView.setImageBitmap(bm);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
