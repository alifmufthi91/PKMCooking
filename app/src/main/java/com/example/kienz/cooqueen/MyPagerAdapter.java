package com.example.kienz.cooqueen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MyPagerAdapter extends FragmentStatePagerAdapter  {
    public MyPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new tab1();
            case 1: return new tab2();
            case 2: return new tab3();
        }
        return null;
    }

    @Override

    public int getCount() {
        return 3;
    }

    @Override

    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Home";
            case 1: return "Search";
            case 2: return "Meal Plan";
            default: return null;
        }
    }
}
