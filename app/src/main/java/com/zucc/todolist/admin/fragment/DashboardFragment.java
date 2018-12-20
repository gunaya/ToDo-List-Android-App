package com.zucc.todolist.admin.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zucc.model.RespDataKantin;
import com.zucc.model.makanan;
import com.zucc.todolist.R;
import com.zucc.todolist.adapter.DashboardAdapterActivity;
import com.zucc.todolist.adapter.MakananAdapter;
import com.zucc.todolist.adapter.MinumanAdapter;
import com.zucc.todolist.apihelper.BaseApiService;
import com.zucc.todolist.apihelper.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashboardFragment extends Fragment {
    List<RespDataKantin> dataKantins = new ArrayList<>();
    List<RespDataKantin> dataMakanan = new ArrayList<>();
    MinumanAdapter minumanAdapter;
    MakananAdapter makananAdapter;

    BaseApiService apiService;

    RecyclerView rv_minum, rv_makanan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard,container,false);

        apiService = RetrofitClient.getService();

        rv_minum = view.findViewById(R.id.rv_minuman);
        rv_makanan = view.findViewById(R.id.rv_makanan);

        setDataMinuman(view);
        setDataMakanan(view);
        return view;
    }
    protected boolean isOnline() {
//        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    public void setDataMakanan(View view){
        if(isOnline()){
            Toast.makeText(getContext(), "You are connected to Internet", Toast.LENGTH_SHORT).show();
            apiService.getMakanan(1).enqueue(new Callback<List<RespDataKantin>>() {
                @Override
                public void onResponse(Call<List<RespDataKantin>> call, Response<List<RespDataKantin>> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(getContext(), "Sukses", Toast.LENGTH_SHORT).show();
                        dataMakanan.addAll(response.body());
                        setAdapterMakanan();
                    } else {
                        Toast.makeText(getContext(), "data Kosong", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<RespDataKantin>> call, Throwable t) {
                    Toast.makeText(getContext(), "API Error", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Toast.makeText(getContext(), "You are not connected to Internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void setAdapterMakanan(){
        makananAdapter = new MakananAdapter(getContext(), dataMakanan);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        rv_makanan.setLayoutManager(layoutManager);
        rv_makanan.setAdapter(makananAdapter);
    }

    public  void setDataMinuman(View view){
        if(isOnline()){
            apiService.getMakanan(2).enqueue(new Callback<List<RespDataKantin>>() {
                @Override
                public void onResponse(Call<List<RespDataKantin>> call, Response<List<RespDataKantin>> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(getContext(), "Sukses", Toast.LENGTH_SHORT).show();
                        dataKantins.addAll(response.body());
                        setAdapterMinuman();

                    }else {
                        Toast.makeText(getContext(), "data Kosong", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<List<RespDataKantin>> call, Throwable t) {
                    Toast.makeText(getContext(), "API Error", Toast.LENGTH_SHORT).show();
                }
            });
        } else {

        }
    }

    private void setAdapterMinuman() {
        minumanAdapter = new MinumanAdapter(getContext(),dataKantins);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        rv_minum.setLayoutManager(layoutManager);
        rv_minum.setAdapter(minumanAdapter);
    }
}
