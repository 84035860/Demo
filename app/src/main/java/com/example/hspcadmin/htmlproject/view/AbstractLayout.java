package com.example.hspcadmin.htmlproject.view;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by hspcadmin on 2018/10/10.
 */

public abstract class AbstractLayout extends LinearLayout{


    public AbstractLayout(Context context) {
        super(context);
    }

    public AbstractLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AbstractLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract void onResume();

    public abstract void onPause();
}
