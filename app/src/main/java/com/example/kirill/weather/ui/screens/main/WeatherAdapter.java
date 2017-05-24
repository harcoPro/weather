package com.example.kirill.weather.ui.screens.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.kirill.weather.ui.screens.weather.WeatherFragment;

import java.util.ArrayList;
import java.util.List;


public class WeatherAdapter extends FragmentPagerAdapter {

    private List<String> items = new ArrayList<>();

    public WeatherAdapter(FragmentManager fm, ArrayList<String> cities) {
        super(fm);
        update(cities);
    }

    @Override
    public Fragment getItem(int position) {
        return WeatherFragment.start(items.get(position));
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return items.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void update(ArrayList<String> cities) {
        items.clear();
        items.addAll(cities);
        notifyDataSetChanged();
    }

    public int getLastItemPosition() {
        return items.size() - 1;
    }
}
