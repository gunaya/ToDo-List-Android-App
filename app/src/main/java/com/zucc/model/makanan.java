package com.zucc.model;

public class makanan {

    private String Title;
    private String Price;
    private int Thumbnails;

    public makanan () {

    }

    public makanan (String title, String price, int thumbnails) {
        Title = title;
        Price = price;
        Thumbnails = thumbnails;
    }

    public String getTitle () {
        return Title;
    }

    public int getThumbnails() {
        return Thumbnails;
    }

    public String getPrice() {
        return Price;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public void setThumbnails(int thumbnails) {
        Thumbnails = thumbnails;
    }


}
