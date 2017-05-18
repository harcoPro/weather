package com.example.kirill.weather.data.api.weather;

import android.content.res.Resources;

import com.example.kirill.weather.data.api.ApiError;

public class WeatherApiError extends ApiError {

    public WeatherApiError(Integer errorCode, String msg) {
        super(errorCode, msg);
    }

    public static ApiError from(int code, Resources resources) {
        //TODO(k.s) Implement handling Weather api error here
        return ApiError.from(code, resources);
    }

}
