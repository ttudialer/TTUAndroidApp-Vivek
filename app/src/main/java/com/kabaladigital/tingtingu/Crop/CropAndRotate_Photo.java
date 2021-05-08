package com.kabaladigital.tingtingu.Crop;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kabaladigital.tingtingu.ImageHelper.CompressImage;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import com.theartofdev.edmodo.cropper.CropImageView;

import static com.kabaladigital.tingtingu.Class.Global.getContactBitmapFromURI;
import static com.kabaladigital.tingtingu.ImageHelper.Imageview.binding_image;

public class CropAndRotate_Photo extends AppCompatActivity implements View.OnClickListener{
    private FloatingActionButton mFab;
    public static Bitmap croppedImage;
    CropImageView cropImageView;
    private View mWaitSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crop_and_rotate);
        cropImageView = (CropImageView) findViewById(R.id.cropImageView);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mWaitSpinner = findViewById(R.id.wait_spinner);

        try {
            Uri uri = Uri.parse( PreferenceUtils.getInstance().getString(R.string.pref_image_path));
            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
          //  Bitmap img = getContactBitmapFromURI(this, uri);
            cropImageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }


        cropImageView.setGuidelines(CropImageView.Guidelines.ON);
        cropImageView.setScaleType(CropImageView.ScaleType.CENTER_INSIDE);
        cropImageView.setMultiTouchEnabled(true);

        mFab = (FloatingActionButton) findViewById(R.id.nextStep);
        mFab.setOnClickListener(this);

        FloatingActionButton  nextleft = (FloatingActionButton) findViewById(R.id.nextleft);
        nextleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cropImageView.rotateImage(-90);
            }
        });

        FloatingActionButton  nextRight = (FloatingActionButton) findViewById(R.id.nextRight);
        nextRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cropImageView.rotateImage(90);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.nextStep) {
            cropImageView.setOnCropImageCompleteListener(new CropImageView.OnCropImageCompleteListener() {
                @Override
                public void onCropImageComplete(CropImageView view, final CropImageView.CropResult result) {

                    Thread t = new Thread(new Runnable() {
                        public void run() {
                            try {
                                waitSpinnerVisible();
                                croppedImage = result.getBitmap();


                               // GlobalClass.setinvestigatorPhoto(croppedImage);
                                //String _InvsetigatorPhoto = CommonUtilities.InvsetigatorPhotoTemp(CommonUtilities.dbpath + GlobalClass.getFileno() + "/" + GlobalClass.getFileno() + "Inv.jpg");

                                CompressImage obj=new CompressImage();
                                croppedImage= obj.Compress(PreferenceUtils.getInstance().getString(R.string.pref_image_path));
                              //  GlobalClass.setinvestigatorPhoto(croppedImage);
                                //_InvsetigatorPhoto = CommonUtilities.InvsetigatorPhoto(CommonUtilities.dbpath + GlobalClass.getFileno() + "/" + GlobalClass.getFileno() + "Inv.jpg");


                              //  GlobalClass.setInvestigoterPhotoPath(_InvsetigatorPhoto);

                              // setimage();
                                waitSpinnerInvisible();

                                finish();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    t.start();
                }
            });
            try {
                cropImageView.getCroppedImageAsync();
            } catch (Exception e) {
            }
        }
    }

    public void setimage() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding_image.ImageView.setImageBitmap(croppedImage);
            }
        });
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
                binding_image.ImageView.setImageBitmap(croppedImage);
                //btncontinueselfi.setVisibility(View.VISIBLE);
            }
        });
    }
}

