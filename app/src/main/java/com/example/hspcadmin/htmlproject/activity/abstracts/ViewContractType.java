package com.example.hspcadmin.htmlproject.activity.abstracts;

import android.view.View;

import com.example.hspcadmin.htmlproject.activity.presenter.AbstractPresenter;

/**
 * Created by hspcadmin on 2018/11/5.
 *
 * 设计模式  页面加载进度控制
 */

public interface ViewContractType {

    interface ViewAction{
        void initView(View view);
        void initViewid(int viewid);
        void setAction();
    }

    interface ViewType{

        void initView();

        void errorView();

        void exitView();

        void updataView(AbstractPresenter viewAction);
    }
}
