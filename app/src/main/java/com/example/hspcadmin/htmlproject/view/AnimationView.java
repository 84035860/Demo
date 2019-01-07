package com.example.hspcadmin.htmlproject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.hspcadmin.htmlproject.R;

/**
 * Created by wzheng on 2018/12/6.
 */

public class AnimationView extends View{

    Context mContext;
    private Paint mPaint;
    private int radius_var = 50;
    private PaintScale paintScale;

    public AnimationView(Context context){
        super(context);
    }

    public AnimationView(Context context, AttributeSet attributes){
        super(context,attributes);
        this.mContext = context;
        init();
    }

    private void init(){
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(mContext.getResources().getColor(R.color.red));

        if(paintScale == null){
            paintScale = new PaintScale();

        }else {

        }
        canvas.drawCircle(radius_var,radius_var,radius_var,mPaint);

        super.onDraw(canvas);
    }

    class PaintScale{
        float mScalex;
        float mScaley;

        public float getmScalex() {
            return mScalex;
        }

        public void setmScalex(float mScalex) {
            this.mScalex = mScalex;
        }

        public float getmScaley() {
            return mScaley;
        }

        public void setmScaley(float mScaley) {
            this.mScaley = mScaley;
        }
    }
}
