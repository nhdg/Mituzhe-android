package com.mituzhe.online;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.graphics.Color;
import android.widget.FrameLayout;
import android.view.KeyEvent;

public class MainActivity extends Activity {
    private WebView webView;
    private static final String GAME_URL = "https://mistuzhe-online-1.onrender.com";

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.rgb(23,36,58));
        getWindow().setNavigationBarColor(Color.rgb(23,36,58));
        FrameLayout root = new FrameLayout(this);
        webView = new WebView(this);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.setBackgroundColor(Color.WHITE);
        root.addView(webView, new FrameLayout.LayoutParams(-1, -1));
        setContentView(root);
        if (savedInstanceState != null) webView.restoreState(savedInstanceState);
        else webView.loadUrl(GAME_URL);
    }
    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (webView != null) webView.saveState(outState);
    }
    @Override public void onBackPressed() {
        if (webView != null && webView.canGoBack()) webView.goBack();
        else super.onBackPressed();
    }
    @Override protected void onDestroy() {
        if (webView != null) { webView.destroy(); webView = null; }
        super.onDestroy();
    }
}
