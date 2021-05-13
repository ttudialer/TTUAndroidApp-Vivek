package com.kabaladigital.tingtingu.ImageHelper;

import android.app.Activity;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.kabaladigital.tingtingu.Class.Global;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.VideoHelper.Activity_galleryview;
import com.kabaladigital.tingtingu.databinding.ImageViewBinding;
import com.kabaladigital.tingtingu.models.LibraryAddModel;
import com.kabaladigital.tingtingu.networking.ApiClient;
import com.kabaladigital.tingtingu.networking.ApiInterface;
import com.kabaladigital.tingtingu.util.PreferenceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Gallery_Image extends Activity {

    String str_image;
    ImageView _ImageView;
    Button _btn_update_ttu_set_default;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_image);
        init();

        _btn_update_ttu_set_default.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                File videoFile = saveVideoToInternalStorage();
                PreferenceUtils.getInstance().putString(R.string.pref_image_path,  videoFile.toString());
                Bundle bundle = new Bundle();
                //File videoFile = new File(PreferenceUtils.getInstance().getString(R.string. pref_image_path));
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("selectedFile",  videoFile.getAbsolutePath())
                        .addFormDataPart("isProfile", "true")
                        .build();

                ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
                Call<LibraryAddModel> call = apiInterface.LibraryAdd(requestBody);
                call.enqueue(new Callback<LibraryAddModel>() {
                    @Override
                    public void onResponse(Call<LibraryAddModel> call,
                                           Response<LibraryAddModel> response) {
                        if (response.code() == 200) {
                            PreferenceUtils.getInstance().putString(R.string.pref_image_path,  videoFile.toString());
                            Toast.makeText(Activity_Gallery_Image.this,"Success",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<LibraryAddModel> call, Throwable t) {
                        Toast.makeText(Activity_Gallery_Image.this, "onFailure= "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });



    }

    private void init() {
        _ImageView = (ImageView) findViewById(R.id.vv_Image);
        _btn_update_ttu_set_default= (Button) findViewById(R.id.btn_update_ttu_set_default);
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

    private File  saveVideoToInternalStorage () {
        File newfile=null;
        try {
            File currentFile = new File(str_image);
            String fileName = currentFile.getName();

            File directory = Global.TTULibraryProfile(Activity_Gallery_Image.this) ;
            newfile = new File(directory, fileName);
            if(currentFile.exists()){

                InputStream in = new FileInputStream(currentFile);
                OutputStream out = new FileOutputStream(newfile);

                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;

                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                Log.v("", "Image file saved successfully.");
            }else{
                Log.v("", "Image saving failed. Source file missing.");
            }
            //PreferenceUtils.getInstance().putString(R.string.pref_profile_path,newfile.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return newfile;
    }





}
