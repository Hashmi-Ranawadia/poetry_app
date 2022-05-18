package com.example.poetryapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitInstance {

    private static Retrofit retrofit;
    private static final String BASEURL = "http://192.168.140.80/mypoetryapp/";

    public static Retrofit getRetrofit() {

        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }
}
