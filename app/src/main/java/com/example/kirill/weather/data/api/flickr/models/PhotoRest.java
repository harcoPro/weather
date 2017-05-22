package com.example.kirill.weather.data.api.flickr.models;


import com.squareup.moshi.Json;

public class PhotoRest {

    @Json(name = "id")
    public final String id;

    public PhotoRest(String id) {
        this.id = id;
    }

}
