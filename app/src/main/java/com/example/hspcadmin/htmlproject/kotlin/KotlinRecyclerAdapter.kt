package com.example.hspcadmin.htmlproject.kotlin

import android.content.Context
import android.os.Build
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.hspcadmin.htmlproject.R
import com.example.hspcadmin.htmlproject.util.SharedPUtils
import com.example.hspcadmin.htmlproject.util.ToolUtils
import kotlinx.android.synthetic.main.kotlin_item_timecheck.view.*
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by hspcadmin on 2018/11/13.
 */
class KotlinRecyclerAdapter(mContext: Context,data : List<KotlinBean>?):BaseQuickAdapter<KotlinBean,BaseViewHolder>(R.layout.kotlin_item_timecheck,data){

    override fun convert(helper: BaseViewHolder, item: KotlinBean) {
        helper?.let {
            helper.itemView.item_kotlin_time_text.text = item.Time
            helper.itemView.item_kotlin_time_account.text = item.Account
            helper.itemView.item_kotlin_close.setOnClickListener({
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    removeVar(item.Id)
                }
            })
        }
//        Test.sayMessage(""+mContext+item)
    }

    /**
     * @account 删除列表数据
     * @author wzheng
     * @param id : 匹配本地数据id
     * */
    fun removeVar(id:Int){
        var jsonarr = JSONArray(SharedPUtils.getKolinJsonVar())
        for (index in 0 until jsonarr.length()){
            if(jsonarr.getJSONObject(index).getInt("id")==id){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                    jsonarr.remove(index)
                    SharedPUtils.setKolinJsonVar(jsonarr.toString())
                    break
            }
        }

        for (index in 0 until data.size){
            if(data.get(index).Id==id){
                data.removeAt(index)
                notifyItemRemoved(index)
                break
            }
        }
    }

    /**
     * @account 增加列表数据
     * @author wzheng
     * @param id : 匹配本地数据id  因为是数据显示倒序 index最后一条的数据  id总是最高
     * */
    fun insertVar(bean: KotlinBean){
        var jsonarr = JSONArray(SharedPUtils.getKolinJsonVar())
        var id:Int = 0
        try {
            if(!ToolUtils.isNull(jsonarr.getJSONObject(jsonarr.length()-1).optString("id"))){
                id =  jsonarr.getJSONObject(jsonarr.length()-1).optString("id").toInt()
                id++
            }
        } catch (e: Exception) { }
        var jsonobj = JSONObject()
        jsonobj.put("id",id)
        jsonobj.put("account",bean.Account)
        jsonobj.put("time",bean.Time)
        jsonobj.put("check",bean.Check)
        jsonarr.put(jsonobj)
        SharedPUtils.setKolinJsonVar(jsonarr.toString())
        data.add(0,bean)
        notifyItemInserted(0)
    }

    /**
     * @account 修改列表数据
     * @author wzheng
     * @param id : 匹配本地数据id
     * */
    fun updateVar(bean: KotlinBean){
        var jsonarr = JSONArray(SharedPUtils.getKolinJsonVar())
        var id:Int = bean.Id
        for (index in 0 until jsonarr.length()){
            if(jsonarr.getJSONObject(index).getInt("id")==id){
                jsonarr.getJSONObject(index).put("account",bean.Account)
                jsonarr.getJSONObject(index).put("time",bean.Time)
                jsonarr.getJSONObject(index).put("check",bean.Check)
                SharedPUtils.setKolinJsonVar(jsonarr.toString())
                break
            }
        }
        for (index in 0 until data.size){
            if(data.get(index).Id==id){
                data.get(index).Account = bean.Account
                data.get(index).Time = bean.Time
                data.get(index).Check = bean.Check
                break
            }
        }
        notifyDataSetChanged()
    }

}