package com.kabaladigital.tingtingu.networking;

import com.google.gson.JsonObject;
import com.kabaladigital.tingtingu.models.ActiveInactiveModel;
import com.kabaladigital.tingtingu.models.AllCampaignModel;
import com.kabaladigital.tingtingu.models.Caller_Id;
import com.kabaladigital.tingtingu.models.GlobalVariableModel;
import com.kabaladigital.tingtingu.models.MemberShipTypeModel;
import com.kabaladigital.tingtingu.models.MobileDetailModel;
import com.kabaladigital.tingtingu.models.MobileInfoModel;
import com.kabaladigital.tingtingu.models.ProfileAdv;
import com.kabaladigital.tingtingu.models.ProfileInformationModel;
import com.kabaladigital.tingtingu.models.ProfileResponse;
import com.kabaladigital.tingtingu.models.RechargeHistoryModel;
import com.kabaladigital.tingtingu.models.RechargeModel;
import com.kabaladigital.tingtingu.models.RewardModel;
import com.kabaladigital.tingtingu.models.SurveyModel;
import com.kabaladigital.tingtingu.models.TrailMembershipCheck;
import com.kabaladigital.tingtingu.models.UpdateProfileModel;
import com.kabaladigital.tingtingu.response_model.MobileNumberResponse;
import com.kabaladigital.tingtingu.response_model.SmsOtpResponse;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @Headers("Content-Type: application/json")
    @POST("user/register")
    Call<MobileNumberResponse> mobileRegister(@Body JsonObject body);

    @Headers("Content-Type: application/json")
    @GET("user/checkMembership")
    Call<TrailMembershipCheck> checkMembership();

    @Headers("Content-Type: application/json")
    @POST("user/checkDevice")
    Call<TrailMembershipCheck> checkDevice(@Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST("sendsms")
    Call<SmsOtpResponse> smsOtpResponse(@Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST("user/upMobileInfo")
    Call<MobileInfoModel> updateMobileNumber(
                   @Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST("user/updateProfile")
    Call<UpdateProfileModel> updateProfile(@Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST("user/updateProfile")
    Call<UpdateProfileModel> updateProfile2(@Body JsonObject body);

//    @Headers("Content-Type: application/json")
//    @GET("incomingCall/getincomingcall")
//    Call<IncomingCallDataModel> getAdData();


    @Headers("Content-Type: application/json")
    @GET("user/adv")
    Call<AllCampaignModel> getAdDatas();



    @Headers("Content-Type: application/json")
    @GET("user/adv")
    Call<List<AllCampaignModel>> getAdData();


    @Headers("Content-Type: application/json")
    @GET("user/adv")
    Call<ProfileResponse> getProfile_new();

    @Headers("Content-Type: application/json")
    @GET("user/profile")
    Call<ProfileInformationModel> getUserProfileInformation();

    @Headers("Content-Type: application/json")
    @GET("user/status")
    Call<ActiveInactiveModel> getUserStatusInformation();

    @Headers("Content-Type: application/json")
    @POST("user/status/tempDays")
    Call<ResponseBody> changeStatus(@Query("tempCount") int tempCount);

    @Headers("Content-Type: application/json")
    @POST("user/inclog/add/{id}")
    Call<ResponseBody> saveLogsOnServer(@Path("id") String id, @Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST("user/popupLog/add/{campId}")
    Call<ResponseBody> savePopUpLogsOnServer(@Path("campId") String campId, @Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST("user/outgoingLog/add/{id}")
    Call<ResponseBody> saveOutgoingLogsOnServer(@Path("id") String id, @Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST("user/inProgressLog/add/{id}")
    Call<ResponseBody> saveInProgressLogsOnServer(@Path("id") String id, @Body JsonObject body);

    @GET("user/membership")
    Call<MemberShipTypeModel>getMemberShipType();

    @GET("user/reward")
    Call<List<RewardModel>> getReward();

    @GET("user/walbal")
    Call<RewardModel> getWalletBalance();

    @GET("user/debits")
    Call<List<RechargeHistoryModel>> getRechargeHistory();

    @GET("user/globalsvars")
    Call<GlobalVariableModel> getGlobalVariables();

    @GET("user/whoIsThis")
    Call<MobileDetailModel>getMobileDetails(@Query("mobile") String mobile);

    @Headers("Content-Type: application/json")
    @POST("user/status/add")
    Call<ResponseBody> saveActiveCount(@Query("activeHours") int count);

    @Headers("Content-Type: application/json")
    @POST("user/recharge")
    Call<RechargeModel> rechargeDetails( @Body JsonObject body);

    @POST("Check_Caller_ID")
    Call<Caller_Id> checkCallerId(@Body RequestBody body);

    @GET("survey/assignedsurveys")
    Call<SurveyModel>getSurveyList();

    @POST("/api/user/updateToken")
    Call<JsonObject> updateTokenAccess (@Body JsonObject body);
}