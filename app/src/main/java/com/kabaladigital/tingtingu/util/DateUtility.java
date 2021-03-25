package com.kabaladigital.tingtingu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtility {

    public static long convertDbDate(String value){
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat= new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = inputFormat.parse(value);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long convertDbEndDate(String value){
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat= new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = inputFormat.parse(value);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, 1);
            date = c.getTime();
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getCurrentDateInLong(){
        Date todaysdate = new Date();
        return todaysdate.getTime();
    }


    public static String getCurrentDate(){
        Date todaysdate = new Date();
        return todaysdate.toString();
    }

    public static String getCurrentMidNightDate(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);// for 0 hour
        calendar.set(Calendar.MINUTE, 0);// for 0 min
        calendar.set(Calendar.SECOND, 0);// for 0 sec

        return String.valueOf(calendar.getTime());
    }

    public static String getDateFormatted(){
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    }


    public static int checkDateTimeDifference(String startDateTime) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");

        try {

                if(startDateTime.length() == 0)
                {
                    return 0;
                }
                else{
                Date startDate = simpleDateFormat.parse(startDateTime);
                Date endDate = simpleDateFormat.parse(new Date().toString());

                //milliseconds
                long different = endDate.getTime() - startDate.getTime();

                System.out.println("startDate : " + startDate);
                System.out.println("endDate : "+ endDate);
                System.out.println("different : " + different);

                long secondsInMilli = 1000;
                long minutesInMilli = secondsInMilli * 60;
                long hoursInMilli = minutesInMilli * 60;
                long daysInMilli = hoursInMilli * 24;

//            long elapsedDays = different / daysInMilli;
//            different = different % daysInMilli;

              long elapsedHours = different / hoursInMilli;
              different = different % hoursInMilli;

//            long elapsedMinutes = different / minutesInMilli;
//            different = different % minutesInMilli;

//            long elapsedSeconds = different / secondsInMilli;
              return (int) elapsedHours;
            }

        } catch (ParseException e) {
            e.printStackTrace();

        }

        return 0;
    }

    public static boolean checkDateDifference(String startDateTime) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date startDate = simpleDateFormat.parse(startDateTime);
            Date endDate = simpleDateFormat.parse(getDateFormatted());

            //milliseconds
            long different = endDate.getTime() - startDate.getTime();

            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;

            long elapsedDays = different / daysInMilli;
            different = different % daysInMilli;

            return elapsedDays > 0;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }

//    public static boolean checkCampaignDate(String startDate, String endDate) {
//        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
//        Date start = null;
//        Date end = null;
//        try {
//            start = sdformat.parse(startDate);
//            end = sdformat.parse(endDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//
//        if(d1.compareTo(d2) > 0) {
//            System.out.println("Date 1 occurs after Date 2");
//        } else if(d1.compareTo(d2) < 0) {
//            System.out.println("Date 1 occurs before Date 2");
//        } else if(d1.compareTo(d2) == 0) {
//            System.out.println("Both dates are equal");
//        }
//    }

    public static String getCallDateFormat(String mCallDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd HH:mm");
        String dateString = simpleDateFormat.format(mCallDate);
        return dateString;
    }

    public static long checkTimeDifference(String startDateTime, String endDateTime) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

        try {
            Date startDate = simpleDateFormat.parse(startDateTime);
            Date endDate = simpleDateFormat.parse(endDateTime);

            //milliseconds
            long different = endDate.getTime() - startDate.getTime();

            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;

            long elapsedHours = different / hoursInMilli;
            different = different % hoursInMilli;

            long elapsedDays = different / daysInMilli;
            different = different % daysInMilli;

            long elapsedMinutes = different / minutesInMilli;
            different = different % minutesInMilli;

            long elapsedSeconds = different / secondsInMilli;

            return elapsedSeconds;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static String convertRechargeHistory(String value){
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat= new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date newDate = inputFormat.parse(value);
            value = outputFormat.format(newDate);
            return value;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return value;
    }
}
