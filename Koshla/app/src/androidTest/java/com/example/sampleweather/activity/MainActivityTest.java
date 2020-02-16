package com.example.sampleweather.activity;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.sampleweather.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mainActivity = null;

    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch() {
        View v = mainActivity.findViewById(R.id.appCompatEditText);
        assertNotNull(v);
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}