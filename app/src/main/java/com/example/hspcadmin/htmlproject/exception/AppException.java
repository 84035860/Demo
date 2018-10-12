package com.example.hspcadmin.htmlproject.exception;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by zheng on 2018/6/28.
 */

public class AppException extends Application {
    private static AppException appException;
    private static Context mContext;
    private static SharedPreferences sp;

    //theme 1 白天  2黑夜
    public final static String SP_THEME = "theme";
    @Override
    public void onCreate() {
        appException = this;
        super.onCreate();
        mContext = getApplicationContext();
        initData();
        initSp();
    }

    public static AppException getInstance() {
        return appException;
    }

    private void initData(){
//        CrashCatchHandler.getInstance().init(mContext);

    }

    private void initSp(){
        sp = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    public SharedPreferences getSp() {
        return sp;
    }

    /***
     * 保存设置
     */
    public void setSharedPreferencesValue(String TRADE_ODER,String oder) {
        sp.edit().putString(TRADE_ODER, oder).apply();
    }

    /**
     * 获取保存
     */
    public String getSharedPreferencesValue(String TRADE_ODER) {
        return sp.getString(TRADE_ODER, "");
    }

}
