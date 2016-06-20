package com.feicui.listviewdemo;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by AAAAA on 2016/6/20.
 */
public class MyWebviewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
