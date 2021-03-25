package com.kabaladigital.tingtingu.service;

import android.content.Intent;
import android.telecom.Call;
import android.telecom.InCallService;

import com.kabaladigital.tingtingu.ui.activity.OngoingCallActivity;
import com.kabaladigital.tingtingu.util.CallManager;


public class CallService extends InCallService {

    public static InCallService inCallServiceInstance;

    @Override
    public void onCallAdded(Call call) {
        super.onCallAdded(call);
        inCallServiceInstance = this;
        Intent intent = new Intent(this, OngoingCallActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        CallManager.sCall = call;
        CallManager.sCalls = inCallServiceInstance.getCalls();
    }

    @Override
    public void onCallRemoved(Call call) {
        super.onCallRemoved(call);
        try{
            if (inCallServiceInstance.getCalls().size()>0)
            {
                for (int i=0;i<inCallServiceInstance.getCalls().size();i++){
                    CallManager.sCall =  inCallServiceInstance.getCalls().get(i);
                }
                if (CallManager.sCalls.size() == 2 && CallManager.sCalls.get(0).getState() == Call.STATE_ACTIVE && CallManager.sCalls.get(1).getDetails().getCallProperties() == Call.Details.PROPERTY_CONFERENCE){
                    CallManager.sCall =  inCallServiceInstance.getCalls().get(0);
                }
            }else {
                CallManager.sCall = null;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }


    }



}
