package com.example.kirill.weather.ui.models;


import com.example.kirill.weather.data.api.flickr.models.InfoRest;

public class CityImage {

    public final String url;

    public CityImage(String url) {
        this.url = url;
    }

    public static CityImage from(InfoRest rest) {
        if (rest.urlsRest.urls.size() > 0) {
            return new CityImage(rest.urlsRest.urls.get(0).url);
        }
        return DEFAULT;
    }


    public static final CityImage DEFAULT = new CityImage("https://www.flickr.com/photos/150543395@N06/34738934896/");

}