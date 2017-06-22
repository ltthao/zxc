package com.example.lttha.a14110180_lethithao_foody.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by lttha on 5/9/2017.
 */
public interface Service {
    //Interfaces để định nghĩa các API cho Webservice
    @GET("/item")
    Call<ArrayList<ItemPlaces>> listItemPlaces(@Query("categoryid") int categoryID,@Query("typeid") int typeID, @Query("districtid") int districtID,@Query("cityid") int cityid,@Query("streetid") int streetid);
    //Call<ArrayList<ItemPlaces>> listItemPlaces(@Query("categoryid") int categoryID, @Query("typeid") int typeID, @Query("districtid") int districtID);

    @GET("/getreview")
    Call<ArrayList<Reviews>> listReviewByItem(@Query("itemID") int itemid);

    @GET("/itemfoods")
    Call<ArrayList<ItemFoods>> listItemFoods(@Query("categoryid") int categoryID,@Query("typeid") int typeID, @Query("districtid") int districtID,@Query("cityid") int cityid);

    @POST("/insertitem")
    @FormUrlEncoded
    Call<ArrayList<ItemPlaces>> insertItem(@Field("address") String address, @Field("name") String name,@Field("img") String img,
                                      @Field("districtid") int districtid, @Field("typeid") int typeid, @Field("cityid") int cityid);
    @POST("/insertitemfood")
    @FormUrlEncoded
    Call<ArrayList<ItemFoods>> insertItemFood(@Field("address") String address, @Field("name") String name,@Field("img") String img,@Field("foodname") String foodname,
                                           @Field("districtid") int districtid, @Field("typeid") int typeid, @Field("cityid") int cityid,@Field("useravatar") String useravatar,@Field("username") String username);

    @POST("/insertuser")
    @FormUrlEncoded
    Call<ArrayList<User>> insertUser(@Field("mail") String mail,@Field("pass") String pass,@Field("username") String username);

    @GET("/login")
    Call<ArrayList<User>> login(@Query("mail") String mail,@Query("pass") String pass);
 }
