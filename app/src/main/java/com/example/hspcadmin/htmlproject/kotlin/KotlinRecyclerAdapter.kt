package com.example.hspcadmin.htmlproject.kotlin

import android.content.Context
import android.os.Build
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.hspcadmin.htmlproject.R
import com.example.hspcadmin.htmlproject.util.SharedPUtils
import kotlinx.android.synthetic.main.item_kotlin_timecheck.view.*
import org.json.JSONArray

/**
 * Created by hspcadmin on 2018/11/13.
 */
class KotlinRecyclerAdapter(mContext: Context,data : List<KotlinBean>?):BaseQuickAdapter<KotlinBean,BaseViewHolder>(R.layout.item_kotlin_timecheck,data){

    override fun convert(helper: BaseViewHolder, item: KotlinBean) {
        helper?.let {
            helper.itemView.item_kotlin_time_text.text = item.Time
            helper.itemView.item_kotlin_time_account.text = item.Account
            helper.itemView.item_kotlin_close.setOnClickListener({
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    settingVar(item.Id)
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
    fun settingVar(id:Int){
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

}