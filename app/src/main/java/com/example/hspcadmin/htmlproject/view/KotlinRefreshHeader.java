package com.example.hspcadmin.htmlproject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.example.hspcadmin.htmlproject.R;
import com.example.hspcadmin.htmlproject.activity.abstracts.AbstractLayout;
import com.example.hspcadmin.htmlproject.activity.presenter.AbstractPresenter;
import com.example.hspcadmin.htmlproject.activity.presenter.Presenter;
import com.example.hspcadmin.htmlproject.kotlin.KotlinaddpopWindow;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

/**
 * Created by wzheng on 2018/6/28.
 */

public class KotlinRefreshHeader extends AbstractLayout<AbstractPresenter> implements RefreshHeader{
    private Context mContext;
    //中心点
    private int Movin_Top = 0;     //图片离顶部高度
    private int Finishint = 0;
    public KotlinaddpopWindow popWindow;

    public KotlinRefreshHeader(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public KotlinRefreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    private void init() {
        contextView = LayoutInflater.from(mContext).inflate(R.layout.kotlin_addvalue_layout,null);
        contextView.findViewById(R.id.add_timedata_edit).setFocusable(false);
        updataView(new Presenter());
        popWindow = new KotlinaddpopWindow(mContext).initView();
//        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                popWindow.dismiss_();
//            }
//        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /*设置布局*/
    @NonNull
    @Override
    public View getView() {
        return this;
    }

    /*设置样式*/
    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.FixedBehind;
    }

    @Override
    public void setPrimaryColors(int... ints) {
    }

    /*刷新*/
    @Override
    public void onInitialized(@NonNull RefreshKernel refreshKernel, int i, int i1) {
    }

    /*绘制*/
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    /*移动 (boolean b, float v, int 移动高度, int 总高度, int 屏幕高度)*/
    @Override
    public void onMoving(boolean b, float v, int i, int height, int i2) {
//        Finishint = Integer.MAX_VALUE;
        if(i>=height){
            if(!popWindow.get_show()){
                popWindow.show_();
            }
        }
    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int i, int i1) {
    }

    /*刷新中  -- 启动动画*/
    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int i, int i1) {
//        animationDrawable.start();
    }

    /*刷新结束 -- 结束动画*/
    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean b) {
            // 图片动画
        return Finishint;
    }

    /*横向事件*/
    @Override
    public void onHorizontalDrag(float v, int i, int i1) {
    }


    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    /*滑动监听*/
    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState refreshState, @NonNull RefreshState newState) {
        switch (newState) {
            case PullDownToRefresh:
                //mHeaderText.setText("下拉开始刷新");
                break;
            case ReleaseToRefresh:
                //mHeaderText.setText("释放立即刷新");
                break;
            case Refreshing:
                //mHeaderText.setText("正在刷新");
                break;
            case None:
                break;
        }
    }

}