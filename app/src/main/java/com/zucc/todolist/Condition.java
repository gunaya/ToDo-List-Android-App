package com.zucc.todolist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.zucc.todolist.admin.FragmentActivity;

/**
 * Created by Gunaya on 10/31/2018.
 */

public class Condition extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharePref sharePref = new SharePref(this);
        int val = sharePref.getDataInt(SharePref.KEY_VALUE,0);
        Log.d("Values",""+val);
        if (val == 0) {
            Intent intent = new Intent(Condition.this, LoginActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(Condition.this, FragmentActivity.class);
            startActivity(intent);
        }
    }
}
