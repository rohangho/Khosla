package com.example.sampleweather.network;

import com.example.sampleweather.pojos.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkAPI {
    @GET("2.5/forecast")
    Call<BaseResponse> getListWeather(@Query("id") String id, @Query("appid") String key);

}
