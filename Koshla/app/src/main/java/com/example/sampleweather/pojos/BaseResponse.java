package com.example.sampleweather.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseResponse {

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private Double message;
    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private List<BaseList> list = null;
    @SerializedName("city")
    @Expose
    private SearchedCity city;


    public BaseResponse(String cod, Double message, Integer cnt, List<BaseList> list, SearchedCity city) {
        super();
        this.cod = cod;
        this.message = message;
        this.cnt = cnt;
        this.list = list;
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public List<BaseList> getList() {
        return list;
    }

    public void setList(List<BaseList> list) {
        this.list = list;
    }

    public SearchedCity getCity() {
        return city;
    }

    public void setCity(SearchedCity city) {
        this.city = city;
    }

}
