package com.hanif.adminapps;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hanif.adminapps.model.User;
import com.hanif.adminapps.ui.login.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.concurrent.locks.LockSupport;

public class MainActivity extends AppCompatActivity {

    private UserPreference mUserPreference;
    private User user;
    private boolean isPreferenceEmpty = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_stuff, R.id.navigation_auction).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        mUserPreference = new UserPreference(this);
        showExistingPreference();
    }

    private void showExistingPreference() {
        user = mUserPreference.getUser();
        checkForm(user);
        populateView(user);
    }

    private void checkForm(User user) {
        if (!user.getIdUser().isEmpty()) {
            isPreferenceEmpty = false;
        } else {
            isPreferenceEmpty = true;
        }
    }

    private void populateView(User user) {
        if (isPreferenceEmpty){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
