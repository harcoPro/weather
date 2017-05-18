package com.example.kirill.weather.data.api.flickr.models;


import com.google.gson.annotations.SerializedName;

public class PhotoRest {

    @SerializedName("id")
    public final String id;

    public PhotoRest(String id) {
        this.id = id;
    }




}
