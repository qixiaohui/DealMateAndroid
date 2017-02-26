package com.dealmate.xiaohui.dealmate.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dealmate.xiaohui.dealmate.R;
import com.dealmate.xiaohui.dealmate.view.BaseWebCLient;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

/**
 * Created by xiaohui on 1/29/2017.
 */

public class DealWebViewActivity extends ActivityBase {
    private WebView webView;
    private CircularProgressView circularProgressView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_webview);
        initView();
        actionBar();
    }

    @Override
    protected void initView() {
        super.initView();
        webView = (WebView) findViewById(R.id.deal_webview);
        circularProgressView = (CircularProgressView) findViewById(R.id.progress_view);
        Intent intent = getIntent();
        String url = intent.getStringExtra("KEY");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        if (Build.VERSION.SDK_INT >= 21) {
            webView.getSettings().setMixedContentMode( WebSettings.MIXED_CONTENT_ALWAYS_ALLOW );
        }
        webView.setWebViewClient(new BaseWebCLient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                circularProgressView.setVisibility(View.GONE);
                view.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void actionBar() {
        toolbar.setNavigationIcon(R.drawable.back);
        super.actionBar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
