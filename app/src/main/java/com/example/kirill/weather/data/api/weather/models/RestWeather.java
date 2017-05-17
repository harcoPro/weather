package com.example.kirill.weather.data.api.weather.models;

import com.google.gson.annotations.SerializedName;

public class RestWeather {

    @SerializedName("id")
    public Long serverId;
    public String main;
    public String description;
    public String icon;

    public RestWeather(Long id, String main, String description, String icon) {
        this.serverId = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

}
