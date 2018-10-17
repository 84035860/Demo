package com.example.hspcadmin.htmlproject.kotlin

import android.os.Bundle
import com.example.hspcadmin.htmlproject.R
import com.example.hspcadmin.htmlproject.activity.abstracts.BaseActivity
import org.json.JSONArray

/**
 * Created by hspcadmin on 2018/10/15.
 */

const val s:String = ""//const 编译期

class KotlinActivity:BaseActivity(){
    var TextTvstr:String = "第一个"
    val TextTv:String = "第二个"
    var Age:String? = ""
    var Account:String = ""
    var jsonarr:JSONArray? = null
    var kotlinBean = KotlinBean("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kotlin_layout)
        InitView()
        InitView(TextTv)
        TextTvstr = InitData()
        jsonarr  = JSONArray("[{age = \"19\",name=\"李唐僧\"},{age = \"20\",name=\"求剑草\"}]")
        Test.sayMessage(jsonarr.toString())
        Account = kotlinBean.Account
    }

    fun InitView(){
        Age = "18"
    }

    fun InitView(str:String){

    }

    fun InitData():String{
        Age = "18"
        return Age as String
    }
}