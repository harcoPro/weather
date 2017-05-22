package com.example.kirill.weather.data.api.flickr.models;


import com.google.gson.annotations.SerializedName;
import com.squareup.moshi.Json;

public class InfoResponse extends FlickrResponse {

    @Json(name = "photo")
    public final InfoRest photo;

    public InfoResponse(String status,
                        Integer code,
                        String message,
                        InfoRest photo) {
        super(status, code, message);
        this.photo = photo;
    }
}
