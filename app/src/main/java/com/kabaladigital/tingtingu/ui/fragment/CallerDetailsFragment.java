package com.kabaladigital.tingtingu.ui.fragment;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.databinding.CallerDetailsFragmentBinding;

import com.kabaladigital.tingtingu.models.ProfileInformationModel;

import com.kabaladigital.tingtingu.ui.activity.MainActivity;
import com.kabaladigital.tingtingu.ui.fragment.profile.ProfileStep1Fragment;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.viewmodels.ProfileStep1ViewModel;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;

import static com.kabaladigital.tingtingu.util.TitleCase.toTitleCase;

public class CallerDetailsFragment extends Fragment {

    private CallerDetailsFragmentBinding binding;
    private ProfileStep1ViewModel mViewModel;
    private String langType;
    private TextView tv_name;
    private TextView tv_mobile;

    public static ProfileStep1Fragment newInstance() {
        return new ProfileStep1Fragment();
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.caller_details_fragment, container, false);
        langType = PreferenceUtils.getInstance().getString(R.string.pref_user_selected_language_key);



        binding.btnUpdateTtuId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                Navigation.findNavController(binding.getRoot())
                        .navigate(R.id.action_viewcallerphotovideo_to_viewcalleridchoose);


            }
        });


        return binding.getRoot();
    }



    private void getProfileInformation() {
        mViewModel = ViewModelProviders.of(this).get(ProfileStep1ViewModel.class);
        mViewModel.hitProfileInformation();
        mViewModel.getProfileInformationModelLiveData().observe(getActivity(), new Observer<ProfileInformationModel>() {
            @Override
            public void onChanged(ProfileInformationModel profileInformationModel) {
                if (profileInformationModel != null){
                    binding.tvMobile.setText(String.valueOf(profileInformationModel.getMobileInfo().getMobileNumber()).toUpperCase());
                    binding.tvName.setText(toTitleCase(profileInformationModel.getFullName()));
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getProfileInformation();

        String p_path=PreferenceUtils.getInstance().getString(R.string.pref_profile_path);
        //Log.d("getpath",p_path);
        File fs=new File(p_path);
        //Log.d("name",getMimeType(fs.getAbsolutePath()));

        String filePath = fs.getPath();
        //Log.d("path=>",filePath);
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        binding.simpleImageView.setImageBitmap(bitmap);
        binding.simpleImageView.setVisibility(View.VISIBLE);
        //Log.d("end",p_path);




        if (p_path != null)
        {
            if (fs.exists()) {

                if (fs.toString().endsWith(".jpg") || fs.toString().endsWith(".JPG"))
                {
                    FileInputStream fi1 = null;
                    Bitmap bm;
                    BitmapFactory.Options bfOptions=new BitmapFactory.Options();
                    try {
                        fi1 = new FileInputStream(new File(p_path));

                        if(fi1!=null) {
                            bm= BitmapFactory.decodeFileDescriptor(fi1.getFD(), null, bfOptions);
                            binding.simpleImageView.setImageBitmap(bm);
                            binding.simpleImageView.setVisibility(View.VISIBLE);
                            binding.VideoView1.setVisibility(View.GONE);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (fs.toString().endsWith(".mp4")||fs.toString().endsWith(".MP4")) {
                    Log.d("====>","elseif");



                    Uri uri = Uri.parse(p_path);
                    binding.VideoView1.setVideoURI(uri);
                    binding.VideoView1.requestFocus();
                    binding.VideoView1.start();
                    binding.VideoView1.setVisibility(View.VISIBLE);
                    binding.simpleImageView.setVisibility(View.GONE);

                }
                else{
                    Log.d("====>","else");
                }
            }
        }
        binding.VideoView1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        binding.VideoView1.setOnTouchListener( new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if( ((VideoView)v).isPlaying() )
                    ((VideoView)v).pause();
                else
                    ((VideoView)v).start();
                return true;
            }
        });




    }

    @Override
    public void onResume() {
        getActivity().setTitle("Caller Details");
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        super.onResume();
    }

    public static boolean isImageFile(String path) {
        String mimeType = URLConnection.guessContentTypeFromName(path);
        return mimeType != null && mimeType.startsWith("image");
    }

    private static String getMimeType(String fileUrl) {
        String extension = MimeTypeMap.getFileExtensionFromUrl(fileUrl);
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }
}
