package com.kabaladigital.tingtingu.ui.fragment.callerid;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.Navigation;


import com.kabaladigital.tingtingu.Class.Functions;
import com.kabaladigital.tingtingu.Class.Global;
import com.kabaladigital.tingtingu.Class.Variables;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.Video_Recording.Video_Recoder_A;
import com.kabaladigital.tingtingu.databinding.CallerDetailsFragmentChooseBinding;
import com.kabaladigital.tingtingu.ui.activity.MainActivity;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.kabaladigital.tingtingu.viewmodels.CallerDetailsChooseViewModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.kabaladigital.tingtingu.Class.Global.TTULibraryImage;
import static com.kabaladigital.tingtingu.Class.Global.getContactBitmapFromURI;


public class CallerDetailsChoose extends Fragment {
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    private String pictureFilePath;
    static final int REQUEST_PICTURE_CAPTURE = 1;
    String Video_Image_type;
    private int SELECT_FILE = 1;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;
    private CallerDetailsChooseViewModel mViewModel;
    private CallerDetailsFragmentChooseBinding binding;
    private static String Image_Video_type="";
    public static final int RequestPermissionCode = 1;
    File pictureFile;
    private  Uri photoURI;
    private String langType;
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
                PopupMenu popup = new PopupMenu(getContext(), binding.btnTvCrVP);
                popup.getMenuInflater().inflate(R.menu.poupup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getTitle().equals("Video"))
                            VideoCapture();
                        else if(item.getTitle().equals("Image"))
                            ImageCapture();
                        return true;
                    }
                });
                popup.show();

            }
        });
        binding.btnTvImpVP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(getContext(), binding.btnTvCrVP);
                popup.getMenuInflater().inflate(R.menu.poupup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
//                        Toast.makeText(getContext(),"You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        if(item.getTitle().equals("Video")) {
                            Image_Video_type = "PICK_VIDEO";
                            VideoCapture();
                        }
                        else if(item.getTitle().equals("Image")) {
                            Image_Video_type = "PICK_IMAGE";
                            openGallery();
                        }
                        return true;
                    }
                });
                popup.show();
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


//                Uri uri = Uri.parse(p_path);
//                binding.VideoView1.setVideoURI(uri);
//                binding.VideoView1.requestFocus();
//                binding.VideoView1.start();
            }
        }

        EnableRuntimePermission();
        return binding.getRoot();
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

        Video_Image_type="VIDEO";
//        Intent cameraIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//        try {
//            pictureFile = getPictureVideo();
//        } catch (IOException ex) {
//            Toast.makeText(getActivity(),"Photo file can't be created, please try again",Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (pictureFile != null) {
//            Uri VideoURI = FileProvider.getUriForFile(getActivity(), BuildConfig.APPLICATION_ID + ".provider",pictureFile);
//            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, VideoURI);
//            cameraIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,15);
//            startActivityForResult(cameraIntent, CAMERA_CAPTURE_VIDEO_REQUEST_CODE);
//        }
    }

    private void ImageCapture() {

//        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        cameraIntent.putExtra( MediaStore.EXTRA_FINISH_ON_COMPLETION, true);
//        if (cameraIntent.resolveActivity( getActivity().getPackageManager()) != null) {
//            File pictureFile = null;
//            try {
//                pictureFile = getPictureFile();
//            } catch (IOException ex) {
//                Toast.makeText(getActivity(),"Photo file can't be created, please try again",Toast.LENGTH_SHORT).show();
//                return;
//            }
//            if (pictureFile != null) {
//                photoURI = FileProvider.getUriForFile(getActivity(), BuildConfig.APPLICATION_ID + ".provider",pictureFile);
//                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                cameraIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,15);
//                startActivityForResult(cameraIntent, REQUEST_PICTURE_CAPTURE);
//            }
//        }
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

    private File getPictureFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String pictureFile = "RAM_" + timeStamp;
        File storageDir = Global.TTULibraryImage(getContext()) ;
        File image = File.createTempFile(pictureFile,  "_P.jpg", storageDir);
        return image;
    }

    private File getPictureVideo() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String pictureFile = "RAM_" + timeStamp;
        File storageDir = Global.TTULibraryVideo(getContext()) ;
        File image = File.createTempFile(pictureFile,  "_P.mp4", storageDir);
        return image;
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_CAPTURE_VIDEO_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                PreferenceUtils.getInstance().putString(R.string.pref_image_path,pictureFile.getAbsolutePath());
                Navigation.findNavController(binding.getRoot())
                        .navigate(R.id.action_viewcalleridchoose_to_videoview);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getActivity(),
                        "User cancelled video recording", Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(getActivity(),
                        "Sorry! Failed to record video", Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == REQUEST_PICTURE_CAPTURE && resultCode == RESULT_OK) {
//            Bitmap bitmap ;
//                    //(Bitmap) data.getExtras().get("data");
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), photoURI);
//             } catch (IOException e) {
//                e.printStackTrace();
//            }


//

            PreferenceUtils.getInstance().putString(R.string.pref_image_path,  photoURI.toString());
           // Intent intent = new Intent(getContext(), CropAndRotate_Photo.class);
            //startActivity(intent);

            Navigation.findNavController(binding.getRoot())
                    .navigate(R.id.action_viewcalleridchoose_to_imageview);

            //if (imgFile.exists()) {

//                PreferenceUtils.getInstance().putString(R.string.pref_image_path,pictureFile.getAbsolutePath());
//                Navigation.findNavController(binding.getRoot())
//                        .navigate(R.id.action_viewcalleridchoose_to_imageview);

//                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//                Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(), bmOptions);
//                File myDir = TTULibraryImage(getContext());
//                File file = new File(myDir, String.valueOf(pictureFile));
//                try {
//                    FileOutputStream out = new FileOutputStream(file);
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
//                    out.flush();
//                    out.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            //}
        }
        else if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            if(Image_Video_type == "PICK_IMAGE"){

            }

            imageUri = data.getData();
           String  pictureFile1 = null;
            try {
                pictureFile1 = Global.getPictureFilePath(getContext());
            } catch (IOException ex) {
                Toast.makeText(getActivity(), "Photo file can't be created, please try again", Toast.LENGTH_SHORT).show();
                return;
            }
            File myDir = TTULibraryImage(getContext());
            File file = new File(myDir, String.valueOf(pictureFile1));
            try {
                FileOutputStream out = new FileOutputStream(file);
                Bitmap img = getContactBitmapFromURI(getContext(), imageUri);
                img.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();

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



    private void selectImage() {
        final CharSequence[] items = { "Take Video", "Choose from Library", "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.drawable.camera);
        builder.setTitle("Add Video!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Video")) {
                    //recordVideo();

                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("video/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent,"Select Video"),SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }



    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final Fragment[] childFragments;
        private final String[] title;

        public ViewPagerAdapter(@NonNull FragmentManager fm, String[] title) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            this.title = title;
            childFragments = new Fragment[] {
                   new CallerDetailsChooseImage(),
                    new CallerDetailsChooseVideo(),
            };
        }


        @Override
        public Fragment getItem(int position) {
            return childFragments[position];
        }

        @Override
        public int getCount() {
            return childFragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }


    }


}
