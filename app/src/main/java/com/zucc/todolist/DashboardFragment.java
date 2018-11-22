package com.zucc.todolist;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zucc.model.makanan;
import com.zucc.todolist.adapter.DashboardAdapterActivity;

import java.util.ArrayList;
import java.util.List;


public class DashboardFragment extends Fragment {

    List<makanan> Listmakanan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard,container,false);

        setMyMakanan(view);
        return view;
    }
    public void setMyMakanan (View v) {
        RecyclerView recyclerViewOn;
        DashboardAdapterActivity dashboardAdapterActivity;

        Listmakanan = new ArrayList<>();

        for (int i=0; i<3; i++){
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
}
