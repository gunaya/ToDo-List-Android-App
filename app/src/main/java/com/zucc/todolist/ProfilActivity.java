package com.zucc.todolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
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

import com.zucc.todolist.admin.FragmentActivity;
import com.zucc.todolist.admin.TambahActivity;
import com.zucc.todolist.apihelper.ApiUtils;
import com.zucc.todolist.apihelper.BaseApiService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilActivity extends AppCompatActivity {
    EditText viewName, viewEmail, viewPhone;
    Button logutBtn, editBtn, saveBtn;
    SharePref sharePref;
    ProgressBar progressBar;
    LinearLayout profilLayout;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        mApiService = ApiUtils.getApiService();

        viewName = findViewById(R.id.name);
        viewEmail = findViewById(R.id.email);
        viewPhone = findViewById(R.id.phone);
        logutBtn = findViewById(R.id.btn_logut);
        editBtn = findViewById(R.id.btn_edit);
        saveBtn = findViewById(R.id.btn_save);
        progressBar = findViewById(R.id.progress_bar);
        profilLayout = findViewById(R.id.profil_layout);

        sharePref = new SharePref(this);
        String userName = sharePref.getDataString(SharePref.KEY_NAME);
        String userEmail = sharePref.getDataString(SharePref.KEY_EMAIL);
        String userPhone = sharePref.getDataString(SharePref.KEY_PHONE);

        Log.d("Test", userName+userEmail+userPhone);

        viewName.setText(userName);
        viewName.setEnabled(false);
        viewEmail.setText(userEmail);
        viewEmail.setEnabled(false);
        viewPhone.setText(userPhone);
        viewPhone.setEnabled(false);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewName.setEnabled(true);
                viewEmail.setEnabled(true);
                viewPhone.setEnabled(true);

                saveBtn.setVisibility(View.VISIBLE);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer id = sharePref.getDataInt(SharePref.KEY_ID, 0);
//                int id = Integer.parseInt(str_id);
                final String name = viewName.getText().toString();
                final String email = viewEmail.getText().toString();
                final String phone = viewPhone.getText().toString();

                profilLayout.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                mApiService.updateProfil(name, email, phone, id).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String responseData = response.body().string();
                            JSONObject jsonResults = new JSONObject(responseData);
                            if (jsonResults.getString("message").equals("success")){
                                Toast.makeText(ProfilActivity.this, "Berhasil dirubah", Toast.LENGTH_SHORT).show();
                                profilLayout.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.INVISIBLE);

                                sharePref.setDataString(SharePref.KEY_EMAIL, email);
                                sharePref.setDataString(SharePref.KEY_NAME, name);
                                sharePref.setDataString(SharePref.KEY_PHONE, phone);

                                viewName.setEnabled(false);
                                viewEmail.setEnabled(false);
                                viewPhone.setEnabled(false);

                                saveBtn.setVisibility(View.INVISIBLE);
                            }
                            else {
                                Toast.makeText(ProfilActivity.this, "Error Kontol", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        profilLayout.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(ProfilActivity.this, "no Internet Connection Detected", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        logutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharePref.setDataInt(SharePref.KEY_VALUE,0);
                Intent intent = new Intent(ProfilActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
//    @Override
//    public void onBackPressed() {
//        new AlertDialog.Builder(this)
//                .setTitle("Exit")
//                .setMessage("Are you sure to exit?")
//                .setNegativeButton(R.string.no, null)
//                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        ProfilActivity.super.onBackPressed();
//                        finishAffinity();
//                        System.exit(0);
//                    }
//                }).create().show();
//    }
}
