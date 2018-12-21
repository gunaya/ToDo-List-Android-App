package com.zucc.todolist.User;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zucc.model.RespDataKantin;
import com.zucc.todolist.DB.DBHelper;
import com.zucc.todolist.LoginActivity;
import com.zucc.todolist.ProfilActivity;
import com.zucc.todolist.R;
import com.zucc.todolist.adapter.MakananAdapter;
import com.zucc.todolist.adapter.MinumanAdapter;
import com.zucc.todolist.apihelper.BaseApiService;
import com.zucc.todolist.apihelper.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserHomeActivity extends AppCompatActivity {
    List<RespDataKantin> dataMinuman = new ArrayList<>();
    List<RespDataKantin> dataMakanan = new ArrayList<>();
    MinumanAdapter minumanAdapter;
    MakananAdapter makananAdapter;
    DBHelper helper;
    BaseApiService apiService;

    RecyclerView user_makanan, user_minuman;

    Button profilBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        helper = new DBHelper(UserHomeActivity.this);
        apiService = RetrofitClient.getService();

        user_makanan = findViewById(R.id.user_makanan);
        user_minuman = findViewById(R.id.user_minuman);
        profilBtn = findViewById(R.id.go_profil);

        setDataMakanan();
        setDataMinuman();

        profilBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHomeActivity.this, ProfilActivity.class);
                startActivity(intent);
            }
        });

    }

    public void setDataMakanan(){
        apiService.getMakanan(1).enqueue(new Callback<List<RespDataKantin>>() {
            @Override
            public void onResponse(Call<List<RespDataKantin>> call, Response<List<RespDataKantin>> response) {
                if (response.isSuccessful()){
                    Toast.makeText(UserHomeActivity.this, "Sukses", Toast.LENGTH_SHORT).show();
                    dataMakanan.addAll(response.body());
                    setAdapterMakanan();
                } else {
                    Toast.makeText(UserHomeActivity.this, "data Kosong", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<RespDataKantin>> call, Throwable t) {
                Toast.makeText(UserHomeActivity.this, "Loading Back Up Data", Toast.LENGTH_SHORT).show();
                dataMakanan = helper.getDataBarang(1);
//                dataMakanan.addAll(helper.getDataBarang(1));
                int count = helper.getDataBarang(1).size();
                Log.d("jumlah", String.valueOf(count));
                setAdapterMakanan();
            }
        });
    }

    private void setAdapterMakanan(){
        makananAdapter = new MakananAdapter(UserHomeActivity.this, dataMakanan);
        LinearLayoutManager layoutManager = new LinearLayoutManager(UserHomeActivity.this,
                LinearLayoutManager.HORIZONTAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(UserHomeActivity.this, 3);
        user_makanan.setLayoutManager(layoutManager);
        user_makanan.setAdapter(makananAdapter);
    }

    public  void setDataMinuman(){
        apiService.getMakanan(2).enqueue(new Callback<List<RespDataKantin>>() {
            @Override
            public void onResponse(Call<List<RespDataKantin>> call, Response<List<RespDataKantin>> response) {
                if (response.isSuccessful()){
                    Toast.makeText(UserHomeActivity.this, "Sukses", Toast.LENGTH_SHORT).show();
                    dataMinuman.addAll(response.body());
                    setAdapterMinuman();

                }else {
                    Toast.makeText(UserHomeActivity.this, "data Kosong", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<RespDataKantin>> call, Throwable t) {
                Toast.makeText(UserHomeActivity.this, "Loading Backup Data", Toast.LENGTH_SHORT).show();
                dataMinuman = helper.getDataBarang(2);
                setAdapterMinuman();
            }
        });
    }

    private void setAdapterMinuman() {
        minumanAdapter = new MinumanAdapter(UserHomeActivity.this,dataMinuman);
        LinearLayoutManager layoutManager = new LinearLayoutManager(UserHomeActivity.this,
                LinearLayoutManager.HORIZONTAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(UserHomeActivity.this,3);
        user_minuman.setLayoutManager(layoutManager);
        user_minuman.setAdapter(minumanAdapter);
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
                        UserHomeActivity.super.onBackPressed();
                        finishAffinity();
                        System.exit(0);
                    }
                }).create().show();
    }
}
