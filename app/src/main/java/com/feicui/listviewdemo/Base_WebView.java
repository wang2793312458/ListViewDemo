package com.feicui.listviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by AAAAA on 2016/6/20.
 */
public class Base_WebView extends AppCompatActivity{
    private static final String TAG = "Base_WebView";
    public String url = MainActivity.url;
    private WebView mWebView;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //实例化一个webview
        openWebView();
    }

    private void openWebView() {
        mWebView= (WebView) findViewById(R.id.webView);
        //获取webview的JavaScript的脚本
        mWebView.getSettings().getJavaScriptEnabled();
        //加载一个网页，传入一个url路径
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new MyWebviewClient());
    }
    //点击后退webview页面回退到上级页面
    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()){
            mWebView.goBack();
            return;
        }
        super.onBackPressed();
    }
}
