package com.example.lttha.a14110180_lethithao_foody.Utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lttha on 5/12/2017.
 */

public class Reviews {
    // map name voi NAME trong json duoc tra ve, neu object
    // trung roi thi khong can dung Serializedname nua
    //@SerializedName cần cho Gson ánh xạ các khoá JSON thành các trường đối tượng Java.

    @SerializedName("ID")
    @Expose
    private Integer id;
    @SerializedName("ITEMID")
    @Expose
    private Integer itemID;
    @SerializedName("COMMENT")
    @Expose
    private String comment;
    @SerializedName("COMMENTTRIM")
    @Expose
    private String commentTrim;
    @SerializedName("AVATAR")
    @Expose
    private Integer avatar;
    @SerializedName("REVIEWURL")
    @Expose
    private String reviewUrl;
    @SerializedName("RATING")
    @Expose
    private Double rating;
    @SerializedName("NAME")
    @Expose
    private String name;

    public Reviews(Integer id, Integer itemID, String comment, String commentTrim, Integer avatar, String reviewUrl, Double rating, String name) {
        this.id = id;
        this.itemID = itemID;
        this.comment = comment;
        this.commentTrim = commentTrim;
        this.avatar = avatar;
        this.reviewUrl = reviewUrl;
        this.rating = rating;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemID() {
        return itemID;
    }

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentTrim() {
        return commentTrim;
    }

    public void setCommentTrim(String commentTrim) {
        this.commentTrim = commentTrim;
    }

    public Integer getAvatar() {
        return avatar;
    }

    public void setAvatar(Integer avatar) {
        this.avatar = avatar;
    }

    public String getReviewUrl() {
        return reviewUrl;
    }

    public void setReviewUrl(String reviewUrl) {
        this.reviewUrl = reviewUrl;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
