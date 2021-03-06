package com.example.kirill.weather.data.preferences.types;

import android.content.SharedPreferences;

public class FloatPreference {
    private final SharedPreferences preferences;
    private final String key;
    private final float defaultValue;

    public FloatPreference(SharedPreferences preferences, String key) {
        this(preferences, key, 0.0f);
    }

    public FloatPreference(SharedPreferences preferences, String key, float defaultValue) {
        this.preferences = preferences;
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public float get() {
        return preferences.getFloat(key, defaultValue);
    }

    public boolean isSet() {
        return preferences.contains(key);
    }

    public void set(Float value) {
        preferences.edit().putFloat(key, value).apply();
    }

    public void delete() {
        preferences.edit().remove(key).apply();
    }
}
