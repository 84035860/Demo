package com.example.hspcadmin.htmlproject.kotlin

import android.widget.Toast
import com.example.hspcadmin.htmlproject.activity.view.SettingHomeViewUi
import com.example.hspcadmin.htmlproject.exception.AppException
import kotlin.reflect.KClass

/**
 * Created by wzheng on 2018/10/15.
 */
data class KotlinBean(
        //实体类
        var Id:Int,
        var Account:String,
        var Time :String,
        var Check :Boolean
)

object Test{
    //静态类
    fun sayMessage(msg:String){
        Toast.makeText(AppException.getInstance().applicationContext,"$msg", Toast.LENGTH_SHORT).show()
        KotlinActivity::class.java
    }

    fun testClass(clazz: Class<SettingHomeViewUi>):String{
        return clazz.simpleName
    }

    fun testClass(clazz: KClass<KotlinActivity>){
        sayMessage(clazz.simpleName.toString())
    }
}