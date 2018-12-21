package com.zucc.todolist.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zucc.model.RespDataTrans;
import com.zucc.todolist.R;

import java.util.List;

/**
 * Created by Gunaya on 12/21/2018.
 */

public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiAdapter.ViewHolder> {

    private Context context;
    private List<RespDataTrans> dataTrans;

    public TransaksiAdapter(Context context, List<RespDataTrans> dataTrans){
        this.context = context;
        this.dataTrans = dataTrans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.activity_notifikasi, parent ,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final RespDataTrans respDataTrans = dataTrans.get(position);
        holder.tv_nama_barang.setText(respDataTrans.getNamaBarang());
        holder.tv_nama_user.setText(respDataTrans.getName());
        holder.tv_harga.setText(respDataTrans.getHarga());
        holder.tv_status.setText(respDataTrans.getStatus());

    }

    @Override
    public int getItemCount() {
        return dataTrans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_nama_barang, tv_nama_user, tv_harga, tv_status;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_nama_barang = itemView.findViewById(R.id.nama_brg);
            tv_nama_user = itemView.findViewById(R.id.nama_user);
            tv_harga = itemView.findViewById(R.id.harga);
            tv_status = itemView.findViewById(R.id.status);
        }
    }

}
