package com.hanif.adminapps.ui.auction;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hanif.adminapps.MainActivity;
import com.hanif.adminapps.R;
import com.hanif.adminapps.adapter.AuctionAdapter;
import com.hanif.adminapps.adapter.StuffAdapter;
import com.hanif.adminapps.api.Api;
import com.hanif.adminapps.model.Result;
import com.hanif.adminapps.model.Stuff;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailAuctionFragment extends Fragment implements View.OnClickListener{

//    private ProgressDialog progress;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swLayout;
    private TextView tvStuffName, tvStuffDescription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_auction, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progressbar);

        tvStuffName = view.findViewById(R.id.tv_stuff_name);
        tvStuffDescription = view.findViewById(R.id.tv_stuff_description);

//        Button btnOpen = view.findViewById(R.id.btn_open);
//        btnOpen.setOnClickListener(this);
//        Button btnClose = view.findViewById(R.id.btn_close);
//        btnClose.setOnClickListener(this);

//        Initialised SwipeRefreshLayout
        swLayout = view.findViewById(R.id.swlayout);

//        Hide a bottom navigation
        BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view);
        navBar.setVisibility(View.GONE);

//        get stuff price and put into textView
//        TextView tvStuffPrice = view.findViewById(R.id.tv_stuff_price);
//        String stuffPrice = getArguments().getString(StuffAdapter.StuffViewHolder.EXTRA_PRICE);
//        tvStuffPrice.setText(stuffPrice);

//        get status
//        String status = getArguments().getString(AuctionAdapter.AuctionViewHolder.EXTRA_STATUS);

//        if (status.equals("ditutup")){
//            btnOpen.setVisibility(View.VISIBLE);
//        } else {
//            btnClose.setVisibility(View.VISIBLE);
//        }

        loadStuff();

        swLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swLayout.setRefreshing(false);
                loadStuff();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Detail Auction Stuff ");
    }

    private void loadStuff(){
        final String stuffId = getArguments().getString(AuctionAdapter.AuctionViewHolder.EXTRA_STUFF_ID);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.253:8000/api/v1/barang/" + stuffId + "/").addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<Stuff> call = api.getStuff();
        call.enqueue(new Callback<Stuff>() {
            @Override
            public void onResponse(Call<Stuff> call, Response<Stuff> response) {
                progressBar.setVisibility(View.GONE);

                String stuffName = response.body().getNama_barang();
                tvStuffName.setText(stuffName);

                String stuffDescription = response.body().getDeskripsi_barang();
                tvStuffDescription.setText(stuffDescription);
            }

            @Override
            public void onFailure(Call<Stuff> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
//        if (view.getId() == R.id.btn_open) {
//
//            String dialogTitle = "Open?";
//            String dialogMessage = "Are you sure want to open this item?";
//
//            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
//            alertDialogBuilder.setTitle(dialogTitle);
//            alertDialogBuilder
//                    .setMessage(dialogMessage)
//                    .setCancelable(false)
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//
//                            progress = new ProgressDialog(getActivity());
//                            progress.setCancelable(false);
//                            progress.setMessage("Loading...");
//                            progress.show();
//
//                            final String stuffId = getArguments().getString(AuctionAdapter.AuctionViewHolder.EXTRA_ID);
//                            Retrofit retrofit = new Retrofit.Builder()
//                                    .baseUrl("http://192.168.43.253:8000/api/v1/lelang/" + stuffId + "/").addConverterFactory(GsonConverterFactory.create())
//                                    .build();
//                            Api api = retrofit.create(Api.class);
//                            Call<Result> call = api.openAuction();
//                            call.enqueue(new Callback<Result>() {
//                                @Override
//                                public void onResponse(Call<Result> call, Response<Result> response) {
//                                    progress.dismiss();
//
//                                    String message = response.body().getMessage();
//                                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
//
//                                }
//
//                                @Override
//                                public void onFailure(Call<Result> call, Throwable t) {
//
//                                }
//                            });
//
//
//                        }
//                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dial, int i) {
//                    dial.cancel();
//                }
//            });
//            AlertDialog alertDialog = alertDialogBuilder.create();
//            alertDialog.show();
//        } else {
//            String dialogTitle = "Close?";
//            String dialogMessage = "Are you sure want to close this item?";
//
//            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
//            alertDialogBuilder.setTitle(dialogTitle);
//            alertDialogBuilder
//                    .setMessage(dialogMessage)
//                    .setCancelable(false)
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//
//                            progress = new ProgressDialog(getActivity());
//                            progress.setCancelable(false);
//                            progress.setMessage("Loading...");
//                            progress.show();
//
//                            final String auctionId = getArguments().getString(AuctionAdapter.AuctionViewHolder.EXTRA_ID);
//                            Retrofit retrofit = new Retrofit.Builder()
//                                    .baseUrl("http://192.168.43.253:8000/api/v1/lelang/" + auctionId + "/").addConverterFactory(GsonConverterFactory.create())
//                                    .build();
//                            Api api = retrofit.create(Api.class);
//                            Call<Result> call = api.closeAuction();
//                            call.enqueue(new Callback<Result>() {
//                                @Override
//                                public void onResponse(Call<Result> call, Response<Result> response) {
//                                    progress.dismiss();
//
//                                    String message = response.body().getMessage();
//                                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
//
//                                }
//
//                                @Override
//                                public void onFailure(Call<Result> call, Throwable t) {
//
//                                }
//                            });
//
//
//                        }
//                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dial, int i) {
//                    dial.cancel();
//                }
//            });
//            AlertDialog alertDialog = alertDialogBuilder.create();
//            alertDialog.show();
//        }
     }
}
