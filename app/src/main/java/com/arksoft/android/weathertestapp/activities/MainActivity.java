package com.arksoft.android.weathertestapp.activities;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.arksoft.android.weathertestapp.R;
import com.arksoft.android.weathertestapp.interfaces.GetWeather;
import com.arksoft.android.weathertestapp.model.LocationWeather;
import com.arksoft.android.weathertestapp.singleton.LocationsTon;
import com.arksoft.android.weathertestapp.singleton.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private GetWeather mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mService = RetrofitInstance.getRetrofitInstance().create(GetWeather.class);
        weatherFetchCurrentCity();
        weatherFetch();

    }


    public void weatherFetchCurrentCity(){

        /** Call the method with parameter in the interface to get the weather data and parse it*/
        Call<LocationWeather> call = mService.getCurrentLocationData("40.7308619", "-73.9871558", "es", "79b0dbfd7269f58d5788cc0f090f45d2");

        call.enqueue(new Callback<LocationWeather>() {
            @Override
            public void onResponse(Call<LocationWeather> call, Response<LocationWeather> response) {
                LocationsTon.get().setLocation(response.body());

            }

            @Override
            public void onFailure(Call<LocationWeather> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void weatherFetch(){

        Call<LocationWeather> call2 = mService.getNewYorkData();
        call2.enqueue(new Callback<LocationWeather>() {
            @Override
            public void onResponse(Call<LocationWeather> call, Response<LocationWeather> response) {
                LocationsTon.get().setNewYorkLocation(response.body());
            }

            @Override
            public void onFailure(Call<LocationWeather> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        Call<LocationWeather> call3 = mService.getTokyoData();
        call3.enqueue(new Callback<LocationWeather>() {
            @Override
            public void onResponse(Call<LocationWeather> call, Response<LocationWeather> response) {
                LocationsTon.get().setTokyoLocation(response.body());
            }
            @Override
            public void onFailure(Call<LocationWeather> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        Call<LocationWeather> call4 = mService.getRomeData();
        call4.enqueue(new Callback<LocationWeather>() {
            @Override
            public void onResponse(Call<LocationWeather> call, Response<LocationWeather> response) {
                LocationsTon.get().setRomeLocation(response.body());
            }
            @Override
            public void onFailure(Call<LocationWeather> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        Call<LocationWeather> call5 = mService.getMoscowData();
        call5.enqueue(new Callback<LocationWeather>() {
            @Override
            public void onResponse(Call<LocationWeather> call, Response<LocationWeather> response) {
                LocationsTon.get().setMoscowLocation(response.body());
            }
            @Override
            public void onFailure(Call<LocationWeather> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
