package com.example.hspcadmin.htmlproject.kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import com.example.hspcadmin.htmlproject.R

/**
 * Created by hspcadmin on 2018/11/15.
 */

class KotlinAddTimeDataPop (mContext : Context): PopupWindow(){

    init {
        var view = LayoutInflater.from(mContext).inflate(R.layout.kotlinadd_timedata_layout,null) as View

//        view.findViewById<TextView>(R.id.item_kotlin_time_account).
    }
}