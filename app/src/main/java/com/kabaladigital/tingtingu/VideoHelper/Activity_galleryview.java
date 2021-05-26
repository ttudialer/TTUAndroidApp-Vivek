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
import com.kabaladigital.tingtingu.models.LibraryGetModel;
import com.kabaladigital.tingtingu.networking.ApiClient;
import com.kabaladigital.tingtingu.networking.ApiInterface;
import com.kabaladigital.tingtingu.util.PreferenceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_galleryview extends Activity {

    String str_video;
    VideoView vv_video;
    Button _btn_update_ttu_set_default;
    private View mWaitSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galleryview);
        init();


        _btn_update_ttu_set_default.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                waitSpinnerVisible();
                File videoFile = saveVideoToInternalStorage();
                Log.d("setpath", videoFile.getAbsolutePath()+".mp4");
                Bundle bundle = new Bundle();
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("selectedFile",  videoFile.getAbsolutePath())
                        .addFormDataPart("isProfile", "false")
                        .build();
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), videoFile);
                MultipartBody.Part body = MultipartBody.Part.createFormData("selectedFile", videoFile.getAbsolutePath(), requestFile);
                RequestBody fullName =  RequestBody.create(MediaType.parse("multipart/form-data"), "false");
                RequestBody fileType =  RequestBody.create(MediaType.parse("multipart/form-data"), "video");


                ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
                Call<LibraryAddModel> call = apiInterface.LibraryAdd(body,fullName,fileType);
                call.enqueue(new Callback<LibraryAddModel>() {
                    @Override
                    public void onResponse(Call<LibraryAddModel> call,
                                           Response<LibraryAddModel> response) {
                        if (response.code() == 200) {
                            Log.d("code",""+ videoFile.getAbsolutePath());

                            PreferenceUtils.getInstance().putString(R.string.pref_profile_path,  videoFile.getAbsolutePath());
                            Get_Profile_Capminion_ID();

                            Toast.makeText(Activity_galleryview.this,"Profile Successfully upload...",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                    @Override
                    public void onFailure(Call<LibraryAddModel> call, Throwable t) {
                        Toast.makeText(Activity_galleryview.this, "onFailure= "+t.getMessage(), Toast.LENGTH_SHORT).show();
                        waitSpinnerInvisible();
                    }
                });
            }
        });
    }


    private void init() {
        vv_video = (VideoView) findViewById(R.id.vv_video);
        _btn_update_ttu_set_default= (Button) findViewById(R.id.btn_update_ttu_set_default);
        mWaitSpinner = findViewById(R.id.wait_spinner);
        str_video = getIntent().getStringExtra("video");
        vv_video.setVideoPath(str_video);
        vv_video.start();
    }


    private void Get_Profile_Capminion_ID() {
        try {
            ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
            Call<List<LibraryGetModel>> call = apiInterface.getLibraryGet();
            call.enqueue(new Callback<List<LibraryGetModel>>() {
                @Override
                public void onResponse(Call<List<LibraryGetModel>> call,
                                       Response<List<LibraryGetModel>> response) {
                    if (response.code() == 200) {
                        int i=response.body().size()-1;
                        String isProfile = response.body().get(i).getIsProfile().toString();
                        String _id =  response.body().get(i).getId().toString();

                        Set_Profile_Capminion(_id);

                        waitSpinnerInvisible();

                        Log.d("result::: ", "onResult::: "+response.body().toString() );
                    }
                }
                @Override
                public void onFailure(Call<List<LibraryGetModel>> call, Throwable t) {
                    Toast.makeText(Activity_galleryview.this, "onFailure= "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception ex) {
            Toast.makeText(Activity_galleryview.this, "onFailure= "+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void Set_Profile_Capminion(String _cid) {
        try {
            ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
            Call<ResponseBody> call = apiInterface.setcreateCamp(_cid);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call,
                                       Response<ResponseBody> response) {
                    if (response.code() == 200) {
                        Log.d("result::: ", "onResult::: "+response.body().toString() );
                        Toast.makeText(Activity_galleryview.this,"Profile Campion Successfully upload...",Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(Activity_galleryview.this, "onFailure= "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception ex) {
            Toast.makeText(Activity_galleryview.this, "onFailure= "+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }






    public void waitSpinnerVisible() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mWaitSpinner.setVisibility(View.VISIBLE);
            }
        });
    }
    public void waitSpinnerInvisible() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mWaitSpinner.setVisibility(View.GONE);
            }
        });
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
