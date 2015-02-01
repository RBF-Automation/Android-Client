package com.rbfautomation;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by brian on 2/1/15.
 */
public class Settings {

    SharedPreferences mPrefs;

    public Settings(Context context) {
        mPrefs = context.getSharedPreferences("Settings", Context.MODE_PRIVATE);
    }

    public void setToken(String token) {
        mPrefs.edit().putString("token", token).commit();
    }

    public String getToken() {
        String token = mPrefs.getString("token", null);
        return token;
    }

}
