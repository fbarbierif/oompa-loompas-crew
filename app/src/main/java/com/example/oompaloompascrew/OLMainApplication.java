package com.example.oompaloompascrew;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import static com.example.oompaloompascrew.utils.RealmUtils.initializeRealm;

public class OLMainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Initialize fresco, used to show images.
        Fresco.initialize(this);
        //Initialize realm, used to store and retrieve data from device db.
        initializeRealm(this);
    }

}
