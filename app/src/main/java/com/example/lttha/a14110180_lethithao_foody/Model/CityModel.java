package com.example.lttha.a14110180_lethithao_foody.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by lttha on 4/7/2017.
 */

public class CityModel implements Serializable {

    Integer cityID;
    String cityName;
    ArrayList<DistrictModel> districts;

    public CityModel(Integer cityID, String cityName) {
        this.cityID = cityID;
        this.cityName = cityName;
    }

    public Integer getId() {
        return cityID;
    }

    public void setId(Integer cityID) {
        this.cityID = cityID;
    }

    public String getName() {
        return cityName;
    }

    public void setName(String cityName) {
        this.cityName = cityName;
    }

    public ArrayList<DistrictModel> getDistricts() {
        return districts;
    }

    public void setDistricts(ArrayList<DistrictModel> districts) {
        this.districts = districts;
    }
}
