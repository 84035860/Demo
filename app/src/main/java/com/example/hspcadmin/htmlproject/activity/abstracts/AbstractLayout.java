package com.example.hspcadmin.htmlproject.activity.abstracts;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.hspcadmin.htmlproject.R;
import com.example.hspcadmin.htmlproject.activity.presenter.AbstractPresenter;
import com.example.hspcadmin.htmlproject.exception.AppException;
import com.example.hspcadmin.htmlproject.util.changeskin.ColorUiInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hspcadmin on 2018/10/10.
 */
public abstract class AbstractLayout <T extends ViewContractType.ViewAction> extends FrameLayout implements ColorUiInterface,ViewContractType.ViewType{
    public AppException exception;
    public String skin_bg_color;
    public String skin_font_color;
    public String skin_line_color;
    public String skin_edit_color;
    public String skin_title_color;
    public String skin_button_color;
    public String skin_back_img;
    public Context context;
    private T viewContract;
    public View contextView;
    private TextView textView;

    public AbstractLayout(Context context) {
        super(context);
    }

    public AbstractLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        exception = AppException.getInstance();
        skin_bg_color = context.getResources().getString(R.string.skin_bg_color);
        skin_font_color = context.getResources().getString(R.string.skin_font_color);
        skin_line_color = context.getResources().getString(R.string.skin_line_color);
        skin_edit_color = context.getResources().getString(R.string.skin_edit_color);
        skin_title_color = context.getResources().getString(R.string.skin_title_color);
        skin_button_color = context.getResources().getString(R.string.skin_button_color);
        skin_back_img = context.getResources().getString(R.string.skin_back_img);
        textView =  new TextView(context);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        textView.setGravity(Gravity.CENTER);
        initView();
        //设置日间夜间模式,用来换肤
        if (exception.getSharedPreferencesValue(AppException.SP_THEME).equals("1")) {
            //白天模式
            this.setBackgroundColor(getContext().getResources().getColor(R.color.white));
        } else {
            //夜间模式
            this.setBackgroundColor(getContext().getResources().getColor(R.color.trade_tab));
        }
    }

    public abstract void onResume();

    public abstract void onPause();

    @Override
    public void initView() {
        this.removeAllViews();
        textView.setText("加载中");
        this.addView(textView);
    }

    @Override
    public void updataView(AbstractPresenter viewAction) {
        this.removeAllViews();
        this.addView(contextView);
        viewAction.initView(contextView);
        viewAction.setAction();
    }

    @Override
    public void errorView() {
        this.removeAllViews();
        textView.setText("页面加载错误");
        this.addView(textView);
    }

    @Override
    public void exitView() {
        this.removeAllViews();
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setTheme() {
        getAllChildViews(this);
    }

    private List<View> getAllChildViews(View view) {
        if(view.getTag()!=null){
            try {
                if(view.getTag().toString().equals(skin_bg_color)){
                    if (exception.getSharedPreferencesValue(AppException.SP_THEME).equals("1")) {
                        //白天模式
                        view.setBackgroundColor(context.getResources().getColor(R.color.white));
                    } else {
                        //夜间模式
                        view.setBackgroundColor(context.getResources().getColor(R.color.trade_tab));
                    }
                }else if(view.getTag().toString().equals(skin_font_color)){
                    if(view instanceof TextView){
                        if (exception.getSharedPreferencesValue(AppException.SP_THEME).equals("1")) {
                            //白天模式
                            ((TextView)view).setTextColor(context.getResources().getColor(R.color.black));
                        } else {
                            //夜间模式
                            ((TextView)view).setTextColor(context.getResources().getColor(R.color.white));
                        }
                    }
                }else if(view.getTag().toString().equals(skin_line_color)){
                    if (exception.getSharedPreferencesValue(AppException.SP_THEME).equals("1")) {
                        //白天模式
                        view.setBackgroundColor(context.getResources().getColor(R.color.black));
                    } else {
                        //夜间模式
                        view.setBackgroundColor(context.getResources().getColor(R.color.white));
                    }
                }else if(view.getTag().toString().equals(skin_edit_color)){
                    if(view instanceof TextView){
                        if (exception.getSharedPreferencesValue(AppException.SP_THEME).equals("1")) {
                            //白天模式
                            ((TextView)view).setTextColor(context.getResources().getColor(R.color.black));
                        } else {
                            //夜间模式
                            ((TextView)view).setTextColor(context.getResources().getColor(R.color.white));
                        }
                    }
                }else if(view.getTag().toString().equals(skin_title_color)){
                    if(view instanceof TextView){
                        if (exception.getSharedPreferencesValue(AppException.SP_THEME).equals("1")) {
                            //白天模式
                            ((TextView)view).setTextColor(context.getResources().getColor(R.color.white));
                            view.setBackgroundColor(context.getResources().getColor(R.color.trade_tab));
                        } else {
                            //夜间模式
                            ((TextView)view).setTextColor(context.getResources().getColor(R.color.white));
                            view.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                        }
                    }
                }else if(view.getTag().toString().equals(skin_button_color)){
                    if(view instanceof TextView){
                        if (exception.getSharedPreferencesValue(AppException.SP_THEME).equals("1")) {
                            //白天模式
                            ((TextView)view).setTextColor(context.getResources().getColor(R.color.white));
                            view.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                        } else {
                            //夜间模式
                            ((TextView)view).setTextColor(context.getResources().getColor(R.color.colorPrimary));
                            view.setBackgroundColor(context.getResources().getColor(R.color.white));
                        }
                    }
                }else if(view.getTag().toString().equals(skin_back_img)){
                    if (exception.getSharedPreferencesValue(AppException.SP_THEME).equals("1")) {
                        //白天模式
                        view.setBackgroundResource(R.mipmap.btn_top_back_pressed);
                    } else {
                        //夜间模式
                        view.setBackgroundResource(R.mipmap.btn_top_back_normal);
                    }
                }
            }catch (Exception e){}
        }

        List<View> allchildren = new ArrayList<>();
        if (view instanceof ViewGroup) {
            ViewGroup vp = (ViewGroup) view;
            for (int i = 0; i < vp.getChildCount(); i++) {
                View viewchild = vp.getChildAt(i);
                allchildren.add(viewchild);
                //再次 调用本身（递归）
                allchildren.addAll(getAllChildViews(viewchild));
            }
        }
        return allchildren;
    }

}
