package tw.brad.apps.brad23;
//目的掃條碼
//新增assets=>裡面寫html
//android scan barcode zlib github https://github.com/dm77/barcodescanner 引入第三方api
//權限apihttps://developer.android.com/training/permissions/requesting
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //權限設置
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

        webView = findViewById(R.id.webview);
        initWebView();
    }

    private  void initWebView(){
        //設定讓js語法可以看到
        WebSettings settings =webView.getSettings();
        settings.setJavaScriptEnabled(true);

        //連接webview
        WebViewClient client = new WebViewClient();
        webView.setWebViewClient(client);

        webView.addJavascriptInterface(new MyBradJS(),"brad");//介紹這個方法讓java物件認識到html

        webView.loadUrl("file:///android_asset/brad.html");//連接到指定的html
    }

    //寫一個讓brad.html認識的物件
    public class  MyBradJS{
        @JavascriptInterface
        public  void callFromjs(){
            gotoScan();
        }
    }

    private  void gotoScan(){
        Intent
                //少一段

    }
}
