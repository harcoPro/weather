package com.example.kirill.weather.data.api.weather.models;

import java.util.List;

public class WeatherByCityNameResponse extends WeatherResponse {

    public RestCoord coord;
    public List<RestWeather> weather;
    public String base;
    public RestMain main;
    public RestWind wind;
    public RestClouds clouds;
    public Long dt;
    public RestSys sys;
    public Long id;
    public String name;

}
