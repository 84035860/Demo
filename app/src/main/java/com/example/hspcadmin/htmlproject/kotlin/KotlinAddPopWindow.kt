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
import com.example.hspcadmin.htmlproject.util.ToolUtils



/**
 * Created by hspcadmin on 2018/11/22.
 */
class KotlinAddPopWindow(mContext: Context) :PopupWindow(){

    private var mView: View? = null
    private var mContext:Context? = mContext
    private var isshowimm:Boolean = false

    interface KotlinAddpopFace{
        fun Submit(bean: KotlinBean)
    }

    fun initView():KotlinAddPopWindow{
        mView = LayoutInflater.from(mContext).inflate(R.layout.kotlin_addvalue_layout,null)
        contentView = mView
        contentView.findViewById<EditText>(R.id.add_timedata_edit).setOnClickListener(View.OnClickListener {
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
        contentView.findViewById<ImageView>(R.id.next_img).setOnClickListener(View.OnClickListener {
            if(!ToolUtils.isFastClick()&&mContext is KotlinAddpopFace){
                    (mContext as KotlinAddpopFace).Submit(KotlinBean(
                            -1,
                            contentView.findViewById<EditText>(R.id.add_timedata_edit).text.toString(),
                            contentView.findViewById<TextView>(R.id.add_timedata_time).text.toString(),
                            false
                    ))
                    dismiss_()
            }
        })
        this.width = ToolUtils.getWidth(mContext)
        // 设置SelectPicPopupWindow弹出窗体的高
        this.height = ToolUtils.dpToPx(250f)
        // 设置SelectPicPopupWindow弹出窗体可点击
        setFocusable(true)
        // 设置允许在外点击消失
        setOutsideTouchable(false)
        // 设置背景，这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        setBackgroundDrawable(BitmapDrawable())
        //软键盘不会挡着popupwindow
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        fitPopupWindowOverStatusBar(true)


        return this
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