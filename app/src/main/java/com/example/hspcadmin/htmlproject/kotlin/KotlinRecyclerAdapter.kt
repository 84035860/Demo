package com.example.hspcadmin.htmlproject.kotlin

import android.content.Context
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.hspcadmin.htmlproject.R

/**
 * Created by hspcadmin on 2018/11/13.
 */
class KotlinRecyclerAdapter(mContext: Context,data : List<KotlinBean>?):BaseQuickAdapter<KotlinBean,BaseViewHolder>(R.layout.item_kotlin_timecheck,data){

    override fun convert(helper: BaseViewHolder, item: KotlinBean) {
        helper.setText(R.id.item_kotlin_time_text,""+item.Time)
        helper.setText(R.id.item_kotlin_time_account,item.Account)
        Test.sayMessage(""+mContext+item)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder {
        return super.onCreateViewHolder(parent, viewType)
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    override fun onBindViewHolder(holder: BaseViewHolder?, positions: Int) {
        super.onBindViewHolder(holder, positions)
    }
}