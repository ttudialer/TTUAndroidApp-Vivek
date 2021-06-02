package com.kabaladigital.tingtingu.ImageHelper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kabaladigital.tingtingu.Class.Global;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.databinding.CallerDetailsFragmentBinding;
import com.kabaladigital.tingtingu.models.LibraryAddModel;
import com.kabaladigital.tingtingu.models.LibraryGetModel;
import com.kabaladigital.tingtingu.networking.ApiClient;
import com.kabaladigital.tingtingu.networking.ApiInterface;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Gallery_Image extends AppCompatActivity {
    String str_image;
    CropImageView _ImageView;
    Button _btn_update_ttu_set_default;
    private View mWaitSpinner;
    public static Bitmap croppedImage;
    private CallerDetailsFragmentBinding binding;
    private Object LibraryGetModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_image);
        PreferenceUtils.getInstance(this,true); // Get the preferences
        _ImageView = (CropImageView) findViewById(R.id.cropImageView);
        _btn_update_ttu_set_default= (Button) findViewById(R.id.btn_update_ttu_set_default);
        mWaitSpinner = findViewById(R.id.wait_spinner);

       // Get_Profile_Capminion_ID();


        str_image = getIntent().getStringExtra("video");
        Log.d("path",getIntent().getStringExtra("video"));
        BitmapFactory.Options bfOptions=new BitmapFactory.Options();
        Bitmap bm;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(new File(str_image));
            if(fs!=null) {
                bm= BitmapFactory.decodeFileDescriptor(fs.getFD(), null, bfOptions);
                _ImageView.setImageBitmap(bm);
                //_ImageView.setImageResource(R.drawable.background_operator_home);
                _ImageView.setGuidelines(CropImageView.Guidelines.ON);
                _ImageView.setScaleType(CropImageView.ScaleType.CENTER_INSIDE);
                _ImageView.setMultiTouchEnabled(true);
                _ImageView.setVisibility(View.VISIBLE);
                _ImageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        FloatingActionButton  nextleft = (FloatingActionButton) findViewById(R.id.nextleft);
        nextleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ImageView.rotateImage(-90);
            }
        });

        FloatingActionButton  nextRight = (FloatingActionButton) findViewById(R.id.nextRight);
        nextRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ImageView.rotateImage(90);
            }
        });


        _btn_update_ttu_set_default.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _ImageView.setOnCropImageCompleteListener(new CropImageView.OnCropImageCompleteListener() {
                    @Override
                    public void onCropImageComplete(CropImageView view, final CropImageView.CropResult result) {
                        Thread t = new Thread(new Runnable() {
                            public void run() {
                                try {
                                    waitSpinnerVisible();
                                    croppedImage = result.getBitmap();
                                    saveVideoToInternalStorage1(croppedImage);
                                    File videoFile = saveVideoToInternalStorage_profile(croppedImage);

                                    Upload_File(videoFile);


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                    }
                });
                try {
                    _ImageView.getCroppedImageAsync();
                } catch (Exception e) {
                }
            }
        });
    }

    public void Upload_File(File _videoFile) {
        Log.d("setpath", _videoFile.getAbsolutePath());
        Bundle bundle = new Bundle();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("selectedFile",  _videoFile.getAbsolutePath())
                .addFormDataPart("isProfile", "false")
                .addFormDataPart("fileType", "image")
                .build();
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), _videoFile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("selectedFile", _videoFile.getName(), requestFile);
        RequestBody fullName =  RequestBody.create(MediaType.parse("multipart/form-data"), "false");
        RequestBody fileType =  RequestBody.create(MediaType.parse("multipart/form-data"), "image");



        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<LibraryAddModel> call = apiInterface.LibraryAdd(body,fullName,fileType);
        call.enqueue(new Callback<LibraryAddModel>() {
            @Override
            public void onResponse(Call<LibraryAddModel> call,
                                   Response<LibraryAddModel> response) {
                if (response.code() == 200) {
                    Log.d("code",""+ _videoFile.getAbsolutePath());
                    PreferenceUtils.getInstance().putString(R.string.pref_profile_path,  _videoFile.getAbsolutePath());

                    Get_Profile_Capminion_ID();

                    Toast.makeText(Activity_Gallery_Image.this,"Profile Successfully upload...",Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onFailure(Call<LibraryAddModel> call, Throwable t) {
                Toast.makeText(Activity_Gallery_Image.this, "onFailure= "+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("code",""+ t.getMessage());
                Log.d("code",""+ _videoFile.getName());
                waitSpinnerInvisible();
            }
        });
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
                    Toast.makeText(Activity_Gallery_Image.this, "onFailure= "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception ex) {
            Toast.makeText(Activity_Gallery_Image.this, "onFailure= "+ex.getMessage(), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(Activity_Gallery_Image.this,"Profile Campion Successfully upload...",Toast.LENGTH_SHORT).show();

                        Navigation.findNavController(binding.getRoot())
                                .navigate(R.id.action_viewcallerphotovideo_to_viewcalleridchoose);

                        finish();

                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(Activity_Gallery_Image.this, "onFailure= "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception ex) {
            Toast.makeText(Activity_Gallery_Image.this, "onFailure= "+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private File  saveVideoToInternalStorage_profile (Bitmap _cimg) {
        File newfile=null;
        try {
            File currentFile = new File(str_image);
            String fileName = currentFile.getName();

            File directory = Global.TTULibraryProfile(Activity_Gallery_Image.this) ;
            newfile = new File(directory, fileName);

            if(newfile.exists()){
                newfile.delete();
            }
            //if(currentFile.exists()){
                try (FileOutputStream out = new FileOutputStream(newfile)) {
                    _cimg.compress(Bitmap.CompressFormat.JPEG, 100, out); // bmp is your Bitmap instance
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.v("", "Image file saved successfully.");
//            }else{
//                Log.v("", "Image saving failed. Source file missing.");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newfile;
    }
    private void  saveVideoToInternalStorage1 (Bitmap _cimg) {
        File newfile=null;
        try {
            File currentFile = new File(str_image);
            String fileName = currentFile.getName();

            String _str_image=str_image.replace(".jpg","1.jpg");

            if(currentFile.exists()){
                currentFile.delete();
            }
            File _currentFile = new File(_str_image);
            if(_currentFile.exists()){
                _currentFile.delete();
            }

            try (FileOutputStream out = new FileOutputStream(_str_image)) {
                _cimg.compress(Bitmap.CompressFormat.JPEG, 100, out); // bmp is your Bitmap instance
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
