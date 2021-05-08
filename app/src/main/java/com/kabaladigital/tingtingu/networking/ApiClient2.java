package com.kabaladigital.tingtingu.networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient2 {
    //public static final String BASE_URL = "https://ttuproduction.el.r.appspot.com/api/";
    public static final String BASE_URL = "https://ttuproduction.el.r.appspot.com/api/";
    public static final String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb2JpbGVOdW1iZXIiOjg4NTE1OTkyNDcsInVzZXJJZCI6IjYwMDE0Y2Y0NzllMThkMDAwYWUxNmIxZSIsImlzTmV3IjpmYWxzZSwiaGF2ZVByb2ZpbGUiOnRydWUsImlhdCI6MTYxODM4MTQyNSwiaXNzIjoiaHR0cHM6Ly9uZXRjbGFuLmNvbSJ9.ZojWcpTWpBW3RXa7svjE8DVk2Ggp5JzbMIa3w0SDsG8";
    //public static final String BASE_URL = "https://technolite.in/staging/demoatt/ayt/";
    private static Retrofit retrofit = null;
    private static ApiInterface apiWorkInterface;

    public static ApiInterface getProfile_new(){
        if (retrofit == null)
        {
            Gson gson = new GsonBuilder().setLenient().create();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(2, TimeUnit.MINUTES)
                    .writeTimeout(2, TimeUnit.MINUTES) // write timeout
                    .readTimeout(2, TimeUnit.MINUTES)
                    .addInterceptor(new Interceptor() {
                        @NotNull
                        @Override
                        public Response intercept(@NotNull Chain chain) throws IOException {
                            Request newRequest  = chain.request().newBuilder()
                                    .addHeader("Authorization", "Bearer " + token)
                                    .build();
                            return chain.proceed(newRequest);
                        }
                    }).build();

            /*Values Inserted in retrofit*/
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }
        if (apiWorkInterface == null)
        {
            apiWorkInterface = retrofit.create(ApiInterface.class);
        }
        return apiWorkInterface;
    }
}
