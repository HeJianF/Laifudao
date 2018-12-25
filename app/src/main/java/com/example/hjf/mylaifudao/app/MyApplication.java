package com.example.hjf.mylaifudao.app;

import android.app.Application;
import android.content.Context;

/**
 * @author heJianfeng
 * @date 2018/12/19
 */
public class MyApplication extends Application {

    private static Context application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = getApplicationContext();
    }

    public static Context getApplication() {
        return application;
    }
}
