package com.example.kirill.weather.data.api.flickr.models;


import com.squareup.moshi.Json;

public class PhotoUrlRest {

    @Json(name = "_content")
    public final String url;

    public PhotoUrlRest(String url) {
        this.url = url;
    }
}
