package com.zucc.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogRegActivity extends AppCompatActivity {

    Button loginBtn, regisBtn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_reg);

        loginBtn = findViewById(R.id.btn_login);
        regisBtn = findViewById(R.id.btn_register);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(LogRegActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        regisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(LogRegActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
