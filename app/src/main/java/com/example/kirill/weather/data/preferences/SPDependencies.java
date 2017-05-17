package com.example.kirill.weather.data.preferences;

import com.example.kirill.weather.data.preferences.qualifiers.LocaleQualifier;
import com.example.kirill.weather.data.preferences.qualifiers.TestQualifiers;
import com.example.kirill.weather.data.preferences.types.StringPreference;


public interface SPDependencies {

    @TestQualifiers
    StringPreference testPreferences();

    @LocaleQualifier
    String localePreferences();

}
