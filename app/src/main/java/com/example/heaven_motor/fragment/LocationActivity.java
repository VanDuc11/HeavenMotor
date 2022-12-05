package com.example.heaven_motor.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.heaven_motor.R;

public class LocationActivity extends AppCompatActivity {
    WebView webView;
    String viTri = "https://www.google.com/maps/dir/20.9238282,105.7850022/Tr%C6%B0%E1%BB%9Dng+Cao+%C4%91%E1%BA%B3ng+FPT+Polytechnic,+T%C3%B2a+nh%C3%A0+FPT+Polytechnic,+P.+Tr%E1%BB%8Bnh+V%C4%83n+B%C3%B4,+Xu%C3%A2n+Ph%C6%B0%C6%A1ng,+Nam+T%E1%BB%AB+Li%C3%AAm,+H%C3%A0+N%E1%BB%99i,+Vi%E1%BB%87t+Nam/@20.9773914,105.7327754,12.74z/data=!4m16!1m6!3m5!1s0x313454b991d80fd5:0x53cefc99d6b0bf6f!2zVHLGsOG7nW5nIENhbyDEkeG6s25nIEZQVCBQb2x5dGVjaG5pYw!8m2!3d21.0381278!4d105.7467871!4m8!1m1!4e1!1m5!1m1!1s0x313454b991d80fd5:0x53cefc99d6b0bf6f!2m2!1d105.7467871!2d21.0381278?hl=vi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        webView = findViewById(R.id.web);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        webView.loadUrl(viTri);



    }
}