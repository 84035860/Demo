package com.example.hspcadmin.htmlproject.activity.okhttp.network;


import com.example.hspcadmin.htmlproject.activity.rxjava.network.ApiRetrofit;

/**
 * http请求地址汇总
 * wzheng
 */

public class ServerAddr {
    public static final String server = ApiRetrofit.service;

    //首页资讯
    public static final String HomeNewsPage = server + "/info/news/queryHomeNewsPage";
    public static final int HomeNewsPagenum = 100710;

}
