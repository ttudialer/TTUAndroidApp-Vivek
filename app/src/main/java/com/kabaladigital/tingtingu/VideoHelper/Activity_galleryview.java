package com.kabaladigital.tingtingu.VideoHelper;

import android.app.Activity;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.room.util.FileUtil;

import com.kabaladigital.tingtingu.Class.Global;
import com.kabaladigital.tingtingu.ImageHelper.Activity_Gallery_Image;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.models.LibraryAddModel;
import com.kabaladigital.tingtingu.networking.ApiClient;
import com.kabaladigital.tingtingu.networking.ApiInterface;
import com.kabaladigital.tingtingu.util.PreferenceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_galleryview extends Activity {

    String str_video;
    VideoView vv_video;
    Button _btn_update_ttu_set_default;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galleryview);
        init();
        _btn_update_ttu_set_default.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                File videoFile = saveVideoToInternalStorage();
                Log.d("setpath", videoFile.getAbsolutePath()+".mp4");
                Bundle bundle = new Bundle();
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("selectedFile",  videoFile.getAbsolutePath())
                        .addFormDataPart("isProfile", "true")
                        .build();
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), videoFile);
                MultipartBody.Part body = MultipartBody.Part.createFormData("selectedFile", videoFile.getAbsolutePath(), requestFile);
                RequestBody fullName =  RequestBody.create(MediaType.parse("multipart/form-data"), "true");

                ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
                Call<LibraryAddModel> call = apiInterface.LibraryAdd(body,fullName);
                call.enqueue(new Callback<LibraryAddModel>() {
                    @Override
                    public void onResponse(Call<LibraryAddModel> call,
                                           Response<LibraryAddModel> response) {

                        if (response.code() == 200) {
                            Log.d("code",""+ videoFile.getAbsolutePath());
                            PreferenceUtils.getInstance().putString(R.string.pref_profile_path,  videoFile.getAbsolutePath());
                            Toast.makeText(Activity_galleryview.this,"Success",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<LibraryAddModel> call, Throwable t) {
                        Toast.makeText(Activity_galleryview.this, "onFailure= "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    private void init() {
        vv_video = (VideoView) findViewById(R.id.vv_video);
        _btn_update_ttu_set_default= (Button) findViewById(R.id.btn_update_ttu_set_default);
        str_video = getIntent().getStringExtra("video");
        vv_video.setVideoPath(str_video);
        vv_video.start();

    }

    private File  saveVideoToInternalStorage () {
        File newfile=null;
        try {
            File currentFile = new File(str_video);
            String fileName = currentFile.getName();

            File directory = Global.TTULibraryProfile(Activity_galleryview.this) ;
            newfile = new File(directory, fileName);
            if(currentFile.exists()){

                InputStream in = new FileInputStream(currentFile);
                OutputStream out = new FileOutputStream(newfile);
                FileUtils.copy(in,out);

                in.close();
                out.close();
                Log.v("", "Image file saved successfully.");
            }else{
                Log.v("", "Image saving failed. Source file missing.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newfile;
    }





}
