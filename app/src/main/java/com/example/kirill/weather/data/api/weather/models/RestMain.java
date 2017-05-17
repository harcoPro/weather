package com.example.kirill.weather.data.api.weather.models;

import com.google.gson.annotations.SerializedName;

public class RestMain {

    public Float temp;
    public Float pressure;
    public Integer humidity;
    @SerializedName("temp_min")
    public Float tempMin;
    @SerializedName("temp_max")
    public Float tempMax;
    @SerializedName("sea_level")
    public Float seaLevel;
    @SerializedName("grnd_level")
    public Float grndLevel;

}
