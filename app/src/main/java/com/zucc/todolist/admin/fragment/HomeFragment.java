package com.zucc.todolist.admin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zucc.todolist.ProfilActivity;
import com.zucc.todolist.R;
import com.zucc.todolist.admin.TambahActivity;


public class HomeFragment extends Fragment {
    CardView foodAdd, foodEdit, orderConfirm, profilEdit;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        foodAdd = view.findViewById(R.id.add_makanan);
        foodAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TambahActivity.class);
                startActivity(intent);
            }
        });

        profilEdit = view.findViewById(R.id.edit_profil);
        profilEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProfilActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
