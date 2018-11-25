package com.example.hspcadmin.htmlproject.widget.mvp.model;

/**
/**
 * author:wzheng on 2016/8/10.
 */
public interface GridInfoModel {

    void loadWidgetInfo(OnLoadWidgetInfoListener onLoadWidgetInfoListener);

    interface OnLoadWidgetInfoListener {
        void loadSuccess(String str);

        void loadFailed(String s);
    }
}
