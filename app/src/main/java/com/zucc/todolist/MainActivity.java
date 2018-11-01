package com.zucc.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView viewName, viewEmail, viewPhone;
    Button logutBtn;
    SharePref sharePref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewName = findViewById(R.id.name);
        viewEmail = findViewById(R.id.email);
        viewPhone = findViewById(R.id.phone);
        logutBtn = findViewById(R.id.btn_logut);

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

    }
}
