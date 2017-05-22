package com.example.kirill.weather.data.api.flickr.models;

import com.squareup.moshi.Json;

import java.util.List;


public class PhotosRest {

    @Json(name = "total")
    public final String total;

    @Json(name = "photo")
    public final List<PhotoRest> photos;


    public PhotosRest(String total, List<PhotoRest> photos) {
        this.total = total;
        this.photos = photos;
    }
}
