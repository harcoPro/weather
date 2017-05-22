package com.example.kirill.weather.data.api.flickr.models;


import com.squareup.moshi.Json;

public class SearchResponse extends FlickrResponse {

    @Json(name = "photos")
    public final PhotosRest photos;

    public SearchResponse(String status, Integer code, String message, PhotosRest photos) {
        super(status, code, message);
        this.photos = photos;
    }
}
