package com.haipo.basemvpdemo;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

/**
 * PackageName: com.haipo.basemvpdemo
 * FileName：   BaseApplication
 * Created by haipo on 2016/12/15.
 */

public class BaseApplication extends Application {

    private static BaseApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
    }

    public static Context getAppContext() {
        return baseApplication;
    }
    @SuppressWarnings("unused")
    public static Resources getAppResources() {
        return baseApplication.getResources();
    }
}
