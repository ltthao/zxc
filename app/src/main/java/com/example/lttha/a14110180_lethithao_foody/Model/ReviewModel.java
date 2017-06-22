package com.example.lttha.a14110180_lethithao_foody.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by lttha on 4/6/2017.
 */

public class ReviewModel implements Serializable {
    private Integer reviewID,itemID,avatar;
    private String name,comment,commentTrim,reviewUrl;
    private Double rating;

    public ReviewModel(){

    }

    public ReviewModel(Integer reviewID,String name, Double rating,  String comment,
                       String commentTrim, Integer avatar, Integer itemID, String reviewUrl) {
        this.reviewID = reviewID;
        this.itemID = itemID;
        this.name = name;
        this.comment = comment;
        this.commentTrim = commentTrim;
        this.avatar = avatar;
        this.reviewUrl = reviewUrl;
        this.rating = rating;
    }

    public Integer getReviewID() {
        return reviewID;
    }

    public void setReviewID(Integer reviewID) {
        this.reviewID = reviewID;
    }

    public Integer getItemID() {
        return itemID;
    }

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    public Integer getAvatar() {
        return avatar;
    }

    public void setAvatar(Integer avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}