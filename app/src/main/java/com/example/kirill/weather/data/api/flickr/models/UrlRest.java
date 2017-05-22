package com.example.kirill.weather.data.api.flickr.models;


import com.squareup.moshi.Json;

import java.util.List;

public class UrlRest {

    @Json(name = "url")
    public final List<PhotoUrlRest> urls;

    public UrlRest(List<PhotoUrlRest> urls) {
        this.urls = urls;
    }
}
