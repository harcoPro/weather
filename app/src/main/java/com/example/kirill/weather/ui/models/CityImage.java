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


    public static final CityImage DEFAULT = new CityImage("https://thumb7.shutterstock.com/display_pic_with_logo/73964/112514351/stock-photo-las-vegas-august-musical-fountains-at-bellagio-hotel-casino-on-august-in-las-vegas-112514351.jpg");

}