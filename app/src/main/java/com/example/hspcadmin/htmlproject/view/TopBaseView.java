package com.example.hspcadmin.htmlproject.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hspcadmin.htmlproject.R;

/**
 * Created by wzheng on 2018/10/8.
 */

public class TopBaseView extends RelativeLayout {
    ImageView Pressed;
    TextView actopBaseTv;
    Context context;

    public TopBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.view_top_layout, this,true);
        Pressed = view.findViewById(R.id.actop_base_pressed);
        actopBaseTv = view.findViewById(R.id.actop_base_tv);
        init();
    }

    public void setText(String s){
       actopBaseTv.setText(s);
    }

    public String getText(){
        return actopBaseTv.getText().toString();
    }

    private void init() {
        Pressed.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) context).finish();
            }
        });
    }

}
