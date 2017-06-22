package com.example.lttha.a14110180_lethithao_foody.DataBase;

import android.util.Log;

import com.example.lttha.a14110180_lethithao_foody.Utils.ApiUtils;
import com.example.lttha.a14110180_lethithao_foody.Utils.ItemFoods;
import com.example.lttha.a14110180_lethithao_foody.Utils.ItemPlaces;
import com.example.lttha.a14110180_lethithao_foody.Utils.Service;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 */

public class DBItemFoodsServer {

    Service mService;

    public   interface getItemFoodListener{
          void  getItemFoods(ArrayList<ItemFoods> listItemFoods);// Model j vayaj ItemWhere
    }
    getItemFoodListener getItemFoodListener;

    public DBItemFoodsServer(getItemFoodListener getItemFoodListener ) {
        this.getItemFoodListener = getItemFoodListener;
    }

    public void getList_ItemFoods(int category_id, int type_id, int district_id, int city_id){
        mService = ApiUtils.getService();
//        Phương thức getList_ItemFoods sẽ trả về một đối tượng Call có một phương thức được gọi là enqueue(Callback<ItemFoods> callback).
        Call<ArrayList<ItemFoods>> call = mService.listItemFoods(category_id,type_id,district_id,city_id);
        Log.e("Response",call.request().url().toString());
//        enqueue() gửi yêu cầu và thông báo cho ứng dụng của bạn một cách không đồng bộ với một hàm callback khi có một phản hồi
        call.enqueue(new Callback<ArrayList<ItemFoods>>() {
//            onResponse(): được gọi khi nhận được một phản hồi HTTP
            @Override
            public void onResponse(Call<ArrayList<ItemFoods>> call, Response<ArrayList<ItemFoods>> response) {
                if(response.isSuccessful()) {
                    getItemFoodListener.getItemFoods(response.body());
                }
                else
                        Log.e("Response","get item lỗi");
            }
//            onFailure(): được gọi khi một ngoại lệ kết nối mạng xảy ra
            @Override
            public void onFailure(Call<ArrayList<ItemFoods>> call, Throwable t) {
                Log.e("Response",t.getMessage());
            }
        });
    }

    //  Phương thức InsertItemFoods sẽ trả về một đối tượng Call có một phương thức được gọi là enqueue(Callback<ItemFoods> callback).
    public void InsertItemFoods(String address,String name,String img,String foodname, int district_id,int type_id, int city_id,String useravatar,String username){
        mService = ApiUtils.getService();
        Call<ArrayList<ItemFoods>> call = mService.insertItemFood(address,name,img,foodname,district_id,type_id,city_id,useravatar,username);
//        enqueue() gửi yêu cầu và thông báo cho ứng dụng của bạn một cách không đồng bộ với một hàm callback khi có một phản hồi
        call.enqueue(new Callback<ArrayList<ItemFoods>>() {
            //            onResponse(): được gọi khi nhận được một phản hồi HTTP
            @Override
            public void onResponse(Call<ArrayList<ItemFoods>> call, Response<ArrayList<ItemFoods>> response) {
                if(response.isSuccessful()) {
                    getItemFoodListener.getItemFoods(response.body());
                }
                else
            //            Log.e("Response",response.message());
                    Log.e("Response","Lỗi");
            }
            //            onFailure(): được gọi khi một ngoại lệ kết nối mạng xảy ra
            @Override
            public void onFailure(Call<ArrayList<ItemFoods>> call, Throwable t) {
                Log.e("Response",t.getMessage());
            }
        });
    }
}
