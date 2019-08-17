package tw.brad.apps.brad23;
//目的掃條碼
//新增assets=>裡面寫html
//android scan barcode zlib github https://github.com/dm77/barcodescanner 引入第三方api
//權限apihttps://developer.android.com/training/permissions/requesting
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
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
                Manifest.permission.CAMERA) //相機權限
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},//相機權限
                    123);
        }
        //webview物件實體化
        webView = findViewById(R.id.webview);
        initWebView();
    }

    //初始化webView方法
    private void initWebView(){
        WebSettings settings = webView.getSettings(); //叫初設定物件
        settings.setJavaScriptEnabled(true); //式定js可以顯示

        WebViewClient client = new WebViewClient(); //webview物件實體
        webView.setWebViewClient(client); //把物件實體連接到webview

        webView.addJavascriptInterface(new MyBradJS(), "brad");//讓java物件給js方法認識

        webView.loadUrl("file:///android_asset/brad.html");

    }

    public class MyBradJS {
        @JavascriptInterface
        public void callFromJS(){
            gotoScan();
        }
    }

    private void gotoScan(){
        Intent intent = new Intent(this, ScanActivity.class); //從這裡到Scana頁面
        //startActivity(intent);
        startActivityForResult(intent, 123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            String code = data.getStringExtra("code"); //得到qrcode的資料
            Log.v("brad", "result => " + code);
            webView.loadUrl("javascript:showCode('"+code+"')"); //連接JS網頁方法
        }
    }
}