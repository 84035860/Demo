package com.example.hspcadmin.htmlproject.kotlin

import com.example.hspcadmin.htmlproject.activity.module.SettingHomeViewUi
import kotlin.reflect.KClass

/**
 * Created by hspcadmin on 2018/10/15.
 */
data class KotlinBean(
        var Account:String
)

object Test{
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