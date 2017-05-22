package com.example.kirill.weather;

import android.app.Application;

import com.example.kirill.weather.data.DataModule;
import com.example.kirill.weather.data.DataService;
import com.example.kirill.weather.data.api.flickr.FlickrApiModule;
import com.example.kirill.weather.data.api.flickr.FlickrService;
import com.example.kirill.weather.data.api.weather.WeatherModule;
import com.example.kirill.weather.data.api.weather.WeatherService;
import com.example.kirill.weather.data.preferences.SPModule;
import com.example.kirill.weather.ui.UiModule;

import dagger.Component;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

@ApplicationScope
@Component(
        modules = {
                AppModule.class,
                WeatherModule.class,
                FlickrApiModule.class,
                DataModule.class,
                SPModule.class,
                UiModule.class
        }
)
public interface AppComponent extends CommonDependencies {

    /**
     * An initializer that creates the graph from an application
     */
    final class Initializer {
        private Initializer() {
            // no instances.
        }

        static AppComponent init(App application) {
            return DaggerAppComponent.builder()
                    .appModule(new AppModule(application))
                    .build();
        }
    }

    Application application();
    WeatherService weatherService();
    FlickrService flickrService();
    DataService dataService();

    Cicerone<Router> cicerone();
    NavigatorHolder navigatorHolder();
    Router router();

}
