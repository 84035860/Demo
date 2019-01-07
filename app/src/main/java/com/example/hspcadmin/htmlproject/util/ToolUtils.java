package com.example.hspcadmin.htmlproject.util;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;

import com.example.hspcadmin.htmlproject.activity.abstracts.AbstractActivity;
import com.example.hspcadmin.htmlproject.exception.AppException;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by wzheng on 2018/10/8.
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
        intent.putExtra(AbstractActivity.ClASS_NAME,ClassName);
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


    /**
     * 格式化时间
     *eg:yyyy-MM-dd HH:mm:ss
     * @param oldFormat:原格式
     * @param newFormat:转换后的新格式
     * @param dateStr:需要转换的字符串
     * @return:转换格式后的字符串
     */
    public static String changeDateFormat(String oldFormat, String newFormat, String dateStr) {
        SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat, Locale.getDefault());
        SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat, Locale.getDefault());
        try {
            return sdf2.format(sdf1.parse(dateStr));
        } catch (Exception e) {
            return dateStr;
        }
    }

    /**
     * 将yyyy-MM-dd格式字符串转换为时间
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }
    /**
     * 将HH:mm:ss格式字符串转换为时间
     *
     * @param strDate
     * @return
     */
    public static Date strToTime(String strDate){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

}
