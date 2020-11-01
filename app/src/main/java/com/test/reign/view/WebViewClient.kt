package com.test.reign.view

import android.webkit.WebView
import android.webkit.WebViewClient

class MyWebViewClient : WebViewClient() {
    fun ShouldOverrideUrlLoading(View: WebView, url: String?): Boolean {
        View.loadUrl(url)
        return true
    }
}