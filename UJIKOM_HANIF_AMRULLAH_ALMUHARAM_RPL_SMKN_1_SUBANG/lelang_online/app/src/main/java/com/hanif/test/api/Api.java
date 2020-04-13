package com.hanif.test.api;

import com.hanif.test.model.Result;
import com.hanif.test.model.Stuff;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @GET("lelang")
    Call<Result> viewAuction();

    @GET(" ")
    Call<Stuff> getStuff();

    @FormUrlEncoded
    @POST("user/signin")
    Call<Result> signin(@Field("username") String username,
                        @Field("password") String password);

    @FormUrlEncoded
    @POST("user/register")
    Call<Result> register(@Field("username") String username,
                          @Field("password") String password,
                          @Field("nama_lengkap") String fullName,
                          @Field("id_level") String idLevel,
                          @Field("telp") String phoneNumber);
}
