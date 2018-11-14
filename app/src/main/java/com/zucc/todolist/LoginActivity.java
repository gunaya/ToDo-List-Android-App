package com.zucc.todolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button submitBtn;
    SharePref sharePref;
    TextView tx_signup;
    BaseApiService mApiService;
    ProgressBar progressBar;
    LinearLayout loginLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        submitBtn = findViewById(R.id.btn_submit);
        tx_signup = findViewById(R.id.tv_sign_up);
        mApiService = ApiUtils.getApiService();
        sharePref = new SharePref(this);

        loginLayout = findViewById(R.id.login_layout);
        progressBar = findViewById(R.id.progress_bar);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });

        tx_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setGone() {
        progressBar.setVisibility(View.VISIBLE);
        loginLayout.setVisibility(View.GONE);
    }

    public void setVisible() {
        progressBar.setVisibility(View.GONE);
        loginLayout.setVisibility(View.VISIBLE);
    }

    public void Login() {
        final String inEmail = etEmail.getText().toString();
        String inPassword = etPassword.getText().toString();

        if (inEmail.equals("")){
            etEmail.setError(getText(R.string.error_invalid_email));
        } else if (inPassword.equals("")){
            etPassword.setError(getText(R.string.error_incorrect_password));
        } else {
            setGone();
            mApiService.loginRequest(etEmail.getText().toString(), etPassword.getText().toString()).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        String responseData = response.body().string();
                        JSONObject jsonResults = new JSONObject(responseData);
                        if (jsonResults.getString("message").equals("success")){
                            setVisible();
                            String name = jsonResults.getString("name");
                            Integer id = jsonResults.getInt("id");
                            Toast.makeText(LoginActivity.this,"Login Success",Toast.LENGTH_SHORT).show();

                            sharePref.setDataString(SharePref.KEY_EMAIL, inEmail);
                            sharePref.setDataString(SharePref.KEY_NAME, name);
                            sharePref.setDataInt(SharePref.KEY_VALUE,1);
                            sharePref.setDataInt(SharePref.KEY_ID, id);

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            setVisible();
                            Toast.makeText(LoginActivity.this, "Email or Password Wrong", Toast.LENGTH_SHORT).show();
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
                    Log.d("Internet", "not connect");
                    Toast.makeText(LoginActivity.this, "Login Failed, no Internet Connection Detected", Toast.LENGTH_SHORT).show();
                }
            });
        }
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
                        LoginActivity.super.onBackPressed();
                        finishAffinity();
                        System.exit(0);
                    }
                }).create().show();
    }
}
