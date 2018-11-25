package com.example.hspcadmin.htmlproject.widget.mvp.model;


import com.example.hspcadmin.htmlproject.okhttp.network.FailureCallback;
import com.example.hspcadmin.htmlproject.okhttp.network.OkHttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Response;

/**
 * @author wzheng  create on 2016/8/10  19:09.
 */
public class GridInfoModelImpl implements GridInfoModel {

    @Override
    public void loadWidgetInfo(final OnLoadWidgetInfoListener onLoadWidgetInfoListener) {
        /**
         * @author  David  created at 2016/8/11 9:53
         *  测试接口使用的是百度ApiSore免费接口
         */

//        ApiRetrofitTest.getInstence().APiService()
//                .getPriceInfoMarket()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DefaultObserver<Object>() {
//                    @Override
//                    public void onSuccess(Object response) {
//
//                    }
//                });
        Map<String,String> map = new HashMap<>();
        map.put("f_inst_ids","ATD,GTD,MTD,A9999,A9995,A100g");
        OkHttpUtils.getEnqueue("http://58.246.144.2:9003/quote/realdata/query", map
                , new FailureCallback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                super.onFailure(call, e);
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                String result = response.body().string();
                if (response != null)
                    onLoadWidgetInfoListener.loadSuccess(result);
                if (!response.isSuccessful()) {
                }
                super.onResponse(call, response);
            }
        });

    }


}
