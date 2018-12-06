package com.example.kienz.cooqueen;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import io.realm.Realm;

public class CooquenApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        Realm.init(this);
    }



}
