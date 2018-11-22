package com.example.hspcadmin.htmlproject.activity.presenter

import android.view.View
import com.example.hspcadmin.htmlproject.activity.abstracts.ViewContractType

/**
 * Created by hspcadmin on 2018/11/22.
 */
abstract class AbstractPresenter:ViewContractType.ViewAction{
    var mView:View? =null

    override fun initView(view: View?) {
        this.mView = view
    }

    override fun setAction() {
    }

}