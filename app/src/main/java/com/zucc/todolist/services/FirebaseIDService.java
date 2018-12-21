package com.zucc.todolist.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.zucc.todolist.SharePref;

public class FirebaseIDService extends FirebaseInstanceIdService {
    SharePref sharePref;
    @Override
    public void onTokenRefresh(){
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        // check here.
        Log.d("REFRESH TOKEN", "" + refreshedToken);
    }

}
