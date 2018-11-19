package com.example.hspcadmin.htmlproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.hspcadmin.htmlproject.R;
import com.example.hspcadmin.htmlproject.util.CompressedZipUtils;
import com.example.hspcadmin.htmlproject.util.permission.PermissionGen;

/**
 * Created by wzheng on 2018/6/21.
 */

public class WebViewActivity extends Activity{
    public WebView myWebView;
    public TextView html_title;
    public String weburl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myWebView  = (WebView)findViewById(R.id.html_webview);
        html_title = (TextView)findViewById(R.id.html_title);
//        weburl = "http://tzyjdev.hsmdb.com/hq3/?code=sh.600570&type=stock";
        weburl = "file://"+ CompressedZipUtils.saveFiles()+"/AppHome/APPHOME/index.html";
        initUrl();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (CompressedZipUtils.userSDKVersion >= 23 && ContextCompat.checkSelfPermission(WebViewActivity.this,
                android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {// 请求获取当前权限
            // 权限：读取手机状态、SD卡读写权限、定位权限
            PermissionGen.needPermission(WebViewActivity.this, 100, new String[]{android.Manifest.permission.READ_PHONE_STATE,
                    android.Manifest.permission.CALL_PHONE });//, Manifest.permission.ACCESS_FINE_LOCATION
        }
    }

    public void initUrl(){
        myWebView.addJavascriptInterface(null, "");

        // JavaScript使能(如果要加载的页面中有JS代码，则必须使能JS)
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setTextZoom(100);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setDomStorageEnabled(true);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
//        }
        myWebView.requestFocus();
        myWebView.setWebViewClient(new MyWebViewClient());
        myWebView.setWebChromeClient(new WebViewChromeClientDemo());
        myWebView.loadUrl(weburl);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //判断是否拨打电话
            if (url.startsWith("mailto:") || url.startsWith("geo:") || url.startsWith("tel:")) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
                if (ActivityCompat.checkSelfPermission(WebViewActivity.this, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent);
                    //这个超连接,java已经处理了，webview不要处理
                }
            } else {
                view.loadUrl(url);// 当打开新链接时，使用当前的 WebView，不会使用系统其他浏览器
            }
            return true;
        }
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//            super.onReceivedSslError(view, handler, error);
            handler.proceed(); // 接受所有网站的证书
        }
    }


    private class WebViewChromeClientDemo extends WebChromeClient {
        // 设置网页加载的进度条
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
            }
            super.onProgressChanged(view, newProgress);
        }

        // 获取网页的标题
        public void onReceivedTitle(WebView view, String title) {
            if (title != null) {
                html_title.setText(title);
            }
        }

        // JavaScript弹出框
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

        // JavaScript输入框
        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

        // JavaScript确认框
        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            return super.onJsConfirm(view, url, message, result);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
