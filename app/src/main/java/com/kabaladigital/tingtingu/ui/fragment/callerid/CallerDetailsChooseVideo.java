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
        //   langType = PreferenceUtils.getInstance().getString(R.string.pref_user_selected_language_key);

        init();
        return binding.getRoot();
    }

    private void init(){
        recyclerView = binding.recyclerView;
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


//        int int_position = 0;
//        Uri uri;
//        Cursor cursor;
//        int column_index_data, column_index_folder_name,column_id,thum;
//
//        String absolutePathOfImage = null;
//        uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
//
//        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Video.Media.BUCKET_DISPLAY_NAME,MediaStore.Video.Media._ID,MediaStore.Video.Thumbnails.DATA};
//
//        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
//        cursor = getContext().getContentResolver().query(uri, projection, null, null, orderBy + " DESC");
//
//        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
//        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME);
//        column_id = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
//        thum = cursor.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA);
//
//        while (cursor.moveToNext()) {
//            absolutePathOfImage = cursor.getString(column_index_data);
//            Log.e("Column", absolutePathOfImage);
//            Log.e("Folder", cursor.getString(column_index_folder_name));
//            Log.e("column_id", cursor.getString(column_id));
//            Log.e("thum", cursor.getString(thum));
//
//            Model_Video obj_model = new Model_Video();
//            obj_model.setBoolean_selected(false);
//            obj_model.setStr_path(absolutePathOfImage);
//            obj_model.setStr_thumb(cursor.getString(thum));
//
//            al_video.add(obj_model);
//
//        }
//
//
//        obj_adapter = new Adapter_VideoFolder(getContext(),al_video,getActivity());
//        recyclerView.setAdapter(obj_adapter);

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        listOfImagesPath = null;
//        listOfImagesPath = RetriveCapturedImagePath();
//        if(listOfImagesPath !=null){
//            binding.gridVideo.setAdapter(new VideoListAdapter(getActivity(),listOfImagesPath));
//            binding.gridVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                public void onItemClick(AdapterView<?> parent, View v, int position, long id){
//                    PreferenceUtils.getInstance().putString(R.string.pref_image_path,new File(tFileList.get(position)).getAbsolutePath());
//                    startActivity(new Intent(getContext(), Video_View1.class));
//
//                }
//            });
//
//
//        }
    }

    public ArrayList<String> RetriveCapturedImagePath(){
         tFileList = new ArrayList<String>();
        File f = new File( Global.TTULibraryVideo(getContext()).getAbsolutePath() );
        if (f.exists()) {
            File[] files = f.listFiles();
            if (files != null) {
                if (files.length > 0) {
                    Arrays.sort( files );
                    for (int i = 0; i < files.length; i++) {
                        File file = files[i];
                        if (file.isDirectory())
                            continue;
                        String filePath = file.getPath();
                        if (filePath.endsWith( ".mp4" )) {
                            tFileList.add( file.getPath() );
                        }
                    }
                }
            }
        }
        if (tFileList.isEmpty()) {
            return null;
        } else {
            return tFileList;
        }
    }




    @Override
    public void onResume() {
        getActivity().setTitle("Caller Details");
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        super.onResume();
    }
}
