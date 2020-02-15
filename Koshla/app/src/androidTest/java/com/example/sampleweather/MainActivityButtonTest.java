package com.example.sampleweather;

import android.support.test.runner.AndroidJUnit4;

import androidx.test.rule.ActivityTestRule;

import com.example.sampleweather.activity.MainActivity;

import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
class MainActiTvityButtonTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTesting =
            new ActivityTestRule<>(MainActivity.class);
}
