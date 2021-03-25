package com.kabaladigital.tingtingu.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.kabaladigital.tingtingu.database.AppDatabase;
import com.kabaladigital.tingtingu.database.DataRepository;
import com.kabaladigital.tingtingu.models.IncomingCallAdData;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ImageVideoDownloadManager {

    private Context mContext;
    private List<IncomingCallAdData> incomingCallAdData;
    private DataRepository mRepository;

    public ImageVideoDownloadManager(Context mContext) {
        this.mContext = mContext;
        this.incomingCallAdData = new ArrayList<>();
        mRepository = DataRepository.getInstance(AppDatabase.getDatabase(mContext));
        this.run();
    }

    private void run(){
        incomingCallAdData = mRepository.getAdsListForURi();

        for (int i = 0; i<incomingCallAdData.size();i++){
            if (incomingCallAdData.get(i).getAdType().equals("Image")){
                downloadImage(incomingCallAdData.get(i));
            }

            if (incomingCallAdData.get(i).getAdType().equals("Video")) {
                new DownloadVideoFile(mRepository
                        ,incomingCallAdData.get(i).getUploadFile()
                        ,incomingCallAdData.get(i).getCampId(),mContext)
                        .execute();
            }
        }
    }

    private void downloadImage(IncomingCallAdData incomingCallAdData){

        Glide.with(mContext)
                .asBitmap()
                .load(incomingCallAdData.getUploadFile())
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        String savedImagePath = null;

                        String format = "";
                        try {
                            format = incomingCallAdData.getUploadFile().substring(incomingCallAdData.getUploadFile().length() - 3);
                        }catch (Exception e){
                            format = "jpg";
                        }

                        String imageFileName = "JPEG_" + incomingCallAdData.getCampId() + "." + format;

//                        File storageDir = new File(Environment.getExternalStoragePublicDirectory
//                                (Environment.DIRECTORY_DOWNLOADS) + File.separator + "Images");
                        File storageDir = mContext.getFilesDir();
                        boolean success = true;
                        if (!storageDir.exists()) {
                            success = storageDir.mkdirs();
                        }
                        if (success) {
                            File imageFile = new File(storageDir, imageFileName);
                            savedImagePath = imageFile.getAbsolutePath();
                            try {
                                OutputStream fOut = new FileOutputStream(imageFile);
                                resource.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                                fOut.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        mRepository.updateAd(savedImagePath,incomingCallAdData.getCampId());
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        Log.i("Image", "onLoadCleared: ");
                    }
                });
    }



    public static class DownloadVideoFile extends AsyncTask<String, String, String> {

        DataRepository mRepository;
        String videoUrl;
        String ID;
        Context inContext;

        private DownloadVideoFile(DataRepository mRepository, String videoUrl, String ID, Context mContext) {
            this.mRepository = mRepository;
            this.videoUrl=videoUrl;
            this.ID = ID;
            this.inContext = mContext;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            int count;
            try {
                String videoFileName = "VIDEO_" + ID + ".mp4";
                String savedVideoPath = null;

//                File storageDir = new File(Environment.getExternalStoragePublicDirectory
//                        (Environment.DIRECTORY_DOWNLOADS) + File.separator + "Videos");
                File storageDir =  inContext.getFilesDir();
                boolean success = true;
                if (!storageDir.exists()) {
                    success = storageDir.mkdirs();
                }
                if (success) {

                    URL url = new URL(videoUrl);

                    URLConnection connection = url.openConnection();
                    connection.connect();

                    // input stream to read file - with 8k buffer
                    InputStream input = new BufferedInputStream(url.openStream(), 8192);

                    File videoFile = new File(storageDir, videoFileName);
                    savedVideoPath = storageDir.getAbsolutePath().concat("/"+videoFileName);

                    // Output stream to write file
                    OutputStream output = new FileOutputStream(videoFile);
                    byte data[] = new byte[1024];

                    long total = 0;
                    while ((count = input.read(data)) != -1) {
                        total += count;
                        output.write(data, 0, count);
                    }
                    // flushing output
                    output.flush();

                    // closing streams
                    output.close();
                    input.close();

                    mRepository.updateAd(savedVideoPath,ID);

                }

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
