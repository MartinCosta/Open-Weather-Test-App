package com.arksoft.android.weathertestapp.singleton;


import com.arksoft.android.weathertestapp.model.LocationWeather;

public class LocationsTon {

    private static LocationsTon sLocationsTon;
    private static LocationWeather sCurrentLocation;
    private static LocationWeather sRomeLocation;
    private static LocationWeather sMoscowLocation;
    private static LocationWeather sTokyoLocation;
    private static LocationWeather sNewYorkLocation;

    private LocationsTon(){
    }

    public static LocationsTon get(){
        if(sLocationsTon == null){
            sLocationsTon = new LocationsTon();
        }
        return sLocationsTon;
    }

    public LocationWeather getNewYorkLocation() {
        return sNewYorkLocation;
    }

    public LocationWeather getRomeLocation() {
        return sRomeLocation;
    }

    public LocationWeather getMoscowLocation() {
        return sMoscowLocation;
    }

    public LocationWeather getTokyoLocation() {
        return sTokyoLocation;
    }

    public void setNewYorkLocation(LocationWeather newYorkLocation) {
        sNewYorkLocation = newYorkLocation;
    }

    public void setRomeLocation(LocationWeather romeLocation) {
        sRomeLocation = romeLocation;
    }

    public void setMoscowLocation(LocationWeather moscowLocation) {
        sMoscowLocation = moscowLocation;
    }

    public void setTokyoLocation(LocationWeather tokyoLocation) {
        sTokyoLocation = tokyoLocation;
    }

    public LocationWeather getCurrentLocation() {
        return sCurrentLocation;
    }

    public void setLocation (LocationWeather location){
        sCurrentLocation = location;
    }
}
