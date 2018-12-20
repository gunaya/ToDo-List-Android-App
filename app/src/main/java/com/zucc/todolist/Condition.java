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
import com.zucc.todolist.admin.FragmentActivity;
import com.zucc.todolist.apihelper.BaseApiService;
import com.zucc.todolist.apihelper.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

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
    private static String URL = "http://praktikum.nusapenidaholidaytour.com/api/kantin/get/sql";
    private static final String TABLE_NAME = "tb_barang";
    private static final String COLUMN_ID = "";
    private static final String COLUMN_NAME = "nama_barang";
    private static final String COLUMN_PHOTO = "foto_barang";
    private static final String COLUMN_EXP = "kadaluarsa";
    private static final String COLUMN_SALE = "harga_jual";
    private static final String COLUMN_CAT = "";

    List<RespDataKantin> dataList = new ArrayList<>();

    BaseApiService apiService;
    JSONArray barang = null;
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
        Log.d("Values",""+val);
        if (val == 0) {
            Intent intent = new Intent(Condition.this, LoginActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(Condition.this, FragmentActivity.class);
            startActivity(intent);
        }
    }
}
