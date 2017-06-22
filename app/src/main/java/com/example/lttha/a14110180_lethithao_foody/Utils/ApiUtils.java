package com.example.lttha.a14110180_lethithao_foody.Utils;

/**
 * Created by lttha on 5/9/2017.
 */

// Lớp tiện ích API có URL cơ sở như là một biến tĩnh và cũng sẽ cung cấp interface APIService
public class ApiUtils {
    public static final String BASE_URL = "https://foody-v2.herokuapp.com/";
//    public static final String BASE_URL = "http://172.20.10.5:8000/";

    public static Service getService() {
        return RetrofitClient.getClient(BASE_URL).create(Service.class);
    }
}
