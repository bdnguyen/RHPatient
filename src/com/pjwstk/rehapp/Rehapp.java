package com.pjwstk.rehapp;

import android.app.Application;
import android.content.Context;

public class Rehapp extends Application {
    private static Context context;

    public void onCreate(){
        super.onCreate();
        Rehapp.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return Rehapp.context;
    }
}
