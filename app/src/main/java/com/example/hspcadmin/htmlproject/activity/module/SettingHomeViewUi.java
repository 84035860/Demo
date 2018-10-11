package com.example.hspcadmin.htmlproject.activity.module;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.example.hspcadmin.htmlproject.R;
import com.example.hspcadmin.htmlproject.view.AbstractLayout;

import butterknife.ButterKnife;

/**
 * Created by hspcadmin on 2018/10/10.
 */

public class SettingHomeViewUi extends AbstractLayout {
    Context context;

    public SettingHomeViewUi(Context context) {
        this(context, null);
    }

    public SettingHomeViewUi(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.main_setting_layout, this, true);
        ButterKnife.bind(this, view);
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
