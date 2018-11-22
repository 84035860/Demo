package com.example.hspcadmin.htmlproject.activity.abstracts;

import android.view.View;

import com.example.hspcadmin.htmlproject.activity.presenter.AbstractPresenter;

/**
 * Created by hspcadmin on 2018/11/5.
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
