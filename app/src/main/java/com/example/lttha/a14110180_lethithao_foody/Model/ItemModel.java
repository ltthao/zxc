package com.example.lttha.a14110180_lethithao_foody.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by lttha on 4/3/2017.
 */

public class ItemModel implements Serializable {

    Integer id,restaurantId,categoryId,districtId,totalPictures,totalReviews,typeId;
    Double avgRating;
    String address,name,img;
    ArrayList<ReviewModel> arrayList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getTotalPictures() {
        return totalPictures;
    }

    public void setTotalPictures(Integer totalPictures) {
        this.totalPictures = totalPictures;
    }

    public Integer getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(Integer totalReviews) {
        this.totalReviews = totalReviews;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public ArrayList<ReviewModel> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<ReviewModel> arrayList) {
        this.arrayList = arrayList;
    }

    public ItemModel(){

    }

    public ItemModel(Integer id, Integer restaurantId, Integer categoryId, Integer districtId, Integer totalPictures, Integer totalReviews, Integer typeId, Double avgRating, String address, String name, String img, ArrayList<ReviewModel> arrayList) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.categoryId = categoryId;
        this.districtId = districtId;
        this.totalPictures = totalPictures;
        this.totalReviews = totalReviews;
        this.typeId = typeId;
        this.avgRating = avgRating;
        this.address = address;
        this.name = name;
        this.img = img;
        this.arrayList = arrayList;
    }
}
