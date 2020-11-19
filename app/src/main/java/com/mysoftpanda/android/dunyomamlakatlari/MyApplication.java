package com.mysoftpanda.android.dunyomamlakatlari;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
//        OneSignal.startInit(this).setNotificationOpenedHandler(new MyNotificationOpenedHandler()).setNotificationReceivedHandler(new MyNotificationReceivedHandler()).init();
    }
}
