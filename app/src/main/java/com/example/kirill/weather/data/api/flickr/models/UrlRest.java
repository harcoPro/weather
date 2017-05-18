package com.example.kirill.weather.data.api.flickr.models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UrlRest {

    @SerializedName("url")
    public final List<PhotoUrlRest> urls;

    public UrlRest(List<PhotoUrlRest> urls) {
        this.urls = urls;
    }
}
