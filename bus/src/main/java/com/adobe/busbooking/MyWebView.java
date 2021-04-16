package com.adobe.busbooking;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import com.adobe.marketing.mobile.AdobeCallback;
import com.adobe.marketing.mobile.Identity;
import com.adobe.marketing.mobile.VisitorID;

public class MyWebView extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Identity.appendVisitorInfoForURL("file:///android_asset/Webview.html", new AdobeCallback<String>() {
            @Override
            public void call(String urlWithAdobeVisitorInfo) {
                //handle the new URL here
                //For example, open the URL on the device browser
                //
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(urlWithAdobeVisitorInfo));
                startActivity(i);
            }
        });

        webview = (WebView) findViewById(R.id.webview);
        webview.loadUrl("file:///android_asset/Webview.html");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}