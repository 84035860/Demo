package com.example.hspcadmin.htmlproject.kotlin

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.example.hspcadmin.htmlproject.R
import com.example.hspcadmin.htmlproject.activity.abstracts.BaseActivity
import com.example.hspcadmin.htmlproject.util.SharedPUtils
import kotlinx.android.synthetic.main.kotlin_layout.*
import org.json.JSONArray
import java.util.*

/**
 * Created by hspcadmin on 2018/10/15.
 */

const val baseName:String = "kotlin"//const 编译期

class KotlinActivity:BaseActivity(){
    private var TextTvstr:String = "第一个"
    private val TextTv:String = "第二个"
    private var Age:String? = ""
    private var jsonarr:JSONArray? = null
    private val kotlinBeans = ArrayList<KotlinBean>()
    private val fragments = arrayOfNulls<String>(1)
    private var mAdapter: KotlinRecyclerAdapter? = null

    /**
     * @author 王政
     * @param Budle savedInstanceState
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kotlin_layout)
        InitView()
        InitView(TextTv)
        TextTvstr = InitData()
    }


    fun InitView(){
        mAdapter = KotlinRecyclerAdapter(this,null)
        kotlin_recycler.layoutManager = LinearLayoutManager(this)
        //分割线
        kotlin_recycler.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        //添加动画
        kotlin_recycler.itemAnimator = DefaultItemAnimator()
        kotlin_recycler.adapter = mAdapter
    }

    fun InitView(str:String){
//        if(ToolUtils.isNull(SharedPUtils.getKolinJsonVar())){
            jsonarr  = JSONArray(
                    "[{id = 0,account = \"吃饭饭\",time=\"2018年11月13日16:55:23\",check = false}," +
                            "{id = 0,account = \"吃饭饭\",time=\"2018年11月13日16:55:23\",check = false}," +
                            "{id = 1,account = \"值班\",time=\"2018年12月01日16:55:23\",check = false}," +
                            "{id = 0,account = \"吃饭饭\",time=\"2018年11月13日16:55:23\",check = false}," +
                            "{id = 0,account = \"吃饭饭\",time=\"2018年11月13日16:55:23\",check = false}]")
            SharedPUtils.setKolinJsonVar(jsonarr.toString())
//        }else{
//            jsonarr = JSONArray(SharedPUtils.getKolinJsonVar())
//        }

        for(index in 0 until jsonarr!!.length()){
            var id = jsonarr!!.getJSONObject(index).optInt("id")
            var account = jsonarr!!.getJSONObject(index).optString("account")
            var time = jsonarr!!.getJSONObject(index).optString("time")
            var check = jsonarr!!.getJSONObject(index).optBoolean("check")
            kotlinBeans.add(KotlinBean(id,account,time,check,0))
        }

        mAdapter!!.setNewData(kotlinBeans)
    }

    fun InitData():String{
        Age = "18"
        fragments[0] = "---数据数据"
        return Age as String
    }
}