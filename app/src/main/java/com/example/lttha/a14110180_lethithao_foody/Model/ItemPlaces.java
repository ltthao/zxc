package com.example.lttha.a14110180_lethithao_foody.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemPlaces {

    // map name voi NAME trong json duoc tra ve, neu object
    // trung roi thi khong can dung Serializedname nua
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("RESTAURANTID")
    @Expose
    private Integer restaurantId;
    @SerializedName("CATEGORYID")
    @Expose
    private Integer categoryId;
    @SerializedName("DISTRICTID")
    @Expose
    private Integer districtId;
    @SerializedName("TOTALPICTURES")
    @Expose
    private Integer totalPictures;
    @SerializedName("TOTALREVIEWS")
    @Expose
    private Integer totalReviews;
    @SerializedName("TYPEID")
    @Expose
    private Integer typeId;
    @SerializedName("AVGRATING")
    @Expose
    private Double avgRating;
    @SerializedName("ADDRESS")
    @Expose
    private String address;
    @SerializedName("NAME")
    @Expose
    private String name;

    //    @SerializedName("CITYID")
//    @Expose
//    private Integer city;
    @SerializedName("IMG")
    @Expose
    private String img;






//    @SerializedName("STREETID")
//    @Expose
//    private Integer street;

//    @SerializedName("CATETYPEID")
//    @Expose
//    private Integer catetypeID;




//    @SerializedName("BOOKMARKS")
//    @Expose
//    private String bookmarks;
//    @SerializedName("HASMEMBERCARD")
//    @Expose
//    private Integer hasMemberCard;
//    @SerializedName("ISDELIVERY")
//    @Expose
//    private Integer isDelivery;
//    @SerializedName("ISBOOKING")
//    @Expose
//    private Integer isBooking;
//    @SerializedName("ISCOLLECTION")
//    @Expose
//    private Integer isCollection;
//    @SerializedName("ISUSER")
//    @Expose
//    private Integer isUser;
//    @SerializedName("latitude")
//    @Expose
//    private Object latitude;
//    @SerializedName("longitude")
//    @Expose
//    private Object longitude;
//    @SerializedName("CREATE_AT")
//    @Expose
//    private Object createAt;


    public ItemPlaces(Integer id, Integer restaurantId, Integer categoryId, Integer districtId, Integer totalPictures, Integer totalReviews, Integer typeId, Double avgRating, String address, String name, String img) {
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
    }

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
}