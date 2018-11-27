package com.example.hspcadmin.htmlproject.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hspcadmin.htmlproject.R;

/**
 * 自定义Toast工具类
 */
public class ToastUtil {
    private static TextView showText;//显示文本信息
    private static Toast mToast;//土司弹窗

    /*初始化显示窗*/
    private static void initView(Context context) {
        showText = new TextView(context);
        showText.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.toast_corners));
        showText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        showText.setMinWidth(ToolUtils.dpToPx(220));
        showText.setPadding(10,ToolUtils.dpToPx(5),10,ToolUtils.dpToPx(5));
        showText.setGravity(Gravity.CENTER);
        showText.setTextSize(16f);
        mToast = new Toast(context.getApplicationContext());
        mToast.setView(showText);
    }

    /**
     * mToast 自定义 居中显示
     *
     * @param context
     * @param message
     * @param showTime 最小显示时间为2000毫秒
     */
    private static void ToastCenter(Context context, String message, int showTime) {
            if (context == null) return;
            if (showText == null) {
                initView(context);
            }
            try {
                showText.setText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mToast.setGravity(Gravity.CENTER, 0, 0);
            if (showTime < 2000) {
                showTime = Toast.LENGTH_SHORT;
            } else {
                showTime = Toast.LENGTH_LONG;
            }
            mToast.setDuration(showTime);
            mToast.show();
    }

    private static Handler handler = new Handler(Looper.getMainLooper());

    public static void ToastCenterHandler(final Context context, final String message, final int showTime) {
        if (context == null) return;
            handler.post(new Runnable() {
                @Override
                public void run() {
                    ToastCenter(context.getApplicationContext(), message, showTime);
                }
            });
    }

    public static void cancelToast(){
        if(null != mToast){
            mToast.cancel();
        }
    }
}
