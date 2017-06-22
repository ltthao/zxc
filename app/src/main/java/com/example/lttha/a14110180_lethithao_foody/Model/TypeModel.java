package com.example.lttha.a14110180_lethithao_foody.Model;

import java.io.Serializable;

/**
 * Created by lttha on 4/2/2017.
 */

public class TypeModel implements Serializable {
    private Integer id;
    private String name;
    private String img;
    private Integer categoryId;


    public TypeModel(Integer id, String name, String img, Integer categoryId) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.categoryId = categoryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
