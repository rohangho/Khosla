package com.example.sampleweather.viewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sampleweather.pojos.BaseResponse;
import com.example.sampleweather.repository.WeatherRepository;

public class WeatherViewModel extends ViewModel {

    private MutableLiveData<BaseResponse> mutableLiveData;
    private WeatherRepository weatherRepository;
    private String cityIds;
    private Application application;

    public WeatherViewModel(Application application, String cityIds) {
        this.application = application;
        this.cityIds = cityIds;
    }


    public void init() {

        weatherRepository = WeatherRepository.getInstance();
        mutableLiveData = weatherRepository.getWeatgerzresponse(cityIds, "19c582ab57628fee373c6c741f78d8d8");
    }


    public LiveData<BaseResponse> getWeatherRepo() {
        return mutableLiveData;
    }

}
