package com.kabaladigital.tingtingu.VideoHelper;

import android.content.Context;
import android.content.ContextWrapper;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.kabaladigital.tingtingu.Class.Global;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.databinding.VideoViewBinding;
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


public class Videoview extends Fragment {

    private VideoViewBinding binding;


    public static Videoview newInstance() {
        return new Videoview();
    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.video_view, container, false);
        //  langType = PreferenceUtils.getInstance().getString(R.string.pref_user_selected_language_key);


        Uri uri = Uri.parse(  PreferenceUtils.getInstance().getString(R.string.pref_image_path));
        binding.VideoView.setVideoURI(uri);
        binding.VideoView.requestFocus();
        binding.VideoView.start();




        binding.btnUpdateTtuSetDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveVideoToInternalStorage(PreferenceUtils.getInstance().getString(R.string.pref_image_path));
                Bundle bundle = new Bundle();
//                Navigation.findNavController(binding.getRoot())
//                        .navigate(R.id.action_viewcallerphotovideo_to_viewcalleridchoose);
               // Uri uri = Uri.parse( PreferenceUtils.getInstance().getString(R.string.pref_image_path));
                File videoFile = new File(PreferenceUtils.getInstance().getString(R.string. pref_image_path));
                RequestBody requestBody1 = RequestBody.create(MediaType.parse("*/*"), videoFile);
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        //.addFormDataPart("selectedFile",  videoFile.getName(),requestBody1)
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
                            Toast.makeText(getContext(),"Success",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<LibraryAddModel> call, Throwable t) {
                        Toast.makeText(getContext(), "onFailure= "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });


        return binding.getRoot();

    }


    private void saveVideoToInternalStorage (String filePath) {

        File newfile;
        try {

            File currentFile = new File(filePath);
            String fileName = currentFile.getName();

            ContextWrapper cw = new ContextWrapper(getContext());
            File directory = Global.TTULibraryProfile(getContext()) ;

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
                Log.v("", "Video file saved successfully.");
            }else{
                Log.v("", "Video saving failed. Source file missing.");
            }

            PreferenceUtils.getInstance().putString(R.string.pref_profile_path,newfile.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
