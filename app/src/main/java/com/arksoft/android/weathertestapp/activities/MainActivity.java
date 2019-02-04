package com.arksoft.android.weathertestapp.activities;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.arksoft.android.weathertestapp.interfaces.BroadcastKey;
import com.arksoft.android.weathertestapp.interfaces.GetWeather;
import com.arksoft.android.weathertestapp.model.LocationWeather;
import com.arksoft.android.weathertestapp.singleton.LocationsTon;
import com.arksoft.android.weathertestapp.singleton.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private GetWeather mService;
    private LocationManager locationManager;
    private Location onlyOneLocation;
    private String currentLat;
    private String currentLon;
    private final int LOCATION_PERMISSION_REQUEST_CODE = 1252;
    private IntentFilter myIntentFilter;
    private BroadcastReceiverMain mBroadcastReceiverMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mService = RetrofitInstance.getRetrofitInstance().create(GetWeather.class);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //set up receiver
        myIntentFilter = new IntentFilter();
        mBroadcastReceiverMain = new BroadcastReceiverMain();
        myIntentFilter.addAction(BroadcastKey.UPDATE_KEY_BROADCAST);
        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiverMain,
                new IntentFilter(BroadcastKey.UPDATE_KEY_BROADCAST));

        Toast.makeText(MainActivity.this, "Please Turn On your GPS if your are using an Emulator", Toast.LENGTH_LONG).show();

        //Request permissions for Localization and gps use.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= 23) { // Marshmallow
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            }
            else{
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0 ,0 ,this);
            }
        }
        else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0 ,0 ,this);
        }
        weatherFetch();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiverMain);
    }

    public void weatherFetchCurrentCity(){

        /** Call the method with parameter in the interface to get the weather data and parse it*/
        Call<LocationWeather> call = mService.getCurrentLocationData(currentLat, currentLon, "es", "79b0dbfd7269f58d5788cc0f090f45d2");

        call.enqueue(new Callback<LocationWeather>() {
            @Override
            public void onResponse(Call<LocationWeather> call, Response<LocationWeather> response) {
                LocationsTon.get().setLocation(response.body());
                Intent local123 = new Intent();
                local123.setAction(BroadcastKey.UPDATE_KEY_FINISHED);
                LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(local123);

                Intent i = new Intent(MainActivity.this, PagerActivity.class);
                //set flags to never create a second instance of the same activity
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
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

    /**
     *
     * Implement Localization Listener methods.
     */
    @Override
    public void onLocationChanged(Location location) {

        onlyOneLocation = location;
        currentLat = "" + onlyOneLocation.getLatitude();
        currentLon = "" + onlyOneLocation.getLongitude();
        locationManager.removeUpdates(this);
        weatherFetchCurrentCity();
    }
    @Override public void onStatusChanged(String provider, int status, Bundle extras) { }
    @Override public void onProviderEnabled(String provider) { }
    @Override public void onProviderDisabled(String provider) { }


    public class BroadcastReceiverMain extends BroadcastReceiver {

        public BroadcastReceiverMain(){
            super();
        }
        @Override public void onReceive(Context context, Intent intent) {

            if (intent.getAction() != null)
                {
                    switch (intent.getAction()) {

                        case BroadcastKey.UPDATE_KEY_BROADCAST:

                            weatherFetch();
                            weatherFetchCurrentCity();
                            break;
                    }
            }
        }
    }

}
