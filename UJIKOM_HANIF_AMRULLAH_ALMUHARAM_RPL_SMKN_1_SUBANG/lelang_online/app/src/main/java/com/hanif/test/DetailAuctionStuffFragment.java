package com.hanif.test;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hanif.test.adapter.AuctionAdapter;
import com.hanif.test.api.Api;
import com.hanif.test.model.Auction;
import com.hanif.test.model.Stuff;
import com.hanif.test.model.User;
import com.hanif.test.ui.home.BIDDialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailAuctionStuffFragment extends Fragment implements View.OnClickListener {

    private ProgressBar progressBar;
    private SwipeRefreshLayout swLayout;
    private TextView tvStuffName, tvStuffDescription;
    private UserPreference mUserPreference;
    private User user;

    public static final int NOTIFICATION_ID = 1;
    public static String CHANNEL_ID = "channel_01";
    public static CharSequence CHANNEL_NAME = "my channel";

    public static String auctionId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_auction_stuff, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        auctionId = getArguments().getString(AuctionAdapter.StuffViewHolder.EXTRA_ID);

        mUserPreference = new UserPreference(getActivity());

        progressBar = view.findViewById(R.id.progressbar);

        tvStuffName = view.findViewById(R.id.tv_stuff_name);
        tvStuffDescription = view.findViewById(R.id.tv_stuff_description);

//        Initialised SwipeRefreshLayout
        swLayout = view.findViewById(R.id.swlayout);

//        Hide a bottom navigation
        BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view);
        navBar.setVisibility(View.GONE);

//        get stuff price and put into textView
        TextView tvHighestPrice = view.findViewById(R.id.tv_highest_price);

        String highest_price = getArguments().getString(AuctionAdapter.StuffViewHolder.EXTRA_PRICE);
        tvHighestPrice.setText(highest_price);

        ImageButton btnBid = view.findViewById(R.id.img_btn_bid);
        btnBid.setOnClickListener(this);

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

    private void loadStuff() {
        final String stuffId = getArguments().getString(AuctionAdapter.StuffViewHolder.EXTRA_STUFF_ID);
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
        user = mUserPreference.getUser();
        if (!user.getIdUser().isEmpty()) {
            BIDDialogFragment mOptionDialogFragment = new BIDDialogFragment();
            FragmentManager mFragmentManager = getChildFragmentManager();
            mOptionDialogFragment.show(mFragmentManager, BIDDialogFragment.class.getSimpleName());
        } else {
            Navigation.findNavController(view).navigate(R.id.action_detailAuctionStuffFragment_to_loginFragment);
        }
    }

    public BIDDialogFragment.OnOptionDialogListener optionDialogListener = new BIDDialogFragment.OnOptionDialogListener() {
        @Override
        public void onOptionChosen(String text) {
            Toast.makeText(getActivity(), BIDDialogFragment.message, Toast.LENGTH_SHORT).show();
            NotificationManager mNotificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getActivity(), CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_notifications_black_24dp))
                    .setContentTitle("BID")
                    .setContentText("anda telah melelang sebuah " + getArguments().getString(AuctionAdapter.StuffViewHolder.EXTRA_STUFF))
                    .setAutoCancel(true);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
                channel.setDescription(CHANNEL_NAME.toString());
                mBuilder.setChannelId(CHANNEL_ID);
                if (mNotificationManager != null) {
                    mNotificationManager.createNotificationChannel(channel);
                }
            }

            Notification notification = mBuilder.build();

            if (mNotificationManager != null) {
                mNotificationManager.notify(NOTIFICATION_ID, notification);
            }
        }
    };
}
