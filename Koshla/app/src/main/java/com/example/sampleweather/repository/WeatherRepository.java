package com.example.sampleweather.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.sampleweather.network.NetworkAPI;
import com.example.sampleweather.network.RetrofitService;
import com.example.sampleweather.pojos.BaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {

    private static WeatherRepository weatherRepository;
    private NetworkAPI networkAPI;

    public WeatherRepository() {
        networkAPI = RetrofitService.createService(NetworkAPI.class);
    }

    public static WeatherRepository getInstance() {
        if (weatherRepository == null) {
            weatherRepository = new WeatherRepository();
        }
        return weatherRepository;
    }

    public MutableLiveData<BaseResponse> getWeatgerzresponse(String cityId, String key) {

        MutableLiveData<BaseResponse> listWeather = new MutableLiveData<>();
        networkAPI.getListWeather(cityId, key).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful())
                    listWeather.setValue(response.body());

            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                listWeather.setValue(null);
            }
        });

        return listWeather;

    }
}
