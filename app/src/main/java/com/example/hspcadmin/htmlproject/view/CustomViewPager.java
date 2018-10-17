package com.example.hspcadmin.htmlproject.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

/**
 * 自定义ViewPager，主要用于控制是否支持左右滑动
 */
public class CustomViewPager extends ViewPager {
    private List<View> views;

    //判断是否支持滑动
    private boolean isCanScroll = false;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return isCanScroll ? super.onTouchEvent(arg0) : false;
    }

    public List<View> getViews() {
        return views;
    }

    public void setViews(List<View> views) {
        this.views = views;
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item,false);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return isCanScroll ? super.onInterceptTouchEvent(arg0) : false;
    }
}