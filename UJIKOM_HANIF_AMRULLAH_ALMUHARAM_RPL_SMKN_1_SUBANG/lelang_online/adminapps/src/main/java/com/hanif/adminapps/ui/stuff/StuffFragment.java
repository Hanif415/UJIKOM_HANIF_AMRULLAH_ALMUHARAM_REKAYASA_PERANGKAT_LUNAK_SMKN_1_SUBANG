package com.hanif.adminapps.ui.stuff;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hanif.adminapps.R;
import com.hanif.adminapps.UserPreference;
import com.hanif.adminapps.adapter.StuffAdapter;
import com.hanif.adminapps.api.Api;
import com.hanif.adminapps.model.Result;
import com.hanif.adminapps.model.Stuff;
import com.hanif.adminapps.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class StuffFragment extends Fragment {

    private static final String URL = "http://192.168.43.253:8000/api/v1/";
    private List<Stuff> stuffs = new ArrayList<>();
    private RecyclerView recyclerView;
    private StuffAdapter stuffAdapter;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swLayout;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_stuff, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view);
        navBar.setVisibility(View.VISIBLE);

        stuffAdapter = new StuffAdapter(this.getActivity(), stuffs);

        progressBar = view.findViewById(R.id.progressbar);

        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(stuffAdapter);

        loadStuff();

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_navigation_stuff_to_registerFragment);
            }
        });

        swLayout = view.findViewById(R.id.swlayout);
        swLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SharedPreferences settings = getActivity().getSharedPreferences(UserPreference.PREFS_NAME, Context.MODE_PRIVATE);
                settings.edit().clear().commit();
                swLayout.setRefreshing(false);
            }
        });
    }

    private void loadStuff() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<Result> call = api.viewStuff();
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
//                String value = response.body().getValue();
                progressBar.setVisibility(View.GONE);

                stuffs = response.body().getResult();
                stuffAdapter = new StuffAdapter(getActivity(), stuffs);
                recyclerView.setAdapter(stuffAdapter);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}