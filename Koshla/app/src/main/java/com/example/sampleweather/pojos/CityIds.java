package com.example.sampleweather.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityIds {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("coord")
    @Expose
    private Coordinates coord;


    public CityIds(Integer id, String name, String country, Coordinates coord) {

        this.id = id;
        this.name = name;
        this.country = country;
        this.coord = coord;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Coordinates getCoord() {
        return coord;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

}
