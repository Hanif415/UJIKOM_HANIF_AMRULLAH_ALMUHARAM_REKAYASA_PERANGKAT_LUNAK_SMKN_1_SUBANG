package com.hanif.adminapps;

import android.content.Context;
import android.content.SharedPreferences;

import com.hanif.adminapps.model.User;

public class UserPreference {
    public static final String PREFS_NAME = "user_pref";

    private static final String ID = "id";
    private static final String LEVEL = "level";

    private final SharedPreferences preferences;

    public UserPreference(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void setUser(User value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(ID, value.idUser);
        editor.putString(LEVEL, value.idLevel);
        editor.apply();
    }

    public User getUser() {
        User model = new User();
        model.setIdUser(preferences.getString(ID, ""));
        model.setIdLevel(preferences.getString(LEVEL, ""));

        return model;
    }
}
