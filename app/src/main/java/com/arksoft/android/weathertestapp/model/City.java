
package com.arksoft.android.weathertestapp.model;

public class City {

    private Integer id;
    private String name;
    private com.arksoft.android.weathertestapp.model.Coord coord;
    private String country;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public com.arksoft.android.weathertestapp.model.Coord getCoord() {
        return coord;
    }

    public void setCoord(com.arksoft.android.weathertestapp.model.Coord coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
