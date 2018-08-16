package com.example.souravmunjal.qrbarcodescanner;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity {
    ZXingScannerView zXingScannerView;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if( checkSelfPermission(Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
        }
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        zXingScannerView.stopCamera();
//    }

    public void scan(View view)
   {
        zXingScannerView= new ZXingScannerView(this);
        zXingScannerView.setResultHandler(new zXingScannerResultHandler());
        setContentView(zXingScannerView);
        zXingScannerView.startCamera();
   }
   class zXingScannerResultHandler implements ZXingScannerView.ResultHandler
   {

       @Override
       public void handleResult(Result result) {
        String resultCode  = result.getText();
           Toast.makeText(MainActivity.this,resultCode, Toast.LENGTH_SHORT).show();
           setContentView(R.layout.activity_main);
           zXingScannerView.stopCamera();
       }
   }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode)
        {
            case 0 :
            {

                if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "Permission not Granted", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

        }
    }

}
