package tw.brad.apps.brad23;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ScanActivity extends AppCompatActivity
        implements ZBarScannerView.ResultHandler{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
