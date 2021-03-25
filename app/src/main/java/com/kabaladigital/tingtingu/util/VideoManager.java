package com.kabaladigital.tingtingu.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.kabaladigital.tingtingu.BuildConfig;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.models.IncomingCallAdData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VideoManager {

    static MediaController mediaController;

    public static void playVideo(VideoView mPlaceholderVideo, Context context, boolean playSound, AudioManager mAudioManager){
        mediaController  = new MediaController(context);
        mediaController.setAnchorView(mPlaceholderVideo);


        String videoFile = returnRandomVideoPath(0);
        Uri u = Uri.parse(videoFile);
        mPlaceholderVideo.setVideoURI(u);

        mPlaceholderVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                if (playSound){
                    mp.setVolume(50, 50);
                }else{
                    mp.setVolume(0, 0);
                }
            }
        });

//        mPlaceholderVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mediaPlayer) {
//                Uri u = Uri.parse(videoFile);
//                mPlaceholderVideo.setVideoURI(u);
//                mPlaceholderVideo.start();
//            }
//        });

        mPlaceholderVideo.start();
    }

    public static void playVideo(VideoView mPlaceholderVideo
            , Context context, boolean playSound
            , AudioManager mAudioManager
    ,List<IncomingCallAdData> incomingCallAdData){
        mediaController  = new MediaController(context);
        mediaController.setAnchorView(mPlaceholderVideo);

        List<IncomingCallAdData> incomingCallAdData1 = new ArrayList<>();
        for (int i=0;i<incomingCallAdData.size();i++){
            if (incomingCallAdData.get(i).getAdType().equals("Video")){
                incomingCallAdData1.add(incomingCallAdData.get(i));
            }
        }

        Uri u;
        String videoFile = returnRandomVideoPath(0);
        if (incomingCallAdData1.size()>0){
            u = Uri.parse(incomingCallAdData1.get(0).getUri());
        }else {
            u = Uri.parse(videoFile);
        }

        mPlaceholderVideo.setVideoURI(u);

        mPlaceholderVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                if (playSound){
                    mp.setVolume(50, 50);
                }else{
                    mp.setVolume(0, 0);
                }
            }
        });

//        mPlaceholderVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mediaPlayer) {
//                Uri u = Uri.parse(videoFile);
//                mPlaceholderVideo.setVideoURI(u);
//                mPlaceholderVideo.start();
//            }
//        });

        mPlaceholderVideo.start();
    }

    public static void stopVideo(VideoView mPlaceholderVideo){
        mPlaceholderVideo.stopPlayback();
        mPlaceholderVideo.setVisibility(View.GONE);
    }


    private static String returnRandomVideoPath(int type){
        List<String> videoList = new ArrayList<>();

        if (type==0){
            videoList.add("android.resource://"+ BuildConfig.APPLICATION_ID +"/" + R.raw.video_4_3_default);
//            videoList.add("android.resource://"+ BuildConfig.APPLICATION_ID +"/" + R.raw.video_ad_2);
//            videoList.add("android.resource://"+ BuildConfig.APPLICATION_ID +"/" R.raw.video_ad_4);
        }

        if (type == 1){
            videoList.add("android.resource://"+ BuildConfig.APPLICATION_ID +"/"+ R.raw.video_full_default);
//            videoList.add("android.resource://"+ BuildConfig.APPLICATION_ID +"/" + R.raw.fullscreen_video_two);
//            videoList.add("android.resource://"+ BuildConfig.APPLICATION_ID +"/" + R.raw.fullscreen_video_three);
        }


        Random rand = new Random();
        int randomNumber = rand.nextInt(2);

//        return videoList.get(randomNumber);
        return videoList.get(0);
    }

    public static void pauseVideo(VideoView mPlaceholderVideo){
        mPlaceholderVideo.pause();
    }

    public static void resumeVideo(VideoView mPlaceholderVideo){
        mPlaceholderVideo.start();
    }


    public static void playFullScreenVideo(VideoView mPlaceholderVideo, Context context, boolean playSound, AudioManager mAudioManager){
        mediaController  = new MediaController(context);
        mediaController.setAnchorView(mPlaceholderVideo);


        String videoFile = returnRandomVideoPath(1);
        Uri u = Uri.parse(videoFile);
        mPlaceholderVideo.setVideoURI(u);

        mPlaceholderVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                if (playSound){
                    mp.setVolume(50, 50);
                }else{
                    mp.setVolume(0, 0);
                }
            }
        });

//        mPlaceholderVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mediaPlayer) {
//                Uri u = Uri.parse(videoFile);
//                mPlaceholderVideo.setVideoURI(u);
//                mPlaceholderVideo.start();
//            }
//        });

        mPlaceholderVideo.start();
    }


    // Full Screen Incoming Video
    public static void playFullScreenIncomingAd(VideoView mPlaceholderVideo
            , Context context, boolean playSound
            ,IncomingCallAdData incomingCallAdData){

        mediaController  = new MediaController(context);
        mediaController.setAnchorView(mPlaceholderVideo);

        Uri u;

        if (incomingCallAdData!=null){
            try {
                u = Uri.parse(incomingCallAdData.getUri());
            }catch (Exception e){
                String videoFile = returnRandomVideoPath(1);
                u = Uri.parse(videoFile);
            }
        }else {
            String videoFile = returnRandomVideoPath(1);
            u = Uri.parse(videoFile);
        }

        mPlaceholderVideo.setVideoURI(u);

        mPlaceholderVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                if (playSound){
                    mp.setVolume(50, 50);
                }else{
                    mp.setVolume(0, 0);
                }
            }
        });
        mPlaceholderVideo.start();
    }

    public static void play43IncomingAd(VideoView mPlaceholderVideo
            , Context context, boolean playSound
            ,IncomingCallAdData incomingCallAdData){
        mediaController  = new MediaController(context);
        mediaController.setAnchorView(mPlaceholderVideo);

        Uri u;
        if (incomingCallAdData!=null){
            try {
                u = Uri.parse(incomingCallAdData.getUri());
            }catch (Exception e){
                String videoFile = returnRandomVideoPath(0);
                u = Uri.parse(videoFile);
            }
        }else {
            String videoFile = returnRandomVideoPath(0);
            u = Uri.parse(videoFile);
        }

        mPlaceholderVideo.setVideoURI(u);
        mPlaceholderVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                if (playSound){
                    mp.setVolume(50, 50);
                }else{
                    mp.setVolume(0, 0);
                }
            }
        });
        mPlaceholderVideo.start();
    }
}
