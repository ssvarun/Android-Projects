package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

class detailedactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailedactivity)
        val url = intent.getStringExtra("url")
        val progressBar=findViewById<ProgressBar>(R.id.progressBar)
        if(url!=null)
        {
            val webView=findViewById<WebView>(R.id.webview)
            webView.settings.javaScriptEnabled=true
            webView.webViewClient= object : WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressBar.visibility= View.GONE
                    webView.visibility=View.VISIBLE
                }
            }
            webView.loadUrl(url)
        }
    }
}