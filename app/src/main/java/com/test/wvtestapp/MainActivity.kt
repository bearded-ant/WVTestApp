package com.test.wvtestapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.mainWebView)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true

        // Загрузка веб-страницы
        webView.loadUrl("https://google.com")
    }

    override fun onBackPressed() {
        // Проверка, есть ли история переходов назад в WebView
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}