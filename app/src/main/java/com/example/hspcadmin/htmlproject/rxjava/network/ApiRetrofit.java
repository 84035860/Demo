package com.example.hspcadmin.htmlproject.rxjava.network;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRetrofit {
    private static final String TAG = "ApiRetrofit";
    private static ApiRetrofit mApiRetrofit;
    private static ApiService mAPIFunction;
    public static String service = "https://viptest.ejintong.com.cn";
    private ApiRetrofit(){
        OkHttpClient mOkHttpClient=new OkHttpClient.Builder()
                .connectTimeout(30000, TimeUnit.SECONDS)
                .readTimeout(30000, TimeUnit.SECONDS)
                .writeTimeout(30000, TimeUnit.SECONDS)
                .addInterceptor(InterceptorUtil.LogInterceptor())//添加日志拦截器
                //设置重连次数为2
                .addInterceptor(new ApiRetrofit.RetryInterceptor(2))
                .build();
        Retrofit mRetrofit=new Retrofit.Builder()
                .baseUrl(service)
                .addConverterFactory(GsonConverterFactory.create())//添加gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加rxjava转换器
                .client(mOkHttpClient)
                .build();
        mAPIFunction=mRetrofit.create(ApiService.class);

    }

    public static ApiRetrofit getInstance(){
        if (mApiRetrofit==null){
            synchronized (ApiRetrofit.class) {
                if (mApiRetrofit == null)
                    mApiRetrofit = new ApiRetrofit();
            }

        }
        return mApiRetrofit;
    }
    public  ApiService APiService(){
        return mAPIFunction;
    }
    /**
     * 自定义重试拦截器
     * 最大重试次数，假如设置为3次重试的话，则最大可能请求4次（默认1次+3次重试）
     * 自定义拦截器做网络重试
     */
    public static class RetryInterceptor implements Interceptor {
        private int retryNum = 0;
        private int maxRetry;

        private RetryInterceptor(int maxRetry) {
            this.maxRetry = maxRetry;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            while (!response.isSuccessful() && retryNum < maxRetry) {
                retryNum++;
                Log.d(TAG, "retryNum=" + retryNum);
                response = chain.proceed(request);
            }
            return response;
        }
    }
}
