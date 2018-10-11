package com.example.hspcadmin.htmlproject.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.util.AttributeSet;
import android.view.View;

import com.example.hspcadmin.htmlproject.R;
import com.example.hspcadmin.htmlproject.util.ToolUtils;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by wzheng on 2018/6/28.
 */

public class ProgressTime extends View{
    private Context context;
    private int Xlenth , Ylenth;
    private float hourScaleX , minuteScaleX , secondScaleX;
    private int hourScaleY , minuteScaleY , secondScaleY;
    private Paint mPaint;
    private int TextWidth;
    private Time t;
    private Bitmap bitmap;

    /**
     * handle what
     * */
    private static int HANDLE_WHAT_STAR = 1;

    private int[] drawables = new int[]{
      R.mipmap.emoji1,R.mipmap.emoji1,R.mipmap.emoji1,
            R.mipmap.emoji1,R.mipmap.emoji1,R.mipmap.emoji1,
            R.mipmap.emoji1,R.mipmap.emoji1,R.mipmap.emoji1,
            R.mipmap.emoji1,R.mipmap.emoji1,R.mipmap.emoji1,
            R.mipmap.emoji1,R.mipmap.emoji1,R.mipmap.emoji1,
            R.mipmap.emoji1,R.mipmap.emoji1,R.mipmap.emoji1,
            R.mipmap.emoji1,R.mipmap.emoji1,R.mipmap.emoji1,
            R.mipmap.emoji1,R.mipmap.emoji1,
    };

    public ProgressTime(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ProgressTime(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context= context;
        init();
    }

    public void init(){
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true); //去锯齿
        mPaint.setStrokeWidth(2);
        mPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.font_super_smallest));
        mPaint.setColor(getResources().getColor(R.color._7C7F99));
        Rect rect = new Rect();
        mPaint.getTextBounds("00",0,"00".length(), rect);
        TextWidth = rect.width();
        t=new Time();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Xlenth = getWidth();
        Ylenth = getHeight()- ToolUtils.dpToPx(5);
        hourScaleX = getWidth()/24.f;
        minuteScaleX = hourScaleX/60.f;
        secondScaleX = minuteScaleX/60.f;

        hourScaleY = getHeight()/2;
        minuteScaleY = hourScaleY/2;
        secondScaleY = minuteScaleY/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.setBackgroundColor(getResources().getColor(R.color.trade_tab));
        mPaint.setColor(getResources().getColor(R.color.green));

        if (timer == null){
            return;
        }

        canvas.drawLine(0,Ylenth,Xlenth,Ylenth,mPaint);

        mPaint.setStrokeWidth(1);
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        bitmap = ((BitmapDrawable)getResources().getDrawable(drawables[getHour()])).getBitmap();
        for(int i=1;i<24;i++){
            canvas.drawLine(hourScaleX*i,Ylenth,hourScaleX*i,Ylenth- secondScaleY,mPaint);
            canvas.drawText(i+"",hourScaleX*i - TextWidth/2,Ylenth - secondScaleY - bitmap.getHeight(),mPaint);
        }
//        canvas.drawBitmap(bitmap,
//                hourScaleX*getHour()+minuteScaleX*getMinute()+secondScaleX*getSecond(),
//                Ylenth - bitmap.getHeight(),
//                    mPaint);
        drawzhibiao((int)(hourScaleX*getHour()+minuteScaleX*getMinute()+secondScaleX*getSecond()) - 5,Ylenth - bitmap.getHeight(),canvas);
    }

    private int getHour(){
        t.setToNow();//获取当前时间
        int hour = t.hour; // 0-23
        return hour;
    }

    private int getMinute(){
        t.setToNow();//获取当前时间
        int minute = t.minute;
        return minute;
    }

    private int getSecond(){
        t.setToNow();//获取当前时间
        int second = t.second;
        return second;
    }

    private void drawzhibiao(int starx,int stary,Canvas canvas){
        Path path = new Path();
        float mArrowWidth = 10f;
        stary = stary + 14;
        starx = starx + 2;
        path.moveTo(starx, stary);
        path.lineTo(starx + mArrowWidth, stary);
        path.lineTo(starx + mArrowWidth / 2, stary+10f);
        path.close();
        canvas.drawPath(path, mPaint);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == HANDLE_WHAT_STAR){
                postInvalidate();
            }
            super.handleMessage(msg);
        }
    };

    public Timer timer;
    public void starProgressTime(){
            timer = new Timer();
            timer.schedule(timerTask,0,1000);
    }

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
                Message message = new Message();
                message.what = HANDLE_WHAT_STAR;
                handler.sendMessage(message);
        }
    };
}
