package com.example.poetryapp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

interface ApiInterface {

    @GET("readpoetry.php")
    Call<Data> getApiData();

    @FormUrlEncoded
    @POST("deletepoetry.php")
    Call<Data> deleteData(@Field("id") String id);

    @FormUrlEncoded
    @POST("mypoetry.php")
    Call<Data> insertData(@Field("poetryData") String poetryData, @Field("poetName") String poetName);

    @FormUrlEncoded
    @POST("updatepoetry.php")
    Call<Data> updateData(@Field("poetryData") String poetry_data, @Field("id") int id);

}
