package com.hanif.adminapps.api;

import com.hanif.adminapps.model.Result;
import com.hanif.adminapps.model.Stuff;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Api {

    @GET("barang")
    Call<Result> viewStuff();

    @GET("histori")
    Call<Result> viewHistory();

    @GET(" ")
    Call<Stuff> getStuff();

    @FormUrlEncoded
    @POST("store")
    Call<Result> addStuffToAuction(@Field("id_barang") String stuffId);

    @FormUrlEncoded
    @POST("barang")
    Call<Result> storeStuff(@Field("nama_barang") String stuffName,
                          @Field("tgl") String date,
                          @Field("harga_awal") String stuffPrice,
                          @Field("deskripsi_barang") String stuffDescription);

    @FormUrlEncoded
    @PUT(" ")
    Call<Result> editStuff(@Field("nama_barang") String stuffName,
                           @Field("tgl") String date,
                           @Field("harga_awal") String stuffPrice,
                           @Field("deskripsi_barang") String stuffDescription);

    @DELETE(" ")
    Call<Result> deleteStuff();

    @FormUrlEncoded
    @POST("user/signin")
    Call<Result> signin(@Field("username") String username,
                        @Field("password") String password);

    @FormUrlEncoded
    @POST("user/register")
    Call<Result> register(@Field("username") String username,
                        @Field("password") String password,
                        @Field("nama_petugas") String officerName,
                        @Field("id_level") String idLevel);

    @GET("lelang")
    Call<Result> viewAuction();

    @PUT(" ")
    Call<Result> openAuction();

    @DELETE(" ")
    Call<Result> closeAuction();
}
