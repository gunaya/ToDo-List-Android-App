package com.zucc.todolist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.JsonParser;
import com.zucc.model.RespDataKantin;
import com.zucc.todolist.DB.DBHelper;
import com.zucc.todolist.User.UserHomeActivity;
import com.zucc.todolist.admin.FragmentActivity;
import com.zucc.todolist.apihelper.BaseApiService;
import com.zucc.todolist.apihelper.RetrofitClient;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gunaya on 10/31/2018.
 */

public class Condition extends Activity{

    List<RespDataKantin> dataList = new ArrayList<>();

    BaseApiService apiService;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiService = RetrofitClient.getService();
        dbHelper = new DBHelper(Condition.this);

        apiService.getData().enqueue(new Callback<List<RespDataKantin>>() {
            @Override
            public void onResponse(Call<List<RespDataKantin>> call, Response<List<RespDataKantin>> response) {
                if (response.isSuccessful()){
                    dbHelper.deleteData();
                    dataList.addAll(response.body());
                    for (int i=0; i < dataList.size(); i++) {
                        String id = String.valueOf(dataList.get(i).getId());
                        String nama = dataList.get(i).getNamaBarang();
                        String photo = dataList.get(i).getFotoBarang();
                        String exp = dataList.get(i).getKadaluarsa();
                        String sale = dataList.get(i).getHargaJual();
                        String cat = dataList.get(i).getKategoriId();
                        dbHelper.saveRecord(id,nama,photo,exp,sale,cat);
                        Log.d("DATA", id+nama+photo+exp+sale+cat);
                    }
                } else {

                }

            }

            @Override
            public void onFailure(Call<List<RespDataKantin>> call, Throwable t) {

            }
        });

        SharePref sharePref = new SharePref(this);
        int val = sharePref.getDataInt(SharePref.KEY_VALUE,0);
        String admin = sharePref.getDataString(SharePref.IS_ADMIN);
        Log.d("Values",""+val+admin);
        if (val == 0) {
            Intent intent = new Intent(Condition.this, LoginActivity.class);
            startActivity(intent);
        } else {
            if (admin.equals("Ya")){
                Intent intent = new Intent(Condition.this, FragmentActivity.class);
                startActivity(intent);
            } else if (admin.equals("Tidak")){
                Intent intent = new Intent(Condition.this, UserHomeActivity.class);
                startActivity(intent);
            }

        }
    }
}
