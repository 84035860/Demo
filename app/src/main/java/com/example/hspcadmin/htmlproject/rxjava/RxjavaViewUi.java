package com.example.hspcadmin.htmlproject.rxjava;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.hspcadmin.htmlproject.R;
import com.example.hspcadmin.htmlproject.rxjava.network.ApiRetrofit;
import com.example.hspcadmin.htmlproject.rxjava.network.DefaultObserver;
import com.example.hspcadmin.htmlproject.activity.abstracts.AbstractLayout;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by wzheng on 2018/10/8.
 */

public class RxjavaViewUi extends AbstractLayout {
    @BindView(R.id.rxjava_event_tv)
    TextView rxjavaEventTv;
    @BindView(R.id.rxjava_event_submit)
    TextView rxjavaEventSubmit;
    private Context context;

    public RxjavaViewUi(Context context) {
        this(context, null);
    }

    public RxjavaViewUi(Context context, AttributeSet attrs) {
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
        ApiRetrofit.getInstance().APiService()
                .getHomeListInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<ResponseBody>() {
                    @Override
                    public void onSuccess(ResponseBody response) {
                        try {
                            String result = response.string();
                            rxjavaEventTv.setText(result);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(String message) {

                    }
                });

    }
}
