package com.hanif.adminapps.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hanif.adminapps.R;
import com.hanif.adminapps.model.History;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Context context;
    private List<History> histories;

    public HistoryAdapter(Context context, List<History> histories) {
        this.context = context;
        this.histories = histories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final History history = histories.get(position);
        holder.tvStuffName.setText(history.getIdBarang());
        holder.tvBidPrice.setText(history.getPenawaranHarga());
        holder.tvDate.setText(history.getDate());
        holder.tvBidder.setText(history.getIdMasyarakat());
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView stuffImage;
        TextView tvStuffName;
        TextView tvBidPrice;
        TextView tvDate;
        TextView tvBidder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stuffImage = itemView.findViewById(R.id.img);
            tvStuffName = itemView.findViewById(R.id.tv_stuff_name);
            tvBidPrice = itemView.findViewById(R.id.tv_bid_price);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvBidder = itemView.findViewById(R.id.tv_user);
        }
    }
}
