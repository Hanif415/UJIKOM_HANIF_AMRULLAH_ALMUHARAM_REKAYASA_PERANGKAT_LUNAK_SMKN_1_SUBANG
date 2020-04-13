package com.hanif.adminapps.api;

import com.hanif.adminapps.model.Result;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginAPI {

    @FormUrlEncoded
    @POST("user/signin")
    Call<Result> signin(@Field("username") String username,
                        @Field("password") String password);

}
