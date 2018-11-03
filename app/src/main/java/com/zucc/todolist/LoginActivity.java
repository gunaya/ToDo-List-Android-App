package com.zucc.todolist;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button submitBtn;
    SharePref sharePref;
    TextView tx_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        submitBtn = findViewById(R.id.btn_submit);
        tx_signup = findViewById(R.id.tv_sign_up);

//        registerBtn = findViewById(R.id.btn_register);

        sharePref = new SharePref(this);

        Login();
        Register();
    }

    private void Register() {
        tx_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
//        registerBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
//                startActivity(intent);
//            }
//        });
    }


    public void Login() {
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inEmail = etEmail.getText().toString();
                String inPassword = etPassword.getText().toString();
                String userEmail = sharePref.getDataString(SharePref.KEY_EMAIL);
                String userPassword = sharePref.getDataString(SharePref.KEY_PASSWORD);

                if (userEmail.equals(inEmail) && userPassword.equals(inPassword)) {
                    sharePref.setDataInt(SharePref.KEY_VALUE,1);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this, "Email or Password Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
