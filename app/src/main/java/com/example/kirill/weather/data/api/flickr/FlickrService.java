package com.example.kirill.weather.data.api.flickr;


import com.example.kirill.weather.data.api.flickr.models.InfoResponse;
import com.example.kirill.weather.data.api.flickr.models.SearchResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface FlickrService {

    @GET("/rest/?method=flickr.photos.search")
    Observable<SearchResponse> search(@Query("text") String city);

    @GET("/rest/?method=flickr.photos.getInfo")
    Observable<InfoResponse> info(@Query("photo_id") Integer photoId);

}
