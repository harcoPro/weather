package com.example.kirill.weather.data.api.flickr.models;


import com.google.gson.annotations.SerializedName;

public class FlickrResponse {

    @SerializedName("stat")
    public final String status;

    @SerializedName("code")
    public final Integer code;

    @SerializedName("message")
    public final String message;

    public FlickrResponse(String status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
