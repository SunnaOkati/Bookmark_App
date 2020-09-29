package com.example.listofurls;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebBackForwardList;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Web extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Intent intent = getIntent();
        String URL = intent.getStringExtra("URL");

        if(!URL.isEmpty()){
            WebView view = (WebView) findViewById(R.id.webView);
            view.getSettings().setJavaScriptEnabled(true);

            //Opens URL in webview rather than browser
            //source: https://stackoverflow.com/questions/7746409/android-webview-launches-browser-when-calling-loadurl
            view.setWebViewClient(new WebViewClient());

            view.loadUrl(URL);
        }
        else
            Toast.makeText(getApplicationContext(),"URL is invalid",Toast.LENGTH_SHORT).show();
    }
}