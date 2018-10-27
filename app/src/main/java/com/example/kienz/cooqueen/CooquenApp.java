package com.example.kienz.cooqueen;

import android.app.Application;

import io.realm.Realm;

public class CooquenApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
