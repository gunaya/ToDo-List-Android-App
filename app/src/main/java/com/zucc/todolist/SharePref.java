package com.zucc.todolist;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Gunaya on 10/31/2018.
 */

public class SharePref {

    public final static String SPName = "sharePref";
    public final static String KEY_NAME = "user_name";
    public final static String KEY_EMAIL = "user_mail";
    public final static String KEY_PHONE = "user_phone";
    public final static String KEY_PASSWORD = "user_password";
    public final static String KEY_VALUE = "0";
    private SharedPreferences sharedPreferences;

    public SharePref (Context context) {
        sharedPreferences = context.getSharedPreferences(SPName, Context.MODE_PRIVATE);
    }
    public void setDataString(String key, String val) {
        sharedPreferences.edit().putString(key,val).apply();
    }
    public String getDataString(String key) {
        return sharedPreferences.getString(key,"");
    }

    public void setDataInt(String key, int val) {
        sharedPreferences.edit().putInt(key, val).apply();
    }
    public int getDataInt(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }
}
