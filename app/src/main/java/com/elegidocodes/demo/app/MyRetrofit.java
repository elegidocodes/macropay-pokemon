package com.elegidocodes.demo.app;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {

    private static final String BASE_URL = "https://pokeapi.co/api/v2";
    private static Retrofit retrofit;

    private MyRetrofit() {

    }

    public static MyAPI getService() {

        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(59, TimeUnit.SECONDS)
                    .writeTimeout(59, TimeUnit.SECONDS)
                    .readTimeout(59, TimeUnit.SECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit.create(MyAPI.class);
    }

}
