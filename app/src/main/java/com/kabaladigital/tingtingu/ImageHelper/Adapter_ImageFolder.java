package com.kabaladigital.tingtingu.ImageHelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kabaladigital.tingtingu.R;

import com.kabaladigital.tingtingu.viewmodels.Model_Video;

import java.util.ArrayList;

public class Adapter_ImageFolder extends RecyclerView.Adapter<Adapter_ImageFolder.ViewHolder> {

    ArrayList<Model_Video> al_image;
    Context context;
    Activity activity;


    public Adapter_ImageFolder(Context context, ArrayList<Model_Video> al_video, Activity activity) {

        this.al_image = al_video;
        this.context = context;
        this.activity = activity;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv_image;
        RelativeLayout rl_select_image;
        public ViewHolder(View v) {

            super(v);

            iv_image = (ImageView) v.findViewById(R.id.iv_image);
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


        Vholder.rl_select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_gallery = new Intent(context, Activity_Gallery_Image.class);
                intent_gallery.putExtra("video",al_image.get(position).getStr_path());
                activity.startActivity(intent_gallery);

            }
        });

    }

    @Override
    public int getItemCount() {

        return al_image.size();
    }

}

