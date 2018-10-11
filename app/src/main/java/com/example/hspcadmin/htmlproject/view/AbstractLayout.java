package com.example.hspcadmin.htmlproject.view;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.hspcadmin.htmlproject.R;
import com.example.hspcadmin.htmlproject.exception.AppException;
import com.example.hspcadmin.htmlproject.util.changeskin.ColorUiInterface;

/**
 * Created by hspcadmin on 2018/10/10.
 */
public abstract class AbstractLayout extends LinearLayout implements ColorUiInterface{
    public AppException exception;
    public int[] Typeds = new int[]{
            R.attr.bg_color,
            R.attr.font_color,
            R.attr.line_color,
            R.attr.edit_color,
            R.attr.title_font_color,
            R.attr.title_bg_color,
            R.attr.button_bg_color,
            R.attr.button_font_color,
            R.attr.back_img
    };

    public AbstractLayout(Context context) {
        super(context);
    }

    public AbstractLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        exception = AppException.getInstance();
    }

    public abstract void onResume();

    public abstract void onPause();

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setTheme() {
        Log.e("---------setTheme","setTheme");

        
    }
}
