package com.example.kirill.weather.data.api.flickr.models;


import com.google.gson.annotations.SerializedName;

public class InfoResponse extends FlickrResponse {

    @SerializedName("photo")
    public final InfoRest photo;

    public InfoResponse(String status,
                        Integer code,
                        String message,
                        InfoRest photo) {
        super(status, code, message);
        this.photo = photo;
    }
}
