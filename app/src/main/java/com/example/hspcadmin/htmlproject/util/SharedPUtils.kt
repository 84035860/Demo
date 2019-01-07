package com.example.hspcadmin.htmlproject.util

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.hspcadmin.htmlproject.exception.AppException

/**
 * Created by wzheng on 2018/11/14.
 */

object SharedPUtils{

    val KolinJson = "KOLIN_JSONARR"

    private val sp = PreferenceManager.getDefaultSharedPreferences(AppException.getInstance().applicationContext)as SharedPreferences

    /***
     * 设置kotlin里面列表数据
     *
     * @param str jsonarr数据
     */
    fun setKolinJsonVar(str : String){
        sp.edit().putString(KolinJson,str).apply()
    }

    /**
     * 获取kotlin里面列表数据
     *
     * @return str jsonarr数据
     */

    fun getKolinJsonVar():String{
        return sp.getString(KolinJson,"")
    }
}