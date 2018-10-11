package com.example.hspcadmin.htmlproject.exception;

import android.app.Application;
import android.content.Context;

/**
 * Created by zheng on 2018/6/28.
 */

public class AppException extends Application {
    private static AppException appException;
    private static Context mContext;
    @Override
    public void onCreate() {
        appException = this;
        super.onCreate();
        mContext = getApplicationContext();
        CrashCatchHandler.getInstance().init(mContext);
    }

    public static AppException getInstance() {
        return appException;
    }
}
