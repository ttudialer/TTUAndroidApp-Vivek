package com.kabaladigital.tingtingu.service;

import android.content.Intent;
import android.telecom.Call;
import android.telecom.InCallService;
import android.util.Log;

import com.kabaladigital.tingtingu.adapter.ConferenceCallAdapter;
import com.kabaladigital.tingtingu.ui.activity.OngoingCallActivity;
import com.kabaladigital.tingtingu.util.CallManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.kabaladigital.tingtingu.ui.activity.OngoingCallActivity.conferenceCallAdapter;


public class CallService extends InCallService {

    public static InCallService inCallServiceInstance;
    boolean is_second=false;
    ArrayList<Integer> arrayList = new ArrayList<Integer>();
    LinkedHashSet<Integer> hashSet ;
    ArrayList<Integer> listWithoutDuplicates ;

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
        //Log.d("size->", ""+arrayList.size());

        Log.d("scall", ""+CallManager.sCalls.size());


    }

    @Override
    public void onCallRemoved(Call call) {
        super.onCallRemoved(call);
        try{
            Log.d("size***", ""+CallManager.sCalls.size());

            //Log.d("prop", ""+CallManager.sCalls.get(1).getDetails().getCallProperties());

            if (inCallServiceInstance.getCalls().size()>0)
            {
                for (int i=0;i<inCallServiceInstance.getCalls().size();i++)
                {
                    Log.d("sizere->", ""+inCallServiceInstance.getCalls().size());
                    CallManager.sCall =  inCallServiceInstance.getCalls().get(i);
                }

                if (CallManager.sCalls.size() == 2 &&
                        CallManager.sCalls.get(0).getState() == Call.STATE_ACTIVE &&
                        CallManager.sCalls.get(1).getDetails().getCallProperties() == Call.Details.PROPERTY_CONFERENCE)
                {
                    CallManager.sCall  =  inCallServiceInstance.getCalls().get(0);
                }
                //Log.d("state", ""+CallManager.sCalls.get(0).getState());
                //Log.d("state", ""+CallManager.sCalls.get(1).getState());
            }
            else {
                CallManager.sCall = null;
            }

           arrayList.add(CallManager.sCalls.size());
           hashSet = new LinkedHashSet<>(arrayList);
           listWithoutDuplicates = new ArrayList<>(hashSet);

            System.out.println("Size -> "+listWithoutDuplicates.size());
            System.out.println("-------------------");
            System.out.println("lastele-> "+listWithoutDuplicates.get(listWithoutDuplicates.size()-1));




            if(listWithoutDuplicates.get(0) == 3 ||  listWithoutDuplicates.get(0) == 2)
            {
                if(listWithoutDuplicates.get(listWithoutDuplicates.size()-1) == 2)
                {
                    //System.out.println("&&&&&");
                    conferenceCallAdapter.demotest();

                }
                else if(listWithoutDuplicates.get(listWithoutDuplicates.size()-1) == 1)
                {
                    //System.out.println("%%%%%");
                    conferenceCallAdapter.demotest();
                }

            }
            else{

                if(listWithoutDuplicates.size() == 3 || listWithoutDuplicates.size() == 4)
                {
                    if(listWithoutDuplicates.get(listWithoutDuplicates.size()-1) == 2)
                    {
                        //System.out.println("@@@@@@@");
                        conferenceCallAdapter.demotest();

                    }
                    else if(listWithoutDuplicates.get(listWithoutDuplicates.size()-1) == 1)
                    {
                        //System.out.println("######");
                        conferenceCallAdapter.demotest();
                    }
                }

            }

           /*if(CallManager.sCalls.size() == 4 || CallManager.sCalls.size() == 3 )
           {
               if(CallManager.sCalls.size() == 2 || CallManager.sCalls.size() == 1)
                   conferenceCallAdapter.demotest();
           }

           else if(CallManager.sCalls.size() == 2 || CallManager.sCalls.size() == 1)
           {
               conferenceCallAdapter.demotest();
           }*/




            if(CallManager.sCalls.size()==0)
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
