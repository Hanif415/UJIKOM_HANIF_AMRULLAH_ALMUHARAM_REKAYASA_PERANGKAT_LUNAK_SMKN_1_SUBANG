package com.hanif.adminapps.ui.login;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hanif.adminapps.MainActivity;
import com.hanif.adminapps.R;
import com.hanif.adminapps.UserPreference;
import com.hanif.adminapps.api.Api;
import com.hanif.adminapps.model.Result;
import com.hanif.adminapps.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String URL = "http://192.168.43.253:8000/api/v1/";

    EditText edtUsername;
    EditText edtPassword;
    Button btn_login;

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_signin);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    @Override
    public void onClick(final View view) {

        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Loading...");
        progress.show();

        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();

        if (view.getId() == R.id.btn_signin) {
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
                    String value = response.body().getValue();
                    String idLevel = response.body().getIdLevel();
                    String idUser = response.body().getIdUser();

                    if (value.equals("0")) {
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    } else if (idLevel.equals("3")){
                        Toast.makeText(LoginActivity.this, "You are not admin", Toast.LENGTH_SHORT).show();
                    } else {
                        saveUser(idUser, idLevel);
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                }
            });
        }
    }

    private void saveUser(String idUser, String idLevel) {
        User user = new User();
        user.setIdUser(idUser);
        user.setIdLevel(idLevel);

        UserPreference userPreference = new UserPreference(this);
        userPreference.setUser(user);
    }
}
