package com.kabaladigital.tingtingu.VideoHelper;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.RequiresApi;
import androidx.room.util.FileUtil;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kabaladigital.tingtingu.Class.Global;
import com.kabaladigital.tingtingu.ImageHelper.Activity_Gallery_Image;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.models.ContactUploadModel;
import com.kabaladigital.tingtingu.models.LibraryAddModel;
import com.kabaladigital.tingtingu.models.LibraryGetModel;
import com.kabaladigital.tingtingu.networking.ApiClient;
import com.kabaladigital.tingtingu.networking.ApiInterface;
import com.kabaladigital.tingtingu.service.ContactUpload;
import com.kabaladigital.tingtingu.ui.activity.MainActivity;
import com.kabaladigital.tingtingu.util.PreferenceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.firebase.crashlytics.internal.Logger.TAG;
import static com.kabaladigital.tingtingu.ui.fragment.callerid.CallerDetailsChoose.binding_choseIV;

public class Activity_galleryview extends Activity {
    JsonArray jsonArrayContact = new JsonArray();
    String str_video;
    VideoView vv_video;
    Button _btn_update_ttu_set_default;
    private View mWaitSpinner;
    private Context _Contaxt;
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
                Uri uri = Uri.parse(videoFile.getAbsolutePath());

                ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
                Call<LibraryAddModel> call = apiInterface.LibraryAdd(body,fullName,fileType);
                call.enqueue(new Callback<LibraryAddModel>() {
                    @Override
                    public void onResponse(Call<LibraryAddModel> call,
                                           Response<LibraryAddModel> response) {
                        if (response.code() == 200) {
                            Log.d("code",""+ videoFile.getAbsolutePath());

                            binding_choseIV.VideoView1.setVideoURI(uri);
                            binding_choseIV.VideoView1.requestFocus();
                            binding_choseIV.VideoView1.start();
                            binding_choseIV.VideoView1.setVisibility(View.VISIBLE);
                            binding_choseIV.simpleImageView.setVisibility(View.GONE);

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
        _Contaxt=this;
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

                        new Thread(new Runnable() {
                            public void run() {
                                ContactUpload _cu=new ContactUpload(_Contaxt);
                                _cu.ContactUpload(_Contaxt,"main_Video");
                            }
                        }).start();

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


    public void ReadContactDetailsJson(String _val) {
        JsonObject contactobj = null;
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    String phoneNo = "";
                    int _i=0;
                    int _total=0;
                    _total=pCur.getCount();
                    while (pCur.moveToNext()) {
                        contactobj = new JsonObject();
                        phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        phoneNo = phoneNo.replaceAll("\\s", "");

//                        Log.i(TAG, "Name: " + name);
//                        Log.i(TAG, "Phone Number: " + phoneNo);
                        contactobj.addProperty("name" , name);
                        String lastFourDigits = "";     //substring containing last 4 characters
                        if (phoneNo.length() > 10) {
                            lastFourDigits = phoneNo.substring(phoneNo.length() - 10);
                        } else {
                            lastFourDigits = phoneNo;
                        }
                        contactobj.addProperty("number", lastFourDigits);
                        jsonArrayContact.add(contactobj);
                    }
                    pCur.close();
                }
            }
        }
        if (cur != null) {
            cur.close();
        }
        JsonObject  contactListObj = new JsonObject();
        contactListObj.add("contactList", jsonArrayContact);
        Toast.makeText(this, ""+_val, Toast.LENGTH_SHORT).show();
        uploadContact(contactListObj);
    }

    private void uploadContact(JsonObject contactListObj) {
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<ContactUploadModel> call = apiInterface.contactUploadDetails(contactListObj);
        // retrofit2.Call<ContactUploadModel> call = apiInterface.contactUploadDetails(contactListObj);
        call.enqueue(new Callback<ContactUploadModel>() {
            @Override
            public void onResponse(Call<ContactUploadModel> call,
                                   Response<ContactUploadModel> response) {
                if (response.code() == 200) {
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ContactUploadModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "onFailure= " + t.getMessage(), Toast.LENGTH_SHORT).show();
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
            File currentFile = new File(str_video);
            String fileName = currentFile.getName();

            File directory = Global.TTULibraryProfile(Activity_galleryview.this) ;
            newfile = new File(directory, fileName);
            if(currentFile.exists()){

                InputStream in = new FileInputStream(currentFile);
                OutputStream out = new FileOutputStream(newfile);

                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
                    // Do something for lollipop and above versions
                    FileUtils.copy(in, out);
                } else{
                    // do something for phones running an SDK oreo
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                }
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
