package com.hanif.adminapps.api;

import com.hanif.adminapps.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AuctionAPI {
    @GET("lelang")
    Call<Result> view();
}
