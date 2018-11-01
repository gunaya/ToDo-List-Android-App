package com.zucc.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText etName, etEmail, etPhone, etPassword;
    Button submitBtn;
    SharePref sharePref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sharePref = new SharePref(this);

        etName = findViewById(R.id.et_username);
        etEmail = findViewById(R.id.et_email);
        etPhone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_password);
        submitBtn = findViewById(R.id.btn_submit);

        Register();
    }

    public void Register(){
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = etName.getText().toString();
                String userEmail = etEmail.getText().toString();
                String userPhone = etPhone.getText().toString();
                String userPassword = etPassword.getText().toString();

                if (!userName.equals("") && !userPassword.equals("")) {
                    sharePref.setDataString(SharePref.KEY_NAME, ""+userName);
                    sharePref.setDataString(SharePref.KEY_EMAIL, ""+userEmail);
                    sharePref.setDataString(SharePref.KEY_PHONE, ""+userPhone);
                    sharePref.setDataString(SharePref.KEY_PASSWORD,""+userPassword);

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity.this, "Input Correct Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
