package com.zucc.todolist.admin.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zucc.model.RespDataTrans;
import com.zucc.todolist.DB.DBHelper;
import com.zucc.todolist.R;
import com.zucc.todolist.adapter.TransaksiAdapter;
import com.zucc.todolist.apihelper.BaseApiService;
import com.zucc.todolist.apihelper.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotifFragment extends Fragment {

    List<RespDataTrans> dataTrans = new ArrayList<>();
    TransaksiAdapter transaksiAdapter;
    BaseApiService apiService;
    private DBHelper dbHelper;

    RecyclerView rv_notifikasi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notif,container,false);
        apiService = RetrofitClient.getService();

        rv_notifikasi = view.findViewById(R.id.rv_notifikasi);
        dbHelper = new DBHelper(getContext());
        loadSql();
        setDataTrans(view);

        return view;
    }

    public void loadSql(){
        apiService.getDataTrans().enqueue(new Callback<List<RespDataTrans>>() {
            @Override
            public void onResponse(Call<List<RespDataTrans>> call, Response<List<RespDataTrans>> response) {
                if (response.isSuccessful()){
                    dbHelper.deleteTrans();
                    dataTrans.addAll(response.body());
                    for (int i=0; i < dataTrans.size(); i++){
                        String id = String.valueOf(dataTrans.get(i).getId());
                        String nama_barang = dataTrans.get(i).getNamaBarang();
                        String nama_user = dataTrans.get(i).getName();
                        String harga = dataTrans.get(i).getHarga();
                        String status = dataTrans.get(i).getStatus();
                        dbHelper.saveTransaksi(id, nama_barang, nama_user, harga, status);
                        Log.d("Trans", id+nama_barang+nama_user+harga+status);
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<RespDataTrans>> call, Throwable t) {

            }
        });
    }

    public void setDataTrans(View view){
        apiService.getDataTrans().enqueue(new Callback<List<RespDataTrans>>() {
            @Override
            public void onResponse(Call<List<RespDataTrans>> call, Response<List<RespDataTrans>> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getContext(), "Sukses", Toast.LENGTH_SHORT).show();
                    dataTrans.addAll(response.body());
                    setDataTransAdapter();
                    Log.d("notif", "success");
                } else {
                    Log.d("notif", "error");
                    Toast.makeText(getContext(), "data Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<RespDataTrans>> call, Throwable t) {
                dataTrans = dbHelper.getDataTrans();
                int count = dataTrans.size();
                for (int i =0; i < count; i++){
                    Log.d("test", dataTrans.get(i).getNamaBarang());
                }
                setDataTransAdapter();
            }
        });
    }

    private void setDataTransAdapter(){
        transaksiAdapter = new TransaksiAdapter(getContext(), dataTrans);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        rv_notifikasi.setLayoutManager(gridLayoutManager);
        rv_notifikasi.setAdapter(transaksiAdapter);
    }
}
