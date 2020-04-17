package com.hanif.test.ui.home;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hanif.test.api.Api;
import com.hanif.test.model.Auction;
import com.hanif.test.model.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Auction>> listAuction = new MutableLiveData<>();
    List<Auction> _listAuction = new ArrayList<>();

    void setAuction(final String auction) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.253:8000/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<Result> call = api.viewAuction();
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                _listAuction = response.body().getResultAuction();
                listAuction.postValue((ArrayList<Auction>) _listAuction);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("Exception", t.getMessage());
            }
        });
    }

    public LiveData<ArrayList<Auction>> getAuction() {
        return listAuction;
    }

    public void showLoading(boolean state, View view) {
        if (state) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }
}