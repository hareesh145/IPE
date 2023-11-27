package com.indiapoliticaledge.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    static RetrofitClient retrofitClient;
    private final Retrofit retrofit;
    public static String BASE_URL = "http://192.168.1.2:8080/ipe/";
//        public static String BASE_URL = "http://192.168.1.7:8080/LEDBizMobile/";
    private OkHttpClient.Builder httpClient;

    private RetrofitClient(Context context) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(
//                        chain -> {
//                            Request original = chain.request();
//                            Request.Builder newBuilder = original.newBuilder();
//                            Request request = newBuilder.build();
//                            return chain.proceed(request);
//                        }
//                ).addInterceptor(httpLoggingInterceptor).build();
//        retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
//                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.interceptors().add(interceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
    }

    public static RetrofitClient getInstance(Context context) {
        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient(context);
        }
        return retrofitClient;
    }

    public RetrofitAPI getRetrofitAPI() {
        return retrofit.create(RetrofitAPI.class);
    }
}
