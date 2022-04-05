package com.google.dunggiaobt.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIRetrofitClient {
    private static Retrofit retrofit=null;
    public static Retrofit getClient (String base_url) {
        OkHttpClient okittpclient = new OkHttpClient.Builder ()
                .readTimeout (  10000, TimeUnit.MILLISECONDS)
                            .writeTimeout (  10000, TimeUnit.MILLISECONDS)
                            .connectTimeout ( 10000, TimeUnit.MILLISECONDS)
                            .retryOnConnectionFailure (true).protocols(Collections.singletonList(Protocol.HTTP_1_1)).build();


        Gson gson = new GsonBuilder().setLenient ().create ();
        retrofit=new Retrofit.Builder().baseUrl(base_url).client(okittpclient).addConverterFactory(GsonConverterFactory.create(gson)).build();
   return  retrofit;
    }
}
