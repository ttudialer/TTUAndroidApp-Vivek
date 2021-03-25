package com.kabaladigital.tingtingu.util;

import android.content.Context;

import com.kabaladigital.tingtingu.database.AppDatabase;
import com.kabaladigital.tingtingu.database.DataRepository;
import com.kabaladigital.tingtingu.database.entity.StateCityModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GetStateCityFromJson {


    public static void getCityStateData(Context context) {
        List<StateCityModel> stateCityModelList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(getJson(context));
            JSONArray jsonArray = jsonObject.getJSONArray("array");
            for (int a =0; a <jsonArray.length(); a++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(a);

                StateCityModel stateCityModel = new StateCityModel();
                stateCityModel.setId(jsonObject1.getString("id"));
                stateCityModel.setCity(jsonObject1.getString("name"));
                stateCityModel.setState(jsonObject1.getString("state"));
                stateCityModelList.add(stateCityModel);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        DataRepository repository = DataRepository
                .getInstance(AppDatabase.getDatabase(context));

        repository.insertStateCity(stateCityModelList);
    }


    public static String getJson(Context context) {
        String json=null;
        try
        {
            // Opening cities.json file
            InputStream is = context.getAssets().open("cities.json");
            // is there any content in the file
            int size = is.available();
            byte[] buffer = new byte[size];
            // read values in the byte array
            is.read(buffer);
            // close the stream --- very important
            is.close();
            // convert byte to string
            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
            return json;
        }
        return json;
    }

}
