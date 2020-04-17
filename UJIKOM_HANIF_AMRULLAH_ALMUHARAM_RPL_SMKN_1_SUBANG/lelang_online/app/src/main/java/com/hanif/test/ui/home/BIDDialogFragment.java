package com.hanif.test.ui.home;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hanif.test.DetailAuctionStuffFragment;
import com.hanif.test.R;
import com.hanif.test.adapter.AuctionAdapter;
import com.hanif.test.api.Api;
import com.hanif.test.model.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class BIDDialogFragment extends DialogFragment implements View.OnClickListener {

    private EditText edtBid;
    private OnOptionDialogListener optionDialogListener;
    private ProgressBar progressBar;

    public static String message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_biddialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnBid = view.findViewById(R.id.btn_bid);
        btnBid.setOnClickListener(this);
        Button btnClose = view.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(this);
        edtBid = view.findViewById(R.id.edt_bid);

        progressBar = view.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Fragment fragment = getParentFragment();
        if (fragment instanceof DetailAuctionStuffFragment) {
            DetailAuctionStuffFragment detailCategoryFragment = (DetailAuctionStuffFragment) fragment;
            this.optionDialogListener = detailCategoryFragment.optionDialogListener;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.optionDialogListener = null;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_close){
            getDialog().cancel();
        } else if(view.getId() == R.id.btn_bid){
            progressBar.setVisibility(View.VISIBLE);
            String price = edtBid.getText().toString().trim();
            bid(price);
        }

        if (optionDialogListener != null) {
            optionDialogListener.onOptionChosen(message);
        }
        getDialog().dismiss();
    }

    public interface OnOptionDialogListener {
        void onOptionChosen(String text);
    }

    private void bid(String bid_price) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.253:8000/api/v1/penawaran/"+ DetailAuctionStuffFragment.auctionId +"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<Result> call = api.bid(bid_price, "1");
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressBar.setVisibility(View.GONE);
                String messages = response.body().getMessage();
                message = messages;
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
