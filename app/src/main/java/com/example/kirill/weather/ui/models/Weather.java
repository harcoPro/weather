package com.example.kirill.weather.ui.models;


import com.example.kirill.weather.data.api.weather.models.WeatherByCityNameResponse;
import com.example.kirill.weather.ui.misc.Strings;

public class Weather {

    public final Long id;
    public final Long serverId;
    public final Long datetime;
    public final Float temp;
    public final Integer clouds;
    public final String description;
    public final Integer humidity;
    public final String city;


    public Weather(Long id,
                   Long serverId,
                   Long datetime,
                   Float temp,
                   Integer clouds,
                   String description,
                   Integer humidity,
                   String city) {
        this.id = id;
        this.serverId = serverId;
        this.datetime = datetime;
        this.temp = temp;
        this.clouds = clouds;
        this.description = description;
        this.humidity = humidity;
        this.city = city;
    }

    public static Weather fromRest(WeatherByCityNameResponse rest, String city) {
        String description = rest.weather.size() > 0 ? Strings.valueOrEmpty(rest.weather.get(0).description) : Strings.EMPTY;
        long time = System.currentTimeMillis();
        return new Weather(
                null,
                rest.id,
                time,
                rest.main.temp,
                rest.clouds.all,
                description,
                rest.main.humidity,
                city
        );
    }

}
