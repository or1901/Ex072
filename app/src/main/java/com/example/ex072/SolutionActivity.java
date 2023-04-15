package com.example.ex072;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SolutionActivity extends AppCompatActivity {
    WebView wV;
    Intent gi;
    double a, b, c;
    double dis, x1, x2;
    String BASE_URL = "https://www.mathpapa.com/quadratic-formula/?q=";
    String expression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution);

        wV = findViewById(R.id.wV);
        wV.getSettings().setJavaScriptEnabled(true);
        wV.setWebViewClient(new MyWebViewClient());

        gi = getIntent();
        a = gi.getDoubleExtra("a", 0);
        b = gi.getDoubleExtra("b", 0);
        c = gi.getDoubleExtra("c", 0);
        expression = "" + a + "x^2+" + b + "x+" + c + "%3D0";

        wV.loadUrl(BASE_URL + expression);
    }

    public void goBack(View view) {
        x1 = 0;
        x2 = 0;
        dis = b * b - (4 * a * c);

        if(dis < 0) {
            gi.putExtra("solutions", 0);
        }
        else if(dis == 0){
            gi.putExtra("solutions", 1);

            x1 = (-b + Math.sqrt(dis)) / (2 * a);
        }
        else{
            gi.putExtra("solutions", 2);

            x1 = (-b + Math.sqrt(dis)) / (2 * a);
            x2 = (-b - Math.sqrt(dis)) / (2 * a);
        }

        gi.putExtra("x1", x1);
        gi.putExtra("x2", x2);
        setResult(RESULT_OK, gi);
        finish();
    }

    private class MyWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}