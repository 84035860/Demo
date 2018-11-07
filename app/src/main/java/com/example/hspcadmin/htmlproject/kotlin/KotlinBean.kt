package com.example.hspcadmin.htmlproject.kotlin

import com.example.hspcadmin.htmlproject.activity.view.SettingHomeViewUi
import kotlin.reflect.KClass

/**
 * Created by hspcadmin on 2018/10/15.
 */
data class KotlinBean(
        //实体类
        var Account:String
)

object Test{
    //静态类
    fun sayMessage(msg:String){
        System.out.println("$msg")
        KotlinActivity::class.java
    }

    fun testClass(clazz: Class<SettingHomeViewUi>):String{
        return clazz.simpleName
    }

    fun testClass(clazz: KClass<KotlinActivity>){
        sayMessage(clazz.simpleName.toString())
    }
}