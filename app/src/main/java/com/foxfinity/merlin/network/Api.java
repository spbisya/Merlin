package com.foxfinity.merlin.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Project Merlin. Created by gwa on 3/18/17.
 */

public class Api {
    private static MerlinService sService;
    private static DefinitionsService definitionsService;

    private static String DATAMUSE_URL = "http://api.datamuse.com";

    public static void init() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Gson sGson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();


        Retrofit sRetrofit = new Retrofit.Builder()
                .baseUrl(DATAMUSE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(sGson))
                .client(okHttpClient)
                .build();

        sService = sRetrofit.create(MerlinService.class);

        sRetrofit = new Retrofit.Builder()
                .baseUrl("https://wordsapiv1.p.mashape.com")
                .addConverterFactory(GsonConverterFactory.create(sGson))
                .client(okHttpClient)
                .build();
        definitionsService = sRetrofit.create(DefinitionsService.class);
    }

    public static DefinitionsService getDefinitionsService() {
        return definitionsService;
    }

    public static MerlinService getService() {
        return sService;
    }
}
