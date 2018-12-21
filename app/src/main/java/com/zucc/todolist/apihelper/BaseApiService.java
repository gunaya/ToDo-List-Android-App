package com.zucc.todolist.apihelper;

import com.zucc.model.RespDataKantin;
import com.zucc.model.RespDataTrans;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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

    @Multipart
    @POST("kantin/add")
    Call<ResponseBody> addNewMenu(
            @Part MultipartBody.Part foto_barang,
            @Part("nama_barang") RequestBody nama_barang,
            @Part("kadaluarsa") RequestBody kaladuarsa,
            @Part("harga_beli") RequestBody harga_beli,
            @Part("harga_jual") RequestBody harga_jual,
            @Part("jumlah") RequestBody jumlah,
            @Part("kategori_id") RequestBody kategori_id);

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

    @GET("kantin/trans/get")
    Call<List<RespDataTrans>> getDataTrans();

    @FormUrlEncoded
    @POST("kantin/update")
    Call<ResponseBody> updateBarang(@Field("id") int id,
                                    @Field("nama_barang") String nama_barang,
                                    @Field("harga_jual") String harga_jual,
                                    @Field("jumlah") String jumlah);

    @FormUrlEncoded
    @POST("kantin/delete")
    Call<ResponseBody> deleteBarang(@Field("id") int id);

    @FormUrlEncoded
    @POST("store_fcm_token")
    Call<ResponseBody> storetoken(@Field("id") int id,
                                  @Field("fcm_token") String fcm_token);
}
