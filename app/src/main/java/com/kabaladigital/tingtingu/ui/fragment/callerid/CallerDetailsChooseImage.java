package com.kabaladigital.tingtingu.ui.fragment.callerid;

import android.content.ContentUris;
import android.database.Cursor;
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
import com.kabaladigital.tingtingu.viewmodels.Model_Video;

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
    ArrayList<Model_Video> al_video = new ArrayList<>();
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
        obj_adapter = new Adapter_ImageFolder(getContext(),al_video,getActivity());
        recyclerView.setAdapter(obj_adapter);

//
//        int int_position = 0;
//        Uri uri;
//        Cursor cursor;
//        int column_index_data, column_index_folder_name,column_id,thum;
//
//        String absolutePathOfImage = null;
//        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//       // uri =  Uri.parse("content:/"+ getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES) +"/TTULibrary/Image") ;
//        //final Uri filesUri = MediaStore.Files.getContentUri(getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES) +"/TTULibrary/Image");
//
//        String[] proj = {MediaStore.Images.Media.DATA};
//        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Video.Media.BUCKET_DISPLAY_NAME,MediaStore.Images.Media._ID
//                                ,MediaStore.Images.Thumbnails.DATA};
//
//        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
//        cursor = getContext().getContentResolver().query(uri, projection, null, null, orderBy + " DESC");
//       // cursor = getActivity().getContentResolver().query(filesUri, proj, null, null, null);
//
//        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
//        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
//        column_id = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
//        thum = cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails.DATA);
//
//        while (cursor.moveToNext()) {
//            int columnIndex = cursor.getColumnIndex(proj[0]);
//            absolutePathOfImage = cursor.getString(columnIndex);
//            Log.e("Column", absolutePathOfImage);
//            Log.e("Folder", cursor.getString(column_index_folder_name));
//            Log.e("column_id", cursor.getString(column_id));
//            Log.e("thum", cursor.getString(thum));
//
//            Model_Video obj_model = new Model_Video();
//            obj_model.setBoolean_selected(false);
//            obj_model.setStr_path(absolutePathOfImage);
//            obj_model.setStr_thumb(cursor.getString(thum));
//            al_video.add(obj_model);
//        }
//
//
//        obj_adapter = new Adapter_ImageFolder(getContext(),al_video,getActivity());
//        recyclerView.setAdapter(obj_adapter);

    }

    public String[] RetriveCapturedImagePath1(){
        tFileList = new ArrayList<String>();
        File f = new File( Global.TTULibraryImage(getContext()).getAbsolutePath() );
        File[] files  = f.listFiles();
        FilePathStrings = new String[files.length];
        for (int i = 0; i < files.length; i++)
        {
            FilePathStrings[i] = files[i].getAbsolutePath();
        }
        return FilePathStrings;
    }
    public ArrayList<String>  RetriveCapturedImagePath(){
        tFileList = new ArrayList<String>();
        File f = new File( Global.TTULibraryImage(getContext()).getAbsolutePath() );
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
                        if (filePath.endsWith( ".jpg" )) {
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
//        tFileList = new ArrayList<String>();
//        Uri uri = Uri.parse( Global.TTULibraryImage(getContext()).getAbsolutePath());
//        Cursor cursor = getActivity().getContentResolver().query(uri , projection, null,null, null);
//        //Cursor cursor = getActivity().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null,null, null);
//        if(cursor == null)
//            return  null;
//
//        while (cursor.moveToNext()) {
//            String absolutePathOfImage = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
////            ImageModel ImageModel = new ImageModel();
////            ImageModel.setImage(absolutePathOfImage);
////            imageList.add(ImageModel);
//
//            tFileList.add(absolutePathOfImage);
//        }
//        cursor.close();
//
//        if(tFileList.isEmpty()){
//            return null;
//        }
//        else{
//            return tFileList;
//        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listOfImagesPath = null;
//        listOfImagesPath = RetriveCapturedImagePath();
//        if(listOfImagesPath !=null){
//            binding.gridImage.setAdapter(new ImageListAdapter(getActivity(),listOfImagesPath));
//            binding.gridImage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                public void onItemClick(AdapterView<?> parent, View v, int position, long id){
//                    PreferenceUtils.getInstance().putString(R.string.pref_image_path,new File(tFileList.get(position)).getAbsolutePath());
//                    startActivity(new Intent(getContext(), Image_View1.class));
//
//                }
//            });
//
//        }
    }

    @Override
    public void onResume() {
        getActivity().setTitle("Caller Details");
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        super.onResume();
    }
}
