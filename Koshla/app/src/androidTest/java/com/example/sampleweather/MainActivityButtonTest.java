package com.example.sampleweather;


import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.sampleweather.activity.WeatherActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityButtonTest {

    @Rule
    public ActivityTestRule<WeatherActivity> activityRule =
            new ActivityTestRule<>(WeatherActivity.class);

    @Test
    public void listGoesOverTheFold() {
        Espresso.onView(withId(R.id.showMore)).perform(click());
        Espresso.onView(withText(R.id.recycler)).check(matches(isDisplayed()));

    }

}
