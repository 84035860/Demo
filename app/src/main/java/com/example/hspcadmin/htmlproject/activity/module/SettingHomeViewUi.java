package com.example.hspcadmin.htmlproject.activity.module;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hspcadmin.htmlproject.R;
import com.example.hspcadmin.htmlproject.exception.AppException;
import com.example.hspcadmin.htmlproject.kotlin.KotlinActivity;
import com.example.hspcadmin.htmlproject.kotlin.Test;
import com.example.hspcadmin.htmlproject.util.EventBusUtils;
import com.example.hspcadmin.htmlproject.util.EventBusVal;
import com.example.hspcadmin.htmlproject.util.ToolUtils;
import com.example.hspcadmin.htmlproject.util.changeskin.ColorUiUtil;
import com.example.hspcadmin.htmlproject.view.AbstractLayout;
import com.example.hspcadmin.htmlproject.view.SwitchView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hspcadmin on 2018/10/10.
 */

public class SettingHomeViewUi extends AbstractLayout {
    Context context;
    @BindView(R.id.tv_skin)
    TextView tvSkin;
    @BindView(R.id.switch_skin)
    SwitchView switchSkin;
    @BindView(R.id.setting_kotlin)
    TextView settingKotlin;
    @BindView(R.id.setting_kotlin_ui)
    TextView settingKotlinUi;

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

    private void init() {
        if (exception.getSharedPreferencesValue(AppException.SP_THEME).equals("1")) {
            switchSkin.setState(false);
        } else {
            switchSkin.setState(true);
        }

        switchSkin.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn() {
                switchSkin.toggleSwitch(true);
                setChangeSkin();
            }

            @Override
            public void toggleToOff() {
                switchSkin.toggleSwitch(false);
                setChangeSkin();
            }
        });
    }

    private void setChangeSkin() {
        if (exception.getSharedPreferencesValue(AppException.SP_THEME).equals("1")) {
            exception.setSharedPreferencesValue(AppException.SP_THEME, "2");
            context.setTheme(R.style.ThemeDayTime);
        } else {
            exception.setSharedPreferencesValue(AppException.SP_THEME, "1");
            context.setTheme(R.style.ThemeDayNight);
        }
//        setStatusBar();
        final View rootView = ((Activity) context).getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= 14) {
            rootView.setDrawingCacheEnabled(true);
            rootView.buildDrawingCache(true);
            final Bitmap localBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
            rootView.setDrawingCacheEnabled(false);
            if (null != localBitmap && rootView instanceof ViewGroup) {
                final View localView2 = new View(context);
                localView2.setBackgroundDrawable(new BitmapDrawable(getResources(), localBitmap));
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                ((ViewGroup) rootView).addView(localView2, params);
                localView2.animate().alpha(0).setDuration(400).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        ColorUiUtil.changeTheme(rootView, context.getTheme());
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        ((ViewGroup) rootView).removeView(localView2);
                        localBitmap.recycle();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();
            }
        } else {
            ColorUiUtil.changeTheme(rootView, context.getTheme());
        }

        EventBus.getDefault().post(new EventBusUtils(EventBusVal.INSTANCE.getEVENTBUS_VAL_HOME_SKIN()));
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @OnClick({R.id.setting_kotlin,R.id.setting_kotlin_ui})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.setting_kotlin:
                Test.INSTANCE.sayMessage(Test.INSTANCE.testClass(SettingHomeViewUi.class) + ":是设置");
                KotlinActivity kotlin = new KotlinActivity();
                kotlin.InitView();
                break;
            case R.id.setting_kotlin_ui:
                ToolUtils.starIntent(KotlinActivity.class);
                break;
        }
    }

}
