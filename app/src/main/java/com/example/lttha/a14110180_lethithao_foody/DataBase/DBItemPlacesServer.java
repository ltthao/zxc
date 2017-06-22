package com.example.lttha.a14110180_lethithao_foody.DataBase;

import android.util.Log;

import com.example.lttha.a14110180_lethithao_foody.Utils.ApiUtils;
import com.example.lttha.a14110180_lethithao_foody.Utils.ItemPlaces;
import com.example.lttha.a14110180_lethithao_foody.Utils.Service;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 */

public class DBItemPlacesServer {

    Service mService;

    public   interface getItemPlaceListener{
          void  getItemPlaces(ArrayList<ItemPlaces> itemPlaces);// Model j vayaj ItemWhere
    }
    getItemPlaceListener getItemPlaceListener;

    public DBItemPlacesServer(getItemPlaceListener getItemPlaceListener ) {
        this.getItemPlaceListener = getItemPlaceListener;
    }
    //        Phương thức getList_ItemPlaces sẽ trả về một đối tượng Call có một phương thức được gọi là enqueue(Callback<ItemFoods> callback).

    public void getList_ItemPlaces(int category_id, int type_id, int district_id, int city_id, int street_id){
        mService = ApiUtils.getService();
        Call<ArrayList<ItemPlaces>> call = mService.listItemPlaces(category_id,type_id,district_id,city_id,street_id);
        //        enqueue() gửi yêu cầu và thông báo cho ứng dụng của bạn một cách không đồng bộ với một hàm callback khi có một phản hồi
        call.enqueue(new Callback<ArrayList<ItemPlaces>>() {
            //            onResponse(): được gọi khi nhận được một phản hồi HTTP
            @Override
            public void onResponse(Call<ArrayList<ItemPlaces>> call, Response<ArrayList<ItemPlaces>> response) {
                if(response.isSuccessful()) {
                    getItemPlaceListener.getItemPlaces(response.body());
                    Log.e("Response","lay duoc "+response.body().size());
                }
                else
//                    Log.e("Response",response.message());
                    Log.e("Response","khong lay duoc");
            }
            //            onFailure(): được gọi khi một ngoại lệ kết nối mạng xảy ra
            @Override
            public void onFailure(Call<ArrayList<ItemPlaces>> call, Throwable t) {
                Log.e("Response",t.getMessage());
            }
        });
    }

//    public void InsertItemPlaces(String address,String name,String img,int type_id, int district_id, int city_id){
//        mService = ApiUtils.getService();
//        Call<ArrayList<ItemPlaces>> call = mService.insertItem(address,name,img,district_id,type_id,city_id);
//        Log.e("Response",call.request().url().toString());
//        call.enqueue(new Callback<ArrayList<ItemPlaces>>() {
//            @Override
//            public void onResponse(Call<ArrayList<ItemPlaces>> call, Response<ArrayList<ItemPlaces>> response) {
//                if(response.isSuccessful()) {
//                    getItemPlaceListener.getItemPlaces(response.body());
//                    Log.e("Response","lay duoc "+response.body().size());
//                }
//                else
////                    Log.e("Response",response.message());
//                    Log.e("Response","khong lay duoc");
//            }
//            @Override
//            public void onFailure(Call<ArrayList<ItemPlaces>> call, Throwable t) {
//                Log.e("Response",t.getMessage());
//            }
//        });
//    }

}
