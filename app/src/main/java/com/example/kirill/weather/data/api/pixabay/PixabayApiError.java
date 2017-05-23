package com.example.kirill.weather.data.api.pixabay;

import android.content.res.Resources;

import com.example.kirill.weather.data.api.ApiError;

public class PixabayApiError extends ApiError {

    public PixabayApiError(int code, String msg) {
        super(code, msg);
    }

    public static ApiError from(int code, Resources resources) {
        //TODO(k.s) Implement handling api error here

        return ApiError.from(code, resources);
    }

}
