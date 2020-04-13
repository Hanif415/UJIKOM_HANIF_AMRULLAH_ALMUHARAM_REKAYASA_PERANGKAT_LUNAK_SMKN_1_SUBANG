package com.hanif.adminapps.ui.login;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hanif.adminapps.R;
import com.hanif.adminapps.api.Api;
import com.hanif.adminapps.model.Result;
import com.hanif.adminapps.ui.login.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {

    private static final String URL = "http://192.168.43.253:8000/api/v1/";

    private EditText edtUsername, edtPassword, edtOfficerName;

    private ProgressDialog progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtUsername = view.findViewById(R.id.edt_username);
        edtPassword = view.findViewById(R.id.edt_password);
        edtOfficerName = view.findViewById(R.id.edt_officer_name);
        Button btnRegister = view.findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onClick(View view) {
        progress = new ProgressDialog(getActivity());
        progress.setCancelable(false);
        progress.setMessage("Loading...");
        progress.show();

        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        String officerName = edtOfficerName.getText().toString();
        String idLevel = "1";

        if (view.getId() == R.id.btn_register) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
                    .build();

            Api api = retrofit.create(Api.class);
            Call<Result> call = api.register(username, password, officerName, idLevel);
            call.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    progress.dismiss();
                    String message = response.body().getMessage();
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {

                }
            });
        }
    }
}
