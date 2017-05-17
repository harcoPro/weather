package com.example.kirill.weather.ui.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.kirill.weather.AppComponent;

import javax.inject.Inject;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;


public abstract class BaseActivity extends MvpAppCompatActivity {

    @Inject
    NavigatorHolder navigatorHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle params = getIntent().getExtras();
        if (params != null) onExtractParams(params);

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        navigatorHolder.setNavigator(navigator());
        super.onResume();
    }

    @Override
    protected void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }

    protected void onExtractParams(@NonNull Bundle params) {
        // default no implementation
    }

    protected abstract void onCreateComponent(AppComponent appComponent);

    protected abstract Navigator navigator();

}
