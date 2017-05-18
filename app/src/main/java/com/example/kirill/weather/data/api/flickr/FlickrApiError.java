package com.example.kirill.weather.data.api.flickr;

import android.content.res.Resources;

import com.example.kirill.weather.data.api.ApiError;

public class FlickrApiError extends ApiError {

    public FlickrApiError(int code, String msg) {
        super(code, msg);
    }

    public static ApiError from(int code, Resources resources) {
        //TODO(k.s) Implement handling Flickr api error here

        return ApiError.from(code, resources);
    }

}
