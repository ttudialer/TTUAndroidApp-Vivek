package com.kabaladigital.tingtingu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.ui.activity.OngoingCallActivity;

import java.util.ArrayList;
import java.util.List;

public class RejectCallMessagesAdapter extends RecyclerView.Adapter<RejectCallMessagesAdapter.ViewHolder> {

    private List<String> messageList = new ArrayList<>();
    private OngoingCallActivity ongoingCallActivity;

    public RejectCallMessagesAdapter(List<String> messageList
            , OngoingCallActivity ongoingCallActivity) {
        this.messageList = messageList;
        this.ongoingCallActivity = ongoingCallActivity;
    }

    @NonNull
    @Override
    public RejectCallMessagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_reject_message, viewGroup, false);
        return new RejectCallMessagesAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RejectCallMessagesAdapter.ViewHolder holder, int position) {

        holder.tvMessage.setText(messageList.get(position));
        if (messageList.get(position).equals("Write a new message")){
            holder.layoutSendMessage.setVisibility(View.VISIBLE);
            holder.tvMessage.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ongoingCallActivity.sendMessage(messageList.get(position));
            }
        });

        holder.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ongoingCallActivity.sendMessage(holder.etMessage.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMessage;
        EditText etMessage;
        ImageButton btnSend;
        LinearLayout layoutSendMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tv_message);
            etMessage = itemView.findViewById(R.id.et_message);
            btnSend = itemView.findViewById(R.id.btn_send);
            layoutSendMessage = itemView.findViewById(R.id.layout_send_sms);
        }
    }
}

