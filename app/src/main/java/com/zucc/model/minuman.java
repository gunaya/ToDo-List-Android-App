package com.zucc.model;

public class minuman {



    private String nama_minuman;
    private String harga_minuman;
    private int thumbnail;

    public minuman(String nama_minuman, String harga_minuman, int thumbnail) {
        this.nama_minuman = nama_minuman;
        this.harga_minuman = harga_minuman;
        this.thumbnail = thumbnail;
    }

    public String getNama_minuman() {
        return nama_minuman;
    }

    public void setNama_minuman(String nama_minuman) {
        this.nama_minuman = nama_minuman;
    }

    public String getHarga_minuman() {
        return harga_minuman;
    }

    public void setHarga_minuman(String harga_minuman) {
        this.harga_minuman = harga_minuman;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }


}
