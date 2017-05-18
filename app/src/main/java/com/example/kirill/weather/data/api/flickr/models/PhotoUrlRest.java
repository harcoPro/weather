package com.example.kirill.weather.data.api.flickr.models;


import com.google.gson.annotations.SerializedName;

public class PhotoUrlRest {

    @SerializedName("_content")
    public final String url;

    public PhotoUrlRest(String url) {
        this.url = url;
    }
}
