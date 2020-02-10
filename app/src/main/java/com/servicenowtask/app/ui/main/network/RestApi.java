package com.servicenowtask.app.ui.main.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApi {


    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.flickr.com/services/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S cteateService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
