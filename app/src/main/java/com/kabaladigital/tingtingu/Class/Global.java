package com.kabaladigital.tingtingu.Class;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Global {


    public static File TTULibraryTTUPROFILE(Context mContext){
        File storageDir = new File( mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES) +"/TTULibrary/TTUPROFILE");
        boolean success = true;
        if (storageDir.exists()==false) {
            success = storageDir.mkdirs();
        }
        return storageDir;
    }

    public static String TTULibraryTTUPROFILE_path(Context mContext){
        String _path=mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES) +"/TTULibrary/TTUPROFILE";
        File storageDir = new File( _path);
        boolean success = true;
        if (storageDir.exists()==false) {
            success = storageDir.mkdirs();
        }
        return _path;
    }



    public static File TTULibraryImage(Context mContext){
        File storageDir = new File(mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES) +"/TTULibrary/Image");
        boolean success = true;
        if (storageDir.exists()==false) {
            success = storageDir.mkdirs();
        }
        return storageDir;
    }
    public static File TTULibraryImageDraft(Context mContext){
        File storageDir = new File(mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES) +"/TTULibrary/Draft");
        boolean success = true;
        if (storageDir.exists()==false) {
            success = storageDir.mkdirs();
        }
        return storageDir;
    }

    public static File TTULibraryVideo(Context mContext){
        File storageDir = new File(mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES) +"/TTULibrary/Video");
        boolean success = true;
        if (!storageDir.exists()) {
            success = storageDir.mkdirs();
        }
        return storageDir;
    }
    public static File TTULibraryProfile(Context mContext){
        File storageDir = new File(mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES) +"/TTULibrary/Profile");
        boolean success = true;
        if (!storageDir.exists()) {
            success = storageDir.mkdirs();
        }
        return storageDir;
    }


    public static File getVideoPath_filename(Context mContext) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String pictureFile = "RAM_" + timeStamp;
        File storageDir = Global.TTULibraryVideo(mContext) ;
        File image = File.createTempFile(pictureFile,  "_P.mp4", storageDir);
        return image;
    }

    public static File getImagePath_P(Context mContext) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String pictureFile = "RAM_" + timeStamp;
        File storageDir = Global.TTULibraryImage(mContext) ;
        File image = File.createTempFile(pictureFile,  "_P.jpg", storageDir);
        return image;
    }

    public static File getImageDraft_Image(Context mContext) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String pictureFile = "Dr_" + timeStamp;
        File storageDir = Global.TTULibraryImageDraft(mContext) ;
        File image = File.createTempFile(pictureFile,  ".jpg", storageDir);
        return image;
    }

    public static String getPictureFilePath(Context mContext) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String pictureFile = "RAM_" + timeStamp;
        return pictureFile;
    }
    public static String getPictureFileName(Context mContext) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String pictureFile = "RAM_" + timeStamp + ".jpg";
        return pictureFile;
    }

    public static Bitmap getContactBitmapFromURI(Context context, Uri uri) {
        try {

            InputStream input = context.getContentResolver().openInputStream(uri);
            if (input == null) {
                return null;
            }
            return BitmapFactory.decodeStream(input);
        }
        catch (FileNotFoundException e)
        {

        }
        return null;

    }
    public static ArrayList<String> RetriveCapturedImagePathImage(ArrayList<String> tFileList,Context mContext) {
        tFileList = new ArrayList<String>();
        File f = TTULibraryImage(mContext);
        if (f.exists()) {
            File[] files=f.listFiles();
            if(files !=null) {
                if (files.length > 0) {
                    Arrays.sort(files);
                    for (int i = 0; i < files.length; i++) {
                        File file = files[i];
                        if (file.isDirectory())
                            continue;
                        String filePath = file.getPath();
                        if(filePath.endsWith(".jpg") || filePath.endsWith(".jepg")) {
                            tFileList.add(file.getPath());
                        }
                    }
                }
            }
        }
        if(tFileList.isEmpty()){
            return null;
        }
        else{
            return tFileList;
        }


    }


}
