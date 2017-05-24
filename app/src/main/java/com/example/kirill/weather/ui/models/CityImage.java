package com.example.kirill.weather.ui.models;


import android.text.TextUtils;

import com.example.kirill.weather.data.api.pixabay.models.ImageRest;

public class CityImage {

    public final String url;

    public CityImage(String url) {
        this.url = url;
    }

    public static CityImage from(ImageRest rest) {
        if (!TextUtils.isEmpty(rest.webformatURL)) {
            return new CityImage(rest.webformatURL);
        }
        return DEFAULT;
    }


    public static final CityImage DEFAULT = new CityImage("http://www.ehypermart.in/0/images/frontend/image-not-found.png");

}