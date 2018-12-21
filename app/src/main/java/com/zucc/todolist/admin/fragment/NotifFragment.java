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

    RecyclerView rv_notifikasi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notif,container,false);
        apiService = RetrofitClient.getService();

        rv_notifikasi = view.findViewById(R.id.rv_notifikasi);

        setDataTrans(view);

        return view;
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
