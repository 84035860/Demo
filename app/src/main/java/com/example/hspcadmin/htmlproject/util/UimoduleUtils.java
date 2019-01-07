package com.example.hspcadmin.htmlproject.util;

import android.content.Context;

import com.example.hspcadmin.htmlproject.activity.home.HomeViewUi;
import com.example.hspcadmin.htmlproject.activity.view.ErrorViewUi;
import com.example.hspcadmin.htmlproject.activity.view.SettingHomeViewUi;
import com.example.hspcadmin.htmlproject.activity.view.WebViewUi;
import com.example.hspcadmin.htmlproject.okhttp.OkhttpViewUi;
import com.example.hspcadmin.htmlproject.rxjava.RxjavaViewUi;
import com.example.hspcadmin.htmlproject.activity.abstracts.AbstractLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * 组件化页面工具类
 * Created by wzheng on 2018/10/10.
 *
 * 组件化页面只需要:
 * 1.在UimoduleUtils()中的MAP添加UIBean
 * 2.getUiView中添加对应view的class.getSimpleName();
 *
 * 调用需要:
 * 1.只获取View UimoduleUtils.getUimoduleUtils().getUiView(UimoduleUtils.BASEVIEW_WEBVIEW, MainHostActivity.this).getAbstractLayout()
 * 2.跳转至Activity ToolUtils.starIntentBaseView(AbstractActivity.class, BASEVIEW_WEBVIEW);
 *
 */

public class UimoduleUtils {
//    public final static int BASEVIEW_ERROR = -1;
    public final static int BASEVIEW_RXJAVA = 0;//Rxjava
    public final static int BASEVIEW_OKHTTP = 1;//Okhttp
    public final static int BASEVIEW_HOME_SETTING = 2;//设置界面
    public final static int BASEVIEW_WEBVIEW = 3;//网页
    public final static int BASEVIEW_HOME = 4;//首页


    private Map<Integer,UiBean> uiView = new HashMap<>();

    static UimoduleUtils uimoduleUtils;

    public static UimoduleUtils getUimoduleUtils() {
        uimoduleUtils = new UimoduleUtils();
        return uimoduleUtils;
    }

    UimoduleUtils(){
        uiView.put(BASEVIEW_WEBVIEW, new UiBean(WebViewUi.class,"网页"));
        uiView.put(BASEVIEW_HOME, new UiBean(HomeViewUi.class,"首页"));
        uiView.put(BASEVIEW_OKHTTP, new UiBean(OkhttpViewUi.class,"OKhttp请求"));
        uiView.put(BASEVIEW_RXJAVA, new UiBean(RxjavaViewUi.class,"RxJava请求"));
        uiView.put(BASEVIEW_HOME_SETTING, new UiBean(SettingHomeViewUi.class,"首页设置"));
        //网页错误比较特殊不做单独页面处理
//        uiView.put(BASEVIEW_ERROR,ErrorViewUi.class);
    }

    public UiBean getUiView(Integer uiNum, Context context) {
        AbstractLayout layout;
        String uiName = "";
        if(uiView.get(uiNum) != null){
            uiName = uiView.get(uiNum).getaClass().getSimpleName();
        }
        switch (uiName){
            case "WebViewUi":
                layout = new WebViewUi(context);
                break;
            case "HomeViewUi":
                layout = new HomeViewUi(context);
                break;
            case "OkhttpViewUi":
                layout = new OkhttpViewUi(context);
                break;
            case "RxjavaViewUi":
                layout = new RxjavaViewUi(context);
                break;
            case "SettingHomeViewUi":
                layout = new SettingHomeViewUi(context);
                break;
            default:
                layout = new ErrorViewUi(context);
                break;
        }

        uiName = "页面异常";
        for (Map.Entry<Integer,UiBean> entry:uiView.entrySet()){
            if(uiView.get(uiNum) != null &&
                    entry.getValue().getName().equals(uiView.get(uiNum).getName())){
                uiName = entry.getValue().getName();
                break;
            }
        }

        return new UiBean(layout,uiName);
    }

    public class UiBean{
        AbstractLayout abstractLayout;
        String name;
        Class aClass;

        UiBean(AbstractLayout abstractLayout,String name){
            this.abstractLayout = abstractLayout;
            this.name = name;
        }

        UiBean(Class aClass,String name){
            this.aClass = aClass;
            this.name = name;
        }

        public AbstractLayout getAbstractLayout() {
            return abstractLayout;
        }

        public String getName() {
            return name;
        }

        public Class getaClass() {
            return aClass;
        }

    }
}
