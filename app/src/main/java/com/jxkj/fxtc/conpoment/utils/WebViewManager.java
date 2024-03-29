package com.jxkj.fxtc.conpoment.utils;

import android.annotation.SuppressLint;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewManager {
    private WebView webView;
    private WebSettings webSettings;

    public WebViewManager(WebView webView) {
        this.webView = webView;
        webSettings = webView.getSettings();
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
    }

    /**
     * 开启自适应功能
     */
    public WebViewManager enableAdaptive() {
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        return this;
    }

    /**
     * 禁用自使用功能
     */
    public WebViewManager disableAdaptive() {
        webSettings.setUseWideViewPort(false);
        webSettings.setLoadWithOverviewMode(false);
        return this;
    }

    /**
     * 开启缩放功能
     */
    public WebViewManager enableZoom() {
        webSettings.setSupportZoom(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        return this;
    }

    /**
     * 禁用缩放功能
     */
    public WebViewManager disableZoom() {
        webSettings.setSupportZoom(false);
        webSettings.setUseWideViewPort(false);
        webSettings.setBuiltInZoomControls(false);
        return this;
    }

    /**
     * 开启JavaScript
     */
    @SuppressLint("SetJavaScriptEnable")
    public WebViewManager enableJavaScript() {
        webSettings.setJavaScriptEnabled(true);
        return this;
    }

    /**
     * 禁用JavaScript
     */
    public WebViewManager disableJavaScript() {
        webSettings.setJavaScriptEnabled(false);
        return this;
    }

    /**
     * 开启JavaScript自动弹窗
     */
    public WebViewManager enableJavaScriptOpenWindowsAutomatically() {
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        return this;
    }

    /**
     * 禁用JavaScript自动弹窗
     */
    public WebViewManager disableJavaScriptOpenWindowsAutomatically() {
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false);
        return this;
    }

    /**
     * 返回
     * @return true:已经返回，fasle：没法返回
     */
    public boolean goBack() {
        if (webView.canGoBack()) {
            webView.goBack();
            return true;
        } else {
            return false;
        }
    }
}
