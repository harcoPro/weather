package com.example.kirill.weather.data.api.flickr.models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InfoRest {

    @SerializedName("urls")
    public final List<UrlRest> urls;

    public InfoRest(List<UrlRest> urls) {
        this.urls = urls;
    }
}
