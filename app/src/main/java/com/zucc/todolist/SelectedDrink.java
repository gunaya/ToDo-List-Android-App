package com.zucc.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SelectedDrink extends AppCompatActivity {

    TextView tv_title, tv_price;
    ImageView iv_thumbnail;
//    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_drink);

        tv_price =  findViewById(R.id.tv_price);
        tv_title =  findViewById(R.id.tv_title);
        iv_thumbnail = findViewById(R.id.iv_thumbnail);

        Intent intent = getIntent();

        int id_data = intent.getIntExtra("id_data",0);
        String nama_barang = intent.getStringExtra("nama_data");
        String harga_jual = intent.getStringExtra("harga_jual");

        tv_title.setText(nama_barang);
        tv_price.setText(harga_jual);

        Toast.makeText(this, "Data " +id_data,
                Toast.LENGTH_SHORT).show();






    }
}
