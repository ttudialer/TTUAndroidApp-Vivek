package com.kabaladigital.tingtingu.Image;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kabaladigital.tingtingu.Class.Functions;
import com.kabaladigital.tingtingu.Class.Global;
import com.kabaladigital.tingtingu.Class.Variables;
import com.kabaladigital.tingtingu.ImageHelper.Activity_Gallery_Image;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.Video_Recording.Preview_Video_A;
import com.kabaladigital.tingtingu.ui.activity.MainActivity;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.zomato.photofilters.FilterPack;
import com.zomato.photofilters.imageprocessors.Filter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ImageSelectActivity extends AppCompatActivity implements ThumbnailCallback {
    static {
        System.loadLibrary("NativeImageProcessor");
    }

    private Activity activity;
    private RecyclerView thumbListView;
    private ImageView placeHolderImageView;
    private View mWaitSpinner;
    String imageUri;
    Bitmap bitmap;
    Bitmap bitmap1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_select);

        activity = this;
        imageUri =    PreferenceUtils.getInstance().getString(R.string.pref_image_path_Draft);
        bitmap = BitmapFactory.decodeFile(imageUri);
        initUIWidgets();

        findViewById(R.id.next_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waitSpinnerVisible();
                File newfile=null;
                try {

                    newfile = Global.getImagePath_P(ImageSelectActivity.this) ;
                    if(newfile.exists()){
                        newfile.delete();
                    }
                    try (FileOutputStream out = new FileOutputStream(newfile)) {
                        bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, out); // bmp is your Bitmap instance
                        Toast.makeText(getApplicationContext(),"Image saved in TTU library",     Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Error in Image saved in TTU library",     Toast.LENGTH_LONG).show();
                    }
                    Log.v("", "Image file saved successfully.");

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Error in Image saved in TTU library",     Toast.LENGTH_LONG).show();
                }
                waitSpinnerInvisible();
                finish();


//                if (select_postion == 0) {
//                    try {
//                        Functions.copyFile(new File(Variables.outputfile2),
//                                new File(Variables.output_filter_file));
//                        Toast.makeText(context, Variables.output_filter_file, Toast.LENGTH_SHORT).show();
//                        Toast.makeText(context, "Video saved successfully1", Toast.LENGTH_SHORT).show();
//                        Intent intent1 = new Intent(Preview_Video_A.this, MainActivity.class);
//                        startActivity(intent1);
//                        finish();
//                        //gotopostScreen();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        Log.d(Variables.tag, e.toString());
//                        save_Video(Variables.outputfile2, Variables.output_filter_file);
//                    }
//                } else
//                    save_Video(Variables.outputfile2, Variables.output_filter_file);
            }
        });

        findViewById(R.id.Goback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
            }
        });
    }

    private void initUIWidgets() {
        thumbListView = (RecyclerView) findViewById(R.id.thumbnails);
        placeHolderImageView = (ImageView) findViewById(R.id.place_holder_imageview);
        //placeHolderImageView.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getApplicationContext().getResources(), drawable), 640, 840, false));
        placeHolderImageView.setImageBitmap(bitmap);
        mWaitSpinner = findViewById(R.id.wait_spinner);

        initHorizontalList();
    }

    private void initHorizontalList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManager.scrollToPosition(0);
        thumbListView.setLayoutManager(layoutManager);
        thumbListView.setHasFixedSize(true);
        bindDataToAdapter();
    }

    private void bindDataToAdapter() {
        final Context context = this.getApplication();
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                //Bitmap thumbImage = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), drawable), 640, 640, false);
                ThumbnailsManager.clearThumbs();
                List<Filter> filters = FilterPack.getFilterPack(getApplicationContext());

                for (Filter filter : filters) {
                    ThumbnailItem thumbnailItem = new ThumbnailItem();
                    //thumbnailItem.image = thumbImage;
                    thumbnailItem.image = bitmap;
                    thumbnailItem.filter = filter;
                    ThumbnailsManager.addThumb(thumbnailItem);
                }
                List<ThumbnailItem> thumbs = ThumbnailsManager.processThumbs(context);
                ThumbnailsAdapter adapter = new ThumbnailsAdapter(thumbs, (ThumbnailCallback) activity);
                thumbListView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        };
        handler.post(r);
    }

    @Override
    public void onThumbnailClick(Filter filter) {
        File sd = Environment.getExternalStorageDirectory();
        File image = new File(sd+imageUri, "imageName");
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bitmap1 = BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);
        //bitmap1 = Bitmap.createScaledBitmap(bitmap,640,840,true);
        //placeHolderImageView.setImageBitmap(filter.processFilter(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getApplicationContext().getResources(), imageUri), 640, 840, false)));
        bitmap1=filter.processFilter(bitmap1);
        placeHolderImageView.setImageBitmap(bitmap1);

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


}