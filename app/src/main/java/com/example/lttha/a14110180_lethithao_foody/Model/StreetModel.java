package com.example.lttha.a14110180_lethithao_foody.Model;

import java.io.Serializable;

/**
 * Created by lttha on 5/14/2017.
 */

public class StreetModel implements Serializable {
    Integer id, districtID;
    String name;

    public StreetModel(Integer id, Integer districtID, String name) {
        this.id = id;
        this.districtID = districtID;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDistrictID() {
        return districtID;
    }

    public void setDistrictID(Integer districtID) {
        this.districtID = districtID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
