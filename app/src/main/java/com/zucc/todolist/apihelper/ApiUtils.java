package com.zucc.todolist.apihelper;

/**
 * Created by Gunaya on 11/3/2018.
 */

public class ApiUtils {
    public static final String BASE_URL = "http://praktikum.nusapenidaholidaytour.com/api/";
//    public static final String BASE_URL = "http://192.168.137.1:8001/api/";
    public static BaseApiService getApiService(){
        return RetrofitClient.getClient(BASE_URL).create(BaseApiService.class);
    }
}
