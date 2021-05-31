package com.kabaladigital.tingtingu.Image;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    Bitmap bitmap_new;
    int height;
    int width;
    int finalHeight;
    int finalWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_select);
        activity = this;
        initUIWidgets();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
      int  maxHeight = displayMetrics.heightPixels;
        int  maxWidth = displayMetrics.widthPixels;

//        double ASPECT_RATIO = 4.0 / 3.0;
//        if (width > height * ASPECT_RATIO) {
//            width = (int) (height * ASPECT_RATIO + 0.5);
//        } else {
//            height = (int) (width / ASPECT_RATIO + 0.5);
//        }

        waitSpinnerVisible();
        imageUri =    PreferenceUtils.getInstance().getString(R.string.pref_image_path_Draft);
        bitmap = BitmapFactory.decodeFile(imageUri);

         width = bitmap.getWidth();
         height = bitmap.getHeight();
        float ratioBitmap = (float) width / (float) height;
        float ratioMax = (float) maxWidth / (float) maxHeight;

         finalWidth = maxWidth;
         finalHeight = maxHeight;
        if (ratioMax > ratioBitmap) {
            finalWidth = (int) ((float)maxHeight * ratioBitmap);
        } else {
            finalHeight = (int) ((float)maxWidth / ratioBitmap);
        }


        Toast.makeText(getApplicationContext(),finalWidth + " :  " + finalHeight,     Toast.LENGTH_LONG).show();
        bitmap= Bitmap.createScaledBitmap(bitmap,finalWidth,finalHeight,true);
        placeHolderImageView.setImageBitmap(bitmap);
        bitmap_new=bitmap;
        waitSpinnerInvisible();


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
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out); // bmp is your Bitmap instance
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
        //File sd = Environment.getExternalStorageDirectory();
        //File image = new File(sd+imageUri, "imageName");
        //BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        //bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);


       // bitmap= Bitmap.createScaledBitmap(bitmap_new,width,height,true);
        bitmap= Bitmap.createScaledBitmap(bitmap_new,finalWidth,finalHeight,true);
        //placeHolderImageView.setImageBitmap(filter.processFilter(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getApplicationContext().getResources(), imageUri), 640, 840, false)));
        bitmap=filter.processFilter(bitmap);
        placeHolderImageView.setImageBitmap(bitmap);

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