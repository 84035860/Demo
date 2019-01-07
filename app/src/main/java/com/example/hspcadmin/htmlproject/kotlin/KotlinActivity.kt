package com.example.hspcadmin.htmlproject.kotlin

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.hspcadmin.htmlproject.R
import com.example.hspcadmin.htmlproject.activity.abstracts.AbstractActivity
import com.example.hspcadmin.htmlproject.util.SharedPUtils
import com.example.hspcadmin.htmlproject.util.ToolUtils
import com.example.hspcadmin.htmlproject.view.KotlinRefreshHeader
import kotlinx.android.synthetic.main.kotlin_layout.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

/**
 * Created by wzheng on 2018/10/15.
 */

const val baseName:String = "kotlin"//const 编译期

class KotlinActivity:AbstractActivity(),KotlinaddpopWindow.KotlinAddpopFace{

    private var TextTvstr:String = "第一个"
    private val TextTv:String = "第二个"
    private var Age:String? = ""
    private var jsonarr:JSONArray? = null
    private val kotlinBeans = ArrayList<KotlinBean>()
    private val fragments = arrayOfNulls<String>(1)
    private var mAdapter: KotlinRecyclerAdapter? = null
    private var kotlinHead: KotlinRefreshHeader? = null
    private var position:Int ?= 200
    private var positions:Int ?= 200

    /**
     * @author 王政
     * @param Budle savedInstanceState
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kotlin_layout)
        InitView()
        InitData(TextTv)
        TextTvstr = InitData()
    }


    fun InitView(){
        mAdapter = KotlinRecyclerAdapter(this,null)
        kotlin_recycler.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        //分割线
        kotlin_recycler.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        //添加动画
        kotlin_recycler.itemAnimator = DefaultItemAnimator()
        kotlin_recycler.adapter = mAdapter
        kotlinHead = KotlinRefreshHeader(this)
        refreshLayout.setEnableLoadMore(false)
        refreshLayout.setRefreshHeader(kotlinHead!!)
        refreshLayout.setHeaderHeight(220f)
        mAdapter!!.setOnItemClickListener(BaseQuickAdapter.OnItemClickListener { baseQuickAdapter, view, i ->
            kotlinHead!!.popWindow.show_(kotlinBeans.get(i))
        })
        Test.sayMessage((position === positions).toString())
    }

    fun InitData(str:String){
        if(ToolUtils.isNull(SharedPUtils.getKolinJsonVar())){
            jsonarr  = JSONArray(
                    "[{id = 0,account = \"模板\",time=\"2018年11月13日 16:55:23\",check = false}")
            SharedPUtils.setKolinJsonVar(jsonarr.toString())
        }else{
            jsonarr = JSONArray(SharedPUtils.getKolinJsonVar())
        }

        for(index in 0 until jsonarr!!.length()){
            try {
                /**
                 * 因为数据倒叙   显示需要反过来
                 * */
                var jsonobj: JSONObject = jsonarr!!.getJSONObject(jsonarr!!.length()-1- index)
                var id = jsonobj!!.optInt("id")
                var account = jsonobj!!.optString("account")
                var time = jsonobj!!.optString("time")
                var check = jsonobj!!.optBoolean("check")
                kotlinBeans.add(KotlinBean(id,account,time,check))
            } catch (e: Exception) { }
        }

        mAdapter!!.setNewData(kotlinBeans)
    }

    fun InitData():String{
        Age = "18"
        fragments[0] = "---数据数据"
        return Age as String
    }

    override fun Submit(bean: KotlinBean) {
        if(bean.Id!=-1){
            mAdapter!!.updateVar(bean)
            mAdapter!!.notifyDataSetChanged()
        }else{
            mAdapter!!.insertVar(bean)
            kotlin_recycler.scrollToPosition(0)
        }
    }
}

