package com.kabaladigital.tingtingu.service;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.models.ContactUploadModel;
import com.kabaladigital.tingtingu.networking.ApiClient;
import com.kabaladigital.tingtingu.networking.ApiInterface;
import com.kabaladigital.tingtingu.util.PreferenceUtils;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUpload {
    Context _context;
    String _ErrorMsg;
    JsonArray jsonArrayContact = new JsonArray();
    private static final String TAG = ContactUpload.class.getSimpleName();

    public ContactUpload(Context context) {
        this._context = context;
    }


    public void ContactUpload(Context context, String _msg) {
        Log.d("call----->", "method");
        _context = context;
        _ErrorMsg = _msg;

        JsonObject contactobj = null;
        ContentResolver cr = _context.getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {

                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    String phoneNo = "";
                    int _i = 0;
                    int _total = 0;
                    _total = pCur.getCount();
                    while (pCur.moveToNext()) {
                        contactobj = new JsonObject();
                        phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        phoneNo = phoneNo.replaceAll("\\s", "");

                        Log.i(TAG, "Name: " + name);
                        Log.i(TAG, "Phone Number: " + phoneNo);
                        contactobj.addProperty("name", name);
                        String lastFourDigits = "";     //substring containing last 4 characters
                        if (phoneNo.length() > 10) {
                            lastFourDigits = phoneNo.substring(phoneNo.length() - 10);
                        } else {
                            lastFourDigits = phoneNo;
                        }
                        contactobj.addProperty("number", lastFourDigits);
                        jsonArrayContact.add(contactobj);
                    }
                    pCur.close();
                }


            }
        }
        if (cur != null) {
            cur.close();
        }

        JsonObject contactListObj = new JsonObject();
        contactListObj.add("contactList", jsonArrayContact);

        uploadContact(contactListObj);
    }


    private void uploadContact(JsonObject contactListObj) {
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<ContactUploadModel> call = apiInterface.contactUploadDetails(contactListObj);
        call.enqueue(new Callback<ContactUploadModel>() {
            @Override
            public void onResponse(Call<ContactUploadModel> call,
                                   Response<ContactUploadModel> response) {
                if (response.code() == 200) {
                    //  Toast.makeText(_context, "Contact Upload Successfully", Toast.LENGTH_SHORT).show();
                    String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                    PreferenceUtils.getInstance().putString(R.string.pref_c_upload_date, timeStamp);

                    //  Toast.makeText(_context, "onFailure= " +_ErrorMsg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ContactUploadModel> call, Throwable t) {
                Toast.makeText(_context, "onFailure= " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}