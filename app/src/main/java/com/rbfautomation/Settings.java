package com.rbfautomation;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

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

    public void setCardOrder(ArrayList<Integer> cardOrder) {
        mPrefs.edit().putString("cardOrder", (new JSONArray(cardOrder)).toString()).commit();
    }

    public ArrayList<Integer> getCardOrder() {

        ArrayList<Integer> cardOrder = new ArrayList<>();
        try {
            JSONArray json = new JSONArray(mPrefs.getString("cardOrder", "[]"));
            for (int i = 0; i < json.length(); i++) {
                cardOrder.add(json.getInt(i));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cardOrder;

    }

}
