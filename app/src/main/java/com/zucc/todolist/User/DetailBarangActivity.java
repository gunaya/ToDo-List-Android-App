package com.zucc.todolist.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zucc.todolist.R;
import com.zucc.todolist.SharePref;
import com.zucc.todolist.apihelper.ApiUtils;
import com.zucc.todolist.apihelper.BaseApiService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBarangActivity extends AppCompatActivity {
    Button buyBtn;
    TextView tv_title, tv_price;
    SharePref sharePref;
    ImageView iv_thumbnail;
    ProgressBar progressBar;
    LinearLayout detailLayout;
    BaseApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_barang);

        sharePref = new SharePref(this);

        tv_price =  findViewById(R.id.tv_price2);
        tv_title =  findViewById(R.id.tv_title2);
        iv_thumbnail = findViewById(R.id.iv_thumbnail2);
        apiService = ApiUtils.getApiService();
        progressBar = findViewById(R.id.progress_bar);
        detailLayout = findViewById(R.id.detail_layout);

        Intent intent = getIntent();

        final int id_data = intent.getIntExtra("id_data",0);
        String nama_barang = intent.getStringExtra("nama_data");
        final String harga_jual = intent.getStringExtra("harga_jual");

        tv_title.setText(nama_barang);
        tv_price.setText(harga_jual);

        Toast.makeText(this, "Data " +id_data,
                Toast.LENGTH_SHORT).show();

        buyBtn = findViewById(R.id.buy_btn);
        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailLayout.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                int id_user = sharePref.getDataInt(SharePref.KEY_ID, 0);
                apiService.addTransaksi(id_data,id_user, harga_jual, 1).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(DetailBarangActivity.this, "Sukses", Toast.LENGTH_SHORT).show();
                            detailLayout.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                        } else {
                            detailLayout.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(DetailBarangActivity.this, "Try Again Later", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(DetailBarangActivity.this, "No Internet Connection Detected", Toast.LENGTH_SHORT).show();
                        detailLayout.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });
    }
}
