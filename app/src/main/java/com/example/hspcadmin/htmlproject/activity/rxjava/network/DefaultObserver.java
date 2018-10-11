package com.example.hspcadmin.htmlproject.activity.rxjava.network;

import com.example.hspcadmin.htmlproject.util.ToolUtils;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.HttpException;


/**
 * Created by DJMoving on 2018/7/11.
 */

public abstract class DefaultObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T response) {
        onSuccess(response);
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
        //这里自行替换判断网络的代码
        if (e instanceof HttpException) {     //   HTTP错误
            ResponseBody body = ((HttpException) e).response().errorBody();
            String jsonStr = null;
            try {
                jsonStr = body.string();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            if (!ToolUtils.isNull(jsonStr)) {
                onFail(jsonStr);
            }

        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {   //   连接错误
            onException(ExceptionReason.CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {   //  连接超时
            onException(ExceptionReason.CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {   //  解析错误
            onException(ExceptionReason.PARSE_ERROR);
        } else {
            onException(ExceptionReason.UNKNOWN_ERROR);
        }
        onFinish();
    }

    @Override
    public void onComplete() {
    }

    /**
     * 请求成功
     *
     * @param response 服务器返回的数据
     */
    abstract public void onSuccess(T response);

    /**
     * 服务器返回数据，但响应码不为200
     */
    public abstract void onFail(String message);

    public void onFinish() {
    }

    /**
     * 请求异常
     *
     * @param reason
     */
    public void onException(ExceptionReason reason) {
        switch (reason) {
            case CONNECT_ERROR:
//                ToastUtils.showMessage("网络链接失败,请检查网络");
                break;

            case CONNECT_TIMEOUT:
//                ToastUtils.showMessage("连接超时,请稍后重试");
                break;

            case BAD_NETWORK:
//                ToastUtils.showMessage("服务器异常");
                break;

            case PARSE_ERROR:
//                ToastUtils.showMessage("解析服务器响应数据失败");
                break;

            case UNKNOWN_ERROR:
            default:
//                ToastUtils.showMessage("未知错误");
                break;
        }
    }

    /**
     * 请求网络失败原因
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR,
    }
}
