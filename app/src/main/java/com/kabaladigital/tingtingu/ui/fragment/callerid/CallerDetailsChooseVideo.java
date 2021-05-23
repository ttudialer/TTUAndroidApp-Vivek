package com.kabaladigital.tingtingu.ui.fragment.callerid;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
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
import com.kabaladigital.tingtingu.VideoHelper.Adapter_VideoFolder;
import com.kabaladigital.tingtingu.databinding.CallerDetailsFragmentChooseVideoBinding;
import com.kabaladigital.tingtingu.ui.activity.MainActivity;
import com.kabaladigital.tingtingu.viewmodels.Model_Video;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CallerDetailsChooseVideo extends Fragment {
    Adapter_VideoFolder obj_adapter;
    private String[]        FilePathStrings;
    private String[]        FilePathStrings1;
    ArrayList<Model_Video> al_video = new ArrayList<>();
    public ArrayList<String> tFileList;
    public List<String> listOfImagesPath;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    private CallerDetailsFragmentChooseVideoBinding binding;

    public static CallerDetailsChooseVideo newInstance() {
        return new CallerDetailsChooseVideo();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.caller_details_fragment_choose_video, container, false);
        init();
        return binding.getRoot();
    }

    private void init(){
        recyclerView = binding.recyclerViewV;
        recyclerViewLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        fn_video();
    }
    public void fn_video() {

        FilePathStrings1 = RetriveCapturedImagePath1();
        if(FilePathStrings1 !=null) {
            for (int i = 0; i < FilePathStrings1.length; i++) {
                File file = new File(FilePathStrings1[i]);
                Model_Video obj_model = new Model_Video();
                obj_model.setBoolean_selected(false);
                obj_model.setStr_path(file.getPath());
                obj_model.setStr_thumb(file.getPath());
                al_video.add(obj_model);
            }
        }
        obj_adapter = new Adapter_VideoFolder(getContext(),al_video,getActivity());
        recyclerView.setAdapter(obj_adapter);
    }

    public String[] RetriveCapturedImagePath1(){
        tFileList = new ArrayList<String>();
        File f = new File( Global.TTULibraryVideo(getContext()).getAbsolutePath() );
        File[] files  = f.listFiles();
        FilePathStrings = new String[files.length];
        for (int i = 0; i < files.length; i++)
        {
            FilePathStrings[i] = files[i].getAbsolutePath();
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
