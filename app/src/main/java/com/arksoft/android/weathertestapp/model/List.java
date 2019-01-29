
package com.arksoft.android.weathertestapp.model;

public class List {

    private Integer dt;
    private com.arksoft.android.weathertestapp.model.Main main;
    private java.util.List<com.arksoft.android.weathertestapp.model.Weather> weather = null;
    private Clouds clouds;
    private com.arksoft.android.weathertestapp.model.Wind wind;
    private com.arksoft.android.weathertestapp.model.Sys sys;
    private String dt_txt;
    private com.arksoft.android.weathertestapp.model.Rain rain;

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public com.arksoft.android.weathertestapp.model.Main getMain() {
        return main;
    }

    public void setMain(com.arksoft.android.weathertestapp.model.Main main) {
        this.main = main;
    }

    public java.util.List<com.arksoft.android.weathertestapp.model.Weather> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<com.arksoft.android.weathertestapp.model.Weather> weather) {
        this.weather = weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public com.arksoft.android.weathertestapp.model.Wind getWind() {
        return wind;
    }

    public void setWind(com.arksoft.android.weathertestapp.model.Wind wind) {
        this.wind = wind;
    }

    public com.arksoft.android.weathertestapp.model.Sys getSys() {
        return sys;
    }

    public void setSys(com.arksoft.android.weathertestapp.model.Sys sys) {
        this.sys = sys;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    public com.arksoft.android.weathertestapp.model.Rain getRain() {
        return rain;
    }

    public void setRain(com.arksoft.android.weathertestapp.model.Rain rain) {
        this.rain = rain;
    }

}
