package com.example.hspcadmin.htmlproject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.hspcadmin.htmlproject.util.ToolUtils;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

/**
 * Created by wzheng on 2018/6/28.
 */

public class SmartRefreshHeader extends LinearLayout implements RefreshHeader {
    private Context context;
    private ImageView imageView;
    private AnimationDrawable animationDrawable;
    //中心点
    private int height = 0;       //总高度
    private int imgtop = 0;     //图片离顶部高度
    private int imgheight = ToolUtils.dpToPx(35);   //图片高度

    public SmartRefreshHeader(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public SmartRefreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        setGravity(Gravity.CENTER);
        imageView = new ImageView(context);
//        imageView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.progress_refresh_scale));
        imageView.setBackgroundDrawable(new AnimationDrawable());
        imageView.getBackground().setLevel(0);
        addView(imageView, imgheight,imgheight);
        setMinimumHeight(ToolUtils.dpToPx(80));
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

    /*移动*/
    @Override
    public void onMoving(boolean b, float v, int i, int i1, int i2) {
        if(height == 0){
            height = i1;
            imgtop = (height - ToolUtils.dpToPx(35))/2;
        }

        //图片离底部位置
        int y = imgtop*1;
        //是否可以进行缩小放大
        if( i<imgtop+imgheight && imgtop<i ){
            int top = i - imgtop;
            int level = (int) (((float)top / imgheight)*10000);

            imageView.getBackground().setLevel(level);
//            Log.e("SmartRefreshHeader---","--onMoving:b"+b+"--y"+y+"--v"+v+"--i"+i+"--i1"+i1+"--i2"+i2);
        }else if(i>imgtop+imgheight){
            //进行图片离底部位计算
//            int top = i - imgtop - imgheight;
//            y = imgtop*2 - top;
            imageView.getBackground().setLevel(10000);
        }

        imageView.setY(y);
    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int i, int i1) {
    }

    /*刷新中  -- 启动动画*/
    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int i, int i1) {
        if(! (imageView.getBackground() instanceof AnimationDrawable)){
//            imageView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.progress_refresh_load));
            animationDrawable = (AnimationDrawable) imageView.getBackground();
        }
//        animationDrawable.start();
    }

    /*刷新结束 -- 结束动画*/
    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean b) {
            // 图片动画
        return 1000;
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
            case None:
                if(! (imageView.getBackground() instanceof AnimationDrawable)){
//                    imageView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.progress_refresh_load));
                    animationDrawable = (AnimationDrawable) imageView.getBackground();
                }
//                animationDrawable.stop();
//                imageView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.progress_refresh_scale));
                imageView.getBackground().setLevel(0);
                break;
            case PullDownToRefresh:
                //mHeaderText.setText("下拉开始刷新");
                break;
            case Refreshing:
                //mHeaderText.setText("正在刷新");
                break;
            case ReleaseToRefresh:
                //mHeaderText.setText("释放立即刷新");
                break;
        }
    }

}