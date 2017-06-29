package com.artie.gourmand;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by ANFIELD on 27/6/2560.
 */

public class GourmandApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        JodaTimeAndroid.init(this);
        Contextor.getInstance().init(getApplicationContext());
    }

}
