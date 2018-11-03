package com.zucc.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    Button submitBtn;
    SharePref sharePref;
    BaseApiService mApiService;

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

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
    }

    public void Register(){
        mApiService.registerRequest(etName.getText().toString(),etEmail.getText().toString(),etPhone.getText().toString(),etPassword.getText().toString()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String responseData = response.body().string();
                    JSONObject jsonResults = new JSONObject(responseData);
                    if (jsonResults.getString("message").equals("success")) {
                        Toast.makeText(RegisterActivity.this,"Register Success",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
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

            }
        });

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
}
