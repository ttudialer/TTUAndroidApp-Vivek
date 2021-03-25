package com.kabaladigital.tingtingu.listener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.kabaladigital.tingtingu.ui.activity.OngoingCallActivity;
import com.kabaladigital.tingtingu.util.CallManager;

public class NotificationActionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action.equals(OngoingCallActivity.ACTION_ANSWER)) {
            // If the user pressed "Answer" from the notification
            CallManager.answer();
        } else if (action.equals(OngoingCallActivity.ACTION_HANGUP)) {
            // If the user pressed "Hang up" from the notification
            CallManager.reject();
        }
    }

}