package com.zucc.todolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zucc.todolist.apihelper.ApiUtils;
import com.zucc.todolist.apihelper.BaseApiService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText etName, etEmail, etPhone, etPassword;
    TextView tvLogin;
    Button submitBtn;
    SharePref sharePref;
    BaseApiService mApiService;
    ProgressBar progressBar;
    LinearLayout regisLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sharePref = new SharePref(this);
        mApiService = ApiUtils.getApiService();

        etName = findViewById(R.id.et_username);
        etEmail = findViewById(R.id.et_email);
        etPhone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_password);
        submitBtn = findViewById(R.id.btn_submit);
        tvLogin = findViewById(R.id.tv_sign_in);

        progressBar = findViewById(R.id.progress_register);
        regisLayout = findViewById(R.id.regis_layout);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setGone() {
        progressBar.setVisibility(View.VISIBLE);
        regisLayout.setVisibility(View.INVISIBLE);
    }

    public void setVisible() {
        progressBar.setVisibility(View.INVISIBLE);
        regisLayout.setVisibility(View.VISIBLE);
    }

    public void Register(){
        String inName = etName.getText().toString();
        String inEmail = etEmail.getText().toString();
        String inPhone = etPhone.getText().toString();
        String inPassword = etPassword.getText().toString();

        if (inName.equals("")){
            etName.setError(getText(R.string.error_field_required));
        } else if (inEmail.equals("")){
            etEmail.setError(getText(R.string.error_field_required));
        } else if (inPhone.equals("")){
            etPhone.setError(getText(R.string.error_field_required));
        } else if (inPassword.equals("")){
            etPassword.setError(getText(R.string.error_field_required));
        } else {
            setGone();
            mApiService.registerRequest(inName, inEmail, inPhone, inPassword).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        String responseData = response.body().string();
                        JSONObject jsonResults = new JSONObject(responseData);
                        if (jsonResults.getString("message").equals("success")) {
                            setVisible();
                            Toast.makeText(RegisterActivity.this,"Register Success",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            setVisible();
                            Toast.makeText(RegisterActivity.this, "Input Correct Data", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    setVisible();
                    Toast.makeText(RegisterActivity.this, "Register Failed, no Internet Connection Detected", Toast.LENGTH_SHORT).show();
                }
            });
        }

//        if (!userName.equals("") && !userPassword.equals("")) {
//            sharePref.setDataString(SharePref.KEY_NAME, ""+userName);
//            sharePref.setDataString(SharePref.KEY_EMAIL, ""+userEmail);
//            sharePref.setDataString(SharePref.KEY_PHONE, ""+userPhone);
//            sharePref.setDataString(SharePref.KEY_PASSWORD,""+userPassword);
//
//            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//            startActivity(intent);
//        } else {
//            Toast.makeText(RegisterActivity.this, "Input Correct Data", Toast.LENGTH_SHORT).show();
//        }
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
                        RegisterActivity.super.onBackPressed();
                        finishAffinity();
                        System.exit(0);
                    }
                }).create().show();
    }
}
