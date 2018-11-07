package com.example.hspcadmin.htmlproject.activity.abstracts;

import android.view.View;

/**
 * Created by hspcadmin on 2018/11/5.
 */

public interface ViewContractType {

    interface ViewCont{
        void addView(View view);
    }

    interface ViewType{

        void initView();

        void errorView();

        void exitView();

        void updataView();
    }
}
