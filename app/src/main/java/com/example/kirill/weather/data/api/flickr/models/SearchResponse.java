package com.example.kirill.weather.data.api.flickr.models;


import com.google.gson.annotations.SerializedName;

public class SearchResponse extends FlickrResponse {

    @SerializedName("photos")
    public final PhotoRest photos;

    public SearchResponse(String status, Integer code, String message, PhotoRest photos) {
        super(status, code, message);
        this.photos = photos;
    }
}
