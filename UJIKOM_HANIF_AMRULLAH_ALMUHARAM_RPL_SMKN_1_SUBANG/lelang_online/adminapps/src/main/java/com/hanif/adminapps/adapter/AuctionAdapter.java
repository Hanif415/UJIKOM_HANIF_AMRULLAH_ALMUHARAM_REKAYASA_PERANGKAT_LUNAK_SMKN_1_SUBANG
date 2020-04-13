package com.hanif.adminapps.adapter;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.hanif.adminapps.R;
import com.hanif.adminapps.api.Api;
import com.hanif.adminapps.model.Auction;
import com.hanif.adminapps.model.Result;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuctionAdapter extends RecyclerView.Adapter<AuctionAdapter.AuctionViewHolder> {

    ProgressDialog progress;

//    private Context context;
//    private List<Auction> auctions;
//
//    private ProgressDialog progress;
//
//    public AuctionAdapter(Context context, List<Auction> auctions) {
//        this.context = context;
//        this.auctions = auctions;
//    }

    private ArrayList<Auction> mData = new ArrayList<>();

    public void setDataAuction(ArrayList<Auction> auctions) {
        mData.clear();
        mData.addAll(auctions);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AuctionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_auction_item, viewGroup, false);
        return new AuctionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuctionViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class AuctionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public static final String EXTRA_ID = "extra_id";
        public static final String EXTRA_STATUS = "extra_status";
        public static final String EXTRA_STUFF_ID = "extra_stuff_id";

        ImageView stuffImage;
        TextView tvStuffName, tvStuffPrice, tvDate;
        Button btnOpen, btnClose;

        AuctionViewHolder(@NonNull View itemView) {
            super(itemView);
            stuffImage = itemView.findViewById(R.id.img);
            tvStuffName = itemView.findViewById(R.id.tv_auction_name);
            tvStuffPrice = itemView.findViewById(R.id.tv_stuff_price);
            tvDate = itemView.findViewById(R.id.tv_date);

            btnOpen = itemView.findViewById(R.id.btn_open);
            btnClose = itemView.findViewById(R.id.btn_close);

            btnClose.setOnClickListener(this);
            btnOpen.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        void bind(Auction auction){

            int position = getAdapterPosition();
            auction = mData.get(position);

            tvStuffName.setText(auction.getNamaBarang());
            tvStuffPrice.setText(auction.getHargaAwal());
            tvDate.setText(auction.getTglLelalng());

            String status = auction.getStatus();
//             Condition for status open or close button button
            if (status.equals("ditutup")){
                btnOpen.setVisibility(View.VISIBLE);
            } else {
                btnClose.setVisibility(View.VISIBLE);
            }
//            btn Open clicked
            btnOpen.setOnClickListener(new View.OnClickListener() {
                int position = getAdapterPosition();
                final Auction auction = mData.get(position);

                @Override
                public void onClick(final View view) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
                    alertDialogBuilder.setTitle("Open?");
                    alertDialogBuilder
                            .setMessage("Are you sure want to open this item?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    progress = new ProgressDialog(view.getContext());
                                    progress.setCancelable(false);
                                    progress.setMessage("Loading...");
                                    progress.show();

                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl("http://192.168.43.253:8000/api/v1/lelang/" + auction.getIdLelang() + "/").addConverterFactory(GsonConverterFactory.create())
                                            .build();
                                    Api api = retrofit.create(Api.class);
                                    Call<Result> call = api.openAuction();
                                    call.enqueue(new Callback<Result>() {
                                        @Override
                                        public void onResponse(Call<Result> call, Response<Result> response) {
                                            progress.dismiss();

                                            String message = response.body().getMessage();
                                            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();

                                        }

                                        @Override
                                        public void onFailure(Call<Result> call, Throwable t) {

                                        }
                                    });
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dial, int i) {
                            dial.cancel();
                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            });
//            btn Close clicked
            btnClose.setOnClickListener(new View.OnClickListener() {
                int position = getAdapterPosition();
                final Auction auction = mData.get(position);

                @Override
                public void onClick(final View view) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
                    alertDialogBuilder.setTitle("Close?");
                    alertDialogBuilder
                            .setMessage("Are you sure want to close this item?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    progress = new ProgressDialog(view.getContext());
                                    progress.setCancelable(false);
                                    progress.setMessage("Loading...");
                                    progress.show();

                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl("http://192.168.43.253:8000/api/v1/lelang/" + auction.getIdLelang() + "/").addConverterFactory(GsonConverterFactory.create())
                                            .build();
                                    Api api = retrofit.create(Api.class);
                                    Call<Result> call = api.closeAuction();
                                    call.enqueue(new Callback<Result>() {
                                        @Override
                                        public void onResponse(Call<Result> call, Response<Result> response) {
                                            progress.dismiss();

                                            String message = response.body().getMessage();
                                            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onFailure(Call<Result> call, Throwable t) {

                                        }
                                    });


                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dial, int i) {
                            dial.cancel();
                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            });
        }

        @Override
        public void onClick(final View view) {
            int position = getAdapterPosition();
            Auction auction = mData.get(position);

            final Bundle mBundle = new Bundle();
            mBundle.putString(EXTRA_ID, auction.getIdLelang());
            mBundle.putString(EXTRA_STATUS, auction.getStatus());
            mBundle.putString(EXTRA_STUFF_ID, auction.getIdBarang());

            Navigation.findNavController(view).navigate(R.id.action_navigation_auction_to_detailAuctionFragment, mBundle);
        }
    }
}
