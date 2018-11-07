package com.example.hspcadmin.htmlproject.activity.presenter;

import com.example.hspcadmin.htmlproject.activity.abstracts.ViewContractType;

/**
 * Created by hspcadmin on 2018/11/7.
 */

public class AbstractPresenter {
    ViewContractType.ViewType viewType;

    public AbstractPresenter(ViewContractType.ViewType viewType){
        this.viewType = viewType;
        viewType.initView();
    }


}
