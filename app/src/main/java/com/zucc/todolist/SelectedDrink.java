package com.zucc.todolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zucc.todolist.admin.FragmentActivity;
import com.zucc.todolist.admin.fragment.DashboardFragment;
import com.zucc.todolist.apihelper.ApiUtils;
import com.zucc.todolist.apihelper.BaseApiService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectedDrink extends AppCompatActivity {

    EditText tv_title, tv_price, tv_jumlah;
    ImageView iv_thumbnail;
    Button editBtn, deleteBtn, submitBtn;
    BaseApiService apiService;
    ProgressBar progressBar;
    LinearLayout selectedLayout;
//    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_drink);

        tv_price =  findViewById(R.id.tv_price);
        tv_title =  findViewById(R.id.tv_title);
        tv_jumlah = findViewById(R.id.tv_jumlah);
        iv_thumbnail = findViewById(R.id.iv_thumbnail);
        editBtn = findViewById(R.id.btn_edit);
        deleteBtn = findViewById(R.id.btn_delete);
        submitBtn = findViewById(R.id.btn_submit_detail_brg);
        progressBar = findViewById(R.id.progress_bar2);
        selectedLayout = findViewById(R.id.selected_layout);

        apiService = ApiUtils.getApiService();

        Intent intent = getIntent();

        final int id_data = intent.getIntExtra("id_data",0);
        final String nama_barang = intent.getStringExtra("nama_data");
        final String harga_jual = intent.getStringExtra("harga_jual");
        final String jumlah = intent.getStringExtra("jumlah");
        final String img_barang = intent.getStringExtra("gambar_menu");

        Glide.with(this)
                .load(img_barang)
                .into(iv_thumbnail);
        tv_title.setText(nama_barang);
        tv_price.setText(harga_jual);
        tv_jumlah.setText(jumlah);

        tv_title.setEnabled(false);
        tv_price.setEnabled(false);
        tv_jumlah.setEnabled(false);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_title.setEnabled(true);
                tv_price.setEnabled(true);
                tv_jumlah.setEnabled(true);

                submitBtn.setVisibility(View.VISIBLE);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                selectedLayout.setVisibility(View.INVISIBLE);
                String nama_b = tv_title.getText().toString();
                String harga = tv_price.getText().toString();
                String jumlah = tv_jumlah.getText().toString();

                apiService.updateBarang(id_data, nama_b, harga, jumlah).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                Log.d("TEST", ""+jsonObject.getString("message"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(SelectedDrink.this, "Berhasil dirubah", Toast.LENGTH_SHORT).show();
                            tv_title.setEnabled(false);
                            tv_price.setEnabled(false);
                            tv_jumlah.setEnabled(false);
                            progressBar.setVisibility(View.INVISIBLE);
                            selectedLayout.setVisibility(View.VISIBLE);
                        }
                        else {
                            Toast.makeText(SelectedDrink.this, "Try Again Later", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                            selectedLayout.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        progressBar.setVisibility(View.INVISIBLE);
                        selectedLayout.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(SelectedDrink.this)
                .setTitle("Delete")
                        .setMessage("Are you sure want to delete this data?")
                        .setNegativeButton(R.string.no, null)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                apiService.deleteBarang(id_data).enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        if (response.isSuccessful()){
                                            Toast.makeText(SelectedDrink.this, "Delete Success", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(SelectedDrink.this, FragmentActivity.class);
                                            startActivity(intent);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                                    }
                                });
                            }
                        }).create().show();
            }
        });


    }
}
