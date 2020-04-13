package com.hanif.test.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.hanif.test.R;
import com.hanif.test.model.Auction;

import java.util.ArrayList;
import java.util.List;

public class AuctionAdapter extends RecyclerView.Adapter<AuctionAdapter.StuffViewHolder> {

    private Context context;
    private List<Auction> auctions;
    private ArrayList<Auction> mData = new ArrayList<>();

    public void setDataAuction(ArrayList<Auction> auctions) {
        mData.clear();
        mData.addAll(auctions);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StuffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_auction_item, parent, false);
        StuffViewHolder holder = new StuffViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StuffViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class StuffViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView stuffImage;
        TextView tvStuffName;
        TextView tvStuffPrice;

        public static final String EXTRA_ID = "extra_id";
        public static final String EXTRA_STATUS = "extra_status";
        public static final String EXTRA_STUFF_ID = "extra_stuff_id";

        StuffViewHolder(@NonNull View itemView) {
            super(itemView);
            stuffImage = itemView.findViewById(R.id.img);
            tvStuffName = itemView.findViewById(R.id.tv_auction_name);
            tvStuffPrice = itemView.findViewById(R.id.tv_auction_price);

            itemView.setOnClickListener(this);
        }

        void bind(Auction auction){
            int position = getAdapterPosition();
            auction = mData.get(position);

            tvStuffName.setText(auction.getNamaBarang());
            tvStuffPrice.setText(auction.getHargaAwal());
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Auction auction = mData.get(position);

            final Bundle mBundle = new Bundle();
            mBundle.putString(EXTRA_ID, auction.getIdLelang());
            mBundle.putString(EXTRA_STATUS, auction.getStatus());
            mBundle.putString(EXTRA_STUFF_ID, auction.getIdBarang());

            Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_detailAuctionStuffFragment, mBundle);
        }
    }
}
