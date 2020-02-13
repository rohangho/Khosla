package com.example.sampleweather.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.sampleweather.R;
import com.example.sampleweather.pojos.CityIds;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    AppCompatButton submit;
    int[] ids;
    int idSelected = 0;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoCompleteTextView = findViewById(R.id.appCompatEditText);
        submit = findViewById(R.id.buttonSubmit);

        submit.setOnClickListener(v -> {
            if (autoCompleteTextView.getText().toString() == null || autoCompleteTextView.getText().toString().length() == 0)
                Toast.makeText(getApplicationContext(), "Please fill the name of the city", Toast.LENGTH_SHORT).show();
            else {
                Intent intent = new Intent(getApplication(), WeatherActivity.class);
                intent.putExtra("cityIds", ids[idSelected]);
                startActivity(intent);
            }
        });

        showSuggesstion();

    }

    /**
     * This Function Show suggestion on the AutoComplete TExtview
     */
    private void showSuggesstion() {
        Observable.fromCallable(() -> {

                    InputStream raw = getResources().openRawResource(R.raw.city_list);
                    Reader rd = new BufferedReader(new InputStreamReader(raw));
                    Gson gson = new Gson();
                    CityIds[] cityIds = gson.fromJson(rd, CityIds[].class);
                    String[] nameOfCity = new String[cityIds.length];
                    ids = new int[cityIds.length];
                    for (int i = 0; i < cityIds.length; i++) {
                        nameOfCity[i] = cityIds[i].getName().toLowerCase();
                        ids[i] = cityIds[i].getId();
                    }
                    return nameOfCity;

                }
        )
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> Log.e("Exception", throwable.getMessage()))
                .subscribe((nameCity) -> {

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                            android.R.layout.select_dialog_item, nameCity);
                    autoCompleteTextView.setThreshold(1);
                    autoCompleteTextView.setAdapter(arrayAdapter);
                    autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                            for (int i = 0; i < nameCity.length; i++) {
                                if (autoCompleteTextView.getText().toString().equalsIgnoreCase(nameCity[i])) {
                                    idSelected = i;
                                    break;
                                }
                            }

                        }
                    });
                }, (throwable) -> Log.e("Exception", throwable.getMessage()));
    }
}
