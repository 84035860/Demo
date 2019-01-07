package com.example.hspcadmin.htmlproject.activity.abstracts;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.hspcadmin.htmlproject.R;
import com.example.hspcadmin.htmlproject.exception.AppException;

/**
 * Created by wzheng on 2018/6/28.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public AppException exception;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreenStatusBar();
        exception = AppException.getInstance();
        //设置日间夜间模式,用来换肤
        if (exception.getSharedPreferencesValue(AppException.SP_THEME).equals("1")) {
            //白天模式
            setTheme(R.style.ThemeDayTime);
        } else {
            //夜间模式
            setTheme(R.style.ThemeDayNight);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //设置全屏沉浸式状态栏
    private void setFullScreenStatusBar() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
