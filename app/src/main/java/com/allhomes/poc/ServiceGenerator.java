package com.allhomes.poc;

import com.allhomes.poc.utils.LogUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cli on 25/02/2018.
 */
public class ServiceGenerator
{
    private static String apiBaseUrl;
    private static OkHttpClient.Builder httpClient;
    private static Retrofit.Builder builder;
    private static final String TAG =ServiceGenerator.class.getName();

    public static void initialize(String baseUrl)
    {
        LogUtil.d(TAG, "Entering initialize...");
        apiBaseUrl=baseUrl;
        httpClient=new OkHttpClient().newBuilder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).writeTimeout(20, TimeUnit.SECONDS);
        builder=new Retrofit.Builder().baseUrl(apiBaseUrl).addConverterFactory(GsonConverterFactory.create());
        LogUtil.d(TAG, "Quiting initialize...");
    }

    public static <S> S createService(Class<S> serviceClass)
    {
        LogUtil.d(TAG, "Entering createService...");
        Retrofit retrofit=builder.client(httpClient.build()).build();
        LogUtil.d(TAG, "Quiting createService...");
        return retrofit.create(serviceClass);
    }
}
