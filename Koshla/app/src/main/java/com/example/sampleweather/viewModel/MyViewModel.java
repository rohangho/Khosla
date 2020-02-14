package com.example.sampleweather.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MyViewModel implements ViewModelProvider.Factory {
    private Application application;
    private String cityIds;

    public MyViewModel(Application mApplication, String cityIds) {

        this.application = mApplication;
        this.cityIds = cityIds;

    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new WeatherViewModel(application, cityIds);
    }
}
