package com.kabaladigital.tingtingu.ImageHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kabaladigital.tingtingu.R;

import com.kabaladigital.tingtingu.viewmodels.Model_Image;
import com.kabaladigital.tingtingu.viewmodels.Model_Video;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import static com.kabaladigital.tingtingu.ui.fragment.callerid.CallerDetailsChoose.binding_choseIV;

public class Adapter_ImageFolder extends RecyclerView.Adapter<Adapter_ImageFolder.ViewHolder> {

    ArrayList<Model_Image> al_image;
    Context context;
    Activity activity;


    public Adapter_ImageFolder(Context context, ArrayList<Model_Image> al_video, Activity activity) {
        this.al_image = al_video;
        this.context = context;
        this.activity = activity;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_image;
        public ImageView btn_share;

        RelativeLayout rl_select_image;
        public ViewHolder(View v) {
            super(v);
            iv_image = (ImageView) v.findViewById(R.id.iv_image);
            btn_share = (ImageView) v.findViewById(R.id.btn_share);
            rl_select_image = (RelativeLayout) v.findViewById(R.id.rl_select_image);
        }
    }

    @Override
    public Adapter_ImageFolder.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adepter_image, parent, false);
        Adapter_ImageFolder.ViewHolder viewHolder1 = new Adapter_ImageFolder.ViewHolder(view);
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(final Adapter_ImageFolder.ViewHolder Vholder, final int position) {
        Glide.with(context).load("file://" + al_image.get(position).getStr_thumb())
                .skipMemoryCache(false)
                .into(Vholder.iv_image);
        Vholder.rl_select_image.setBackgroundColor(Color.parseColor("#FFFFFF"));
        Vholder.rl_select_image.setAlpha(0);

        Vholder.btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareImage(al_image.get(position).getStr_path());
            }
        });


        Vholder.rl_select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_gallery = new Intent(context, Activity_Gallery_Image.class);
                //Log.d("setpath",al_image.get(position).getStr_thumb());
                intent_gallery.putExtra("video", al_image.get(position).getStr_path());
                activity.startActivity(intent_gallery);

            }
        });

        Vholder.rl_select_image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                String _url = al_image.get(position).getStr_path();

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Delete");
                alertDialog.setMessage("Do you want to delete this Image?");
                alertDialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        //Toast.makeText(context,  "You have pressed it long :)", Toast.LENGTH_SHORT).show();
                        File _currentFile = new File(_url);
                        if (_currentFile.exists()) {
                            _currentFile.delete();
                        }
                        binding_choseIV.viewPager.getAdapter().notifyDataSetChanged();
                    }
                });
                alertDialog.setPositiveButton("Keep", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // alertDialog.dismiss();
                    }
                });

                alertDialog.show();


                return true;
            }
        });


    }

    @Override
    public int getItemCount() {

        return al_image.size();
    }


    private void shareImage(String str_image) {

        BitmapFactory.Options bfOptions=new BitmapFactory.Options();
        Bitmap bm=null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(new File(str_image));
            if(fs!=null) {
                bm = BitmapFactory.decodeFileDescriptor(fs.getFD(), null, bfOptions);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(),bm, "Title", null);
        Uri imageUri =  Uri.parse(path);
        share.putExtra(Intent.EXTRA_STREAM, imageUri);
        context.startActivity(Intent.createChooser(share, "Share Image"));
    }

}

