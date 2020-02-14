package com.example.sampleweather.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.sampleweather.R;
import com.example.sampleweather.pojos.BaseResponse;
import com.example.sampleweather.pojos.BottomTemp;
import com.example.sampleweather.viewModel.MyViewModel;
import com.example.sampleweather.viewModel.WeatherViewModel;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WeatherActivity extends AppCompatActivity {
    int ids;

    WeatherViewModel weatherViewModel;
    private AppCompatTextView temperature;
    private AppCompatTextView date;
    private AppCompatImageView iconTemperature;
    private AppCompatButton showMore;
    private List<BottomTemp> alltemp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        ids = getIntent().getIntExtra("cityIds", 0);
        iconTemperature = findViewById(R.id.icon);
        temperature = findViewById(R.id.appCompatTextView);
        date = findViewById(R.id.date);
        showMore = findViewById(R.id.showMore);

        MyViewModel factory = new MyViewModel(this.getApplication(), Integer.toString(ids));
        weatherViewModel = ViewModelProviders.of(this, factory).get(WeatherViewModel.class);
        weatherViewModel.init();
        weatherViewModel.getWeatherRepo().observe(this, this::getResponsFromLiveData);

        showMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               
            }
        });


    }

    /**
     * This function
     *
     * @param baseResponse
     */
    private void getResponsFromLiveData(BaseResponse baseResponse) {
        Glide.with(this).load("http://openweathermap.org/img/wn/" + baseResponse.getList().get(0).getWeather().get(0).getIcon() + "@2x" + ".png").into(iconTemperature);
        temperature.setText(new DecimalFormat("##.##").format(baseResponse.getList().get(0).getMain().getTemp() - 273.15) + " \u2103");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            String ds2 = sdf2.format(sdf1.parse(baseResponse.getList().get(0).getDtTxt()));
            date.setText(ds2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        getFiveDays(baseResponse);


    }

    /**
     * Get the weather of the 5 days
     *
     * @param baseResponse
     */
    private void getFiveDays(BaseResponse baseResponse) {

        Observable.fromCallable(() -> {

                    List<BottomTemp> alltemp = new ArrayList<>();
                    for (int i = 0; i < baseResponse.getList().size(); i = i + 8) {
                        BottomTemp bottomTemp = new BottomTemp(baseResponse.getList().get(0).getWeather().get(0).getIcon() + "@2x" + ".png",
                                new DecimalFormat("##.##").format(baseResponse.getList().get(i).getMain().getTemp() - 273.15),
                                baseResponse.getList().get(0).getDtTxt());
                        alltemp.add(bottomTemp);
                        if (alltemp.size() > 0)
                            break;
                    }

                    return alltemp;


                }
        )
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> Log.e("Exception", throwable.getMessage()))
                .subscribe((alltemp) -> {
                    alltemp = alltemp;
                }, (throwable) -> Log.e("Exception", throwable.getMessage()));
    }

}
