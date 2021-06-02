package com.kabaladigital.tingtingu.ui.fragment.callerid;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.media.ExifInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.Navigation;


import com.kabaladigital.tingtingu.BuildConfig;
import com.kabaladigital.tingtingu.Class.Functions;
import com.kabaladigital.tingtingu.Class.Global;
import com.kabaladigital.tingtingu.Class.Variables;
import com.kabaladigital.tingtingu.Image.ImageSelectActivity;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.VideoHelper.Activity_galleryview;
import com.kabaladigital.tingtingu.Video_Recording.Video_Recoder_A;
import com.kabaladigital.tingtingu.databinding.CallerDetailsFragmentChooseBinding;
import com.kabaladigital.tingtingu.ui.activity.MainActivity;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.viewmodels.CallerDetailsChooseViewModel;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.bumptech.glide.load.resource.bitmap.TransformationUtils.rotateImage;
import static com.kabaladigital.tingtingu.Class.Global.TTULibraryImage;
import static com.kabaladigital.tingtingu.Class.Global.TTULibraryImageDraft;
import static com.kabaladigital.tingtingu.Class.Global.getContactBitmapFromURI;


public class CallerDetailsChoose extends Fragment {
    Uri imageUri;
    private String pictureFilePath;
    String Video_Image_type;
    private int SELECT_FILE = 1;
    private CallerDetailsChooseViewModel mViewModel;
    private CallerDetailsFragmentChooseBinding binding;
    private static String Image_Video_type="";
    public static final int RequestPermissionCode = 1;
    File pictureFile;
    private  Uri photoURI;
    private String langType;
    private Uri imageUri1;
    private View mWaitSpinner;
    public static CallerDetailsChoose newInstance() {
        return new CallerDetailsChoose();
    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.caller_details_fragment_choose, container, false);
        langType = PreferenceUtils.getInstance().getString(R.string.pref_user_selected_language_key);

        String[] title ;
        if (langType.equals("hi")){
            title = new String[]{"तस्वीर", "वीडियो"};
        } else {
            title = new String[]{"Image","Video"};
        }
        binding.viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), title));
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        binding.btnTvCrVP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu_CR(view, true, R.style.MyPopupStyle);
            }
        });
        binding.btnTvImpVP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu_IM(view, true, R.style.MyPopupStyle);
            }
        });

        binding.simpleImageView.setVisibility(View.VISIBLE);
        binding.VideoView1.setVisibility(View.GONE);
        String p_path=PreferenceUtils.getInstance().getString(R.string.pref_profile_path);
        File fs=new File(p_path);
        if (p_path != null) {
            if (fs.exists()) {
                if (fs.toString().endsWith(".jpg") || fs.toString().endsWith(".JPG")) {
                    Bitmap bm;
                    FileInputStream fi1 = null;
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
                } else if (fs.toString().endsWith(".mp4")||fs.toString().endsWith(".MP4")) {
                    Uri uri = Uri.parse(p_path);
                    binding.VideoView1.setVideoURI(uri);
                    binding.VideoView1.requestFocus();
                    binding.VideoView1.start();
                    binding.VideoView1.setVisibility(View.VISIBLE);
                    binding.simpleImageView.setVisibility(View.GONE);



                }
            }
        }
        binding.VideoView1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mp.setVolume(0, 0);

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

        EnableRuntimePermission();
        return binding.getRoot();
    }
    private void showPopupMenu_IM(View anchor, boolean isWithIcons, int style) {
        //init the wrapper with style
        Context wrapper = new ContextThemeWrapper(getContext(), style);
        //init the popup
        PopupMenu popup = new PopupMenu(wrapper, anchor);

        /*  The below code in try catch is responsible to display icons*/
        if (isWithIcons) {
            try {
                Field[] fields = popup.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if ("mPopup".equals(field.getName())) {
                        field.setAccessible(true);
                        Object menuPopupHelper = field.get(popup);
                        Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
                        Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class);
                        setForceIcons.invoke(menuPopupHelper, true);
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //inflate menu
        popup.getMenuInflater().inflate(R.menu.poupup_menu, popup.getMenu());
        //implement click events
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.image:
                        ImageOpenGallery();
                        break;
                    case R.id.video:
                        VideoOpenGallery();
                        break;
                }
                return true;
            }
        });
        popup.show();
    }
    private void showPopupMenu_CR(View anchor, boolean isWithIcons, int style) {
        //init the wrapper with style
        Context wrapper = new ContextThemeWrapper(getContext(), style);

        //init the popup
        PopupMenu popup = new PopupMenu(wrapper, anchor);

        /*  The below code in try catch is responsible to display icons*/
        if (isWithIcons) {
            try {
                Field[] fields = popup.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if ("mPopup".equals(field.getName())) {
                        field.setAccessible(true);
                        Object menuPopupHelper = field.get(popup);
                        Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
                        Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class);
                        setForceIcons.invoke(menuPopupHelper, true);
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //inflate menu
        popup.getMenuInflater().inflate(R.menu.poupup_menu, popup.getMenu());
        //implement click events
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.image:
                        ImageCapture();
                        break;
                    case R.id.video:
                        VideoCapture();
                        break;
                }
                return true;
            }
        });
        popup.show();

    }

    public void EnableRuntimePermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.CAMERA)) {
            Toast.makeText(getActivity(),"CAMERA permission allows us to Access CAMERA app",     Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(getActivity(),new String[]{
                    Manifest.permission.CAMERA}, RequestPermissionCode);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] result) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (result.length > 0 && result[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
    private void VideoCapture() {
        if (check_permissions()) {
            Functions.make_directry(Variables.app_folder);
            Functions.make_directry(Variables.draft_app_folder);
            Intent intent = new Intent(getActivity(), Video_Recoder_A.class);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.in_from_bottom, R.anim.out_to_top);
        }


    }
    private void ImageCapture() {
        if (check_permissions()) {
//            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            cameraIntent.putExtra(MediaStore.EXTRA_FINISH_ON_COMPLETION, true);
            //if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
//                File  file = null;
//                try {
//                    file = Global.getImageDraft_Image(getContext());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
               // FileOutputStream out = new FileOutputStream(file);
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                File file = new File(Environment.getExternalStorageDirectory(), "/ttu/a" + "/photo_" + timeStamp + ".jpg");

                imageUri1 = FileProvider.getUriForFile(getContext(),BuildConfig.APPLICATION_ID + ".provider", file);
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri1);
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, 0);
                intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 100*100);
                startActivityForResult(intent, 5);


            //Camera.Parameters params = mCamera.getParameters();
//            if (pictureFile != null) {
//                photoURI = FileProvider.getUriForFile(getActivity(), BuildConfig.APPLICATION_ID + ".provider",pictureFile);
//                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                cameraIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,15);
//                startActivityForResult(cameraIntent, REQUEST_PICTURE_CAPTURE);
//            }
        }
    }

    public boolean check_permissions() {
        String[] PERMISSIONS = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA
        };

        if (!hasPermissions(getActivity(), PERMISSIONS)) {
            ActivityCompat.requestPermissions(getActivity(),PERMISSIONS, 2);
        } else {

            return true;
        }

        return false;
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void VideoOpenGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        intent.setType("video/*");
        startActivityForResult(intent, 2);

    }

    private void ImageOpenGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        gallery.setType("image/*");
        startActivityForResult(gallery, 1);
    }

    private static Bitmap rotateImageIfRequired(Bitmap img, Uri selectedImage) throws IOException {
        ExifInterface ei = new ExifInterface(selectedImage.getPath());
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotateImage(img, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateImage(img, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotateImage(img, 270);
            default:
                return img;
        }
    }

    private static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return rotatedImg;
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5) {
            try {
                Bitmap thumbnail = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imageUri1);
                thumbnail = rotateImageIfRequired(thumbnail, imageUri1);

                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                File file = new File(Environment.getExternalStorageDirectory(), "/ttu/a" + "/photo_" + timeStamp + ".jpg");

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

                byte[] bitmapData = bytes.toByteArray();
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(bitmapData);
                fos.flush();
                fos.close();
                imageUri1= Uri.fromFile(file);
                PreferenceUtils.getInstance().putString(R.string.pref_image_path_Draft,imageUri1.getPath());
                Intent intent = new Intent(getActivity(), ImageSelectActivity.class);
                startActivity(intent);



            } catch (Exception e) {
                e.printStackTrace();
            }


       }
        else if (resultCode == RESULT_OK && requestCode == 1)
        {
            imageUri = data.getData();
            String pictureFile1 = null;
            try {
                pictureFile1 = Global.getPictureFileName(getContext());
            } catch (IOException e) {
                e.printStackTrace();
            }
            File myDir = TTULibraryImage(getContext());
            File file = new File(myDir, String.valueOf(pictureFile1));
            try {
                FileOutputStream out = new FileOutputStream(file);
                Bitmap img = getContactBitmapFromURI(getContext(), imageUri);
                img.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.flush();
                out.close();

                //code for refresh adapter
                binding.viewPager.getAdapter().notifyDataSetChanged();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == 2)
        {
            Uri selectedImage = data.getData();
            try {
                String[] filePath = { MediaStore.Video.Media.DATA };
                Cursor c = getContext().getContentResolver().query(selectedImage, filePath,
                        null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String videoPath = c.getString(columnIndex);
                c.close();

                File currentFile = new File(videoPath);
                File destinationFilename = null;// android.os.Environment.getExternalStorageDirectory().getPath()+File.separatorChar+"abc.mp4";
                try {
                    destinationFilename = Global.getVideoPath_filename(getContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (currentFile.exists()) {
                    InputStream in = new FileInputStream(currentFile);
                    OutputStream out = new FileOutputStream(destinationFilename);
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

                    binding.viewPager.getAdapter().notifyDataSetChanged();


                    Log.v("", "Video file saved successfully.");
                } else {
                    Log.v("", "Video saving failed. Source file missing.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("SelectedVideoPath", selectedImage.getPath());
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onResume() {
        getActivity().setTitle("Caller Details");
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        super.onResume();
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter
    {
        //private final Fragment[] childFragments;
        //private final String[] title;

        public ViewPagerAdapter(@NonNull FragmentManager fm, String[] title)
        {
            /*super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            this.title = title;
            childFragments = new Fragment[]
            {
                   new CallerDetailsChooseImage(),
                   new CallerDetailsChooseVideo(),
            };*/
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }


        @Override
        public Fragment getItem(int position) {

            Fragment childFragments = null;
            if(position == 0)
            {
                childFragments =  new CallerDetailsChooseImage();
            }
            else if(position == 1)
            {
                childFragments =  new CallerDetailsChooseVideo();
            }

            return childFragments;
        }

        @Override
        public int getCount() {

            return 2;
        }


        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            if (position == 0)
                title = "IMAGE";
            else if (position == 1)
                title = "VIDEO";

            return title;
        }


    }





    public void waitSpinnerVisible() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.waitSpinner.setVisibility(View.VISIBLE);
            }
        });
    }

    public void waitSpinnerInvisible() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.waitSpinner.setVisibility(View.GONE);
            }
        });
    }

}
