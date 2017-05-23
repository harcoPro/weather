package com.example.kirill.weather.data.api.pixabay.models;


import com.squareup.moshi.Json;

import java.util.List;

public class PixabayResponse {

    @Json(name = "total")
    public Integer total;

    @Json(name = "totalHits")
    public Integer totalHits;

    @Json(name = "hits")
    public List<ImageRest> images;

}
