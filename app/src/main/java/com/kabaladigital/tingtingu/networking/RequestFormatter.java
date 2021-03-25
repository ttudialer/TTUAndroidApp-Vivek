package com.kabaladigital.tingtingu.networking;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class RequestFormatter {

  /* todo 1. LoginMobileNumber  */
     public static JsonObject jsonObjectLogin (String mobileNumber,
                                               int isOtpVerified,
                                               String referralCode,
                                               String deviceId){

        JsonObject jsonObject = new JsonObject();
        try {
            jsonObject.addProperty("mobileNumber",mobileNumber);
            jsonObject.addProperty("isOtpVerified",isOtpVerified);
            jsonObject.addProperty("referralCode",referralCode);
            jsonObject.addProperty("deviceId",deviceId);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return jsonObject;
    }

    /* todo 2. sms OTP */
     public static JsonObject jsonObjectSmsOtp (String sender,String route,
                                                String country,String message,
                                                String mobileNumber) {
         JsonObject jsonObject = new JsonObject();
         try {
             jsonObject.addProperty("sender",sender);
             jsonObject.addProperty("route",route);
             jsonObject.addProperty("country",country);

             JsonArray jsonArray = new JsonArray();

             JsonObject jsonObject2 = new JsonObject();
             jsonObject2.addProperty("message",message);

             JsonArray jsonArray2 = new JsonArray();
             jsonArray2.add(mobileNumber);

             jsonObject2.add("to",jsonArray2);

             jsonArray.add(jsonObject2);

             jsonObject.add("sms",jsonArray);

         }catch (Exception e){
             e.printStackTrace();
         }
         return jsonObject;
     }



    /* todo 3. mobileNumber/updateMobileNumberInformation */
    public static JsonObject jsonUpdateMobileNumberInfo (String operator,
                                                         String operatorCircle,
                                                         int numberType){

        JsonObject jsonObject = new JsonObject();
        try {
            jsonObject.addProperty("operator",operator);
            jsonObject.addProperty("operatorCircle",operatorCircle);
            jsonObject.addProperty("numberType",numberType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /* Todo 4. UpdateProfile */
    public static JsonObject jsonUpdateProfile (String email, String fullName,
                                                int age, int gender, String state,
                                                String city, String pincode,
                                                ArrayList<String> knownLanguage,
                                                String profession, String education){

        JsonObject jsonObject = new JsonObject();

        JsonArray jsonArray = new JsonArray();

        try {
            for (int i = 0;i<knownLanguage.size();i++){
              //  JsonObject jsonObject2 = new JsonObject();
               // jsonObject2.addProperty("id",i+1);
                jsonArray.add(knownLanguage.get(i));
               // jsonArray.add(jsonObject2);
            }

            jsonObject.addProperty("email",email);
            jsonObject.addProperty("fullName",fullName);
            jsonObject.addProperty("age",age);
            jsonObject.addProperty("gender",gender);
            jsonObject.addProperty("state",state);
            jsonObject.addProperty("city",city);
            jsonObject.addProperty("pincode",pincode);
            jsonObject.add("knownLanguages",jsonArray);
            jsonObject.addProperty("profession",profession);
            jsonObject.addProperty("education",education);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    /* Todo 5. UpdateProfile */
    public static JsonObject jsonUpdateProfile2 (String email, String fullName,
                                                int age, int gender, String state,
                                                String city, String pincode,
                                                ArrayList<String> knownLanguage,
                                                String profession, String education,
                                                 String address1, String address2,String address3){

        JsonObject jsonObject = new JsonObject();

        try {
            jsonObject.addProperty("email",email);
            jsonObject.addProperty("fullName",fullName);
            jsonObject.addProperty("age",age);
            jsonObject.addProperty("gender",gender);
            jsonObject.addProperty("state",state);
            jsonObject.addProperty("city",city);
            jsonObject.addProperty("pincode",pincode);

            JsonArray jsonArray = new JsonArray();
            for (int i = 0; i <knownLanguage.size(); i++) {
//                JsonObject jsonObject2 = new JsonObject();
//                jsonObject2.addProperty("id",i+1);
//                jsonObject2.addProperty("language",knownLanguage.get(i));

                jsonArray.add(knownLanguage.get(i));
                //jsonArray.add(jsonObject2);
            }

            jsonObject.add("knownLanguages", jsonArray);
            jsonObject.addProperty("profession",profession);
            jsonObject.addProperty("education",education);

            JsonObject jsonObjectAddress = new JsonObject();
            jsonObjectAddress.addProperty("address1", address1);
            jsonObjectAddress.addProperty("address2", address2);
            jsonObjectAddress.addProperty("address3", address3);

            jsonObject.add("address",jsonObjectAddress);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    public static JsonObject jsonObjectLogs (String startTime
    ,String endTime,String callType,String callResponse){

        JsonObject jsonObject = new JsonObject();
        try {
            jsonObject.addProperty("startTime",startTime);
            jsonObject.addProperty("endTime",endTime);
            jsonObject.addProperty("callType",callType);
            jsonObject.addProperty("callResponse",callResponse);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return jsonObject;
    }

    public static JsonObject jsonObjectLogs (String startTime
            ,String endTime,String callType,String callResponse,String closeType,int isCallToAction, String callToActionTime){

        JsonObject jsonObject = new JsonObject();
        try {
            jsonObject.addProperty("startTime",startTime);
            jsonObject.addProperty("endTime",endTime);
            jsonObject.addProperty("callType",callType);
            jsonObject.addProperty("callResponse",callResponse);
            jsonObject.addProperty("closeType",closeType);
            jsonObject.addProperty("isCallToAction",isCallToAction);
            jsonObject.addProperty("callToActionTime",callToActionTime);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return jsonObject;
    }

    public static JsonObject jsonObjectOutgoingLogs (String startTime
            ,String endTime,String callType,String callResponse){

        JsonObject jsonObject = new JsonObject();
        try {
            jsonObject.addProperty("startTime",startTime);
            jsonObject.addProperty("endTime",endTime);
            jsonObject.addProperty("callType",callType);
            jsonObject.addProperty("callResponse",callResponse);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return jsonObject;
    }

    public static JsonObject jsonObjectInProgressLogs (String callStartTime, String startTime
            ,String endTime,String callType,String callResponse){

        JsonObject jsonObject = new JsonObject();
        try {
            jsonObject.addProperty("callStartTime",callStartTime);
            jsonObject.addProperty("startTime",startTime);
            jsonObject.addProperty("endTime",endTime);
            jsonObject.addProperty("callType",callType);
            jsonObject.addProperty("callResponse",callResponse);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return jsonObject;
    }

    public static JsonObject jsonObjectRecharge (long rnum, int ramt
            ,String optr,String type,String optional){

        JsonObject jsonObject = new JsonObject();
        try {
            jsonObject.addProperty("rnum",rnum);
            jsonObject.addProperty("ramt",ramt);
            jsonObject.addProperty("optr",optr);
            jsonObject.addProperty("type",type);
            jsonObject.addProperty("optional",optional);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return jsonObject;
    }

    public static JsonObject jsonObjectCheckDeviceID(String deviceId){
        JsonObject jsonObject = new JsonObject();
        try {
            jsonObject.addProperty("deviceId",deviceId);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return jsonObject;
    }
}
