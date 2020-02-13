package com.example.sampleweather.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sampleweather.R;

public class WeatherActivity extends AppCompatActivity {
    int ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        ids = getIntent().getIntExtra("cityIds", 0);
        Toast.makeText(this, Integer.toString(ids), Toast.LENGTH_SHORT).show();

    }
}
