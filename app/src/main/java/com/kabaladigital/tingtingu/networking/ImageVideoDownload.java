package com.kabaladigital.tingtingu.networking;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.kabaladigital.tingtingu.database.DataRepository;
import com.kabaladigital.tingtingu.ui.activity.MainActivity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import static com.google.firebase.crashlytics.internal.Logger.TAG;

public class ImageVideoDownload {
    private Context mContext;
    private String _downloadUrl;
    private String _saveUrl;
    private String _downloadType;

    public ImageVideoDownload(Context mContext) {
        this.mContext = mContext;
        this.run();
    }
    public ImageVideoDownload(Context mContext,String downloadUrl,String SaveUrl,String DownloadType) {
        this.mContext = mContext;
        this._downloadUrl=downloadUrl;
        this._saveUrl=SaveUrl;
        this._downloadType=DownloadType;
        this.run();
    }

    private void run(){
        if(_downloadType.equalsIgnoreCase("image")){
            downloadImage(_downloadUrl);
        } else if(_downloadType.equalsIgnoreCase("video")){
            new ImageVideoDownload.DownloadVideoFile( mContext, _downloadUrl,_saveUrl,_downloadType)
                    .execute();
        }
    }

    private void downloadImage(String _downloadUrl){
        Glide.with(mContext)
                .asBitmap()
                .load(_downloadUrl)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

                        boolean success = true;
                        Log.i(TAG, "Name: " + _saveUrl);
                        //Toast.makeText(mContext, ""+_saveUrl, Toast.LENGTH_SHORT).show();
                        if (success) {
                            File imageFile = new File(_saveUrl);
                            try {
                                OutputStream fOut = new FileOutputStream(imageFile);
                                resource.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                                fOut.close();
                                //Toast.makeText(mContext, "bitmap", Toast.LENGTH_SHORT).show();

                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(mContext, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        Log.i("Image", "onLoadCleared: ");
                    }
                });
    }


    public static class DownloadVideoFile extends AsyncTask<String, String, String> {
        private Context mContext;
        private DataRepository mRepository;
        private String _downloadUrl;
        private String _saveUrl;
        private String _downloadType;

        private DownloadVideoFile(Context mContext,String downloadUrl,String SaveUrl,String DownloadType) {
            this.mContext = mContext;
            this._downloadUrl=downloadUrl;
            this._saveUrl=SaveUrl;
            this._downloadType=DownloadType;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            int count;
            try {
                boolean success = true;
                if (success) {
                    URL url = new URL(_downloadUrl);
                    URLConnection connection = url.openConnection();
                    connection.connect();

                    // input stream to read file - with 8k buffer
                    InputStream input = new BufferedInputStream(url.openStream(), 8192);
                    File videoFile = new File(_saveUrl);
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
