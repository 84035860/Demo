package com.example.hspcadmin.htmlproject.rxjava.network;


import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface ApiService {

    //--------------------------------------模板----------------------------------------------
    /**
     * 根据身份证获取短信验证码
     */
    @GET("")
    Observable<ResponseBody> GETValue(@QueryMap Map<String, String> params);

    /**
     * 开通时增加目前支持以下会员的提示
     */
    @GET("")
    Observable<ResponseBody> GETNullValue();
    /**
     * 更新客户交易指令
     */
    @FormUrlEncoded
    @POST("")
    Observable<ResponseBody> POSTValue(@FieldMap Map<String, String> params);

    /**
     * post接入客服
     */
    @FormUrlEncoded
    @POST("")
    Observable<ResponseBody> POSTHeadValue(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> params);


    //--------------------------------------结束----------------------------------------------

    /**
     * 首页获取资讯数据
     */
    @GET("/info/news/queryHomeNewsPage")
    Observable<ResponseBody> getHomeListInfo();
}

