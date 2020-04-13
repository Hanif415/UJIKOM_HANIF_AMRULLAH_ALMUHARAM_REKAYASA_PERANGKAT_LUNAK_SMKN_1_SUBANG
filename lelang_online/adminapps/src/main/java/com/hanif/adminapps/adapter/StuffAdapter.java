package com.hanif.adminapps.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.hanif.adminapps.MainActivity;
import com.hanif.adminapps.R;
import com.hanif.adminapps.api.Api;
import com.hanif.adminapps.api.StuffAPI;
import com.hanif.adminapps.model.Result;
import com.hanif.adminapps.model.Stuff;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StuffAdapter extends RecyclerView.Adapter<StuffAdapter.StuffViewHolder> {

    private Context context;
    private List<Stuff> stuffs;

    private ProgressDialog progress;

    public StuffAdapter(Context context, List<Stuff> stuffs) {
        this.context = context;
        this.stuffs = stuffs;
    }

    @NonNull
    @Override
    public StuffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_stuff_item, parent, false);
        StuffViewHolder holder = new StuffViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StuffViewHolder holder, final int position) {
        final Stuff stuff = stuffs.get(position);
        holder.tvStuffName.setText(stuff.getNama_barang());
        holder.tvStuffPrice.setText(stuff.getHarga_awal());
        holder.tvDate.setText(stuff.getTgl());
    }

    @Override
    public int getItemCount() {
        return stuffs.size();
    }

    public class StuffViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView stuffImage;
        TextView tvStuffName;
        TextView tvStuffPrice;
        TextView tvDate;
        Button btnEdit, btnDelete;

        public static final String EXTRA_ID = "extra_id";
        public static final String EXTRA_NAME = "extra_name";
        public static final String EXTRA_CATEGORY = "extra_category";
        public static final String EXTRA_DATE = "extra_date";
        public static final String EXTRA_PRICE = "extra_price";
        public static final String EXTRA_DESCRIPTION = "extra_detail";

        StuffViewHolder(@NonNull View itemView) {
            super(itemView);

            stuffImage = itemView.findViewById(R.id.img);
            tvStuffName = itemView.findViewById(R.id.tv_auction_name);
            tvStuffPrice = itemView.findViewById(R.id.tv_stuff_price);
            tvDate = itemView.findViewById(R.id.tv_date);
//            btnEdit = itemView.findViewById(R.id.btn_edit);
//            btnDelete = itemView.findViewById(R.id.btn_delete);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(final View view) {
            int position = getAdapterPosition();
            final Stuff stuff = stuffs.get(position);

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Choose an action");

//            Bundle
            final Bundle mBundle = new Bundle();
            mBundle.putString(EXTRA_ID, stuff.getIdBarang());
            mBundle.putString(EXTRA_NAME, stuff.getNama_barang());
            mBundle.putString(EXTRA_CATEGORY, stuff.getKategori_barang());
            mBundle.putString(EXTRA_DATE, stuff.getTgl());
            mBundle.putString(EXTRA_PRICE, stuff.getHarga_awal());
            mBundle.putString(EXTRA_DESCRIPTION, stuff.getDeskripsi_barang());

            String[] actions = {"View", "Edit", "Delete"};
            builder.setItems(actions, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {

                    String dialogTitle = "Delete?";
                    String dialogMessage = "Are you sure want to delete this item?";

                    switch (which) {
                        case 0:
                            Navigation.findNavController(view).navigate(R.id.action_navigation_stuff_to_detaiStuffFragment, mBundle);
                            break;
                        case 1:
                            Navigation.findNavController(view).navigate(R.id.action_navigation_stuff_to_editStuffFragment, mBundle);
                            break;
                        case 2:
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
                            alertDialogBuilder.setTitle(dialogTitle);
                            alertDialogBuilder
                                    .setMessage(dialogMessage)
                                    .setCancelable(false)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            progress = new ProgressDialog(view.getContext());
                                            progress.setCancelable(false);
                                            progress.setMessage("Loading...");
                                            progress.show();

                                            Retrofit retrofit = new Retrofit.Builder()
                                                    .baseUrl("http://192.168.43.253:8000/api/v1/barang/" + stuff.getIdBarang() + "/").addConverterFactory(GsonConverterFactory.create())
                                                    .build();
                                            Api api = retrofit.create(Api.class);
                                            Call<Result> call = api.deleteStuff();
                                            call.enqueue(new Callback<Result>() {
                                                @Override
                                                public  void onResponse(Call<Result> call, Response<Result> response) {
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
                            } );
                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                            break;
                    }
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
