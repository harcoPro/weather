package com.example.kirill.weather;

import android.app.Application;


import com.example.kirill.weather.data.DataModule;
import com.example.kirill.weather.data.DataService;
import com.example.kirill.weather.data.api.ApiService;
import com.example.kirill.weather.data.preferences.SPModule;
import com.example.kirill.weather.data.preferences.qualifiers.LocaleQualifier;
import com.example.kirill.weather.data.preferences.types.StringPreference;
import com.example.kirill.weather.ui.UiModule;

import dagger.Component;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

@ApplicationScope
@Component(
        modules = {
                AppModule.class,
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
    ApiService apiServices();
    DataService dataService();

    Cicerone<Router> cicerone();
    NavigatorHolder navigatorHolder();
    Router router();

}
