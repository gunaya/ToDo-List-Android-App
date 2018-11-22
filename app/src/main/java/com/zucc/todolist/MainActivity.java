package com.zucc.todolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView viewName, viewEmail, viewPhone;
    Button logutBtn;
    Button fragmentBtn;
    SharePref sharePref;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewName = findViewById(R.id.name);
        viewEmail = findViewById(R.id.email);
        viewPhone = findViewById(R.id.phone);
        logutBtn = findViewById(R.id.btn_logut);
        fragmentBtn = findViewById(R.id.btn_fragment);
        floatingActionButton = findViewById(R.id.btn_tambah);

        sharePref = new SharePref(this);
        String userName = sharePref.getDataString(SharePref.KEY_NAME);
        String userEmail = sharePref.getDataString(SharePref.KEY_EMAIL);
        String userPhone = sharePref.getDataString(SharePref.KEY_PHONE);

        viewName.setText(userName);
        viewEmail.setText(userEmail);
        viewPhone.setText(userPhone);

        logutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharePref.setDataInt(SharePref.KEY_VALUE,0);
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        fragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharePref.setDataInt(SharePref.KEY_VALUE,0);
                Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
                startActivity(intent);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TambahActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure to exit?")
                .setNegativeButton(R.string.no, null)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.super.onBackPressed();
                        finishAffinity();
                        System.exit(0);
                    }
                }).create().show();
    }
}
