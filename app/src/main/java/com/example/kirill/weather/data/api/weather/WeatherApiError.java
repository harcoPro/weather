package com.example.kirill.weather.data.api.weather;

public class WeatherApiError extends Throwable {

    public Integer errorCode;
    public String msg;

    public WeatherApiError(String msg) {
        this(0, msg);
    }

    public WeatherApiError(Integer errorCode, String msg) {
        this.msg = msg;
        this.errorCode = errorCode;
    }

}
