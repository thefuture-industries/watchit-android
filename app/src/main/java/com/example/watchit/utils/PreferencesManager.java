package com.example.watchit.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {
    private static final String PREFERENCE_NAME = "watchit_app_pref";

    private final SharedPreferences preferences;

    public PreferencesManager(Context context) {
        preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public void saveString(String key, String data) {
        preferences.edit().putString(key, data).apply();
    }

    public String getString(String key) {
        return preferences.getString(key, null);
    }

    public void clear(String key) {
        preferences.edit().remove(key).apply();
    }
}
