package com.example.kirill.weather.data.api.flickr.models;


import com.squareup.moshi.Json;


public class InfoRest {

    @Json(name = "urls")
    public final UrlsRest urlsRest;

    public InfoRest(UrlsRest urlsRest) {
        this.urlsRest = urlsRest;
    }
}
