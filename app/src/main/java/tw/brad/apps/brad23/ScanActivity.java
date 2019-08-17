package tw.brad.apps.brad23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class ScanActivity extends AppCompatActivity
        implements ZBarScannerView.ResultHandler{//時做對方寫好的介面
    private ZBarScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        setContentView(mScannerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera(); //開相機鏡頭
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera(); //關閉相機鏡頭
    }

    @Override
    public void handleResult(Result rawResult) {
        String content = rawResult.getContents();
        Log.v("brad", content); // Prints scan results
        Log.v("brad", rawResult.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)

        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);

        //如果內容有近來做這些事
        if (content != null){
            Intent intent = new Intent();
            intent.putExtra("code", content);
            setResult(RESULT_OK, intent);
            finish();
        }

    }
}