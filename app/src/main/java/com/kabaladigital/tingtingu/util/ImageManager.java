package com.kabaladigital.tingtingu.util;

import android.net.Uri;
import android.os.Handler;
import android.widget.ImageView;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.models.IncomingCallAdData;

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
                imageView.setImageURI(Uri.parse(incomingCallAdData.getUri()));
            }catch (Exception e){
                imageView.setImageResource(R.drawable.default_incoming);
            }
        }
    }
}
