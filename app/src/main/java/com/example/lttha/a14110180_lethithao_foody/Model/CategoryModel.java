package com.example.lttha.a14110180_lethithao_foody.Model;

import java.io.Serializable;

/**
 * Created by lttha on 4/1/2017.
 */

public class CategoryModel implements Serializable {
    private Integer idCategory;
    private String name;
    private String img;
    private String imgs;


    public CategoryModel(Integer idCategory, String name, String img, String imgs) {
        this.idCategory = idCategory;
        this.name = name;
        this.img = img;
        this.imgs=imgs;

    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
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

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }
}
