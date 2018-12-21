package com.zucc.todolist.apihelper;

import com.zucc.model.RespDataKantin;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Gunaya on 11/3/2018.
 */

public interface BaseApiService {
    @FormUrlEncoded
    @POST("auth/login")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("auth/signup")
    Call<ResponseBody> registerRequest(@Field("name") String name,
                                       @Field("email") String email,
                                       @Field("phone") String phone,
                                       @Field("password") String password);

    @FormUrlEncoded
    @POST("kantin/add")
    Call<ResponseBody> tambahMakananRequest(@Field("nama_barang") String nama_barang,
                                            @Field("foto_barang") String foto_barang,
                                            @Field("kadaluarsa") String kadaluarsa,
                                            @Field("harga_beli") int harga_beli,
                                            @Field("harga_jual") int harga_jual,
                                            @Field("jumlah") int jumlah,
                                            @Field("kategori_id") int kategori_id);
    @FormUrlEncoded
    @POST("kantin/get")
    Call<List<RespDataKantin>> getMakanan(@Field("kategori_id") int kategori_id);

    @FormUrlEncoded
    @POST("kantin/profil/update")
    Call<ResponseBody> updateProfil(@Field("name") String name,
                                    @Field("email") String email,
                                    @Field("phone") String phone,
                                    @Field("id") int id);
    @FormUrlEncoded
    @POST("kantin/trans/add")
    Call<ResponseBody> addTransaksi(@Field("id_barang") int id_barang,
                                    @Field("id_user") int id_user,
                                    @Field("harga") String harga,
                                    @Field("status") int status);

    @GET("kantin/get/sql/")
    Call<List<RespDataKantin>> getData();


}
