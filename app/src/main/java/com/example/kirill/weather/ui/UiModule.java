package com.example.kirill.weather.ui;


import com.example.kirill.weather.ApplicationScope;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

@Module
public class UiModule {

    @Provides
    @ApplicationScope
    Cicerone<Router> provideCicerone() {
        return Cicerone.create();
    }

    @Provides
    @ApplicationScope
    NavigatorHolder provideNavigatorHolder(Cicerone<Router> cicerone) {
        return cicerone.getNavigatorHolder();
    }

    @Provides
    @ApplicationScope
    Router provideRouter(Cicerone<Router> cicerone) {
        return cicerone.getRouter();
    }

}
