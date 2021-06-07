package com.kabaladigital.tingtingu.service;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.google.gson.JsonArray;
import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.util.PreferenceUtils;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class UploadContactService extends Worker {
        Context context;
        JsonArray jsonArrayContact = new JsonArray();
        private static final String TAG = UploadContactService.class.getSimpleName();

        public UploadContactService(@NonNull Context context, @NonNull WorkerParameters workerParams) {
            super(context, workerParams);
            this.context = context;
        }

        @NonNull
        @Override
        public ListenableWorker.Result doWork() {
            Log.d("call----->","method");
            String _DBtime = PreferenceUtils.getInstance().getString(R.string.pref_c_upload_date);
                if(_DBtime ==null){
                    ContactUpload _cu = new ContactUpload(context);
                    _cu.ContactUpload(context, "main_C");
                }

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                long _hour=           Hours_Details();
                if(_hour>24){
                    ContactUpload _cu = new ContactUpload(context);
                    _cu.ContactUpload(context, "main_C");
                }
            } else {
                String _ctime = new SimpleDateFormat("yyyyMMdd").format(new Date());
                if (_ctime.equalsIgnoreCase(_DBtime) == false) {
                    ContactUpload _cu = new ContactUpload(context);
                    _cu.ContactUpload(context, "main_C");
                }
            }

            return ListenableWorker.Result.success();
        }
        @Override
        public void onStopped() {
            super.onStopped();
            Log.i(TAG, "OnStopped called for this worker");
        }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public long Hours_Details() {
        String date1 = PreferenceUtils.getInstance().getString(R.string.pref_c_upload_date);;
        String date2  = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        Duration diff = Duration.between(LocalDateTime.parse(date1, formatter),
                LocalDateTime.parse(date2, formatter));
        long hours=0;
        if (diff.isZero()) {
            System.out.println("0m");
        } else {
            long days = diff.toDays();
            if (days != 0) {
                System.out.print("" + days + "d ");
                diff = diff.minusDays(days);
            }
            hours = diff.toHours();
            if (hours != 0) {
                System.out.print("" + hours + "h ");
                diff = diff.minusHours(hours);
            }
            long minutes = diff.toMinutes();
            if (minutes != 0) {
                System.out.print("" + minutes + "m ");
                diff = diff.minusMinutes(minutes);
            }
            long seconds = diff.getSeconds();
            if (seconds != 0) {
                System.out.print("" + seconds + "s ");
            }
            System.out.println();

        }
        return hours;
    }


    }