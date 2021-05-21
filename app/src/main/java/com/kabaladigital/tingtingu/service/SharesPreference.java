package com.kabaladigital.tingtingu.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kabaladigital.tingtingu.models.ProfileResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SharesPreference
{
    public static SharedPreferences appSharedPrefs;
    private static Context mcontext;

    public static SharedPreferences getInstance(Context context) {
        appSharedPrefs = context.getSharedPreferences("attendance", Context.MODE_PRIVATE);
        mcontext = context;
        return appSharedPrefs;
    }

    public static ProfileResponse getprofile(Context mcontext) {
        Gson gson = new Gson();
        String json = getInstance(mcontext).getString("advert", "");
        return gson.fromJson(json, ProfileResponse.class);
    }

    public static void saveprofile(Context mcontext, ProfileResponse mLovsDatum) {
        SharedPreferences.Editor prefsEditor = getInstance(mcontext).edit();
        Gson gson = new Gson();
        String json = gson.toJson(mLovsDatum);
        prefsEditor.putString("advert", json);
        prefsEditor.apply();
    }




    public static void saveArrayList(ArrayList<String> list, String key){
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = getInstance(mcontext).edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("profilearr", json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public static ArrayList<String> getArrayList(String key){
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        Gson gson = new Gson();
        String json = getInstance(mcontext).getString("profilearr", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }







}