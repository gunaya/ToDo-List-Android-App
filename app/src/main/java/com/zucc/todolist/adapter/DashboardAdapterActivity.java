package com.zucc.todolist.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zucc.model.makanan;
import com.zucc.todolist.R;

import java.util.List;

public class DashboardAdapterActivity extends RecyclerView.Adapter<DashboardAdapterActivity.MyViewHolder>{

    private Context context;
    private List<makanan> makanans;
    public DashboardAdapterActivity(Context context, List<makanan> makanans){
        this.context = context;
        this.makanans = makanans;
    }

    @NonNull
    @Override
    public DashboardAdapterActivity.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.activity_list_home_activity, viewGroup ,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {
        myViewHolder.iv_makanan_bg.setImageResource(makanans.get(position).getThumbnails());
        myViewHolder.tv_makanan_harga.setText(makanans.get(position).getPrice());
        myViewHolder.tv_makanan.setText(makanans.get(position).getTitle());


    }


    @Override
    public int getItemCount() {
        return makanans.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_makanan_bg;
        TextView tv_makanan;
        TextView tv_makanan_harga;
        CardView cardView;


        public MyViewHolder(View itemView){
            super(itemView);

            iv_makanan_bg = itemView.findViewById(R.id.iv_fim_bg_next);
            tv_makanan = itemView.findViewById(R.id.tv_nama_makanan);
            tv_makanan_harga = itemView.findViewById(R.id.tv_price_makaanan);
            cardView = itemView.findViewById(R.id.cv_makanan);


        }
    }


}
