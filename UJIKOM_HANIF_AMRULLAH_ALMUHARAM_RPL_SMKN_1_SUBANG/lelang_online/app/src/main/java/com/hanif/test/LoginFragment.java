package com.hanif.test;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hanif.test.api.Api;
import com.hanif.test.model.Result;
import com.hanif.test.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginFragment extends Fragment implements View.OnClickListener {
    public static final String URL = "http://192.168.43.253:8000/api/v1/";

    private EditText edtUsername;
    private EditText edtPassword;
    private TextView tvRegister;

    private ProgressDialog progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtUsername = view.findViewById(R.id.edt_username);
        edtPassword = view.findViewById(R.id.edt_password);
        tvRegister = view.findViewById(R.id.tv_register);
        tvRegister.setOnClickListener(this);
        Button btn_login = view.findViewById(R.id.btn_signin);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_signin) {
            progress = new ProgressDialog(getContext());
            progress.setCancelable(false);
            progress.setMessage("Loading...");
            progress.show();

            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
                    .build();

            Api api = retrofit.create(Api.class);
            Call<Result> call = api.signin(username, password);
            call.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    progress.dismiss();

                    String message = response.body().getMessage();
//                    String value = response.body().getValue();
                    String idLevel = response.body().getIdLevel();
                    String idUser = response.body().getIdUser();

                    saveUser(idUser, idLevel);
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {

                }
            });
        } else {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment);
        }
    }

    private void saveUser(String idUser, String idLevel) {
        User user = new User();
        user.setIdUser(idUser);
        user.setIdLevel(idLevel);

        UserPreference userPreference = new UserPreference(getActivity());
        userPreference.setUser(user);
    }
}
