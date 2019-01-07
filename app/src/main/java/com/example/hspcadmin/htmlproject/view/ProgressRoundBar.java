package com.example.hspcadmin.htmlproject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.example.hspcadmin.htmlproject.R;
import com.example.hspcadmin.htmlproject.util.TimeCountDown;

/**
 * 环形倒计时控件
 *
 * Created by wzheng on 2018/11/30.
 */

public class ProgressRoundBar extends View implements TimeCountDown.TimeCountFace{

    private Paint paint;
    private Context mContext;
    private int mStrokeWidth = 5;
    private float level = 0.0f;
    private int times = 0;
    private int radius_var = 50;

    public ProgressRoundBar(Context context) {
        super(context);
    }

    public ProgressRoundBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(radius_var*3,radius_var*3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.setBackgroundColor(mContext.getResources().getColor(R.color.transparent));
        /**
         *
         * 全圆
         * */
        paint.setStrokeWidth(mStrokeWidth);
        paint.setColor(mContext.getResources().getColor(R.color.yellow));
        canvas.drawCircle(radius_var+mStrokeWidth,radius_var+mStrokeWidth,radius_var,paint);

//        RectF rectF = new RectF();
//        rectF.left = 0+mStrokeWidth;
//        rectF.right = radius_var*2+mStrokeWidth;
//        rectF.top = 0+mStrokeWidth;
//        rectF.bottom = radius_var*2+mStrokeWidth;
//        paint.setColor(mContext.getResources().getColor(R.color.green));
//        canvas.drawArc(rectF,-90,360,false,paint);

        /**
         *
         * 进度
         * */
        RectF rectF = new RectF();
        rectF.left = 0+mStrokeWidth*2;
        rectF.right = radius_var*2;
        rectF.top = 0+mStrokeWidth*2;
        rectF.bottom = radius_var*2;
        paint.setColor(mContext.getResources().getColor(R.color.green));
        canvas.drawArc(rectF,-90,360*level,false,paint);

        /**
         * 为传入 time_var 的倍数
         * */
        String str = String.valueOf(times/100 + 1);
        Rect rect = new Rect();
        paint.setTextSize(46f);
        paint.getTextBounds(str,0,str.length(), rect);
        //rect.width()  rect.height()
        paint.setStrokeWidth(2);
        canvas.drawText(str,radius_var-rect.width()/2,radius_var+rect.height()/2,paint);

    }

    /**
    *@param time_var*period 为毫秒数 :  500 -- 5s
    *
    * */
    private TimeCountDown countDown;
    private int time_var;
    public void setProgress_run(int time_var){
        if(countDown == null){
            countDown = new TimeCountDown().init(this);
        }
        this.time_var = time_var;
        this.times = time_var;
        countDown.star(10);
    }

    @Override
    public void setMessage() {
        times --;
        Message message = new Message();
        if(times<0){
            countDown.cancel();
            message.what = TimeCountDown.HANDLE_WHAT_STAR;
        }else {
            level = (float)1 - ((float) times / (float)time_var);
            message.what = TimeCountDown.HANDLE_WHAT_STAR;
        }
        handler.sendMessage(message);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == TimeCountDown.HANDLE_WHAT_STAR){
            }else if(msg.what == TimeCountDown.HANDLE_WHAT_END){
            }
            postInvalidate();
            super.handleMessage(msg);
        }
    };
}
