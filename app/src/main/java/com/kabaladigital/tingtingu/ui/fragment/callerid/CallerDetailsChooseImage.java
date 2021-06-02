package com.kabaladigital.tingtingu.ui.fragment.callerid;

import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kabaladigital.tingtingu.Class.Global;
import com.kabaladigital.tingtingu.ImageHelper.Adapter_ImageFolder;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.databinding.CallerDetailsFragmentChooseImageBinding;
import com.kabaladigital.tingtingu.ui.activity.MainActivity;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.viewmodels.Model_Image;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CallerDetailsChooseImage extends Fragment {
    public ArrayList<String> tFileList;
    private String[]        FilePathStrings;
    private String[]        FilePathStrings1;
    public List<String> listOfImagesPath;
    Adapter_ImageFolder obj_adapter;
    ArrayList<Model_Image> al_video = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    private CallerDetailsFragmentChooseImageBinding binding;

    public static CallerDetailsChooseImage newInstance() {
        return new CallerDetailsChooseImage();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.caller_details_fragment_choose_image, container, false);
        init();
        return binding.getRoot();
    }
    private void init(){
        recyclerView = binding.recyclerView;
        recyclerViewLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        fn_image();
    }
    public void fn_image() {
        FilePathStrings1 = RetriveCapturedImagePath1();
        al_video = new ArrayList<>();
        if(FilePathStrings1 !=null) {
            for (int i = 0; i < FilePathStrings1.length; i++) {
                File file = new File(FilePathStrings1[i]);
                Model_Image obj_model = new Model_Image();
                obj_model.setBoolean_selected(false);
                obj_model.setStr_path(file.getPath());
                obj_model.setStr_thumb(file.getPath());
                al_video.add(obj_model);
            }
        }
        obj_adapter = new Adapter_ImageFolder(getContext(),al_video,getActivity());
        recyclerView.setAdapter(obj_adapter);
    }

    public String[] RetriveCapturedImagePath1(){
        tFileList = new ArrayList<String>();
        File f = new File( Global.TTULibraryImage(getContext()).getAbsolutePath() );
        File[] files  = f.listFiles();
        String[]        FilePathStrings_temp;

        FilePathStrings_temp = new String[files.length];
        int z=0;
        for (int i = 0; i < files.length; i++)
        {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            Bitmap bitmap = BitmapFactory.decodeFile(files[i].getAbsolutePath(), options);
            if (options.outWidth != -1 && options.outHeight != -1) {
                FilePathStrings_temp[z] = files[i].getAbsolutePath();
                z=z+1;
            }
        }
        FilePathStrings=new String[z];
        for (int i = 0; i < z; i++) {
            FilePathStrings[i] = FilePathStrings_temp[i];
        }

        return FilePathStrings;
    }

    @Override
    public void onResume() {
        getActivity().setTitle("Caller Details");
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        super.onResume();
    }
}
