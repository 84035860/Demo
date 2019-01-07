package com.example.hspcadmin.htmlproject.kotlin

import android.app.Activity
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import com.example.hspcadmin.htmlproject.R
import com.example.hspcadmin.htmlproject.util.ToastUtil
import com.example.hspcadmin.htmlproject.util.ToolUtils
import com.example.hspcadmin.htmlproject.view.datepicker.WheelMain


/**
 * Created by wzheng on 2018/11/22.
 */
class KotlinaddpopWindow(mContext: Context) :PopupWindow(){

    private var mView: View? = null
    private var mContext:Context? = mContext
    private var isshowimm:Boolean = false
    private var wheelMain: WheelMain? = null
    private var data_edit:EditText?=null
    private var data_date:TextView?=null
    private var data_time:TextView?=null

    interface KotlinAddpopFace{
        fun Submit(bean: KotlinBean)
    }

    fun initView():KotlinaddpopWindow{
        mView = LayoutInflater.from(mContext).inflate(R.layout.kotlin_addvalue_layout,null)
        contentView = mView
        data_edit = contentView.findViewById(R.id.add_timedata_edit)
        data_date = contentView.findViewById(R.id.add_timedata_date)
        data_time = contentView.findViewById(R.id.add_timedata_time)
        /**
         * 弹出输入框
         * */

        data_edit!!.setOnClickListener(View.OnClickListener {
            view: View? ->
            val imm = mContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            view!!.requestFocus()
            if (isshowimm){
                imm.hideSoftInputFromWindow(view!!.windowToken,0)
            }else{
                imm.showSoftInput(view!!,0)
            }
            isshowimm = !isshowimm
        })
        /**
         * 时间 2018年11月27日
         * */
        data_date!!.setOnClickListener(View.OnClickListener {
            wheelMain = WheelMain(mContext,
                    data_date!!.text as String?
                    ,View.OnClickListener {
                data_date!!.text = wheelMain!!.time
            })
            wheelMain!!._show()
        })
        /**
         * 时间 10:26:49
         * */
        data_time!!.setOnClickListener(View.OnClickListener {
            wheelMain = WheelMain(mContext,
                    data_time!!.text as String?
                    ,View.OnClickListener {
                data_time!!.text = wheelMain!!.time
            })
            wheelMain!!._show()
        })
        /**
         * 确定
         * submit
         * */
        contentView.findViewById<ImageView>(R.id.next_img).setOnClickListener(View.OnClickListener {
            if(!ToolUtils.isFastClick() && mContext is KotlinAddpopFace){
                if(ToolUtils.isNull(data_edit!!.text.toString())){
                    ToastUtil.ToastCenterHandler(mContext,"内容不可为空哦",1000)
                    return@OnClickListener
                }

                if(contentView!!.getTag()!=null){
                    (mContext as KotlinAddpopFace).Submit(KotlinBean(
                            contentView!!.getTag() as Int,
                            data_edit!!.text.toString(),
                            data_date!!.text.toString() +" "+ data_time!!.text.toString(),
                            false
                    ))
                }else{
                    (mContext as KotlinAddpopFace).Submit(KotlinBean(
                            -1,
                            data_edit!!.text.toString(),
                            data_date!!.text.toString() +" "+ data_time!!.text.toString(),
                            false
                    ))
                }
            }
            dismiss_()
        })
        this.setOnDismissListener {
            data_edit!!.setText("")
            contentView!!.setTag(null) }
        this.width = ToolUtils.getWidth(mContext)
        this.height = ToolUtils.dpToPx(220f)
        setFocusable(true)
        // 设置允许在外点击消失
        setOutsideTouchable(false)
        // 设置背景，这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        contentView.setBackgroundDrawable(mContext!!.resources.getDrawable(R.mipmap.pop_bg))
        setBackgroundDrawable(BitmapDrawable())
        //软键盘不会挡着popupwindow
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        fitPopupWindowOverStatusBar(true)
        return this
    }

    /**
     * 修改数据
     * */
    fun show_(kotlinBean: KotlinBean){
        contentView.setTag(kotlinBean.Id)
        data_edit!!.setText(kotlinBean.Account)
        try {
            data_date!!.setText(ToolUtils.changeDateFormat("yyyy年MM月dd日 HH:mm:ss",
                    "yyyy年MM月dd日",kotlinBean.Time))
            data_time!!.setText(ToolUtils.changeDateFormat("yyyy年MM月dd日 HH:mm:ss",
                    "HH:mm:ss",kotlinBean.Time))
            data_edit!!.setSelection(kotlinBean.Account.length)
        }catch (e:Exception){}
        showAtLocation((mContext as Activity).getWindow().getDecorView(), Gravity.TOP, 0, 0)
    }

    fun show_(){
        showAtLocation((mContext as Activity).getWindow().getDecorView(), Gravity.TOP, 0, 0)
    }

    fun dismiss_(){
        dismiss()
    }

    /**
     * 使其覆盖状态栏,全屏显示
     * @param needFullScreen
     */
    private fun fitPopupWindowOverStatusBar(needFullScreen: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                val mLayoutInScreen = PopupWindow::class.java.getDeclaredField("mLayoutInScreen")
                mLayoutInScreen.isAccessible = true
                mLayoutInScreen.set(this, needFullScreen)
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }

        }
    }

    fun get_show():Boolean{
        return isShowing
    }
}