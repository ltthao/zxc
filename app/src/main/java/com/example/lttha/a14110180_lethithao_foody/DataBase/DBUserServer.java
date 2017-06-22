package com.example.lttha.a14110180_lethithao_foody.DataBase;

import android.util.Log;

import com.example.lttha.a14110180_lethithao_foody.Utils.ApiUtils;
import com.example.lttha.a14110180_lethithao_foody.Utils.Service;
import com.example.lttha.a14110180_lethithao_foody.Utils.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lttha on 5/17/2017.
 */

public class DBUserServer {
    Service mService;

    public   interface getUserListener{
        void  getUser(ArrayList<User> listUser);// Model j vayaj ItemWhere
    }
    getUserListener getUserListener;

    public DBUserServer(getUserListener getItemPlaceListener ) {
        this.getUserListener = getItemPlaceListener;
    }
// Hàm insert user chỗ đăng ký
    public void InsertUser(String mail, String pass, String username){
        mService = ApiUtils.getService();
        Call<ArrayList<User>> call = mService.insertUser(mail,pass,username);
        Log.e("Response",call.request().url().toString());
        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                if(response.isSuccessful()) {
                    getUserListener.getUser(response.body());
                }
                else
//                    Log.e("Response",response.message());
                    Log.e("Response","Lỗi");
            }
            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Log.e("Response",t.getMessage());
            }
        });
    }
// Hàm login
    public void Login(String mail, String pass){
        mService = ApiUtils.getService();
        Call<ArrayList<User>> call = mService.login(mail,pass);
        Log.e("Response",call.request().url().toString());
        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                if(response.isSuccessful()) {
                    getUserListener.getUser(response.body());
                    Log.e("Response","lay duoc "+response.body().size());
                }
                else
//                    Log.e("Response",response.message());
                    Log.e("Response","khong lay duoc");
            }
            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Log.e("Response",t.getMessage());
            }
        });
    }



}
