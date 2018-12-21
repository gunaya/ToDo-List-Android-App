package com.zucc.todolist.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zucc.model.RespDataKantin;
import com.zucc.todolist.R;
import com.zucc.todolist.SelectedDrink;
import com.zucc.todolist.SharePref;
import com.zucc.todolist.User.DetailBarangActivity;

import java.util.List;

public class MinumanAdapter extends RecyclerView.Adapter<MinumanAdapter.ViewHolder>{

    private Context context;
    private List<RespDataKantin> dataKantins;

    public MinumanAdapter(Context context, List<RespDataKantin> dataKantins){
        this.context = context;
        this.dataKantins = dataKantins;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.activity_list_home_activity, parent ,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final RespDataKantin respDataKantin = dataKantins.get(position);
        holder.tv_makanan.setText(respDataKantin.getNamaBarang());
        holder.tv_makanan_harga.setText(respDataKantin.getHargaJual());
        SharePref sharePref = new SharePref(context);
        String admin = sharePref.getDataString(SharePref.IS_ADMIN);
        if (admin.equals("Ya")){
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(holder.itemView.getContext(), SelectedDrink.class);

                    int id_data = respDataKantin.getId();
                    String nama_barang = respDataKantin.getNamaBarang();
                    String harga_jual = respDataKantin.getHargaJual();
                    String jumlah = respDataKantin.getJumlah();

                    intent.putExtra("id_data", id_data);
                    intent.putExtra("nama_data", nama_barang);
                    intent.putExtra("harga_jual", harga_jual);
                    intent.putExtra("jumlah", jumlah);

                    Toast.makeText(context, "ID Data "+id_data, Toast.LENGTH_SHORT).show();
                    holder.itemView.getContext().startActivity(intent);
                }
            });
        } else {
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(holder.itemView.getContext(), DetailBarangActivity.class);

                    int id_data = respDataKantin.getId();
                    String nama_barang = respDataKantin.getNamaBarang();
                    String harga_jual = respDataKantin.getHargaJual();
                    String jumlah = respDataKantin.getJumlah();

                    intent.putExtra("id_data", id_data);
                    intent.putExtra("nama_data", nama_barang);
                    intent.putExtra("harga_jual", harga_jual);
                    intent.putExtra("jumlah", jumlah);

                    Toast.makeText(context, "ID Data "+id_data, Toast.LENGTH_SHORT).show();
                    holder.itemView.getContext().startActivity(intent);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return dataKantins.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_makanan_bg;
        TextView tv_makanan;
        TextView tv_makanan_harga;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            iv_makanan_bg = itemView.findViewById(R.id.iv_fim_bg_next);
            tv_makanan = itemView.findViewById(R.id.tv_nama_makanan);
            tv_makanan_harga = itemView.findViewById(R.id.tv_price_makaanan);
            cardView = itemView.findViewById(R.id.cv_makanan);

        }
    }
}
