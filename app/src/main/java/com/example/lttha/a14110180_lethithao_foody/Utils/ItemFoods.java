package com.example.lttha.a14110180_lethithao_foody.Utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lttha on 5/14/2017.
 */

public class ItemFoods {
    // map name voi NAME trong json duoc tra ve, neu object
    // trung roi thi khong can dung Serializedname nua
    //@SerializedName cần cho Gson ánh xạ các khoá JSON thành các trường đối tượng Java.
    @SerializedName("ID")
    @Expose
    private Integer id;
    @SerializedName("FOODNAME")
    @Expose
    private String foodName;
    @SerializedName("CATEGORYID")
    @Expose
    private Integer categoryId;
    @SerializedName("DISTRICTID")
    @Expose
    private Integer districtId;
    @SerializedName("USERNAME")
    @Expose
    private String userName;
    @SerializedName("DATECREATED")
    @Expose
    private String dateCreated;
    @SerializedName("TYPEID")
    @Expose
    private Integer typeId;
    @SerializedName("ADDRESS")
    @Expose
    private String address;
    @SerializedName("NAME")
    @Expose
    private String name;
    @SerializedName("IMG")
    @Expose
    private String img;
    @SerializedName("CITYID")
    @Expose
    private Integer cityID;
    @SerializedName("USERAVATAR")
    @Expose
    private String userAvatar;

    public ItemFoods(Integer id, String foodName, Integer categoryId, Integer districtId, String userName, String dateCreated, Integer typeId, String address, String name, String img, Integer cityID, String userAvatar) {
        this.id = id;
        this.foodName = foodName;
        this.categoryId = categoryId;
        this.districtId = districtId;
        this.userName = userName;
        this.dateCreated = dateCreated;
        this.typeId = typeId;
        this.address = address;
        this.name = name;
        this.img = img;
        this.cityID = cityID;
        this.userAvatar = userAvatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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

    public Integer getCityID() {
        return cityID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
}
