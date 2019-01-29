package com.arksoft.android.weathertestapp.interfaces;

import com.arksoft.android.weathertestapp.model.LocationWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetWeather {

        @GET("forecast?")

        Call<LocationWeather> getCurrentLocationData(
                @Query("lat") String lat,
                @Query("lon") String lon,
                @Query("lan") String language,
                @Query("APPID") String key);

        @GET("forecast?lat=40.7308619&lon=-73.9871558&lan=es&APPID=79b0dbfd7269f58d5788cc0f090f45d2")
        Call<LocationWeather>getNewYorkData();

        @GET("forecast?lat=35.6828387&lon=139.7594549&lan=es&APPID=79b0dbfd7269f58d5788cc0f090f45d2")
        Call<LocationWeather>getTokyoData();

        @GET("forecast?lat=41.894802&lon=12.4853384&lan=es&APPID=79b0dbfd7269f58d5788cc0f090f45d2")
        Call<LocationWeather>getRomeData();

        @GET("forecast?lat=55.7504461&lon=37.6174943&lan=es&APPID=79b0dbfd7269f58d5788cc0f090f45d2")
        Call<LocationWeather>getMoscowData();
}

