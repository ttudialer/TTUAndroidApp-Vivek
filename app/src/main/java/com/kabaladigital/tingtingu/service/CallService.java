package com.kabaladigital.tingtingu.service;

import android.content.Intent;
import android.telecom.Call;
import android.telecom.InCallService;
import android.util.Log;

import com.kabaladigital.tingtingu.ui.activity.OngoingCallActivity;
import com.kabaladigital.tingtingu.util.CallManager;

import static com.kabaladigital.tingtingu.ui.activity.OngoingCallActivity.conferenceCallAdapter;


public class CallService extends InCallService {

    public static InCallService inCallServiceInstance;
    boolean is_second=false;

    @Override
    public void onCallAdded(Call call) {
        super.onCallAdded(call);
        inCallServiceInstance = this;

        Intent intent = new Intent(this, OngoingCallActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

        if(inCallServiceInstance.getCalls().size() <= 2)
        {
            CallManager.sCall = call;
            CallManager.sCalls = inCallServiceInstance.getCalls();

        }
        //Log.d("oncall", ""+inCallServiceInstance.getCalls().size());
        //Log.d("scall", ""+CallManager.sCalls.size());


    }

    @Override
    public void onCallRemoved(Call call) {
        super.onCallRemoved(call);


        try{
           Log.d("size***", ""+CallManager.sCalls.size());
           Log.d("size&&&", ""+inCallServiceInstance.getCalls().size());

            if (inCallServiceInstance.getCalls().size()>0)
            {
                for (int i=0;i<inCallServiceInstance.getCalls().size();i++)
                {
                    CallManager.sCall =  inCallServiceInstance.getCalls().get(i);
                }

                if (CallManager.sCalls.size() == 2 &&
                        CallManager.sCalls.get(0).getState() == Call.STATE_ACTIVE &&
                        CallManager.sCalls.get(1).getDetails().getCallProperties() == Call.Details.PROPERTY_CONFERENCE)
                {
                    CallManager.sCall  =  inCallServiceInstance.getCalls().get(0);
                }

                conferenceCallAdapter.demotest(0);


            }
            else {
                CallManager.sCall = null;
            }

            if(CallManager.sCalls.size()==0 )
            {
                ((OngoingCallActivity) OngoingCallActivity.activity ).endCall();
                ((OngoingCallActivity) OngoingCallActivity.activity ).cancelNotification();
            }




        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }


    }



}
