package com.zucc.todolist.admin;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zucc.todolist.LoginActivity;
import com.zucc.todolist.MainActivity;
import com.zucc.todolist.R;
import com.zucc.todolist.RegisterActivity;
import com.zucc.todolist.apihelper.ApiUtils;
import com.zucc.todolist.apihelper.BaseApiService;
import com.zucc.todolist.fragment.DatePickerFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    EditText name, buyPrice, sellPrice, stock;
    TextView date, pictName;
    String currentDate;
    String category[] = {"makanan","minuman"};
    int category_id;
    Button submitFood, getPict;
    Spinner getCategory;
    BaseApiService mApiService;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mApiService = ApiUtils.getApiService();

        setContentView(R.layout.activity_tambah);
        name = findViewById(R.id.food_name);
        pictName = findViewById(R.id.pict_name);
        getPict = findViewById(R.id.load_pict);
        date = findViewById(R.id.load_date);
        buyPrice = findViewById(R.id.buy_price);
        sellPrice = findViewById(R.id.sell_price);
        stock = findViewById(R.id.set_stock);
        submitFood = findViewById(R.id.submit_food);
        getCategory = findViewById(R.id.set_category);

        getPict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,100);
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, category);
        getCategory.setAdapter(adapter);
        getCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        category_id = 1;
                        break;

                    case 1:
                        category_id = 2;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submitFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Hasil", "Tanggal:"+currentDate+". Kategori:"+category_id);
                sendData();
            }
        });

    }

    private void startActivityForResult(Intent intent) {
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, day);
        month = month + 1;
        currentDate = year+"/"+month+"/"+day;
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        date.setText(currentDateString);
    }

    public void sendData(){
        String foodName = name.getText().toString();
        String buyFood = buyPrice.getText().toString();
        int buyFoodPrice = Integer.parseInt(buyFood);
        String sellFood = sellPrice.getText().toString();
        int sellFoodPrice = Integer.parseInt(sellFood);
        String stockString = stock.getText().toString();
        int stockInt = Integer.parseInt(stockString);
//      Data Dummy
        String pict = "Data Dummy";
        Log.d("Data",""+foodName+" "+buyFoodPrice+" "+sellFoodPrice+" "+stockInt+" "+currentDate+" "+pict+" "+category_id);
        mApiService.tambahMakananRequest(foodName, pict, currentDate, buyFoodPrice, sellFoodPrice, stockInt, category_id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    String responseData = response.body().string();
//                    JSONObject jsonResults = new JSONObject(responseData);
//                    Log.d("status", ""+jsonResults.getString("message"));
//                    if (jsonResults.getString("message").equals("success")) {
//                        Toast.makeText(TambahActivity.this,"Input Data Success",Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(TambahActivity.this, MainActivity.class);
//                        startActivity(intent);
//                    } else {
//                        Toast.makeText(TambahActivity.this, "Input Correct Data", Toast.LENGTH_SHORT).show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    if (jsonObject.getString("message").equals("success")) {
                        Log.d("status","keisini");
                        Log.d("status", ""+jsonObject.getString("message"));
                        Toast.makeText(TambahActivity.this,"Input Data Success",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TambahActivity.this, FragmentActivity.class);
                        startActivity(intent);
                    } else  {
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("status","onfailuer");
            }
        });
    }
}
