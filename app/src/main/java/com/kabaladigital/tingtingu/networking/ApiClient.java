package com.kabaladigital.tingtingu.networking;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.util.PreferenceUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    //todo TTU Api client
    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request newRequest  = chain.request().newBuilder()
                            .addHeader("Authorization", " Bearer " + PreferenceUtils.getInstance().getString(R.string.pref_user_token_value))
                            .build();
                    return chain.proceed(newRequest);
                }
            })
            .build();

<<<<<<< HEAD
  // todo Msg91 SMS for OTP client authKey
  private static OkHttpClient httpSmsOtpClient = new OkHttpClient.Builder()
          .addInterceptor(new Interceptor() {
              @Override
              public Response intercept(Chain chain) throws IOException {
                  Request newRequest  = chain.request().newBuilder()
                          .addHeader("authkey","315206A0HI8Th3vB2C60ae37e5P1" )
                          .build();
                  return chain.proceed(newRequest);
              }
          })
          .build();


//    public static final String BASE_URL = "https://devapi-dot-tingtingu-285110.el.r.appspot.com/api/";
=======
    // todo Msg91 SMS for OTP client authKey
    private static OkHttpClient httpSmsOtpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request newRequest  = chain.request().newBuilder()
                            .addHeader("authkey","315206A0HI8Th3vB2C60ae37e5P1" )
                            .build();
                    return chain.proceed(newRequest);
                }
            })
            .build();


    //    public static final String BASE_URL = "https://devapi-dot-tingtingu-285110.el.r.appspot.com/api/";
>>>>>>> ee8cab7ffab88267b3e2f341ba62855161884f51
    public static final String BASE_URL = "https://ttuproduction.el.r.appspot.com/api/";
    public static final String URL = "https://ttuproduction.el.r.appspot.com/";
    public static final String BASE_URL_SMS_OTP = "https://api.msg91.com/api/v2/";

    public static final String URL1 = "https://productionadmin-dot-ttuproduction.el.r.appspot.com/";
    public static final String VP_URL = "http://virtualpages.in/tingtingu.com/global-controller/Webservice_c/";



    public static <S> S createService(Class<S> serviceClass ) {
        Retrofit builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        return builder.create(serviceClass);
    }


    public static <S> S createSMSOTPService(Class<S> createSMSOTPService ) {
        Retrofit builder = new Retrofit.Builder()
                .baseUrl(BASE_URL_SMS_OTP)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpSmsOtpClient)
                .build();
        return builder.create(createSMSOTPService);
    }

    public static <S> S createVPService(Class<S> createSMSOTPService ) {
        Retrofit builder = new Retrofit.Builder()
                .baseUrl(VP_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return builder.create(createSMSOTPService);
    }



}
