package com.zucc.model;


import com.google.gson.annotations.SerializedName;


public class RespDataKantin{

	@SerializedName("foto_barang")
	private String fotoBarang;

	@SerializedName("kategori_id")
	private String kategoriId;

	@SerializedName("jumlah")
	private String jumlah;

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("harga_beli")
	private String hargaBeli;

	@SerializedName("kadaluarsa")
	private String kadaluarsa;

	@SerializedName("created_at")
	private Object createdAt;

	@SerializedName("nama_barang")
	private String namaBarang;

	@SerializedName("id")
	private int id;

	@SerializedName("harga_jual")
	private String hargaJual;

	public void setFotoBarang(String fotoBarang){
		this.fotoBarang = fotoBarang;
	}

	public String getFotoBarang(){
		return fotoBarang;
	}

	public void setKategoriId(String kategoriId){
		this.kategoriId = kategoriId;
	}

	public String getKategoriId(){
		return kategoriId;
	}

	public void setJumlah(String jumlah){
		this.jumlah = jumlah;
	}

	public String getJumlah(){
		return jumlah;
	}

	public void setUpdatedAt(Object updatedAt){
		this.updatedAt = updatedAt;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public void setHargaBeli(String hargaBeli){
		this.hargaBeli = hargaBeli;
	}

	public String getHargaBeli(){
		return hargaBeli;
	}

	public void setKadaluarsa(String kadaluarsa){
		this.kadaluarsa = kadaluarsa;
	}

	public String getKadaluarsa(){
		return kadaluarsa;
	}

	public void setCreatedAt(Object createdAt){
		this.createdAt = createdAt;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public void setNamaBarang(String namaBarang){
		this.namaBarang = namaBarang;
	}

	public String getNamaBarang(){
		return namaBarang;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setHargaJual(String hargaJual){
		this.hargaJual = hargaJual;
	}

	public String getHargaJual(){
		return hargaJual;
	}

	@Override
 	public String toString(){
		return 
			"RespDataKantin{" + 
			"foto_barang = '" + fotoBarang + '\'' + 
			",kategori_id = '" + kategoriId + '\'' + 
			",jumlah = '" + jumlah + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",harga_beli = '" + hargaBeli + '\'' + 
			",kadaluarsa = '" + kadaluarsa + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",nama_barang = '" + namaBarang + '\'' + 
			",id = '" + id + '\'' + 
			",harga_jual = '" + hargaJual + '\'' + 
			"}";
		}
}