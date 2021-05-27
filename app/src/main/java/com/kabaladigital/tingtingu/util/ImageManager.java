package com.kabaladigital.tingtingu.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.models.IncomingCallAdData;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ImageManager {

    public static void setImageAd(ImageView imageView){

        int []imageArray = {R.drawable.default_outgoing,R.drawable.progress_one
                   ,R.drawable.progress_two};

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i=0;
            public void run() {
                imageView.setImageResource(imageArray[i]);
                i++;
                if(i>imageArray.length-1) {
                    i=0;
                }
                handler.postDelayed(this, 10000);  //for interval...
            }
        };
        handler.postDelayed(runnable, 0); //for initial delay..
    }

    public static void setImageAd(ImageView imageView, List<IncomingCallAdData> incomingCallAdData){

        List<IncomingCallAdData> incomingCallAdData1 = new ArrayList<>();
        for (int i=0;i<incomingCallAdData.size();i++){
            if (incomingCallAdData.get(i).getAdType().equals("Image")){
                incomingCallAdData1.add(incomingCallAdData.get(i));
            }
        }
        int []imageArray = {R.drawable.ic_image_1,R.drawable.ic_image_2,R.drawable.ic_image_3
                ,R.drawable.ic_image_4,R.drawable.ic_image_5,R.drawable.ic_image_6};

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i=0;
            public void run() {
                if (incomingCallAdData1.size()<1){
                    imageView.setImageResource(imageArray[i]);
                    i++;
                    if(i>imageArray.length-1) {
                        i=0;
                    }
                }else {
//                    if (incomingCallAdData.get(i).getAdType().equals("Image")){
                        imageView.setImageURI(Uri.parse(incomingCallAdData1.get(i).getUri()));
//                    }else {
//                        imageView.setImageResource(imageArray[0]);
//                    }
                    i++;
                    if(i>incomingCallAdData1.size()-1) {
                        i=0;
                    }
                }

                handler.postDelayed(this, 10000);  //for interval...
            }
        };
        handler.postDelayed(runnable, 0); //for initial delay..
    }

    public static void setBannerImageAd(ImageView imageView){

        int []imageArray = {R.drawable.ticker_01,R.drawable.ticker_02,R.drawable.ticker_03};

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i=0;
            public void run() {
                imageView.setImageResource(imageArray[i]);
                i++;
                if(i>imageArray.length-1) {
                    i=0;
                }
                handler.postDelayed(this, 8000);  //for interval...
            }
        };
        handler.postDelayed(runnable, 0); //for initial delay..
    }


    // Incoming Image Ad
    public static void setIncomingCallImageAd(ImageView imageView, IncomingCallAdData incomingCallAdData){
        Random rand = new Random();

        int []imageArray = {R.drawable.ic_image_1,R.drawable.ic_image_2,R.drawable.ic_image_3
                ,R.drawable.ic_image_4,R.drawable.ic_image_5,R.drawable.ic_image_6};
        if (incomingCallAdData==null){
            imageView.setImageResource(R.drawable.default_incoming);
        }else {
            try {
                //Log.d("uri3",""+Uri.parse(incomingCallAdData.getUri()).toString());
                //imageView.setImageURI(Uri.parse(incomingCallAdData.getUri()));
                imageView.setImageResource(R.drawable.default_incoming);
            }catch (Exception e){
                imageView.setImageResource(R.drawable.default_incoming);
            }
        }
    }


    public static void setIncomingCallImageAd_2(ImageView imageView, String path, Context ctx){
        Random rand = new Random();

        int []imageArray = {R.drawable.ic_image_1,R.drawable.ic_image_2,R.drawable.ic_image_3
                ,R.drawable.ic_image_4,R.drawable.ic_image_5,R.drawable.ic_image_6};

        if (path==null){
            imageView.setImageResource(R.drawable.default_incoming);
        }else {
            try {
                File file = new File(path);
                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(file));
                imageView.setImageBitmap(b);
            }catch (Exception e){
                e.printStackTrace();
                imageView.setImageResource(R.drawable.default_incoming);
            }
        }
    }



}
