package com.example.hspcadmin.htmlproject.activity.module;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hspcadmin.htmlproject.R;
import com.example.hspcadmin.htmlproject.exception.AppException;
import com.example.hspcadmin.htmlproject.view.AbstractLayout;

/**
 * Created by hspcadmin on 2018/10/10.
 */

public class ErrorViewUi extends AbstractLayout{
    Context context;
    TextView errorVeiw;

    public ErrorViewUi(Context context) {
        this(context, null);
    }

    public ErrorViewUi(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        errorVeiw = new TextView(context);
        errorVeiw.setTextColor(context.getResources().getColor(R.color.yellow));
        errorVeiw.setGravity(Gravity.CENTER);
        errorVeiw.setTextSize(TypedValue.COMPLEX_UNIT_PX,context.getResources().getDimension(R.dimen.font_tv_big));
        if (exception.getSharedPreferencesValue(AppException.SP_THEME).equals("1")) {
            errorVeiw.setBackgroundColor(context.getResources().getColor(R.color.white));
        } else {
            errorVeiw.setBackgroundColor(context.getResources().getColor(R.color.trade_tab));
        }
        errorVeiw.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        errorVeiw.setText("页面处理错误");
        errorVeiw.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        this.addView(errorVeiw);
        init();
    }

    private void init(){

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }
}
