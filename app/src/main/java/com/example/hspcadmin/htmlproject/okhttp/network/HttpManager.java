package com.example.hspcadmin.htmlproject.okhttp.network;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Map;

import okhttp3.Response;

/**
 * Created by wzheng on 2017/7/3.
 */

public class HttpManager {
    public static void HttpPost(String HttpUrl, final Context context, final Map<String, String> map, final Handler handler, final int FundType) {
        OkHttpUtils.postEnqueue(HttpUrl, map, new FailureCallback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                super.onFailure(call, e);
                if (e instanceof ConnectException) {
                    tooTosat(context, "网络异常,请检查网络!");
                }
                if (e instanceof java.net.SocketTimeoutException) {
                    tooTosat(context, "请求超时!");
                }
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                Message message = new Message();
                String result = response.body().string();
                if (response != null)
                message.obj = new ResponseBean(response, result);
                message.what = FundType;
                handler.sendMessage(message);
                if (!response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        tooTosat(context, jsonObject.optString("error_info"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                super.onResponse(call, response);
            }
        });
    }
    /*带头参数POST*/
    public static void HttpPostHead(String HttpUrl, final Context context, Map<String, String> map, Map<String, String> headMap, final Handler handler, final int FundType) {
        OkHttpUtils.postEnqueueHeader(HttpUrl, map,headMap,new FailureCallback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                super.onFailure(call, e);
                if (e instanceof java.net.SocketTimeoutException) {
                    tooTosat(context, "请求超时!");
                }else if  (e instanceof UnknownHostException){
                    tooTosat(context, "网络异常,请检查网络!");
                }
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                Message message = new Message();
                String result = response.body().string();
                if (response != null)
                    message.obj = new ResponseBean(response, result);
                message.what = FundType;
                handler.sendMessage(message);
                if (!response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        tooTosat(context, jsonObject.optString("error_info"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                super.onResponse(call, response);
            }
        });
    }

    public static void HttpPatchEnqueue(String HttpUrl, final Context context, final Map<String, String> map, final Handler handler, final int FundType) {
        OkHttpUtils.patchEnqueue(HttpUrl, map, new FailureCallback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                super.onFailure(call, e);
                if (e instanceof ConnectException) {
                    tooTosat(context, "网络异常,请检查网络!");
                }
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                Message message = new Message();
                String result = response.body().string();
                if (response != null)
                message.obj = new ResponseBean(response, result);
                message.what = FundType;
                handler.sendMessage(message);
                if (!response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        tooTosat(context, jsonObject.optString("error_info"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                super.onResponse(call, response);
            }
        });
    }

    public static void HttpgetEnqueue(String HttpUrl, final Context context, final Map<String, String> map, final Handler handler, final int FundType) {
        OkHttpUtils.getEnqueue(HttpUrl, map, new FailureCallback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                super.onFailure(call, e);
                Message message = new Message();
                message.what = -1;
                if (e instanceof ConnectException) {
                    tooTosat(context, "网络异常,请检查网络!");
                }
                if (e instanceof java.net.SocketTimeoutException) {
                    tooTosat(context, "请求超时!");
                }
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                Message message = new Message();
                String result = response.body().string();
                if (response != null)
                message.obj = new ResponseBean(response, result);
                message.what = FundType;
                handler.sendMessage(message);
                if (!response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        tooTosat(context, jsonObject.optString("error_info"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                super.onResponse(call, response);
            }
        });
    }

    /*带头参数GET*/
    public static void HttpgetEnqueueHead(String HttpUrl, final Context context, final Map<String, String> map, Map<String, String> headMap, final Handler handler, final int FundType) {
        OkHttpUtils.getEnqueueAsHeader(HttpUrl, map, headMap, new FailureCallback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                super.onFailure(call, e);
                if (e instanceof ConnectException) {
                    tooTosat(context, "网络异常,请检查网络!");
                }
                if (e instanceof java.net.SocketTimeoutException) {
                    tooTosat(context, "请求超时!");
                }
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                Message message = new Message();
                String result = response.body().string();
                if (response != null)
                    message.obj = new ResponseBean(response, result);
                message.what = FundType;
                handler.sendMessage(message);
                if (!response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        tooTosat(context, jsonObject.optString("error_info"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                super.onResponse(call, response);
            }
        });
    }

    public static void tooTosat(final Context context, final String msg) {
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static class ResponseBean {
        Response response;
        String result;

        ResponseBean(Response response, String result) {
            this.response = response;
            this.result = result;
        }

        ;

        public Response getResponse() {
            return response;
        }

        public String getResult() {
            return result;
        }
    }
}
