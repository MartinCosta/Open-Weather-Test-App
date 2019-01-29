package com.arksoft.android.weathertestapp.singleton;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";

    /**
     * Create an instance of Retrofit object
     * */
    public static Retrofit getRetrofitInstance() {
        Gson gson = new Gson();
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    // We create a private default constructor so we assure always there is only one instances of
    //retrofit created on the app
    private RetrofitInstance(){

    }
}