package com.example.hspcadmin.htmlproject.activity.presenter

import com.example.hspcadmin.htmlproject.R

/**
 * Created by hspcadmin on 2018/11/22.
 */
class KotlinPresenter :AbstractPresenter(){
    var isAction:Boolean = false

    override fun initViewid(viewid: Int) {
        if(viewid == R.layout.kotlin_addvalue_layout)
            isAction = true
    }

    override fun setAction() {
        super.setAction()
        if(isAction){
//            mView!!.findViewById<>()
        }
    }

}