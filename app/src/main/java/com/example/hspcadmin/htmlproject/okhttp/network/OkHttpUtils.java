package com.example.hspcadmin.htmlproject.okhttp.network;

import android.util.Log;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 软件著作权：恒生电子股份有限公司 版权所有 <br>
 * 系统名称：投资赢家移动理财终端5.0 <br>
 * 开发人员：weixw11652 <br>
 * 开发时间：2016/11/17 14:33 <br>
 * 功能描述：<br>
 */

public class OkHttpUtils {

//        public static final MediaType JSON
//            = MediaType.parse("application/json;charset=utf-8");

    private static OkHttpClient mOkHttpClient;

    public static OkHttpClient getOkHttpClient() {
        init();
        return mOkHttpClient;
    }

    private static void init() {
        if (mOkHttpClient == null) {
            synchronized (OkHttpUtils.class) {
                if (mOkHttpClient == null) {
                    HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    };
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    builder.hostnameVerifier(DO_NOT_VERIFY);
                    builder.connectTimeout(10, TimeUnit.SECONDS);
                    builder.writeTimeout(10, TimeUnit.SECONDS);
                    builder.readTimeout(10, TimeUnit.SECONDS);
                    mOkHttpClient = builder.build();
                }
            }
        }
    }

    /**
     * get多个参数参数异步请求切带HTTP头
     * @param url
     * @param callback
     */
    public static void getEnqueueAsHeader(String url, Map<String, String> stringMap , Map<String, String> headMap, Callback callback){
        init();
        Request.Builder requestBuilder = new Request.Builder();
        String str = "";
        if (stringMap != null && stringMap.size() > 0){
            StringBuilder stringBuilder = new StringBuilder();
            Iterator<Map.Entry<String, String>> iterator = stringMap.entrySet().iterator();
            if (iterator.hasNext()){
                Map.Entry<String, String> entry = iterator.next();
                stringBuilder.append("?"+entry.getKey() + "=" + entry.getValue());
            }
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                stringBuilder.append("&" + entry.getKey() + "=" + entry.getValue());
            }
            str = stringBuilder.toString();
        }
        if (headMap != null && headMap.size() > 0){
            Iterator<Map.Entry<String, String>> iterator = headMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        requestBuilder.url(url+str);
        mOkHttpClient.newCall(requestBuilder.build()).enqueue(callback);
    }
    /**
     * get无参数参数异步请求
     *
     * @param url
     * @param callback
     */
    public static void getEnqueue(String url, Callback callback) {
        init();
        Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * get单个参数参数异步请求
     *
     * @param url
     * @param callback
     */
    public static void getEnqueue(String url, String key, String value, Callback callback) {
        init();
        Request request = new Request.Builder().url(url + "?" + key + "=" + value).build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * get多个参数参数异步请求
     *
     * @param url
     * @param callback
     */
    public static void getEnqueue(String url, Map<String, String> stringMap, Callback callback) {
        try {
            init();
            Request.Builder requestBuilder = new Request.Builder();
            if (stringMap != null && stringMap.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                Iterator<Map.Entry<String, String>> iterator = stringMap.entrySet().iterator();
                if (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    stringBuilder.append("?" + entry.getKey() + "=" + entry.getValue());
                }
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    stringBuilder.append("&" + entry.getKey() + "=" + entry.getValue());
                }
                requestBuilder.url(url + stringBuilder.toString());
            } else {
                requestBuilder.url(url);
            }
            mOkHttpClient.newCall(requestBuilder.build()).enqueue(callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * post多个参数异步请求
     *
     * @param url
     * @param stringMap
     * @param callback
     */
    public static void postEnqueue(String url, Map<String, String> stringMap, Callback callback) {
        init();
        Request.Builder requestBuild = new Request.Builder();
        if (stringMap != null && stringMap.size() > 0) {
            FormBody.Builder builder = new FormBody.Builder();
            Iterator<Map.Entry<String, String>> iterator = stringMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                builder.add(entry.getKey(), entry.getValue());
            }
            requestBuild.post(builder.build());
        }
        Request request = requestBuild.url(url).build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }


    /**
     * post多个参数带头参数异步请求
     *
     * @param url
     * @param stringMap
     * @param callback
     */
    public static void postEnqueueHeader(String url, Map<String, String> stringMap, Map<String, String> headMap, Callback callback) {
        init();
        try{
            Request.Builder requestBuild = new Request.Builder();
            if (headMap != null && headMap.size() > 0){
                Iterator<Map.Entry<String, String>> iterator = headMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    requestBuild.addHeader(entry.getKey(), entry.getValue());
                }
            }
            if (stringMap != null && stringMap.size() > 0) {
                FormBody.Builder builder = new FormBody.Builder();
                Iterator<Map.Entry<String, String>> iterator = stringMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    builder.add(entry.getKey(), entry.getValue());
                }
                requestBuild.post(builder.build());
            }
            Request request = requestBuild.url(url).build();
            mOkHttpClient.newCall(request).enqueue(callback);
        }catch (Exception e){
            Log.e("OKHttpUtils -- ","sm:"+stringMap.toString()+"hm:"+headMap.toString());
        }

    }

    /**
     * post单个参数异步请求
     *
     * @param url
     * @param key
     * @param value
     * @param callback
     */
    public static void postEnqueue(String url, String key, String value, Callback callback) {
        init();
        RequestBody requestBody = new FormBody.Builder()
                .add(key, value)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }


    /**
     * patch单个参数异步请求
     *
     * @param url
     * @param key
     * @param value
     * @param callback
     */
    public static void patchEnqueue(String url, String key, String value, Callback callback) {
        init();
        RequestBody requestBody = new FormBody.Builder()
                .add(key, value)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .patch(requestBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }


    /**
     * patch多个参数异步请求
     *
     * @param url
     * @param stringMap
     * @param callback
     */
    public static void patchEnqueue(String url, Map<String, String> stringMap, Callback callback) {
        init();
        Request.Builder requestBuild = new Request.Builder();
        if (stringMap != null && stringMap.size() > 0) {
            FormBody.Builder builder = new FormBody.Builder();
            Iterator<Map.Entry<String, String>> iterator = stringMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                builder.add(entry.getKey(), entry.getValue());
            }
            requestBuild.patch(builder.build());
        }
        Request request = requestBuild.url(url).build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }

//    /**
//     * patch json参数异步请求
//     * @param url
//     * @param json
//     * @param callback
//     */
//    public static void patchEnqueue(String url, String json, Callback callback){
//        init();
//        RequestBody requestBody = RequestBody.create(JSON, json);
//        Request request = new Request.Builder().addHeader("Content-Type", "application/json;charset=utf-8").addHeader("Transfer-Encoding", "chunked").url(url).patch(requestBody).build();
//        mOkHttpClient.newCall(request).enqueue(callback);
//    }

}
