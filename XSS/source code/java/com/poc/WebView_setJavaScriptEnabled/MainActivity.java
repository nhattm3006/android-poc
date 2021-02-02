package com.poc.WebView_setJavaScriptEnabled;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edittext);
        button = findViewById(R.id.button);

        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);

        /*
          By default, WebView does not implement JavaScript alert dialogs
          => To trigger XSS alert, needs:
             - setJavaScriptEnabled(true)
             - set a WebChromeClient to handle requests for alert dialogs

          URL test 1: javascript:alert(1)
          URL test 2: javascript:document.write("JavaScript")
        */
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editText.getText().toString().trim();
                webView.loadUrl(url);
            }
        });
    }
}
