package com.example.lttha.a14110180_lethithao_foody.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lttha on 5/9/2017.
 */

public class RetrofitClient {
//    Lớp này sẽ tạo ra một singleton của Retrofit trong phương thức getClient(String baseUrl) và trả nó về cho hàm gọi.
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
