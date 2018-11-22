package com.example.hspcadmin.htmlproject.util;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;

import com.example.hspcadmin.htmlproject.activity.abstracts.AbstractActivity;
import com.example.hspcadmin.htmlproject.exception.AppException;

/**
 * Created by hspcadmin on 2018/10/8.
 */

public class ToolUtils {

    static Resources res = null;

    static {
        res = AppException.getInstance().getResources();
    }

    /**
     * 密度转换为像素值
     *
     * @param dp
     * @return
     */
    public static int dpToPx(float dp) {
        final float scale = res.getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static void starIntent(Class s){
        if(isFastClick()){
            return;
        }
        Intent intent = new Intent(AppException.getInstance().getApplicationContext(),s);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        AppException.getInstance().getApplicationContext().startActivity(intent);
    }

    /**'
     *
     *
     * */
    public static void starIntentBaseView(Class s,int ClassName){
        if(isFastClick()){
            return;
        }
        Intent intent = new Intent(AppException.getInstance().getApplicationContext(),s);
        intent.putExtra(AbstractActivity.ClASSNAME,ClassName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        AppException.getInstance().getApplicationContext().startActivity(intent);
    }

    /**
     * 防止一个控件被过快重复点击
     */
    private static long lastClickTime;

    public synchronized static boolean isFastClick() {
        return isFastClick(800);
    }

    public synchronized static boolean isFastClick(int intervals) {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < intervals) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static boolean isNull(String str){
        if(str == null || str.equals("")){
            return true;
        }
        return false;
    }

    /**
     * 屏幕高度
     * */
    public static int getHeight(Context context){
        return context.getResources().getDisplayMetrics().heightPixels;
    }
    /**
     * 屏幕宽度
     * */
    public static int  getWidth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
