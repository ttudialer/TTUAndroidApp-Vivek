package com.kabaladigital.tingtingu.VideoHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kabaladigital.tingtingu.ImageHelper.Adapter_ImageFolder;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.viewmodels.Model_Video;

import java.io.File;
import java.util.ArrayList;

import static com.kabaladigital.tingtingu.ui.fragment.callerid.CallerDetailsChoose.binding_choseIV;


public class Adapter_VideoFolder extends RecyclerView.Adapter<Adapter_VideoFolder.ViewHolder> {

    ArrayList<Model_Video> al_video;
    Context context;
    Activity activity;
    public Adapter_VideoFolder(Context context, ArrayList<Model_Video> al_video, Activity activity) {
        this.al_video = al_video;
        this.context = context;
        this.activity = activity;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_image;
        RelativeLayout rl_select;
        public ViewHolder(View v) {
            super(v);
            iv_image = (ImageView) v.findViewById(R.id.iv_image);
            rl_select = (RelativeLayout) v.findViewById(R.id.rl_select);
        }
    }

    @Override
    public Adapter_VideoFolder.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_videos, parent, false);
        Adapter_VideoFolder.ViewHolder viewHolder1 = new Adapter_VideoFolder.ViewHolder(view);
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(final ViewHolder Vholder, final int position) {
        Glide.with(context).load("file://" + al_video.get(position).getStr_thumb())
                .skipMemoryCache(false)
                .into(Vholder.iv_image);
        Vholder.rl_select.setBackgroundColor(Color.parseColor("#FFFFFF"));
        Vholder.rl_select.setAlpha(0);

        Vholder.rl_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_gallery = new Intent(context,Activity_galleryview.class);
                intent_gallery.putExtra("video",al_video.get(position).getStr_path());
                activity.startActivity(intent_gallery);

            }
        });
        Vholder.rl_select.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                String _url=al_video.get(position).getStr_path();

                AlertDialog.Builder alertDialog = new  AlertDialog.Builder(context);
                alertDialog.setTitle("Delete");
                alertDialog.setMessage("Do you want to delete this Image?");
                alertDialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        //Toast.makeText(context,  "You have pressed it long :)", Toast.LENGTH_SHORT).show();
                        File _currentFile = new File(_url);
                        if(_currentFile.exists()){
                            _currentFile.delete();
                        }
                        binding_choseIV.viewPager.getAdapter().notifyDataSetChanged();
                    } });
                alertDialog.setPositiveButton("Keep", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // alertDialog.dismiss();
                    } });

                alertDialog.show();


                return true;
            }
        });

    }

    @Override
    public int getItemCount() {

        return al_video.size();
    }

}

