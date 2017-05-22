package com.example.kirill.weather.data.api.flickr;

import android.app.Application;

import com.example.kirill.weather.ApplicationScope;
import com.example.kirill.weather.data.api.ApiError;
import com.example.kirill.weather.data.api.flickr.models.FlickrResponse;
import com.example.kirill.weather.data.api.flickr.models.InfoRest;
import com.example.kirill.weather.data.api.flickr.models.PhotoRest;
import com.example.kirill.weather.data.api.flickr.models.PhotosRest;

import javax.inject.Inject;

import rx.Observable;

@ApplicationScope
public class FlickrApiService {

    private final Application app;
    private final FlickrService flickr;

    @Inject
    public FlickrApiService(Application app, FlickrService flickr) {
        this.app = app;
        this.flickr = flickr;
    }

    public Observable<PhotosRest> search(String city) {
        return flickr.search(city)
                .onErrorResumeNext(throwable -> Observable.error(ApiError.from(throwable, app.getResources())))
                .flatMap(this::handleApiResponse)
                .map(response -> response.photos)
                ;
    }

    public Observable<InfoRest> info(String photoId) {
        return flickr.info(photoId)
                .onErrorResumeNext(throwable -> Observable.error(ApiError.from(throwable, app.getResources())))
                .flatMap(this::handleApiResponse)
                .map(response -> response.photo)
                ;
    }

    private <R extends FlickrResponse> Observable<R> handleApiResponse(R response) {
        if (response.code != null) {
            return Observable.error(new FlickrApiError(response.code, response.message));
        } else {
            return Observable.just(response);
        }
    }
}
