package com.hanif.adminapps.api;

import com.hanif.adminapps.model.Result;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface StuffAPI {

    @GET("barang")
    Call<Result> view();

    @FormUrlEncoded
    @POST("store")
    Call<Result> store(@Field("id_barang") String stuffId);

    @FormUrlEncoded
    @POST("barang")
    Call<Result> addStuff(@Field("nama_barang") String stuffName,
                          @Field("kategori_barang") String stuffCategory,
                          @Field("tgl") String date,
                          @Field("harga_awal") String stuffPrice,
                          @Field("deskripsi_barang") String stuffDescription);

    @FormUrlEncoded
    @PUT(" ")
    Call<Result> editStuff(@Field("nama_barang") String stuffName,
                          @Field("kategori_barang") String stuffCategory,
                          @Field("tgl") String date,
                          @Field("harga_awal") String stuffPrice,
                          @Field("deskripsi_barang") String stuffDescription);

    @DELETE(" ")
    Call<Result> delete();
}
