package com.example.kirill.weather.ui.models;


public class WeatherWithImage {

    public final Weather weather;
    public final CityImage cityImage;


    public WeatherWithImage(Weather weather, CityImage cityImage) {
        this.weather = weather;
        this.cityImage = cityImage;
    }

}
