package com.hanif.adminapps.ui.auction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hanif.adminapps.R;
import com.hanif.adminapps.adapter.AuctionAdapter;
import com.hanif.adminapps.model.Auction;

import java.util.ArrayList;

public class AuctionFragment extends Fragment {

    private AuctionAdapter auctionAdapter;
    private AuctionViewModel auctionViewModel;
    private ProgressBar progressbar;
    private SwipeRefreshLayout swLayout;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_auction, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view);
        navBar.setVisibility(View.VISIBLE);

        auctionAdapter = new AuctionAdapter();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(auctionAdapter);

        auctionViewModel = ViewModelProviders.of(this).get(AuctionViewModel.class);
        progressbar = view.findViewById(R.id.progressbar);
        auctionViewModel.showLoading(true, progressbar);

        auctionViewModel.getAuction().observe(getViewLifecycleOwner(), getAuction);
        auctionViewModel.setAuction("EXTRA_MOVIE");

        swLayout = view.findViewById(R.id.swlayout);
        swLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swLayout.setRefreshing(false);
                auctionViewModel.showLoading(true, progressbar);

                auctionViewModel.getAuction().observe(getViewLifecycleOwner(), getAuction);
                auctionViewModel.setAuction("EXTRA_MOVIE");
            }
        });
    }

    private Observer<ArrayList<Auction>> getAuction = new Observer<ArrayList<Auction>>() {
        @Override
        public void onChanged(ArrayList<Auction> auctions) {
            if (auctions != null){
                auctionAdapter.setDataAuction(auctions);
            }
            auctionViewModel.showLoading(false, progressbar);
        }
    };


}