package com.zucc.todolist.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.zucc.model.RespDataKantin;
import com.zucc.model.RespDataTrans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gunaya on 12/20/2018.
 */

public class DBHelper {
    private static final String DATABASE_NAME = "kantin.db";
    private static final int DATABASE_VERSION = 5;
    public static final String TABLE_NAME = "tb_barang";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "nama_barang";
    public static final String COLUMN_PHOTO = "foto_barang";
    public static final String COLUMN_EXP = "kadaluarsa";
    public static final String COLUMN_SALE = "harga_jual";
    public static final String COLUMN_CAT = "kategori_id";

    public static final String TRANS_NAME = "transaksi";
    public static final String TRANS_ID = "id";
    public static final String TRANS_BARANG = "nama_barang";
    public static final String TRANS_USER = "nama_user";
    public static final String TRANS_HARGA = "harga";
    public static final String TRANS_STATUS = "status";

    Helper openHelper;
    private SQLiteDatabase sqLiteDatabase;

    public DBHelper (Context context){
        openHelper = new Helper(context);
        sqLiteDatabase = openHelper.getWritableDatabase();
    }
    public void saveRecord(String id, String name, String photo, String exp, String sale, String cat){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, id);
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_PHOTO, photo);
        contentValues.put(COLUMN_EXP, exp);
        contentValues.put(COLUMN_SALE, sale);
        contentValues.put(COLUMN_CAT, cat);
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public void saveTransaksi(String id, String nama_barang, String nama_user, String harga, String status){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TRANS_ID, id);
        contentValues.put(TRANS_BARANG, nama_barang);
        contentValues.put(TRANS_USER, nama_user);
        contentValues.put(TRANS_HARGA, harga);
        contentValues.put(TRANS_STATUS, status);
        sqLiteDatabase.insert(TRANS_NAME, null, contentValues);
    }

    public void deleteData(){
        sqLiteDatabase.delete(TABLE_NAME,null,null);
        Log.d("delete", "success");
    }

    public void deleteTrans(){
        sqLiteDatabase.delete("transaksi", null,null);
    }

    public List<RespDataTrans> getDataTrans(){
        List<RespDataTrans> transList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = openHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TRANS_NAME, null);
        int count = cursor.getCount();
        if (count>0){
            while (cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String nama_barang = cursor.getString(cursor.getColumnIndex("nama_barang"));
                String nama_user = cursor.getString(cursor.getColumnIndex("nama_user"));
                String harga = cursor.getString(cursor.getColumnIndex("harga"));
                String status = cursor.getString(cursor.getColumnIndex("status"));

                RespDataTrans temp = new RespDataTrans();
                temp.setId(id);
                temp.setNamaBarang(nama_barang);
                temp.setName(nama_user);
                temp.setHarga(harga);
                temp.setStatus(status);
                transList.add(temp);
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return transList;
    }

    public List<RespDataKantin> getDataBarang(Integer kategori) {
        List<RespDataKantin> barangList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = openHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLE_NAME+" where "+COLUMN_CAT+" = "+kategori,null);
        int count = cursor.getCount();
        Log.d("da", String.valueOf(count));
        if (count>0){
            while (cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String photo = cursor.getString(cursor.getColumnIndex(COLUMN_PHOTO));
                String exp = cursor.getString(cursor.getColumnIndex(COLUMN_EXP));
                String sale = cursor.getString(cursor.getColumnIndex(COLUMN_SALE));
                int cat = cursor.getInt(cursor.getColumnIndex(COLUMN_CAT));

                RespDataKantin temp = new RespDataKantin();
                temp.setId(1);
                temp.setNamaBarang(name);
                temp.setFotoBarang(photo);
                temp.setKadaluarsa(exp);
                temp.setHargaJual(sale);
                temp.setKategoriId(String.valueOf(cat));
                barangList.add(temp);
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        Log.d("list", String.valueOf(barangList.size()));
        return barangList;
    }

    private class Helper extends SQLiteOpenHelper {

        public Helper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_NAME + " ("
                    +COLUMN_ID + " INTEGER, "
                    +COLUMN_NAME+ " TEXT, "
                    +COLUMN_PHOTO+ " TEXT, "
                    +COLUMN_EXP+ " TEXT, "
                    +COLUMN_SALE+ " TEXT, "
                    +COLUMN_CAT+ " INTEGER );");

            sqLiteDatabase.execSQL("CREATE TABLE " + TRANS_NAME + " ("
                    +TRANS_ID+ " INTEGER, "
                    +TRANS_BARANG+ " TEXT, "
                    +TRANS_USER+ " TEXT, "
                    +TRANS_HARGA+ " TEXT, "
                    +TRANS_STATUS+ " TEXT )");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS 'transaksi' ");
            onCreate(sqLiteDatabase);
        }
    }

}
