package com.example.hspcadmin.htmlproject.activity.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.example.hspcadmin.htmlproject.R;
import com.example.hspcadmin.htmlproject.activity.abstracts.AbstractLayout;

/**
 * Created by wzheng on 2018/10/10.
 */

public class WebViewUi extends AbstractLayout{
    private Context context;
    private WebView webView;
    private String WebViewUrl = "https://www.baidu.com/";
    private EditText code_edit;

    public WebViewUi(Context context) {
        this(context, null);
    }

    public WebViewUi(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        contextView = LayoutInflater.from(context).inflate(R.layout.webview_layout,null);
        updataView(null);
        init();
    }

    private void init() {
        webView = contextView.findViewById(R.id.html_webview);
        code_edit = contextView.findViewById(R.id.html_code_edit);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setTextZoom(100);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        webView.requestFocus();
        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebChromeClient(new WebViewChromeClientDemo());

        code_edit.setText("<html>\n" +
                "\t<h3 id=\"demo\"> 点击打开App并跳转至指定界面</h3>\n" +
                "\t<button onclick=\"myFunction()\">点击</button>\n" +
                "\t<script>\n" +
                "\t function myFunction(){\n" +
                "\t\twindow.location.href = \"scheme://host/pathPrefix\"\n" +
                "\t }\n" +
                "\t</script>\n" +
                "</html>");

        contextView.findViewById(R.id.html_star).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadDataWithBaseURL(null,code_edit.getText().toString(), "text/html", "utf-8", null);
                webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            }
        });

        contextView.findViewById(R.id.html_clean).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                code_edit.setText("");
                webView.loadUrl(WebViewUrl);
            }
        });
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        /**
         * 网页跳转app  如果直接是html则使用浏览器跳转方式
         *
         * 如果使用app的webview跳转app则区需要在WebViewClient里进行特殊处理
         * */
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // 判断是否拨打电话
            try{
                if(!url.contains("http")||url.contains("www")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    context.startActivity(intent);
                } else {
                    view.loadUrl(url);// 当打开新链接时，使用当前的 WebView，不会使用系统其他浏览器
                }
            }catch (Exception e){}
            return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
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

        }

        // JavaScript弹出框
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

        // JavaScript输入框
        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue,
                                  JsPromptResult result) {
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

        // JavaScript确认框
        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            return super.onJsConfirm(view, url, message, result);
        }
    }

}

