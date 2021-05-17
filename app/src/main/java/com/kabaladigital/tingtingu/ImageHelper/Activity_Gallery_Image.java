package com.kabaladigital.tingtingu.ImageHelper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kabaladigital.tingtingu.Class.Global;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.databinding.CallerDetailsFragmentBinding;
import com.kabaladigital.tingtingu.models.LibraryAddModel;
import com.kabaladigital.tingtingu.networking.ApiClient;
import com.kabaladigital.tingtingu.networking.ApiInterface;
import com.kabaladigital.tingtingu.ui.fragment.CallerDetailsFragment;
import com.kabaladigital.tingtingu.ui.fragment.OperatorHomeFragmentDirections;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.theartofdev.edmodo.cropper.CropImageView;

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

public class Activity_Gallery_Image extends AppCompatActivity {
    String str_image;
    CropImageView _ImageView;
    Button _btn_update_ttu_set_default;
    private View mWaitSpinner;
    public static Bitmap croppedImage;
    private CallerDetailsFragmentBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_image);
        PreferenceUtils.getInstance(this,true); // Get the preferences
        _ImageView = (CropImageView) findViewById(R.id.cropImageView);
        _btn_update_ttu_set_default= (Button) findViewById(R.id.btn_update_ttu_set_default);
        mWaitSpinner = findViewById(R.id.wait_spinner);




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

                                    waitSpinnerInvisible();
//                                    Fragment fragment = new CallerDetailsFragment();
//                                    getSupportFragmentManager().beginTransaction()
//                                            .replace(R.id.action_viewcallerphotovideo_to_viewcalleridchoose, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();


                                    finish();
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
        Log.d("setpath", _videoFile.getAbsolutePath()+".jpg");
        Bundle bundle = new Bundle();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("selectedFile",  _videoFile.getAbsolutePath())
                .addFormDataPart("isProfile", "true")
                .build();
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), _videoFile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("selectedFile", _videoFile.getAbsolutePath(), requestFile);
        RequestBody fullName =  RequestBody.create(MediaType.parse("multipart/form-data"), "true");

        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<LibraryAddModel> call = apiInterface.LibraryAdd(body,fullName);
        call.enqueue(new Callback<LibraryAddModel>() {
            @Override
            public void onResponse(Call<LibraryAddModel> call,
                                   Response<LibraryAddModel> response) {
                if (response.code() == 200) {
                    Log.d("code",""+ _videoFile.getAbsolutePath());
                    PreferenceUtils.getInstance().putString(R.string.pref_profile_path,  _videoFile.getAbsolutePath());
                    Toast.makeText(Activity_Gallery_Image.this,"Profile Successfully upload...",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<LibraryAddModel> call, Throwable t) {
                Toast.makeText(Activity_Gallery_Image.this, "onFailure= "+t.getMessage(), Toast.LENGTH_SHORT).show();
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
