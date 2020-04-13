package com.hanif.adminapps.ui.stuff.crud;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hanif.adminapps.MainActivity;
import com.hanif.adminapps.R;
import com.hanif.adminapps.adapter.StuffAdapter;
import com.hanif.adminapps.api.Api;
import com.hanif.adminapps.model.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DetailStuffFragment extends Fragment {

    public static final String URL = "http://192.168.43.253:8000/api/v1/lelang/";
    private ProgressDialog progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_stuff, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        Hide a bottom navigation
        BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view);
        navBar.setVisibility(View.GONE);

//        get stuff name and put into textView
        TextView tvNameStuff = view.findViewById(R.id.tv_stuff_name);
        String stuffName = getArguments().getString(StuffAdapter.StuffViewHolder.EXTRA_NAME);
        tvNameStuff.setText(stuffName);

//        get stuff price and put into textView
        TextView tvStuffPrice = view.findViewById(R.id.tv_stuff_price);
        String stuffPrice = getArguments().getString(StuffAdapter.StuffViewHolder.EXTRA_PRICE);
        tvStuffPrice.setText(stuffPrice);

//        get stuff Description and put into textView
        TextView tvStuffDescription = view.findViewById(R.id.tv_stuff_description);
        String stuffDescription = getArguments().getString(StuffAdapter.StuffViewHolder.EXTRA_DESCRIPTION);
        tvStuffDescription.setText(stuffDescription);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToAuction();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Detail Stuff");
    }

    private void addToAuction(){
        progress = new ProgressDialog(getActivity());
        progress.setCancelable(false);
        progress.setMessage("Loading...");
        progress.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        String stuffId = getArguments().getString(StuffAdapter.StuffViewHolder.EXTRA_ID);

        Call<Result> call = api.addStuffToAuction(stuffId);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progress.dismiss();

                String message = response.body().getMessage();
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
