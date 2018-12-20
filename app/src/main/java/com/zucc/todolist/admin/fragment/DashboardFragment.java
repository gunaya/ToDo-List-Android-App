package com.zucc.todolist.admin.fragment;

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
import android.widget.Toast;

import com.zucc.model.RespDataKantin;
import com.zucc.model.makanan;
import com.zucc.todolist.R;
import com.zucc.todolist.adapter.DashboardAdapterActivity;
import com.zucc.todolist.adapter.MinumanAdapter;
import com.zucc.todolist.apihelper.BaseApiService;
import com.zucc.todolist.apihelper.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashboardFragment extends Fragment {

    List<makanan> Listmakanan;

//    Minuman
    List<RespDataKantin> dataKantins = new ArrayList<>();
    MinumanAdapter minumanAdapter;

    BaseApiService apiService;


    RecyclerView rv_minum;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard,container,false);

        apiService = RetrofitClient.getService();

        rv_minum = view.findViewById(R.id.rv_minuman);

        setMyMakanan(view);

        setDataMinuman(view);
        return view;
    }
    public void setMyMakanan (View v) {

        RecyclerView recyclerViewOn;

        DashboardAdapterActivity dashboardAdapterActivity;

        Listmakanan = new ArrayList<>();

        for (int i=0; i<6; i++){
            Listmakanan.add(new makanan("Miles 22", "Rp.5.000", R.drawable.bir));
            Listmakanan.add(new makanan("Miasases 22", "Rp.5.000", R.drawable.bir));
            Listmakanan.add(new makanan("assaes 22", "Rp.6.000", R.drawable.bir));
        }

        recyclerViewOn = v.findViewById(R.id.rv_dashboard);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        dashboardAdapterActivity = new DashboardAdapterActivity(getContext(), Listmakanan);
        recyclerViewOn.setLayoutManager(layoutManager);
        recyclerViewOn.setAdapter(dashboardAdapterActivity);

    }

    public  void setDataMinuman(View view){
        RecyclerView rv_minum = view.findViewById(R.id.rv_minuman);



       apiService.getMakanan(1)
               .enqueue(new Callback<List<RespDataKantin>>() {
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
