package com.hanif.adminapps.ui.stuff.crud;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hanif.adminapps.MainActivity;
import com.hanif.adminapps.R;
import com.hanif.adminapps.api.Api;
import com.hanif.adminapps.model.Result;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AddStuffFragment extends Fragment implements View.OnClickListener{

    private static final String URL = "http://192.168.43.253:8000/api/v1/";
    private ProgressDialog progress;

    private EditText edtStuffName;
    private EditText edtDate;
    private EditText edtStuffPrice;
    private EditText edtStuffDescription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_stuff, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        Hide a bottom navigation
        BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view);
        navBar.setVisibility(View.GONE);

        edtStuffName = view.findViewById(R.id.edt_stuff_name);
        edtDate = view.findViewById(R.id.edt_date);
        edtStuffPrice = view.findViewById(R.id.edt_stuff_price);
        edtStuffDescription = view.findViewById(R.id.edt_stuff_description);

        Button btn_add_stuff = view.findViewById(R.id.btn_add_stuff);
        btn_add_stuff.setOnClickListener(this);

        TextWatcher tw = new TextWatcher() {
            private String current = "";
            private String yyyymmdd = "yyyymmdd";
            private Calendar cal = Calendar.getInstance();

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]|\\.", "");
                    String cleanC = current.replaceAll("[^\\d.]|\\.", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 4; i <= cl && i < 8; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 9){
                        clean = clean + yyyymmdd.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int year  = Integer.parseInt(clean.substring(0,4));
                        int mon  = Integer.parseInt(clean.substring(4,6));
                        int day = Integer.parseInt(clean.substring(6,8));

                        mon = mon < 1 ? 1 : mon > 12 ? 12 : mon;
                        cal.set(Calendar.MONTH, mon-1);
                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s-%s-%s", clean.substring(0,4),
                            clean.substring(4,6),
                            clean.substring(6,8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    edtDate.setText(current);
                    edtDate.setSelection(sel < current.length() ? sel : current.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        edtDate.addTextChangedListener(tw);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Add New Stuff");
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_add_stuff){

            progress = new ProgressDialog(getActivity());
            progress.setCancelable(false);
            progress.setMessage("Loading...");
            progress.show();

            String stuffName = edtStuffName.getText().toString();
            String date = edtDate.getText().toString();
            String stuffPrice = edtStuffPrice.getText().toString();
            String stuffDescription = edtStuffDescription.getText().toString();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
                    .build();
            Api api = retrofit.create(Api.class);
            Call<Result> call = api.storeStuff(stuffName, date, stuffPrice, stuffDescription);
            call.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    progress.dismiss();

                    String message = response.body().getMessage();
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

                    edtStuffName.setText(" ");
                    edtDate.setText(" ");
                    edtStuffPrice.setText(" ");
                    edtStuffDescription.setText(" ");
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                }
            });
        }
    }
}
