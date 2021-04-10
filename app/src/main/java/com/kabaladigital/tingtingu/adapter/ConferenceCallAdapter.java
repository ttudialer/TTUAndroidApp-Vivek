package com.kabaladigital.tingtingu.adapter;

import android.content.Context;
import android.net.Uri;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.database.entity.Contact;
import com.kabaladigital.tingtingu.ui.activity.OngoingCallActivity;
import com.kabaladigital.tingtingu.util.CallManager;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ConferenceCallAdapter extends RecyclerView.Adapter<ConferenceCallAdapter.ViewHolder> {

    private List<Call> calls = new ArrayList<>();
    private Context context;
    private OngoingCallActivity ongoingCallActivity;


    public ConferenceCallAdapter(List<Call> calls, Context context, OngoingCallActivity ongoingCallActivity) {
        this.calls = calls;
        this.context = context;
        this.ongoingCallActivity = ongoingCallActivity;
    }

    @NonNull
    @Override
    public ConferenceCallAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_conference_call, viewGroup, false);
        return new ConferenceCallAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ConferenceCallAdapter.ViewHolder holder, int position) {
        Call call = calls.get(position);

        holder.tvTime.setText(returnStatus(call.getState()));
        Contact callerContact = CallManager.getDisplayContact(context,call);

        try{
            if (!callerContact.getName().isEmpty()) {
                holder.tvNameNumber.setText(callerContact.getName());

                if (callerContact.getPhotoUri() != null) {
                    holder.circleImageView.setImageURI(Uri.parse(callerContact.getPhotoUri()));
                }
            } else {

                holder.tvNameNumber.setText("Incoming Call");
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        holder.ivHold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ongoingCallActivity.endCall(position);
//                CallManager.sCalls.get(position).disconnect();
            }
        });

    }

    @Override
    public int getItemCount() {

        Log.d("sizeinadp",""+calls.size());
        return calls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameNumber, tvTime;
        CircleImageView circleImageView;
        ImageView imageView, ivHold;
        FrameLayout frameLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameNumber = itemView.findViewById(R.id.tv_name_number);
            tvTime = itemView.findViewById(R.id.tv_time);
            circleImageView = itemView.findViewById(R.id.image_photo);
            imageView = itemView.findViewById(R.id.image_placeholder);
            frameLayout = itemView.findViewById(R.id.frameLayout);

            ivHold = itemView.findViewById(R.id.iv_end);
        }
    }

    private String returnStatus(int state){
        String statusTextRes;
        switch (state) {
            case Call.STATE_ACTIVE: // Ongoing
                statusTextRes = context.getResources().getString(R.string.status_call_active);
                break;
            case Call.STATE_DISCONNECTED: // Ended
                statusTextRes = context.getResources().getString(R.string.status_call_disconnected);
                break;
            case Call.STATE_RINGING: // Incoming
                statusTextRes = context.getResources().getString(R.string.status_call_incoming);
                break;
            case Call.STATE_DIALING: // Outgoing
                statusTextRes = context.getResources().getString(R.string.status_call_dialing);
                break;
            case Call.STATE_CONNECTING: // Connecting (probably outgoing)
                statusTextRes = context.getResources().getString(R.string.status_call_dialing);
                break;
            case Call.STATE_HOLDING: // On Hold
                statusTextRes = context.getResources().getString(R.string.status_call_holding);
                break;
            default:
                statusTextRes = context.getResources().getString(R.string.status_call_active);
                break;
        }
        return statusTextRes;
    }


    public void demotest(int position)
    {
        Log.d("arrsize",""+calls.size());
        calls.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, calls.size());

        //calls.remove(0);
        //conferenceCallAdapter = new ConferenceCallAdapter(calls, this, OngoingCallActivity.this);
        //binding.ongoingCallLayout.rvCalls.setAdapter(conferenceCallAdapter);

        //conferenceCallAdapter.notifyDataSetChanged();
    }
}

