package com.example.hspcadmin.htmlproject.okhttp;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.hspcadmin.htmlproject.R;
import com.example.hspcadmin.htmlproject.okhttp.network.HttpManager;
import com.example.hspcadmin.htmlproject.okhttp.network.ServerAddr;
import com.example.hspcadmin.htmlproject.activity.abstracts.AbstractLayout;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Response;

/**
 * Created by wzheng on 2018/10/8.
 */

public class OkhttpViewUi extends AbstractLayout {
    @BindView(R.id.rxjava_event_tv)
    TextView rxjavaEventTv;
    @BindView(R.id.rxjava_event_submit)
    TextView rxjavaEventSubmit;
    private Context context;

    public OkhttpViewUi(Context context) {
        this(context, null);
    }

    public OkhttpViewUi(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.rxjava_layout, this, true);
        ButterKnife.bind(this, view);
        init();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    private void init() {

    }

    @OnClick(R.id.rxjava_event_submit)
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.rxjava_event_submit:
                rxjava_submit();
                break;
        }
    }

    private void rxjava_submit() {
        HttpManager.HttpgetEnqueue(ServerAddr.HomeNewsPage, context, new HashMap<String, String>(), handler, ServerAddr.HomeNewsPagenum);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final HttpManager.ResponseBean responseBean = (HttpManager.ResponseBean) msg.obj;
            final Response response = responseBean.getResponse();
            if (response.isSuccessful()) {
                switch (msg.what) {
                    case ServerAddr.HomeNewsPagenum:
                        ((Activity)context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                rxjavaEventTv.setText(responseBean.getResult());
                            }
                        });
                        break;
                }
            }
        }
    };

}
