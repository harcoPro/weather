package com.example.kirill.weather.data.api.flickr.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class PhotosRest {

    @SerializedName("total")
    public final int total;

    @SerializedName("photo")
    public final List<PhotoRest> photos;


    public PhotosRest(int total, List<PhotoRest> photos) {
        this.total = total;
        this.photos = photos;
    }
}
