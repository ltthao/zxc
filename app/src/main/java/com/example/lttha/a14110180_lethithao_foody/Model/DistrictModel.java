package com.example.lttha.a14110180_lethithao_foody.Model;

import java.io.Serializable;

/**
 * Created by lttha on 4/6/2017.
 */

public class DistrictModel implements Serializable {
    Integer id;
    String name;
    Integer cityId;

    public DistrictModel(Integer id,Integer cityId, String name) {
        this.id = id;
        this.name = name;
        this.cityId = cityId;
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


    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}

